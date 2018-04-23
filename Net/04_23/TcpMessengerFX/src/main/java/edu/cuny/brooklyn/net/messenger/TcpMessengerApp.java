/*
 * An awkward design without using the observer pattern. We may
 * improve design if we consider the observer pattern. How would
 * you go by it? 
 */
package edu.cuny.brooklyn.net.messenger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.messenger.model.TcpMessengerConfig;
import edu.cuny.brooklyn.net.messenger.controller.TcpMessengerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TcpMessengerApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(TcpMessengerApp.class);
	private final static String FXML_MAIN_SCENE = "view/fxml_tcpmessenger.fxml";
	private final static String APP_TITLE = "Tcp Peer-to-Peer Messenger for CISC 3120";

	private TcpMessengerController tcpMessengerController = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() {
		Parameters appParameters = getParameters();
		String portNumString = appParameters.getNamed().get(TcpMessengerConfig.PORT_KEY);
		try {
			int port = Integer.parseInt(portNumString);
			TcpMessengerConfig.setLitensingPort(port);
			LOGGER.info("Listening port is set to " + port);
		} catch (NumberFormatException e) {
			System.err.println("Usage: TcpMessengerApp --port=port_number");
			Platform.exit();
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LOGGER.info("TcpMessenger started.");
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_MAIN_SCENE));

		Parent root = loader.load();

		tcpMessengerController = loader.getController();
		LOGGER.debug("TcpMessengerController object is at " + tcpMessengerController.toString());

		Scene scene = new Scene(root);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		LOGGER.info("TcpMessengerController UI is shown.");
	}

	@Override
	public void stop() {
		if (tcpMessengerController != null) {
			LOGGER.info("shuting down TcpMessengerController.");
			tcpMessengerController.shutdown();
			tcpMessengerController = null;
		}
		LOGGER.info("TcpMessengerApp is closing.");
	}
}
