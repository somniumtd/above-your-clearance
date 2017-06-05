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

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Main game screen. The UI of the game.
 */
public class GameScreen implements Screen {
	
	GameMain main;
	Model model;
	
	TextureAtlas atlas;
	BitmapFont font;
	NinePatchDrawable uiBackground;
	Skin skin;
	float scale = 1f;

	Stage stage;
	Table mainGuiTable;
	
	Label output, clones, name, credits;
	Array<ChoiceButton> choices;

	public GameScreen(GameMain oycMain) {
		this.main = oycMain;
		atlas = main.atlas;
		font = main.font;
		skin = main.skin;
		this.model = main.model;
		uiBackground = new NinePatchDrawable(atlas.createPatch("uisection"));
	}

	@Override
	public void show() {
		buildLayout();
	}
	
	private void buildLayout()
	{
		if (Gdx.graphics.getWidth()>2*1024) // Scale high-DPI displays
		{
			scale = 2.0f;
		}
		Viewport viewport = new FitViewport(Gdx.graphics.getWidth()/scale, Gdx.graphics.getHeight()/scale);
		stage = new Stage(viewport);
		
		// Create a single top-level table for the UI
		mainGuiTable = new Table();
		mainGuiTable.setFillParent(true);
//		mainGuiTable.debug();
		
		// As the game runs in full-screen mode, add an exit button in the top right corner.
		TextButton btnQuit = new TextButton("X", skin);
		btnQuit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Gdx.app.exit();
			}
		});
		mainGuiTable.add(btnQuit).align(Align.topRight).colspan(3).row();
		if (Gdx.app.getType() == ApplicationType.WebGL) // Hide exit button in HTML-version, as it has no effect there.
		{
			btnQuit.setVisible(false);
		}
		
		// Table containing the UI itself
		Table columnsTable = new Table();
		
		// The section containing the node text, i.e. the description of what happens at the given node.
		output = new Label(model.getCurrentNode().description, skin);
		output.setWrap(true);
		output.setAlignment(Align.left);
		Table textTable = new Table();
		textTable.setBackground(uiBackground);
		textTable.add(output).width((Gdx.graphics.getWidth()/scale)/1.5f).height((Gdx.graphics.getHeight()/scale)/2).row();
		columnsTable.add(textTable).height((Gdx.graphics.getHeight()/scale)/2).row();
		
		// Prepare the info table, i.e. the section displaying the player name, and his current credits and remaining clones.
		clones = new Label("", skin);
		name = new Label("", skin);
		credits = new Label("", skin);

		Table infoTable = new Table().padTop(10.0f).padBottom(10.0f);
		
		infoTable.add(name).align(Align.left).padRight(20.0f);
		infoTable.add(credits).align(Align.left).padRight(20.0f);
		infoTable.add(clones).align(Align.left);
		
		mainGuiTable.add();
		mainGuiTable.add(columnsTable).width((Gdx.graphics.getWidth()/scale)/2);
		mainGuiTable.add().row();
		
		// Prepare the table containing the buttons that the player uses to make his choices.
		Table buttonTable = new Table();
		buttonTable.pad(10.0f);
		choices = new Array<ChoiceButton>();
		for (int i = 0; i<model.maxNumberOfChoices; i++)
		{
			ChoiceButton button = new ChoiceButton(skin, model, this);
			buttonTable.add(button).align(Align.left).padTop(10.0f);
			buttonTable.add().expandX().row();
			choices.add(button);
		}
		columnsTable.add(infoTable).row();
		columnsTable.add(buttonTable).align(Align.left).row();

		mainGuiTable.add().expandX().expandY().colspan(3).row();
		
		mainGuiTable.invalidateHierarchy();
		stage.addActor(mainGuiTable);
		
		Gdx.input.setInputProcessor(stage);
		
		refreshButtons();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	/**
	 * When the node changes, this method is called in order to update the text, resource labels,
	 * and choice buttons to reflect the new node.
	 */
	public void refreshButtons() {
		Node node = model.getCurrentNode();
		String description = model.substituteTokens(node.description);
		output.setText(description);
		for (ChoiceButton button : choices)
		{
			button.newChoice(null); // Reset all choice buttons (making them hidden)
		}
		
		// Display buttons corresponding with choices which are present, and where their requirement is fulfilled,
		int currentChoiceButton = 0;
		for (int i = 0; i<node.choices.size; i++)
		{
			Choice currentChoice = node.choices.get(i); 
			if (model.evaluateRequirement(currentChoice.req))
			{
				choices.get(currentChoiceButton++).newChoice(node.choices.get(i));
			}
		}
		
		// Update the info-labels with the current game world values.
		name.setText("Name: "+model.name);
		clones.setText("Remaining clones: "+model.numberOfClones);
		credits.setText("Remaining credits: "+model.credits);
	}

}
