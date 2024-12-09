package edu.westga.cs3211.text_adventure_game.view;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.DropItem;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.UseItem;
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
import javafx.scene.control.ListView;
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
    private Label coinsLabel;
	
	@FXML
	private Label itemStatusLabel;

    @FXML
    private TextArea actionsDescriptionTextArea;

    @FXML
    private Label currentLocationNameLabel;

    @FXML
    private Label healthLabel;

    @FXML
    private ListView<Item> inventoryListView;

    @FXML
    private Label locationDescriptionLabel;

    @FXML
    private TextArea locationDescriptionTextArea;

    @FXML
    private Button takeActionButton;

    @FXML
    private Label weightTxtLabel;

    @FXML
    private Label worldNameLabel;
	
	private TextAdventureViewModel viewModel;

	private SimpleObjectProperty<Action> selectedActionProperty;
	private SimpleObjectProperty<Item> selectedItemProperty;
	
	/**
	 * Creates an instance of the main window for the text adventure game
	 * @author Colby
	 * @version Fall 2024
	 */
	public MainWindow() {
		this.viewModel = new TextAdventureViewModel();
		this.selectedActionProperty = new SimpleObjectProperty<Action>();
		this.selectedItemProperty = new SimpleObjectProperty<Item>();
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
		this.inventoryListView.getSelectionModel().clearSelection();
	}
	
	private void bindPropertiesFromViewModel() {
		this.actionsComboBox.itemsProperty().bind(this.viewModel.getActionsListProperty());
		this.actionsDescriptionTextArea.textProperty().bind(this.viewModel.getActionsDescriptionProperty());
		this.locationDescriptionTextArea.textProperty().bind(this.viewModel.getLocationDescriptionProperty());
		this.healthLabel.textProperty().bind(this.viewModel.getPlayerStatusDescriptionProperty());
		this.viewModel.getSelectedActionProperty().bindBidirectional(this.selectedActionProperty);
		this.inventoryListView.itemsProperty().bind(this.viewModel.getItemsListProperty());
		this.itemStatusLabel.textProperty().bind(this.viewModel.getItemsStatusProperty());
		this.coinsLabel.textProperty().bind(this.viewModel.getCoinsProperty());
		this.currentLocationNameLabel.textProperty().bind(this.viewModel.getCurrentLocationNameProperty());
		this.weightTxtLabel.textProperty().bind(this.viewModel.getWeightTextProperty());
	}
	
	private void setupListeners() {
		this.actionsComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.selectedActionProperty.setValue(newValue);
			}
		});
		this.inventoryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
			
				this.selectedItemProperty.setValue(newValue);
				this.viewModel.addItemUseActions(newValue);	
			
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
