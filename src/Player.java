import java.awt.Point;


public class Player {
	String name = "Zombie Smasher";
	int rechargeTime = 750;
	
	private Integer lastSmashTime;
	private Point position;
	
	public Player() {
		this.setPosition(new Point(0, 0));
	}

	public boolean smashAt(int currentTime) {
		boolean canSmash = false;		
		
		if(this.getLastSmashTime() == null) {
			canSmash = currentTime >= rechargeTime;
		} else if( currentTime - this.getLastSmashTime() > rechargeTime) {
			canSmash = true;
		}
		
		if(canSmash) {
			this.setLastSmashTime(currentTime);
		}

		return canSmash;
	}
	
	public int getHandicap(int currentTime) {
		if(this.getLastSmashTime() == null) {
			return 0;
		}
		
			
		if(currentTime - this.getLastSmashTime() >= rechargeTime) {
			 return 0;
		}
		
		return Math.abs(currentTime - this.getLastSmashTime());
		
		
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
