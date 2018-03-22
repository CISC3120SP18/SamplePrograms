package edu.cuny.brooklyn.design;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


/*
 * TODO: experiment with binding, and understand 
 * 			(a) that a binding is "lazily" evaluated, i.e., if a dependency changes, the result of 
 * 				a binding is not immediately recalculated, but it is marked as invalid (trigger 
 * 				an Invalidation event); 
 * 			(b) and that next time the value of an invalid binding is requested, it is recalculated,
 * 				the binding becomes valid.  
 * 		(1) add an InvalidationListener to the binding "binding" in the addResultField method, and
 * 			in the listener, write one ***single statement*** to log "the binding is invalid" 
 * 			using the Logger object (referenced in	LOGGER). 
 * 
 * 			run the application, enter a number in each of the 5 TextField's without pressing the
 * 			"Compute" button, observe output. Answer the question in a comment,
 * 
 * 				How many times is the invalidation listener triggered when you change the value of
 * 				the binding's dependencies (enter values in the TextFied's) ***before*** pressing the
 * 				"compute" button.
 * 
 *  	(2) Comment out the lines of the code you wrote for the TODO (1) in the above. Immediately
 *  		below the commented lines,  add an InvalidationListener to the binding "binding" in 
 *  		the addResultField method, and in the listener, write ***two statements*** 
 *  			(i) to log "the binding is invalid" using the Logger object (referenced in	LOGGER);
 *  			(ii) and to compute and log the binding's value, and form a log entry like
 *  				
 *  				"the binding's value is now <VALUE OBTAINED USING the binding's get() method>"
 *  	 
 * 			run the application, enter a number in each of the 5 TextField's without pressing the
 * 			"Compute" button, observe output. Answer the question in a comment,
 * 
 * 				How many times is the invalidation event triggered when you change the value of
 * 				the binding's dependencies (enter values in the TextFied's) ***before**** pressing
 * 				the	"compute" button. Explain the experimental results in TODO(1) and TODO(2)
 * 				and why they exhibit different behaviors. 
 * 				
 *		(3) (a) Comment out the lines of the code you wrote for the TODO (2) in the above.
 *			(b) Comment out the lines of the code that add a button and register an EventHandler
 *				for the ActionEvent in the addResultField method. Those lines of code you should
 *				comment out are,
 *					Button computeButton = new Button("Compute");
 *					pane.add(computeButton, 1, rowIndex + 1);
 *					computeButton.setOnAction(e -> {
 *						result.setText(binding.get());
 *					});
 *			(c) Add one or few lines of code to use the binding's InvalidationListener to update
 *				and display the result of the sum in the "result" Label.
 *
 *		(4) Answer the question:
 *			Regarding your solution to the TODO (c), what if we comment out the following line in 
 *			the addInputField method,
 *				input.textProperty().addListener((obsv, oldv, newv) -> {if (resultBinding != null) resultBinding.invalidate();});
 */
public class SumAllEvaluatorView {
	private final static Logger LOGGER = LoggerFactory.getLogger(SumAllEvaluatorView.class);
	private Scene scene;
	private ArrayList<StringProperty> inputList;
	private StringBinding resultBinding;
	
	public SumAllEvaluatorView() {
		inputList = new ArrayList<StringProperty>();
		
		GridPane pane = new GridPane();
		scene = new Scene(pane);

		pane.setHgap(5.);
		pane.setVgap(10.);
		pane.setPadding(new Insets(50., 250., 150., 250.));
		
		addColumns(pane);
		
		for (int i = 0; i < 5; i++) {
			addInputField(pane, i);
		}
		
		addOperatorAndSeparator(pane, 5);
		
		addResultField(pane, 6);
	}


	public void showOn(Stage stage) {
		stage.setTitle("Sum All Fields");
		stage.setScene(scene);
		stage.show();
	}
	
	private void addColumns(GridPane pane) {
		ColumnConstraints column = new ColumnConstraints(50., 50., Double.MAX_VALUE, Priority.NEVER, HPos.RIGHT, true);
		pane.getColumnConstraints().add(column);
		column = new ColumnConstraints(150., 150., Double.MAX_VALUE, Priority.ALWAYS, HPos.RIGHT, true);
		pane.getColumnConstraints().add(column);
	}
	
	private void addInputField(GridPane pane, int rowIndex) {
		TextField input = new TextField();
		input.textProperty().addListener((obsv, oldv, newv) -> {if (resultBinding != null) resultBinding.invalidate();});
		input.setAlignment(Pos.CENTER_RIGHT);
		inputList.add(input.textProperty());
		pane.add(input, 1, rowIndex);
		input.setOnMouseClicked(e -> input.selectAll());
	}
	
	private void addOperatorAndSeparator(GridPane pane, int rowIndex) {
		pane.add(new Label("+"), 0, rowIndex-1);
		pane.add(new Separator(), 0, rowIndex, 2, 1);
	}
	
	
	private void addResultField(GridPane pane, int rowIndex) {
		pane.add(new Label("="), 0, rowIndex);
		Label result = new Label();
		pane.add(result, 1, rowIndex);
		
		resultBinding = new SumAllBinding(inputList);
		
		/* Solution 1: using a button to compute and display the result */
		Button computeButton = new Button("Compute");
		pane.add(computeButton, 1, rowIndex + 1);
		computeButton.setOnAction(e -> {
			result.setText(resultBinding.get());
		});

		/* Solution 2: we may also get rid of the computeButton, bind 
		 * the result's textProperty to the binding
		 *  
		 * However the UI "effect" and the workload imposed to the
		 * application and the platform are different. Compare the
		 * two. */
//		result.textProperty().bind(resultBinding);
	}
	
	private class SumAllBinding extends StringBinding {
		SumAllBinding(final ArrayList<StringProperty> inputList) {
			super.bind(inputList.toArray(new StringProperty[inputList.size()]));
		}

		@Override
		protected String computeValue() {
			LOGGER.debug("computeValue called");
			double sum = 0.;
			for (StringProperty e: inputList) {
				String s = e.get();
				if (!s.isEmpty()) {
					double d = Double.parseDouble(s);
					sum += d;
				}
			}
			return Double.toString(sum);
		}
	}
}
