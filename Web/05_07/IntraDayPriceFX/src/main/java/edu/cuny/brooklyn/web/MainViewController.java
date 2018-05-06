package edu.cuny.brooklyn.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.web.price.IntradaySharePrice;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class MainViewController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);

	@FXML
	private TextField ticketSymbolField;

	@FXML
	private ComboBox<Integer> intervalComboBox;

	@FXML
	private LineChart<Number, Double> priceChart;

	@FXML
	private Button drawPriceButton;
	
	private IntradaySharePriceService sharePriceService;
	
	private LocalDateTime startDateTime;
	
	public void initialize() {
		drawPriceButton.setOnAction(e -> updatePriceChart());
		intervalComboBox.getItems().addAll(1, 5, 15, 30, 60);
		intervalComboBox.setCellFactory(listView -> new ListCell<Integer>() {

			@Override
			protected void updateItem(Integer minutes, boolean empty) {
				super.updateItem(minutes, empty);

				if (minutes == null || empty) {
					setText(null);
				} else {
					setText(Integer.toString(minutes) + (minutes == 1 ? " minute" : " minutes"));
				}
			}
		});
		intervalComboBox.setButtonCell(intervalComboBox.getCellFactory().call(null));
		intervalComboBox.getSelectionModel().selectFirst();
	}

	public void setSecuritySharePriceShare(IntradaySharePriceService service) {
		if (service == null) {
			throw new IllegalArgumentException("IntradaySharePriceService object must not be null.");
		}
		sharePriceService = service;
	}
	
	private void chartSharePrice(IntradaySharePrice intradaySharePrice) {
		LOGGER.info("Charting the share price");
		
		final  XYChart.Series<Number, Double> series = new XYChart.Series<Number, Double>();
		priceChart.getData().clear();
		startDateTime = null;
		intradaySharePrice.forEach((localDateTime, sharePrice) -> {
		
			int minutes = 0;
			if (startDateTime == null) {
				startDateTime = localDateTime;
			} 
			minutes =(int)ChronoUnit.MINUTES.between(startDateTime, localDateTime);
			
			double price = sharePrice.getClose();
			LOGGER.debug(startDateTime + "," + localDateTime.toString() + "," + minutes + "," + price);
			series.getData().add(new XYChart.Data<Number, Double>(minutes, price));
		});
		priceChart.getData().add(series);
		priceChart.getXAxis().setLabel("Time");
		priceChart.getYAxis().setLabel("Price ($)");
		((ValueAxis<Number>)priceChart.getXAxis()).setTickLabelFormatter(new StringConverter<Number>() {

			@Override
			public Number fromString(String dateTime) {
				LocalDateTime thisDateTime = LocalDateTime.parse(dateTime);
				int minutes = (int)ChronoUnit.MINUTES.between(startDateTime, thisDateTime);
				return minutes;
			}

			@Override
			public String toString(Number minutes) {
				LocalDateTime dateTime =  ChronoUnit.MINUTES.addTo(startDateTime, minutes.intValue());
				return dateTime.toString();
			}
			
		});
		priceChart.setLegendVisible(false);

		LOGGER.info("Charted the share price");
	}
	
	// this can be slow, how can we make this application more responsive? 
	private void updatePriceChart() {
		String ticket = ticketSymbolField.getText().toUpperCase();
		ticketSymbolField.setText(ticket);
		
		if (ticket.isEmpty()) return;				// should notify
		
		int interval = intervalComboBox.getValue();
		
		try {
			IntradaySharePrice sharePrice = sharePriceService.getSharePrice(ticket, interval);
			chartSharePrice(sharePrice);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
