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

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * A custom ClickListener, which performs the Choice when a click is registered.
 * When necessary, the contained Choice instance is updated by the ChoiceButton using this listener. 
 */
public class ChoiceListener extends ClickListener {
	public Choice choice;
	public Model model;
	public GameScreen gui;
	
	public ChoiceListener(Model model, GameScreen gui)
	{
		this.model = model;
		this.gui = gui;
	}
	
	@Override
	public void clicked(InputEvent event, float x, float y)
	{
		model.excecuteChoice(choice);
		gui.refreshButtons();
	}

}
