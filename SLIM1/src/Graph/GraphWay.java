package Graph;
import java.util.LinkedList;

/**
 * @author Sandeep Sasidharan
 *
 */
public class GraphWay {
	// all nodes on this path ( ref0 -> ref1 -> ref2  -> ...)
	private LinkedList<Long> refs;
	private long id;
    private String type;
    private String name;
    private String other_tags;
    private int speedMax;
    private boolean isOneway;

	public GraphWay() {
		this.refs = new LinkedList<Long>();
		this.id = 0;
	    this.speedMax = 0;
	    this.isOneway = false;
	    this.other_tags = null;
	}

	public GraphWay(LinkedList<Long> refs, long id,boolean isOneway,int speedMax) {
		this.refs = refs;
		this.id = id;
		this.speedMax = 0;
	    this.isOneway = false;

	}
	
	public LinkedList<Long> getRefs() {
		return refs;
	}
	
	public long getId() {
		return id;
	}
	
	
	public void setRefs(LinkedList<Long> refs) {
		this.refs = refs;
	}
	
	public void setId(long l) {
		this.id = l;
	}
	
	
	public void addRef(long ref){
		this.refs.add(new Long(ref));
	}
    public void setType(String type){
		this.type = type;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setSpeedMax(int speedMax) {
        this.speedMax = speedMax;
    }
    
    public int getSpeedMax() {
        return speedMax;
    }
    public boolean getOneway() {
        return isOneway;
    }
    public String getType() {
        return type;
    }
    
    public void setOneway(boolean isOneway) {
        this.isOneway = isOneway;
    }
    public boolean isOneway() {
        return isOneway;
    }
    
    public void setOtherTags(String other_tags) {
		this.other_tags = other_tags;
	}

}
