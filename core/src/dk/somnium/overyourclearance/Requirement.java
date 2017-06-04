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
 * This class wraps information about requirements that needs to be fulfilled in the world state,
 * in order to display a specific choice.
 * A Requirement instance contains the type of state to check, and the magnitude is the value to compare this state against. 
 * 
 * Includes static factory methods, that covers the various usages.
 */
public class Requirement {
	public enum REQ
	{
		PLOT, // Requires that a specific plot flag is set
		NOPLOT, // Requires that a specific plot flag is not set
		MINCREDITS // Requires a minimum of credits.
	}
	
	public REQ requirement;
	public int magnitude;
	
	private Requirement(REQ requirement, int magnitude) // Private - only create instances using the static factory methods.
	{
		this.requirement = requirement;
		this.magnitude = magnitude;
	}
	
	/**
	 * The given plot flag must be set
	 * @param plotPoint
	 * @return
	 */
	public static Requirement p(int plotPoint)
	{
		return new Requirement(REQ.PLOT, plotPoint);
	}
	
	/**
	 * The given plot flag must not be set
	 * @param plotPoint
	 * @return
	 */
	public static Requirement np(int plotPoint)
	{
		return new Requirement(REQ.NOPLOT, plotPoint);
	}
	
	/**
	 * The player must have the given minimum of credits
	 * @param minCredits
	 * @return
	 */
	public static Requirement cr(int minCredits)
	{
		return new Requirement(REQ.MINCREDITS, minCredits);
	}
	
}
