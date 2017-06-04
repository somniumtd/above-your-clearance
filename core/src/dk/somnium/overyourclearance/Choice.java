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
 * This class wraps all information related to a specific choice-selection,
 * including the description of the choice, the requirement for the choice being displayed,
 * the effects if choosing the choice, and which node the choice leads to.
 * 
 * Includes static factory methods, that covers the various usages. 
 */
public class Choice {
	public String description;
	public int destination;
	public Requirement req;
	public Effect[] effects;
	
	private Choice() {}; // Only create instances using the static factory methods.
	
	/**
	 * Simple choice, with only a description and a destination
	 * @param description
	 * @param destination
	 * @return
	 */
	public static Choice c(String description, int destination)
	{
		Choice choice = new Choice();
		choice.description = description;
		choice.destination = destination;
		choice.effects = new Effect[0];
		return choice;
	}
	
	/**
	 * Conditional choice, which is only shown when the given requirement is fulfilled.
	 * @param description
	 * @param destination
	 * @param req
	 * @return
	 */
	public static Choice c(String description, int destination, Requirement req)
	{
		Choice choice = new Choice();
		choice.description = description;
		choice.destination = destination;
		choice.req = req;
		choice.effects = new Effect[0];
		return choice;
	}

	/**
	 * Choice with affects the game world.
	 * @param description
	 * @param destination
	 * @param effects
	 * @return
	 */
	public static Choice c(String description, int destination, Effect...effects)
	{
		Choice choice = new Choice();
		choice.description = description;
		choice.destination = destination;
		choice.effects = effects;
		return choice;
	}

	/**
	 * Conditional choice that affects the game world, and which is only shown when the given requirement is fulfilled.
	 * @param description
	 * @param destination
	 * @param req
	 * @param effects
	 * @return
	 */
	public static Choice c(String description, int destination, Requirement req, Effect...effects)
	{
		Choice choice = new Choice();
		choice.description = description;
		choice.destination = destination;
		choice.req = req;
		choice.effects = effects;
		return choice;
	}
}
