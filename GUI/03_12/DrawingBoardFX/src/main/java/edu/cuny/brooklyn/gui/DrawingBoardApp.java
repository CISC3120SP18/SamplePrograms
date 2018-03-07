package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DrawingBoardApp extends Application {
	// Don't worry about the style for now, and we should NOT use it in this
	// fashion any way. 
	private final static String CANVAS_HOLDER_STYLE = "-fx-background-color: white";
	private final static double CANVAS_WIDTH = 650.;
	private final static double CANVAS_HEIGHT = 400.;


	private final static double MAIN_SCENE_WIDTH = 900.;
	private final static double MAIN_SCENE_HEIGHT = 500.;
	private final static double BRUSH_THICKNESS_SCENE_WIDTH = 500.;
	private final static double BRUSH_THICKNESS_SCENE_HEIGHT = 300.;

	private final static double BRUSH_THICKNESS_DEMO_LINE_CANVAS_WIDTH = 100.;
	private final static double BRUSH_THICKNESS_DEMO_LINE_CANVAS_HEIGHT = 100.;
	private final static double MIN_LINE_WIDTH = 1.0;
	private final static double MAX_LINE_WIDTH = 100.0;
	private final static double LINE_WIDTH_STEP = 1.0;

	private final static double BRUSH_COLOR_SCENE_WIDTH = 400.;
	private final static double BRUSH_COLOR_SCENE_HEIGHT = 300.;

	private final static double DEFAULT_BRUSH_LINE_WIDTH = 1.0;
	private final static Color DEFAULT_BRUSH_COLOR = Color.RED;

	private final static double BUTTON_SPACING = 20.;
	private final static double PADDING_TOP = 20.;
	private final static double PADDING_RIGHT = 20.;
	private final static double PADDING_BOTTOM = 20.;
	private final static double PADDING_LEFT = 20.;
	private final static Insets VBOX_PADDING = new Insets(PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM, PADDING_LEFT);
	private final static Insets HBOX_PADDING = new Insets(PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM, PADDING_LEFT);

//	private boolean isDrawing;
	private Stage brushThicknessStage;
	private double brushLineWidth;
	private Stage brushColorPickerStage;
	private Color brushColor;

	@Override
	public void init() {
		brushLineWidth = DEFAULT_BRUSH_LINE_WIDTH;
		brushColor = DEFAULT_BRUSH_COLOR;
	}

	@Override
	public void start(Stage primaryStage) {
		buildBrushTicknessStage();
		buildColorPickerStage();

		Scene mainScene = buildMainScene();
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Simple Drawing Board");
		primaryStage.show();

	}

	private Scene buildMainScene() {
		Button btnBrushThickness = new Button("Brush Thickness");
		btnBrushThickness.setMaxWidth(Double.MAX_VALUE);

		Button btnBrushColor = new Button("Brush Color");
		btnBrushColor.setMaxWidth(Double.MAX_VALUE);

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setPadding(VBOX_PADDING);
		vbox.setSpacing(BUTTON_SPACING);
		vbox.getChildren().add(btnBrushThickness);
		vbox.getChildren().add(btnBrushColor);

		Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		btnBrushThickness.setOnAction(e -> {
			brushThicknessStage.showAndWait();
			gc.setLineWidth(brushLineWidth);
		});
		btnBrushColor.setOnAction(e -> {
			brushColorPickerStage.showAndWait();
			gc.setStroke(brushColor);
		});

		gc.setLineWidth(brushLineWidth);
		gc.setStroke(brushColor);

		// Examine: different design: click-and-draw or press-and-hold-and-draw?
		// different events for different effect.
		// 1. click-and-draw
//		 isDrawing = false;
//		canvas.setOnMouseClicked(e -> {
//			if (isDrawing) {
//				gc.closePath();
//				isDrawing = false;
//			} else {
//				double x0 = e.getX();
//				double y0 = e.getY();
//				gc.moveTo(x0, y0);
//				gc.beginPath();
//				isDrawing = true;
//			}
//		});

//		canvas.setOnMouseMoved(e -> {
//			if (isDrawing) {
//				double x1 = e.getX();
//				double y1 = e.getY();
//				gc.lineTo(x1, y1);
//				gc.stroke();
//			}
//		});
		////////////////////////////////////////////////////////////////

		// Examine: different design: click-and-draw or press-and-hold-and-draw?
		// different events for different effect.
		// 2. press-and-hold-and-draw
		canvas.setOnMousePressed(e -> {
			double x0 = e.getX();
			double y0 = e.getY();
			gc.moveTo(x0, y0);
			gc.beginPath();
		});

		canvas.setOnMouseDragged(e -> {
			double x1 = e.getX();
			double y1 = e.getY();
			gc.lineTo(x1, y1);
			gc.stroke();
		});

		canvas.setOnMouseReleased(e -> gc.closePath());
		
		
		////////////////////////////////////////////////////////////////////

		StackPane canvasHolder = new StackPane();
		canvasHolder.setStyle(CANVAS_HOLDER_STYLE);
		canvasHolder.getChildren().add(canvas);

		HBox hbox = new HBox();
		hbox.setPadding(HBOX_PADDING);
		hbox.getChildren().add(canvasHolder);
		hbox.getChildren().add(vbox);

		Scene mainScene = new Scene(hbox, MAIN_SCENE_WIDTH, MAIN_SCENE_HEIGHT);
		return mainScene;
	}

	private Scene buildBrushThicknessScene() {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(HBOX_PADDING);
		hbox.setSpacing(BUTTON_SPACING);
		Canvas canvas = new Canvas(BRUSH_THICKNESS_DEMO_LINE_CANVAS_WIDTH, BRUSH_THICKNESS_DEMO_LINE_CANVAS_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(brushLineWidth);
		gc.setStroke(brushColor);
		gc.beginPath();
		gc.moveTo(0, BRUSH_THICKNESS_DEMO_LINE_CANVAS_HEIGHT / 2.);
		gc.lineTo(BRUSH_THICKNESS_DEMO_LINE_CANVAS_WIDTH, BRUSH_THICKNESS_DEMO_LINE_CANVAS_HEIGHT / 2.);
		gc.stroke();
		gc.closePath();
		hbox.getChildren().add(canvas);

		Spinner<Double> spinner = new Spinner<Double>();
		SpinnerValueFactory<Double> lineWidthValueFactory = new SpinnerValueFactory<Double>() {

			@Override
			public void decrement(int steps) {
				double lw = brushLineWidth;
				lw -= steps * LINE_WIDTH_STEP;
				if (lw >= MIN_LINE_WIDTH) {
					brushLineWidth = lw;
					setValue(brushLineWidth);
					updateTheLine();
				}
			}

			@Override
			public void increment(int steps) {
				double lw = brushLineWidth;
				lw += steps * LINE_WIDTH_STEP;
				if (lw <= MAX_LINE_WIDTH) {
					brushLineWidth = lw;
					setValue(brushLineWidth);
					updateTheLine();
				}
			}

			private void updateTheLine() {
				gc.setLineWidth(brushLineWidth);
				gc.setStroke(brushColor);
				gc.clearRect(0, 0, BRUSH_THICKNESS_DEMO_LINE_CANVAS_WIDTH, BRUSH_THICKNESS_DEMO_LINE_CANVAS_HEIGHT);
				gc.beginPath();
				gc.moveTo(0, BRUSH_THICKNESS_DEMO_LINE_CANVAS_HEIGHT / 2.);
				gc.lineTo(BRUSH_THICKNESS_DEMO_LINE_CANVAS_WIDTH, BRUSH_THICKNESS_DEMO_LINE_CANVAS_HEIGHT / 2.);
				gc.stroke();
				gc.closePath();
			}

		};
		lineWidthValueFactory.setValue(brushLineWidth);
		spinner.setValueFactory(lineWidthValueFactory);

		hbox.getChildren().add(spinner);

		Scene brushThicknessScene = new Scene(hbox, BRUSH_THICKNESS_SCENE_WIDTH, BRUSH_THICKNESS_SCENE_HEIGHT);
		return brushThicknessScene;
	}

	private void buildBrushTicknessStage() {
		Scene brushThicknessScene = buildBrushThicknessScene();
		brushThicknessStage = new Stage();
		brushThicknessStage.setTitle("Select thickness of your paintbrush");
		brushThicknessStage.initModality(Modality.APPLICATION_MODAL);
		brushThicknessStage.setScene(brushThicknessScene);
	}

	private Scene buildBrushColorPickerScene() {

		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setMaxWidth(Double.MAX_VALUE);
		colorPicker.setValue(brushColor);
		colorPicker.setOnAction(e -> brushColor = colorPicker.getValue());

		StackPane holder = new StackPane();
		holder.setAlignment(Pos.TOP_CENTER);
		holder.setPadding(HBOX_PADDING);
		holder.getChildren().add(colorPicker);
		Scene brushColorPickerScene = new Scene(holder, BRUSH_COLOR_SCENE_WIDTH, BRUSH_COLOR_SCENE_HEIGHT);
		return brushColorPickerScene;
	}

	private void buildColorPickerStage() {
		Scene brushColorPickerScene = buildBrushColorPickerScene();
		brushColorPickerStage = new Stage();
		brushColorPickerStage.setTitle("Select the color of your paintbrush");
		brushColorPickerStage.initModality(Modality.APPLICATION_MODAL);
		brushColorPickerStage.setScene(brushColorPickerScene);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
