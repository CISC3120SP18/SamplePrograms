/**
	 TODO 3: complete the Help|About menu functionality. When a user
	 		 clicks on the Help|ABout menu item, the application should
	 		 display a short description of your choice to describe
	 		 the application. You will complete the functionality.
	 
	 TODO 4: this is a bonus requirement. 
	 TODO 4 (a): save history. 
		 	Many application saves the history of opened files in the File menu.
			See the Eclipse IDE's File menu for an example. 
		 
			You will write the code to save the history of the opened file. This is
			typically to save the path of opened file to a configuration file. 
			Consider the following when you design the application logic for this
			requirement:
				(i) while file format will you use to save the history? 
					The instructor suggests the Java Properties File. 
					See the Oracle's Java tutorial at
					https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
		 		(ii) what do you do if the file does not exist? 
				(iii) how do you record the history? how many history entries do you wish to write?
	TODO 4 (b): load history. 
			This requires you to read the configuration file, and create necessary menu item
			programmatically, and add event handler that opens the file for editing to each 
			menu item. There are design choices, each has its advantage and disadvantage.
			For examples,
				(i). add a number of menu items to the File menu in the FXML file; however,
				  initially make them invisible. Update the menu items when you read the
				  configuration file. 
				(ii). add a group node to the view that represent the menu items of the
				  history. Add menu items to the node when reading the configuration file.
				(iii). add each menu item to the File menu as you read the configuration file. 
			Make your own choice, and write a comment to justify your choice. 
 */
