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

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * A custom TextButton, which contains the Choice-instance it represents.
 * Makes it easy to change the displayed choice dynamically. 
 */
public class ChoiceButton extends TextButton {
	public ChoiceListener listener;
	public Model model;
	public ChoiceButton (Skin skin, Model model, GameScreen gui)
	{
		super("", skin);
		listener = new ChoiceListener(model, gui);
		addListener(listener);
		setVisible(false);
		this.model = model;
	}
	
	/**
	 * Make the choice-button display a different choice.
	 * @param choice
	 */
	public void newChoice(Choice choice)
	{
		if (choice == null)
		{
			setTouchable(Touchable.disabled);
			setVisible(false);
		} else
		{
			String text = choice.description;
			text = text.replaceAll("<name>", model.name);
			if (text.equals("<wait>"))
			{
				text = Model.generateRandomWaitText();
			}
			setText(text);
			listener.choice = choice;
			setTouchable(Touchable.enabled);
			setVisible(true);
		}
	}
}
