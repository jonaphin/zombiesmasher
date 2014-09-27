import java.awt.Point;
import java.awt.geom.Point2D;


public class Zombie implements Comparable<Object> {
	private Point position;
	private int animatesAt;
	private boolean isAlive;
	private int diesAt;
	private int LONGEVITY = 1000;
	
	// If Smashed or Expired
	boolean isDead = false;
	
	public Zombie(Point position, int animatesAt) {
		this.setPosition(position);
		this.setIsAlive(false);
		this.setAnimatesAt(animatesAt);
		this.setDeathTime();
	}
	
	
	public static Zombie FactoryFromInput(String inputString) {
		String[] parts = inputString.split(" ");
		Point position = new Point(Integer.parseInt(parts[0]) * 100, Integer.parseInt(parts[1]) * 100);
		int animatesAt = Integer.parseInt(parts[2]);
		
		return new Zombie(position, animatesAt);
	}
	
	public int distanceFrom(Point p) {
		return (int) this.getPosition().distance((Point2D) p);
	}
	
	public boolean canBeReachedAndSmashedFromPoint(Point p, int nextFullChargedAt) {
		int distance = this.distanceFrom(p);
		int trueDistance = nextFullChargedAt > distance ? nextFullChargedAt : distance;
		
		return (this.getAnimatesAt() <= trueDistance && this.getDiesAt() >= trueDistance);
	}
	

	@Override
	public int compareTo(Object z) {
		return (this.getAnimatesAt() - ((Zombie) z).getAnimatesAt());
	}

	
	// Getters / Setters

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public boolean isAlive(int currentTime) {
		if(isDead) {
			return false;
		}
		
		return isAlive;
	}
	
	private void setDeathTime() {
		this.setDiesAt(this.getAnimatesAt() + this.LONGEVITY);
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getAnimatesAt() {
		return animatesAt;
	}

	public void setAnimatesAt(int animatesAt) {
		this.animatesAt = animatesAt;
	}

	public int getDiesAt() {
		return diesAt;
	}

	public void setDiesAt(int diesAt) {
		this.diesAt = diesAt;
	}

}
