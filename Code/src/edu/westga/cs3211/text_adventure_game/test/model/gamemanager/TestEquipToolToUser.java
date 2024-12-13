package edu.westga.cs3211.text_adventure_game.test.model.gamemanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.cs3211.text_adventure_game.model.GameManager;
class TestEquipToolToUser {

	@ParameterizedTest
    @ValueSource(ints = {-100, -10, 0, 5, 10, 100})
    void testEquipTool(int damage) {
        GameManager gm = new GameManager();
        gm.getPlayer().setDamage(0); 
        gm.equipToolToUser(damage); 
        assertEquals(damage, gm.getPlayer().getDamage());
    }

}
