package tree_animation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LeafApp extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		new GrassGenerator(root, 10).generateGrass();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
