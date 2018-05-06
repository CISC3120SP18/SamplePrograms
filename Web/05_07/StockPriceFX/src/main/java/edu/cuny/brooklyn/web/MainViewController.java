package edu.cuny.brooklyn.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class MainViewController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);

	@FXML
	private DatePicker startDatePicker;

	@FXML
	private TextField ticketSymbolField;

	@FXML
	private DatePicker endDatePicker;

	@FXML
	private LineChart<Number, Double> priceChart;

	@FXML
	private Button drawPriceButton;
	
	private HistoricalSharePriceService sharePriceService;
	
	private LocalDate startDate;
	
	private LocalDate endDate;

	public void initialize() {
		drawPriceButton.setOnAction(e -> updatePriceChart());
		startDatePicker.setOnAction(e -> setStartDate());
		endDatePicker.setOnAction(e -> setEndDate());
	}

	public void setSecuritySharePriceShare(HistoricalSharePriceService service) {
		if (service == null) {
			throw new IllegalArgumentException("SecuritySharePriceService object must not be null.");
		}
		sharePriceService = service;
	}
	
	private void chartSharePrice(SharePrice sharePrice) {
		final  XYChart.Series<Number, Double> series = new XYChart.Series<Number, Double>();
		priceChart.getData().clear();
		sharePrice.forEachClosePrice((date, price) -> {
			LocalDate thisDate = LocalDate.parse(date);
			
			if (thisDate.isBefore(startDate)) return;
			if (thisDate.isAfter(endDate)) return;
			
			long days = (int)ChronoUnit.DAYS.between(startDate, thisDate);
			
			LOGGER.debug(startDate + "," + date + "," + days + "," + price);
			series.getData().add(new XYChart.Data<Number, Double>(days, price));
		});
		priceChart.getData().add(series);
		priceChart.getXAxis().setLabel("Time");
		priceChart.getYAxis().setLabel("Price ($)");
		((ValueAxis<Number>)priceChart.getXAxis()).setTickLabelFormatter(new StringConverter<Number>() {

			@Override
			public Number fromString(String date) {
				LocalDate thisDate = LocalDate.parse(date);
				long days = (int)ChronoUnit.DAYS.between(startDate, thisDate);
				return days;
			}

			@Override
			public String toString(Number days) {
				LocalDate date =  ChronoUnit.DAYS.addTo(startDate, days.longValue());
				return date.toString();

			}
			
		});
		priceChart.setLegendVisible(false);

		LOGGER.info("Charting the share price");
	}
	
	private void setStartDate() {
		startDate = startDatePicker.getValue();
	}
	
	private void setEndDate() {
		endDate = endDatePicker.getValue();
	}
	
	
	// this can be slow, how can we make this application more responsive? 
	private void updatePriceChart() {
		String ticket = ticketSymbolField.getText().toUpperCase();
		ticketSymbolField.setText(ticket);
		
		if (ticket.isEmpty()) return;				// should notify
		
		if (endDate.isBefore(startDate)) return;	// should notify
		
		try {
			SharePrice sharePrice = sharePriceService.getSharePrice(ticket, startDate, endDate);
			chartSharePrice(sharePrice);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
