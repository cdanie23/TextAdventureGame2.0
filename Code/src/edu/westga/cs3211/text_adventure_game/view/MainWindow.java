package edu.westga.cs3211.text_adventure_game.view;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.viewmodel.TextAdventureViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @author Colby
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private ComboBox<Action> actionsComboBox;

	@FXML
	private TextArea actionsDescriptionTextArea;

	@FXML
	private AnchorPane actionsLabel;

	@FXML
	private Label locationDescriptionLabel;

	@FXML
	private TextArea locationDescriptionTextArea;

	@FXML
	private Button takeActionButton;
	private ObjectProperty<Action> selectedActionProperty;
	
	private TextAdventureViewModel viewModel;
	
	/**
	 * Creates an instance of the main window for the text adventure game
	 * @author Colby
	 * @version Fall 2024
	 */
	public MainWindow() {
		this.viewModel = new TextAdventureViewModel();
		this.selectedActionProperty = new SimpleObjectProperty<Action>();
	}

	/**
	 * Initializes the GUI components, binding them to the view model properties
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	@FXML
	public void initialize() {
		this.bindPropertiesFromViewModel();
		this.setupListeners();
	}

	@FXML
	void takeActionButtonClick(ActionEvent event) {
		this.viewModel.takeAction();
		this.showWinDialog();
		this.disableButtonsOnGameEnd();
	}
	
	private void bindPropertiesFromViewModel() {
		this.actionsComboBox.itemsProperty().bind(this.viewModel.getActionsListProperty());
		this.actionsDescriptionTextArea.textProperty().bind(this.viewModel.getActionsDescriptionProperty());
		this.locationDescriptionTextArea.textProperty().bind(this.viewModel.getLocationDescriptionProperty());
		//this.playerStatusTextArea.textProperty().bind(this.viewModel.getPlayerStatusDescriptionProperty());
		this.viewModel.getSelectedActionProperty().bindBidirectional(this.selectedActionProperty);
	}
	
	private void setupListeners() {
		this.actionsComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.selectedActionProperty.setValue(newValue);
			}
		});
	}
	
	private void disableButtonsOnGameEnd() {
		if (this.viewModel.checkIfLostState() || this.viewModel.checkIfWinState()) {
			this.takeActionButton.setDisable(true);
		}
	}
	
	private void showWinDialog() {
		if (this.viewModel.checkIfWinState()) {
			var alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Text-Adventure Game");
			alert.setHeaderText("Congratulations");
			alert.setContentText("You have beat my impossible game");
			alert.showAndWait();
		}
	}
}
