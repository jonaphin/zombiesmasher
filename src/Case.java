import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Case {
	public ArrayList<Zombie> zombies;
	public int zombiesCount;
	public int maxSmashes = 0;
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
			if(zombie.getAnimatesAt() >= currentTime) {
				zombies.add(zombie);
			}
		}
		
		return zombies;
	}
	
	public void play() {
		Collections.sort((ArrayList<Zombie>)this.zombies);
		
		Player player = new Player();
		ArrayList<SmashedZombie> killedZombies = this.smashDownThatPath(null, this.zombies, player.getPosition(), 0 + player.getHandicap(0));
		
		this.maxSmashes = this.getBestSmashCount(killedZombies);		
	}
	
	private int getBestSmashCount(ArrayList<SmashedZombie> smashedZombies) {
		int maxScore = 0;
		
		for(SmashedZombie zombie : smashedZombies) {
			int currentSmashDepth = zombie.maxDepth();
			
			if(currentSmashDepth > maxScore) {
				maxScore = currentSmashDepth;
			}
		}
		
		return maxScore;
	}
	
	private ArrayList<SmashedZombie> smashDownThatPath(SmashedZombie parentSmashedZombie, List<Zombie> remainingZombies, Point p, int currentTime) {
		if(remainingZombies == null || remainingZombies.size() < 1) {
//			System.out.println("no more zombies");
			return null;
		}
		
		if(currentTime < 0)
			currentTime = 0;
		
		ArrayList<SmashedZombie> killedZombies = new ArrayList<SmashedZombie>();
		
		for(Zombie zombie : remainingZombies) {
			if(zombie.canBeReachedAndSmashedFromPoint(p, currentTime)) {				
				List<Zombie> subRemainingZombies;				
				
				Player player = new Player();
				
				if(parentSmashedZombie != null) {
					player.setLastSmashTime(parentSmashedZombie.deadAt);
				}
				
				SmashedZombie smashedZombie = new SmashedZombie(parentSmashedZombie, zombie);
				smashedZombie.deadAt = zombie.getAnimatesAt() + player.getHandicap(zombie.getAnimatesAt());
				
//				System.out.println("Player killed zombie("+smashedZombie.depth+") " + zombie.getPosition().toString() +" at " + smashedZombie.deadAt);
				
				subRemainingZombies = this.getZombiesAtTime(smashedZombie.deadAt);
				
				
				if(subRemainingZombies.contains(zombie)) {
					subRemainingZombies.remove(zombie);
//					System.out.println("Removing Zombie("+smashedZombie.depth+") " + zombie.getPosition().toString());
				} 
				
				SmashedZombie ancestor = parentSmashedZombie;
				
				while(ancestor != null) {
					if(subRemainingZombies.contains(ancestor.zombie)) {
						subRemainingZombies.remove(ancestor.zombie);
//						System.out.println("Removing Parent Zombie("+ancestor.depth+") " + ancestor.zombie.getPosition().toString());
						
						ancestor = ancestor.parent;
					} else {
						ancestor = null;
					}
				}
				
//				System.out.println("Subs" + subRemainingZombies);
				
				smashedZombie.children = this.smashDownThatPath(smashedZombie, subRemainingZombies, zombie.getPosition(), smashedZombie.deadAt);
				
				killedZombies.add(smashedZombie);
			}
		}
		
		return killedZombies;
	}

}
