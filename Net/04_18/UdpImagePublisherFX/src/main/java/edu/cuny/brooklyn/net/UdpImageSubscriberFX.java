package edu.cuny.brooklyn.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UdpImageSubscriberFX extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(UdpImageSubscriberFX.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() {
		LOGGER.info("Launching the app ...");		
	}

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(20., 20., 20., 20.));
		vbox.setSpacing(20.);
		vbox.setAlignment(Pos.TOP_CENTER);
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20.);
		TextField groupAddressField = new TextField("225.0.0.1");
		groupAddressField.setPromptText("Enter IP Multicast address valid for this application.");
		TextField publisherPortField = new TextField("54001");
		publisherPortField.setPromptText("Enter a valid port number to subscriber the service.");
		Button subscribeButton = new Button("Subscribe");
		hbox.getChildren().addAll(groupAddressField, publisherPortField, subscribeButton);
		
		ImageView imageView = new ImageView();
		
		Label errorMsgLabel = new Label();
		vbox.getChildren().addAll(hbox, imageView, errorMsgLabel);
		
		Scene scene = new Scene(vbox, 800, 600);
		primaryStage.setTitle("Image Subscriber");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		subscribeButton.setOnAction(e -> subscribeImageService(imageView, errorMsgLabel, subscribeButton,
				groupAddressField, publisherPortField));
	}
	
	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
	
	private void subscribeImageService(ImageView imageView, Label errorMsgLabel, Button subscribeButton,
			TextField groupAddressField, TextField publisherPortField) {
		
		String groupAddress = groupAddressField.getText();
		int servicePort = Integer.parseInt(publisherPortField.getText());
		
		try {
			UdpImageSubscriber subscriber = new UdpImageSubscriber(InetAddress.getByName(groupAddress), servicePort);
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					LOGGER.debug("calling subscriber.subscribe()");
					subscriber.subscribe();
					return null;
				}
			};
			Thread th = new Thread(task);
			th.setDaemon(true);
			th.start();

			imageView.imageProperty().bind(subscriber.imageProperty());
			subscribeButton.setDisable(true);
			groupAddressField.setDisable(true);
			publisherPortField.setDisable(true);
			errorMsgLabel.setText("");
		} catch (UnknownHostException e) {
			LOGGER.error("Uknown host: " + e.getMessage(), e);
			errorMsgLabel.setText(groupAddress + " is not an acceptable address for this application.");
		}
	}
}