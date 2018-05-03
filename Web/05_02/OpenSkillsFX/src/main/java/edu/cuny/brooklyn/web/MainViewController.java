package edu.cuny.brooklyn.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainViewController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);
	@FXML
	private Button searchButton;

	@FXML
	private TextField keywordField;

	@FXML
	private ListView<JobTitle> jobTitleListView;
	
    @FXML
    private Label statusMsgLabel;

	private JobTitleService jobTitleService;

	public void initialize() {
		searchButton.setOnAction(e -> searchJobTitles());
		keywordField.setOnAction(e -> searchJobTitles());
		// here we demonstrate that we replace the each cell in the
		// list view by a new value. we do this because we often
		// use list view to show a list of objects (other than
		// strings)
		jobTitleListView.setCellFactory(titleView -> new ListCell<JobTitle>() {
			@Override
			public void updateItem(JobTitle item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
				} else {
					setText(item.getJobTitle());
				}
			}
		});
	}

	public void setJobTitleService(JobTitleService service) {
		if (service == null) {
			throw new IllegalArgumentException("JobTitleService object must not be null.");
		}
		jobTitleService = service;
	}

	private void searchJobTitles() {
		String keyword = keywordField.getText();
		if (keyword == null || keyword.isEmpty()) {
			statusMsgLabel.setText("You must enter a key word or a phrase.");
			return;
		}

		try {
			validateJobTitleService();
			
			statusMsgLabel.setText(null);
			jobTitleListView.getItems().clear();

			List<JobTitle> jobTitleList = jobTitleService.getJobTitleList(keyword);
			ObservableList<JobTitle> jobTitles = FXCollections.observableList(jobTitleList);
			jobTitleListView.setItems(jobTitles);
		} catch (IOException e) {
			statusMsgLabel.setText(e.getMessage());
			LOGGER.error(e.getMessage(), e);
		} 
	}

	private void validateJobTitleService() {
		if (jobTitleService == null) {
			throw new IllegalStateException("The JobTitleService object must not be null.");
		}
	}
}
