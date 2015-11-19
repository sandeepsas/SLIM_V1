package Graph;


/******************************************************************************
 *  Compilation:  javac DirectedEdge.java
 *  Execution:    java DirectedEdge
 *  Dependencies: StdOut.java
 *
 *  Immutable weighted directed edge.
 *
 ******************************************************************************/
/**
 *  @author Sandeep Sasidharan
 */

public class DirectedEdge { 
    private final GraphNode startNode;
    private final GraphNode endNode;
    private double length;
    private final int speedMax;
    private boolean isOneway;
    private String type;
    private String name;
    private String other_tags;

    /**
     * Constructor
     */
    public DirectedEdge(GraphNode startNode, GraphNode endNode,
    		double length, int speedMax, boolean isOneway, String type, String name ) {
        
        this.startNode = startNode;
        this.endNode = endNode;
        this.speedMax = speedMax;
        this.isOneway = isOneway;
        this.length = length;
        this.type = type;
        this.name = name;
    }
    
	public DirectedEdge() {
		
        this.startNode = null;
        this.endNode = null;
        this.speedMax = -1;
        this.isOneway = false;
        this.length = 0.00;
        this.type = null;
        this.name = null;
	}
	
    public GraphNode from() {
        return startNode;
    }


    public GraphNode to() {
        return endNode;
    }


    public int speedMax() {
        return speedMax;
    }
    
    public double getLength() {
        return length;
    }
    
    public boolean isOneway() {
        return isOneway;
    }
    
    public void setType(String type){
		this.type = type;
	}
    public String getType(){
		return type;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setOtherTags(String other_tags) {
		this.other_tags = other_tags;
	}
    
    public float getWeight(){
		float time =0;
		time = (float) (this.length/this.speedMax);
    	return (time*60);
	}
    
    public String toString() {
        return startNode.getId()  + "->" + endNode.getId() + " " + speedMax;
    }

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}