import java.awt.Point;


public class Player {
	String name = "Zombie Smasher";
	int rechargeTime = 750;
	
	private Integer lastSmashTime;
	private Point position;
	
	public Player(String name) {
		this.setName(name);
	}

	public boolean SmashAt(int currentTime) {
		boolean canSmash = false;		
		
		if(this.getLastSmashTime() == null) {
			canSmash = true;
		} else if( currentTime - this.getLastSmashTime() > rechargeTime) {
			canSmash = true;
		}
		
		if(canSmash) {
			this.setLastSmashTime(currentTime);
		}

		return canSmash;
	}
	
	public Point move(int x, int y) {
		this.setPosition(new Point(x, y));
		
		return this.getPosition();
	}
	
	// Getters / Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLastSmashTime() {
		return lastSmashTime;
	}

	public void setLastSmashTime(int lastSmashTime) {
		this.lastSmashTime = lastSmashTime;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	

}
