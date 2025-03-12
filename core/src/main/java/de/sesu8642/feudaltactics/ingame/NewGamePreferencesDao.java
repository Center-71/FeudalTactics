// SPDX-License-Identifier: GPL-3.0-or-later

package de.sesu8642.feudaltactics.ingame;

import com.badlogic.gdx.Preferences;
import de.sesu8642.feudaltactics.ingame.NewGamePreferences.Densities;
import de.sesu8642.feudaltactics.ingame.NewGamePreferences.MapSizes;
import de.sesu8642.feudaltactics.ingame.dagger.NewGamePrefsPrefStore;
import de.sesu8642.feudaltactics.lib.ingame.botai.Intelligence;
import de.sesu8642.feudaltactics.renderer.MapRenderer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Data access object for the new game preferences.
 */
@Singleton
public class NewGamePreferencesDao {

    public static final String NEW_GAME_PREFERENCES_NAME = "newGamePreferences";

    private static final String NEW_GAME_PREFERENCES_DENSITY_NAME = "density";
    private static final String NEW_GAME_PREFERENCES_MAP_SIZE_NAME = "mapSize";
    private static final String NEW_GAME_PREFERENCES_BOT_INTELLIGENCE_NAME = "botIntelligence";
    private static final String NEW_GAME_PREFERENCES_STARTING_POSITION_NAME = "startingPosition";
    private static final String NEW_GAME_PREFERENCES_NUMBER_OF_PLAYERS_NAME = "numberOfPlayers";

    private final Preferences prefStore;

    @Inject
    public NewGamePreferencesDao(@NewGamePrefsPrefStore Preferences newGamePrefs) {
        this.prefStore = newGamePrefs;
    }

    /**
     * Saves the preferences the users configured last when starting a new game.
     *
     * @param prefs preferences to save
     */
    public void saveNewGamePreferences(NewGamePreferences prefs) {
        prefStore.putInteger(NEW_GAME_PREFERENCES_BOT_INTELLIGENCE_NAME, prefs.getBotIntelligence().ordinal());
        prefStore.putInteger(NEW_GAME_PREFERENCES_MAP_SIZE_NAME, prefs.getMapSize().ordinal());
        prefStore.putInteger(NEW_GAME_PREFERENCES_DENSITY_NAME, prefs.getDensity().ordinal());
        prefStore.putInteger(NEW_GAME_PREFERENCES_STARTING_POSITION_NAME, prefs.getStartingPosition());
        prefStore.putInteger(NEW_GAME_PREFERENCES_NUMBER_OF_PLAYERS_NAME, prefs.getNumberOfPlayers());
        prefStore.flush();
    }

    /**
     * Loads the preferences the users configured last when starting a new game.
     *
     * @return preferences to load
     */
    public NewGamePreferences getNewGamePreferences() {
        Intelligence botIntelligence = Intelligence.values()[prefStore
                .getInteger(NEW_GAME_PREFERENCES_BOT_INTELLIGENCE_NAME, 0)];
        MapSizes mapSize = MapSizes.values()[prefStore.getInteger(NEW_GAME_PREFERENCES_MAP_SIZE_NAME, 0)];
        Densities density = Densities.values()[prefStore.getInteger(NEW_GAME_PREFERENCES_DENSITY_NAME, 0)];
        int startingPosition = prefStore.getInteger(NEW_GAME_PREFERENCES_STARTING_POSITION_NAME, 0);
        int numberOfPlayers = prefStore.getInteger(NEW_GAME_PREFERENCES_NUMBER_OF_PLAYERS_NAME, MapRenderer.PLAYER_COLOR_PALETTE.size());
        return new NewGamePreferences(botIntelligence, mapSize, density, startingPosition, numberOfPlayers);
    }

}
