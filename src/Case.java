import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Case {
	public ArrayList<Zombie> zombies;
	public int zombiesCount;
	Player player = new Player("Zombie Smasher");
	int curTime = 0;
	
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
	
	public void play() {
		Collections.sort(this.zombies);
		
		ArrayList<ArrayList<Zombie>> killedZombies = new ArrayList<ArrayList<Zombie>>();
		
		killedZombies.addAll(this.smashDownThatPath(this.zombies, player.getPosition(), 0));
		
		System.out.print(killedZombies);
	}
	
	private ArrayList<ArrayList<Zombie>> smashDownThatPath(List<Zombie> remainingZombies, Point p, int currentTime) {
		if(remainingZombies == null || remainingZombies.size() < 1) {
			return null;
		}
		
		ArrayList<ArrayList<Zombie>> killedZombies = new ArrayList<ArrayList<Zombie>>();
		
		for(Zombie zombie : remainingZombies) {
			if(zombie.canBeReachedAndSmashedFromPoint(p, currentTime)) {
				int nextZombieIdx = remainingZombies.indexOf(zombie) + 1;
				int lastZombieIdx = remainingZombies.size() - 1;
				List<Zombie> subRemainingZombies;
			
				if(nextZombieIdx <= lastZombieIdx) {
					subRemainingZombies = remainingZombies.subList(nextZombieIdx, lastZombieIdx);
				} else {
					subRemainingZombies = null;
				}
				
				ArrayList<ArrayList<Zombie>> deeperSmashery = this.smashDownThatPath(subRemainingZombies, zombie.getPosition(), zombie.getAnimatesAt());
				
				if(deeperSmashery != null) {
					killedZombies.addAll( deeperSmashery );
				}				
			}
		}
		
		return killedZombies;
	}

}
