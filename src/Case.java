import java.util.ArrayList;


public class Case {
	public ArrayList<Zombie> zombies;
	public int zombiesCount;
	
	public Case(int zombiesCount) {
		this.zombiesCount = zombiesCount;
		
		this.zombies = new ArrayList<Zombie>();
	}
	
	public void AddZombie(Zombie freshZombie) {
		this.zombies.add(freshZombie);
		
		if(this.zombies.size() > zombiesCount) {
			throw new RuntimeException("Sacre Bleu! There is one too many zombies!");
		}
	}
	
	public ArrayList<Zombie> getZombiesAtTime(int currentTime) {
		ArrayList<Zombie> zombies = new ArrayList<Zombie>();
		
		for (Zombie zombie : this.zombies) {
			if(zombie.isAlive(currentTime)) {
				zombies.add(zombie);
			}
		}
		
		return zombies;
	}

}
