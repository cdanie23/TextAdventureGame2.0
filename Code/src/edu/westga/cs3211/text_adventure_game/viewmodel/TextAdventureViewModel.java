package edu.westga.cs3211.text_adventure_game.viewmodel;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.ActionableItem;
import edu.westga.cs3211.text_adventure_game.model.DropItem;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Move;
import edu.westga.cs3211.text_adventure_game.model.UseItem;
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
	
	private ListProperty<Item> itemsListProperty;
	private ObjectProperty<Item> selectedItemProperty;

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
		
		this.itemsListProperty = new SimpleListProperty<Item>();
		this.selectedItemProperty = new SimpleObjectProperty<Item>();
		
		this.itemsListProperty.setValue(FXCollections.observableList(this.gameManager.getPlayer().getInventory()));
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
				.setValue(FXCollections.observableList(this.gameManager.getActions()));
		this.itemsListProperty.setValue(FXCollections.observableList(this.gameManager.getPlayer().getInventory()));
		
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
		if (selectedAction instanceof Move) {
			Move moveAction = (Move) selectedAction;
			this.gameManager.updateLocation(moveAction);
		}
		if (selectedAction instanceof ActionableItem) {
			ActionableItem itemAction = (ActionableItem) selectedAction;
			this.gameManager.usePlayerActionableItem(itemAction);
			this.removeOldUseActions();
				
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

	/**
	 * Gets the items list property
	 * @return the itemsListProperty
	 */
	public ListProperty<Item> getItemsListProperty() {
		return this.itemsListProperty;
	}

	/**
	 * Gets the selected item property
	 * @return the selectedItemProperty
	 */
	public ObjectProperty<Item> getSelectedItemProperty() {
		return this.selectedItemProperty;
	}
	
	/**
	 * Adds the selected items use actions to the available actions
	 * and removes the older actions from the previous selection
	 * @param item the item to add the actions for 
	 */
	public void addItemUseActions(Item item) {
		this.removeOldUseActions();
		this.gameManager.getActions().add(new UseItem(item));
		this.gameManager.getActions().add(new DropItem(item));
		this.update();
	}
	
	private void removeOldUseActions() {
		List<Action> flaggedActions = new ArrayList<Action>();
		for (Action action : this.gameManager.getActions()) {
			if ((action instanceof UseItem || action instanceof DropItem)) {
				flaggedActions.add(action);
			}
		}
		for (Action action : flaggedActions) {
			this.gameManager.getActions().remove(action);
		}
		this.update();
		
	}
}
