/**
 *  Copyright 2017 Somnium
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.package dk.somnium.overyourclearance.client;
 */

package dk.somnium.overyourclearance;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Main class of the game - initialize resources and start the game.
 */
public class GameMain extends Game {
	public static final String GAME_TITLE = "Above Your Clearance";
	public static final boolean FULLSCREEN = true;
	public static final int SCREEN_WIDTH = 1024;
	public static final int SCREEN_HEIGHT = 720;
	
	public AssetManager res;
	public TextureAtlas atlas;
	public BitmapFont font;
	public Skin skin;

	public SpriteBatch batch;
	
	public Model model;
	
	public Screen scr_game;
	
	public enum SCREEN
	{
		GAME,
		;
	}

	@Override
	public void create () {
		// Load resources
		res = new AssetManager();
		res.load("atlas.atlas", TextureAtlas.class);
		res.finishLoading();
		
		// Prepare resources
		atlas = res.get("atlas.atlas", TextureAtlas.class);
		font = new BitmapFont(Gdx.files.internal("font.fnt"), atlas.findRegion("font"));
		skin = new Skin(Gdx.files.internal("skin.json"), atlas);
		batch = new SpriteBatch();
		
		// Create world
		model = new Model(this);
		
		// Create UI
		scr_game = new GameScreen(this);
		
		// Start game
		showScreen(SCREEN.GAME);
	}
	
	public void showScreen(SCREEN screen)
	{
		switch (screen)
		{
		case GAME:
			setScreen(scr_game);
			break;
		default:
			break;
		}
	}

	/**
	 * Clean up of resources when the game ends.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		skin.dispose();
		atlas.dispose();
		res.dispose();
		if (scr_game != null)
		{
			scr_game.dispose();
		}
	}
}
