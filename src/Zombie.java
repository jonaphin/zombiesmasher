import java.awt.Point;


public class Zombie {
	private Point position;
	private int animateAt;
	private boolean isAlive;
	private int diesAt;
	private int LONGEVITY = 1000;
	
	// If Smashed or Expired
	boolean isDead = false;
	
	public Zombie(Point position, int animatesAt) {
		this.setPosition(position);
		this.setIsAlive(false);
		this.setAnimateAt(animatesAt);
		this.setDeathTime();
	}
	
	
	public static Zombie FactoryFromInput(String inputString) {
		String[] parts = inputString.split(" ");
		Point position = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		int animatesAt = Integer.parseInt(parts[2]);
		
		return new Zombie(position, animatesAt);
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
		this.setDiesAt(this.getAnimateAt() + this.LONGEVITY);
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getAnimateAt() {
		return animateAt;
	}

	public void setAnimateAt(int animateAt) {
		this.animateAt = animateAt;
	}

	public int getDiesAt() {
		return diesAt;
	}

	public void setDiesAt(int diesAt) {
		this.diesAt = diesAt;
	}

}
