import java.util.ArrayList;


public class SmashedZombie {
	public int depth = 1;
	public ArrayList<SmashedZombie> children;
	public SmashedZombie parent;
	public Zombie zombie;
	public int deadAt;
	
	public SmashedZombie(SmashedZombie parent, Zombie zombie) {
		if(parent != null) {
			this.parent = parent;
			this.depth = parent.depth + 1;
		}
		
		this.zombie = zombie;
	}
	
	public Integer maxDepth() {
		if(children == null) {
			return this.depth;
		}
		
		int maxDepth = this.depth;
		
		for(SmashedZombie child : children) {
			int childMaxDepth = child.maxDepth();
			
			if(childMaxDepth > maxDepth) {
				maxDepth = childMaxDepth;
			}
		}
		
		return maxDepth;
	}
	
	public String toString() {
		return this.maxDepth().toString();		
	}

}
