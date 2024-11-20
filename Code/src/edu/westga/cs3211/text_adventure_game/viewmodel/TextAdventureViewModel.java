package edu.westga.cs3211.text_adventure_game.viewmodel;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.MoveAction;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * The view model for the text adventure game
 * 
 * @author Colby
 * @version Fall 2024
 */
public class TextAdventureViewModel {

	private GameManager gameManager;

	private StringProperty locationDescriptionProperty;
	private StringProperty actionsDescriptionProperty;
	private StringProperty playerStatusDescriptionProperty;

	private ListProperty<Action> actionsListProperty;
	private ObjectProperty<Action> selectedActionProperty;

	/**
	 * Instantiates an instance of the view model
	 * 
	 * @postcondition this.gameManager != null && this.locationDescription != null
	 *                && this.actionsDescriptionProperty != null &&
	 *                this.playerstatusDescriptionProperty != null &&
	 *                this.actionsListProperty != null &&
	 *                this.selectedActionProperty != null
	 */
	public TextAdventureViewModel() {
		this.gameManager = new GameManager();
		this.locationDescriptionProperty = new SimpleStringProperty();
		this.actionsDescriptionProperty = new SimpleStringProperty();
		this.playerStatusDescriptionProperty = new SimpleStringProperty();

		this.actionsListProperty = new SimpleListProperty<Action>();
		this.selectedActionProperty = new SimpleObjectProperty<Action>();
		this.update();
	}

	/**
	 * Updates all the properties of the view model to the data of the game manager
	 * 
	 * @precondition: none
	 * @postcondition: this.locationDescriptionProperty.getValue ==
	 *                 this.gameManager.getLocationDescription() &&
	 *                 this.actionsDescriptionProperty.getValue ==
	 *                 this.gameManager.getAvailableActionsDescription &&
	 *                 this.playerStatusDescriptionProperty.getValue ==
	 *                 this.gameManager.getPlayerStatus() &&
	 *                 this.actionsListProperty.getValue ==
	 *                 this.gameManager.getCurrLocation().getActions()
	 */
	public void update() {
		this.locationDescriptionProperty.setValue(this.gameManager.getLocationDescription());
		this.actionsDescriptionProperty.setValue(this.gameManager.getAvailableActionsDescription());
		this.playerStatusDescriptionProperty.setValue(this.gameManager.getPlayerStatus());
		this.actionsListProperty
				.setValue(FXCollections.observableList(this.gameManager.getCurrLocation().getActions()));
	}

	/**
	 * Checks if the player has entered the win state
	 * 
	 * @return true or false based on if the player has won the game
	 */
	public Boolean checkIfWinState() {
		return this.gameManager.getPlayerHasWon();
	}

	/**
	 * Checks if the player has entered the lost state
	 * 
	 * @return true or false based on if the player has lost
	 */
	public Boolean checkIfLostState() {
		return this.gameManager.getPlayer().getIsDead();
	}

	/**
	 * Takes the action selected
	 */

	public void takeAction() {
		Action selectedAction = this.selectedActionProperty.getValue();
		if (selectedAction instanceof MoveAction) {
			MoveAction moveAction = (MoveAction) selectedAction;
			this.gameManager.updateLocation(moveAction);
		}
		this.update();

	}

	/**
	 * Gets the locationDescription
	 * 
	 * @return the location description property
	 */
	public StringProperty getLocationDescriptionProperty() {
		return this.locationDescriptionProperty;
	}

	/**
	 * Gets the actions description property
	 * 
	 * @return the actionsDescriptionProperty
	 */
	public StringProperty getActionsDescriptionProperty() {
		return this.actionsDescriptionProperty;
	}

	/**
	 * Gets the player status description property
	 * 
	 * @return the playerStatusDescriptionProperty
	 */
	public StringProperty getPlayerStatusDescriptionProperty() {
		return this.playerStatusDescriptionProperty;
	}

	/**
	 * Gets the action list property
	 * 
	 * @return the actionsListProperty
	 */
	public ListProperty<Action> getActionsListProperty() {
		return this.actionsListProperty;
	}

	/**
	 * Gets the selected action property
	 * 
	 * @return the selectedAction
	 */
	public ObjectProperty<Action> getSelectedActionProperty() {
		return this.selectedActionProperty;
	}
	
	/**
	 * Gets the game manager
	 * @return the game manager
	 */
	public GameManager getGameManager() {
		return this.gameManager;
	}
}
