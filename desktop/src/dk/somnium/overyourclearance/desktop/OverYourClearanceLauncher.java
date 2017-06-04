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

package dk.somnium.overyourclearance.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dk.somnium.overyourclearance.GameMain;

public class OverYourClearanceLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = GameMain.GAME_TITLE;
		config.fullscreen = GameMain.FULLSCREEN;
		config.width = GameMain.SCREEN_WIDTH;
		config.height = GameMain.SCREEN_HEIGHT;
		config.resizable = false;

		new LwjglApplication(new GameMain(), config);
	}
}
