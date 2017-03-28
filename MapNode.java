/**
 * A class to represent a node in the map
 */
package roadgraph;

import java.util.HashSet;
import java.util.Set;
import java.lang.*;
import java.util.HashMap;

import geography.GeographicPoint;

/**
 * @author UCSD MOOC development team
 * 
 * Class representing a vertex (or node) in our MapGraph
 *
 */
class MapNode implements Comparable {
	/** The list of edges out of this node */
	private HashSet<MapEdge> edges;
		
	/** the latitude and longitude of this node */
	private GeographicPoint location;
	private double distanceFromStart;
	private double distanceToEnd;
		
	/** 
	 * Create a new MapNode at a given Geographic location
	 * @param loc the location of this node
	 */
	MapNode(GeographicPoint loc)
	{
		location = loc;
		edges = new HashSet<MapEdge>();
		distanceFromStart = Double.POSITIVE_INFINITY;
		distanceToEnd = Double.POSITIVE_INFINITY;
	}
		
	/**
	 * Add an edge that is outgoing from this node in the graph
	 * @param edge The edge to be added
	 */
	void addEdge(MapEdge edge)
	{
		edges.add(edge);
	}
	
	/**  
	 * Return the neighbors of this MapNode 
	 * @return a set containing all the neighbors of this node
	 */
	HashMap<MapNode,Double> getNeighbors()
	{
		HashMap<MapNode,Double> neighbors = new HashMap<MapNode,Double>();
		for (MapEdge edge : edges) {
			neighbors.put(edge.getOtherNode(this),edge.getLength());
		}
		return neighbors;
	}
	
	/**
	 * Get the geographic location that this node represents
	 * @return the geographic location of this node
	 */
	GeographicPoint getLocation()
	{
		return location;
	}
	
	/**
	 * return the edges out of this node
	 * @return a set contianing all the edges out of this node.
	 */
	Set<MapEdge> getEdges()
	{
		return edges;
	}
	
	/** Returns whether two nodes are equal.
	 * Nodes are considered equal if their locations are the same, 
	 * even if their street list is different.
	 * @param o the node to compare to
	 * @return true if these nodes are at the same location, false otherwise
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof MapNode) || (o == null)) {
			return false;
		}
		MapNode node = (MapNode)o;
		return node.location.equals(this.location);
	}
	
	/** Because we compare nodes using their location, we also 
	 * may use their location for HashCode.
	 * @return The HashCode for this node, which is the HashCode for the 
	 * underlying point
	 */
	@Override
	public int hashCode()
	{
		return location.hashCode();
	}
	
	/** ToString to print out a MapNode object
	 *  @return the string representation of a MapNode
	 */
	@Override
	public String toString()
	{
		String toReturn = "[NODE at location (" + location + ")";
		toReturn += " intersects streets: ";
		for (MapEdge e: edges) {
			toReturn += e.getRoadName() + ", ";
		}
		toReturn += "]";
		return toReturn;
	}

	// For debugging, output roadNames as a String.
	public String roadNamesAsString()
	{
		String toReturn = "(";
		for (MapEdge e: edges) {
			toReturn += e.getRoadName() + ", ";
		}
		toReturn += ")";
		return toReturn;
	}
	
	public double getDistanceFromStart(){
		return distanceFromStart;
	}
	
	public void setDistanceFromStart(double distance){
		distanceFromStart = distance;
		
	}
	public double getDistanceToEnd(){
		return distanceToEnd;
		
	}
	
	public void setDistanceToEnd(GeographicPoint goal){
		
		distanceToEnd = location.distance(goal);
		
		
	}
	
	
	

	@Override
	public int compareTo(Object o) {
		
		MapNode node = (MapNode) o;
		
		//System.out.println(this);
		//System.out.println(o);
			
				
		if (this.getDistanceFromStart() < node.getDistanceFromStart()){
		
			System.out.println("Current Head: " + this.getDistanceFromStart());
			System.out.println("Current Compare to: " + node.getDistanceFromStart());
			System.out.println("Current Head: " + this.getDistanceToEnd());
			System.out.println("Current Compare to: " + node.getDistanceToEnd());
			
			if (this.getDistanceToEnd() < node.getDistanceToEnd()){
							
				return -1;	
				
			}
			if (this.getDistanceToEnd() > node.getDistanceToEnd()){
				
				
				return 1;
			}
			
			System.out.println("Compare equal to 1");
			
			return 0;
	}
		
		if (this.getDistanceFromStart() > node.getDistanceFromStart()){
			if (this.getDistanceToEnd() < node.getDistanceToEnd())
			{
				
				System.out.println("Compared to less than");
				return -1;	
				
			}
			if (this.getDistanceToEnd() > node.getDistanceToEnd())
{
				
				System.out.println("Compared to greater than");
				return 1;
			}
			System.out.println("Compare equal to 2");
			return 0;
			}
			
		if(this.getDistanceFromStart() == node.getDistanceFromStart()){
			if(this.getDistanceToEnd() < node.getDistanceToEnd())
				return -1;
			if(this.getDistanceToEnd() > node.getDistanceToEnd())
				return 1;
			
			return 0;
			}
		
		
	
		return 0;
	}
	
	

}