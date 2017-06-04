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

/**
 * This class wraps information about gameworld effects of choices.
 * An Effect instance contains the type of change to be affected, as well as the magnitude of this change. 
 * 
 * Includes static factory methods, that covers the various usages.
 */
public class Effect {
	public enum TYPE
	{
		NAME, // Change the player's name. The magnitude is the name id.
		CLONECHANGE, // Change the number of remaining clones of the player. The magnitude is the amount changed.
		CREDITCHANGE, // Change the number of remaining credits of the player. The magnitude is the amount changed. 
		SETPLOT, // Set a plot flag. The magnitude is the plot flag ID (as an integer).
		REMOVEPLOT, // Unset a plot flag. The magnitude is the plot flag ID (as an integer).
		RESTART, // Trigger a restart of the game, i.e. the Gameworld is reset, and the current node is set to the starting node. The magnitude is not used.
	}
	
	public TYPE type;
	public int magnitude;
	
	private Effect(TYPE type, int magnitude) // Private - only create instances using the static factory methods.
	{
		this.type = type;
		this.magnitude = magnitude;
	}
	
	/**
	 * Set name of player to the specified name id
	 * @param name
	 * @return
	 */
	public static Effect n(int name)
	{
		return new Effect(TYPE.NAME, name);
	}
	
	/**
	 * Set plot flag
	 * @param plotpoint
	 * @return
	 */
	public static Effect pa(int plotpoint)
	{
		return new Effect(TYPE.SETPLOT, plotpoint);
	}

	/**
	 * Remove plot flag
	 * @param plotpoint
	 * @return
	 */
	public static Effect pr(int plotpoint)
	{
		return new Effect(TYPE.REMOVEPLOT, plotpoint);
	}
	
	/**
	 * Change number of clones
	 * @param cloneDiff
	 * @return
	 */
	public static Effect cl(int cloneDiff)
	{
		return new Effect(TYPE.CLONECHANGE, cloneDiff);
	}

	/**
	 * Change number of credits
	 * @param creditDiff
	 * @return
	 */
	public static Effect cr(int creditDiff)
	{
		return new Effect(TYPE.CREDITCHANGE, creditDiff);
	}
	
	/**
	 * Restart the game
	 * @return
	 */
	public static Effect restart()
	{
		return new Effect(TYPE.RESTART, 0);
	}

}