package edu.cuny.brooklyn.io.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.io.model.SimpleHtmlEditor;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainViewController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);

	@FXML
	private MenuBar menuBar;

	@FXML
	private MenuItem closeTheFileMenuItem;

	@FXML
	private MenuItem saveTheFileMenuItem;

	@FXML
	private HTMLEditor htmlEditor;

	private Stage stage;
	private SimpleHtmlEditor editor = new SimpleHtmlEditor();
	private HTMLEditorContentChangeListener contentChangeListener = null;

	public void setStage(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage must not be null.");
		}
		this.stage = stage;
		this.stage.setOnCloseRequest(event -> {
			LOGGER.debug("User clicked the X button on the stage.");
			exitApp(event);
		});
	}

	@FXML
	void initialize() {
		makeContentChangeListener();
		htmlEditor.setDisable(true);
		closeTheFileMenuItem.setDisable(true);
		saveTheFileMenuItem.setDisable(true);
		WebView webview = (WebView) htmlEditor.lookup("WebView");
		GridPane.setHgrow(webview, Priority.ALWAYS);
		GridPane.setVgrow(webview, Priority.ALWAYS);
		LOGGER.debug("Controller MainSceneController initialized.");
	}

	@FXML
	void newFile(ActionEvent event) {
		if (editor.isEditorContentChanged()) {
			UserDecision decision = askUserDecision();
			switch (decision) {
			case CancelPendingAction:
				break;
			case DiscardChange:
				setUIForContentNotChanged();
				createNewFile();
				resetContentChangeListener();
				htmlEditor.setDisable(false);
				LOGGER.debug(String.format("Discared content edited for the new file %s.", editor.getTheFile()));
				break;
			case SaveChange:
				try {
					editor.saveTheFile(htmlEditor.getHtmlText());
					LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile()));
					setUIForContentNotChanged();
					createNewFile();
					resetContentChangeListener();
					htmlEditor.setDisable(false);
					LOGGER.debug(String.format("Created new file %s for editing.", editor.getTheFile()));
				} catch (FileNotFoundException e) {
					LOGGER.error(String.format("Cannot found the file %s while saving the file.", editor.getTheFile()),
							e);
					NotificationHelper.showFileNotFound(editor.getTheFile());
				} catch (IOException e) {
					LOGGER.error(
							String.format("Cannot write to the file %s while saving the file.", editor.getTheFile()),
							e);
					NotificationHelper.showWritingError(editor.getTheFile());
				}
				break;
			default:
				throw new IllegalArgumentException(String.format("User decision's value (%s) is unexpected", decision));
			}
		} else {
			createNewFile();
			if (editor.getTheFile() != null) {
				resetContentChangeListener();
				htmlEditor.setDisable(false);
				LOGGER.debug(String.format("Created new file %s for editing.", editor.getTheFile()));
			}
		}
	}

	@FXML
	void openFile(ActionEvent event) {
		try {
			if (editor.isEditorContentChanged()) {
				UserDecision decision = askUserDecision();
				switch (decision) {
				case CancelPendingAction:
					break;
				case DiscardChange:
					setUIForContentNotChanged();
					openFileFromFileSystem();
					resetContentChangeListener();
					htmlEditor.setDisable(false);
					LOGGER.debug(String.format("Opened file %s for editing.", editor.getTheFile()));
					break;
				case SaveChange:
					editor.saveTheFile(htmlEditor.getHtmlText());
					LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile()));
					setUIForContentNotChanged();
					openFileFromFileSystem();
					resetContentChangeListener();
					htmlEditor.setDisable(false);
					LOGGER.debug(String.format("Opened file %s for editing.", editor.getTheFile()));
					break;
				default:
					throw new IllegalArgumentException(
							String.format("User decision's value (%s) is unexpected", decision));
				}
			} else {
				openFileFromFileSystem();
				if (editor.getTheFile() != null) {
					setUIForContentNotChanged();
					htmlEditor.setDisable(false);
					closeTheFileMenuItem.setDisable(false);
					resetContentChangeListener();
					LOGGER.debug(String.format("Opened file %s for editing.", editor.getTheFile()));
				}
			}
		} catch (FileNotFoundException e) {
			LOGGER.error(String.format("Cannot found the file %s while opening the file.", editor.getTheFile()), e);
			NotificationHelper.showFileNotFound(editor.getTheFile());
		} catch (IOException e) {
			LOGGER.error(String.format("Cannot load the file %s while opening the file.", editor.getTheFile()), e);
			NotificationHelper.showWritingError(editor.getTheFile());
		}
	}

	@FXML
	void saveTheFile(ActionEvent event) {
		if (editor.isEditorContentChanged()) {
			try {
				editor.saveTheFile(htmlEditor.getHtmlText());
				setUIForContentNotChanged();
				closeTheFileMenuItem.setDisable(false);
				resetContentChangeListener();
				LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile()));
			} catch (FileNotFoundException e) {
				LOGGER.error(String.format("Cannot found the file %s while saving the file.", editor.getTheFile()), e);
				NotificationHelper.showFileNotFound(editor.getTheFile());
			} catch (IOException e) {
				LOGGER.error(String.format("Cannot write to the file %s while saving the file.", editor.getTheFile()),
						e);
				NotificationHelper.showWritingError(editor.getTheFile());
			}
		} else {
			closeTheFileMenuItem.setDisable(false);
		}
	}

	@FXML
	void closeTheFile(ActionEvent event) {
		if (editor.isEditorContentChanged()) {
			UserDecision decision = askUserDecision();
			switch (decision) {
			case CancelPendingAction:
				break;
			case DiscardChange:
				htmlEditor.setHtmlText("");
				setUIForContentNotChanged();
				htmlEditor.setDisable(true);
				LOGGER.debug(String.format("Closed editting pane for the file %s.", editor.getTheFile()));
				break;
			case SaveChange:
				try {
					editor.saveTheFile(htmlEditor.getHtmlText());
					LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile()));
					setUIForContentNotChanged();
					htmlEditor.setDisable(true);
					LOGGER.debug(String.format("Closed editting pane for the file %s.", editor.getTheFile()));
				} catch (FileNotFoundException e) {
					LOGGER.error(String.format("Cannot found the file %s while saving the file.", editor.getTheFile()),
							e);
					NotificationHelper.showFileNotFound(editor.getTheFile());
				} catch (IOException e) {
					LOGGER.error(
							String.format("Cannot write to the file %s while saving the file.", editor.getTheFile()),
							e);
					NotificationHelper.showWritingError(editor.getTheFile());
				}
				break;
			default:
				throw new IllegalArgumentException(String.format("User decision's value (%s) is unexpected", decision));
			}
		} else {
			htmlEditor.setHtmlText("");
			setUIForContentNotChanged();
			htmlEditor.setDisable(true);
		}
	}

	@FXML
	void exitApp(ActionEvent event) {
		LOGGER.debug("call exitApp(ActionEvent event).");
		exitApp((Event) event);
	}

	private void createNewFile() {
		validateStage();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Create New HTML File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("HTML Files", "*.htm", "*.html"),
				new ExtensionFilter("All Files", "*.*"));
		
		File theFile = fileChooser.showSaveDialog(stage);
		if (theFile != null) {
			editor.setTheFile(theFile.toPath());
		}
	}

	private void openFileFromFileSystem() throws FileNotFoundException, IOException {
		validateStage();
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open an HTML File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("HTML Files", "*.htm", "*.html"),
				new ExtensionFilter("All Files", "*.*"));
		File theFile = fileChooser.showOpenDialog(stage);
		if (theFile != null) {
			editor.setTheFile(theFile.toPath());
			String htmlText;
			htmlText = editor.readFile();
			htmlEditor.setHtmlText(htmlText);
		}
	}

	private void exitApp(WindowEvent event) {
		LOGGER.debug("call exitApp(WindowEvent event).");
		exitApp((Event) event);
	}

	private void exitApp(Event event) {
		LOGGER.debug("call exitApp(Event event).");
		if (editor.isEditorContentChanged()) {
			UserDecision decision = askUserDecision();
			switch (decision) {
			case CancelPendingAction:
				event.consume();
				break;
			case DiscardChange:
				Platform.exit();
				break;
			case SaveChange:
				try {
					editor.saveTheFile(htmlEditor.getHtmlText());
					LOGGER.debug(String.format("Saved the file %s.", editor.getTheFile()));
					Platform.exit();
				} catch (FileNotFoundException e) {
					LOGGER.error(String.format("Cannot found the file %s while saving the file.", editor.getTheFile()),
							e);
					NotificationHelper.showFileNotFound(editor.getTheFile());
				} catch (IOException e) {
					LOGGER.error(
							String.format("Cannot write to the file %s while saving the file.", editor.getTheFile()),
							e);
					NotificationHelper.showWritingError(editor.getTheFile());
				}
				break;
			default:
				throw new IllegalArgumentException(String.format("User decision's value (%s) is unexpected", decision));
			}

		} else {
			Platform.exit();
		}
	}

	private UserDecision askUserDecision() {
		DecisionWrapper decision = new DecisionWrapper(UserDecision.CancelPendingAction);

		ButtonType saveButton = new ButtonType("Save", ButtonData.YES);
		ButtonType dontSaveButton = new ButtonType("Don't Save", ButtonData.NO);
		Alert alert = new Alert(AlertType.WARNING,
				"Want to save your content to a file?" + "\n\n"
						+ "If you click \"Don't save\", your change will be lost.",
				saveButton, dontSaveButton, ButtonType.CANCEL);
		alert.showAndWait().ifPresent((response) -> {
			if (response.getButtonData() == ButtonData.YES) {
				decision.setValue(UserDecision.SaveChange);
			} else if (response.getButtonData() == ButtonData.NO) {
				decision.setValue(UserDecision.DiscardChange);
			} else {
				decision.setValue(UserDecision.CancelPendingAction);
			}
		});

		return decision.getValue();
	}

	private class DecisionWrapper {
		private UserDecision value;

		public DecisionWrapper(UserDecision decisionValue) {
			value = decisionValue;
		}

		public UserDecision getValue() {
			return value;
		}

		public void setValue(UserDecision value) {
			this.value = value;
		}
	}

	private enum UserDecision {
		SaveChange, DiscardChange, CancelPendingAction
	}

	private void makeContentChangeListener() {
		contentChangeListener = new HTMLEditorContentChangeListener(htmlEditor);
		contentChangeListener.contentChangedProperty().addListener((observedValue, oldValue, newValue) -> {
			editor.setEditorContentChanged(newValue);
			setUIForContentChanged();
		});
	}

	private void setUIForContentNotChanged() {
		editor.setEditorContentChanged(false);
		closeTheFileMenuItem.setDisable(true);
		saveTheFileMenuItem.setDisable(true);
		// call resetContentChangeListener after call this method
	}

	private void setUIForContentChanged() {
		editor.setEditorContentChanged(true);
		closeTheFileMenuItem.setDisable(false);
		saveTheFileMenuItem.setDisable(false);
		// don't call resetContentChangeListener after call this method
	}

	private void resetContentChangeListener() {
		releaseContentChangeListener();
		makeContentChangeListener();
	}

	private void releaseContentChangeListener() {
		contentChangeListener = null;
		editor.setEditorContentChanged(false);
	}
	
	private void validateStage() {
		if (this.stage == null) {
			throw new IllegalStateException("Stage must not be null.");
		}
	}

	/**
	 * Unfortunately, HTMLEditor does not have a convenient EventHandler for us to
	 * determine whether the content is changed. We have to improvise.
	 * 
	 * In Java a Property is a class that one may monitor its changes with a
	 * ChangeListener. There are many kinds of Property classes. Below we use a
	 * BooleanProperty to monitor whether the content of the HTMLEditor is changed
	 * by monitoring keyboard and mouse events.
	 */
	private class HTMLEditorContentChangeListener {
		private final BooleanProperty contentChangedProperty;

		private String prevContent;

		public HTMLEditorContentChangeListener(final HTMLEditor editor) {
			contentChangedProperty = new SimpleBooleanProperty();
			contentChangedProperty.set(false);
			prevContent = null;
			editor.setOnMouseClicked(event -> checkContentEdited(editor));
			editor.addEventFilter(KeyEvent.KEY_TYPED, event -> checkContentEdited(editor));
			/*
			 * You may wonder why we use an EventFilter instead of an EventHandler. We use
			 * EventFilter because EventFilter does not consume the event. Imaging that you
			 * replace the following by a EventHander, e.g., editor.setOnKeyTyped(event ->
			 * checkContentEdited(editor)); you will not be able to edit the content, because
			 * the above code consumes the KEY_TYPED event (the KEY_TYPED is gone).
			 * 
			 * See https://docs.oracle.com/javase/8/javafx/events-tutorial/events.htm for
			 * more information.
			 */
		}

		public BooleanProperty contentChangedProperty() {
			return contentChangedProperty;
		}

		private void checkContentEdited(final HTMLEditor editor) {
			String currentContent = editor.getHtmlText();
			if (contentChangedProperty.get()) {
				// if already changed, do nothing, for efficiency
				return;
			} else if (prevContent != null && prevContent.length() != currentContent.length()
					|| prevContent == null & !currentContent.isEmpty() || !prevContent.equals(currentContent)) {
				// it is a change
				contentChangedProperty.set(true);
			}
			prevContent = currentContent;
		}
	}
	
}
