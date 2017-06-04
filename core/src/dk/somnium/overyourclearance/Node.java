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

import com.badlogic.gdx.utils.Array;

/**
 * This class wraps information about a specific wold node, i.e. the description of the node,
 * and the possible choices the player may take from here.
 */
public class Node {
	public static int nodeNo = 0;
	
	public String description;
	public Array<Choice> choices;
	
	public Node()
	{
		this.choices = new Array<Choice>();
	}
	
	public static Node n(String description, Choice...choices)
	{
		Node node = new Node();
		node.description = description;
		for (Choice c : choices)
		{
			node.choices.add(c);
		}
		return node;
	}
}
