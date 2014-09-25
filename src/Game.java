import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	int testCasesCount;
	ArrayList<Case> cases = new ArrayList<Case>();

	public Game() {
		this.loadScenario();
	}
	
	private void loadScenario() {

		Scanner scanner;
		try {
			scanner = new Scanner(new FileInputStream("res/input.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		testCasesCount = Integer.parseInt(scanner.nextLine());
		System.out.println(testCasesCount);
		
		// load data for each case
		for(int i = 0; i < testCasesCount; i++) {			
			// determine how many zombies will exist for this case
			int zombiesCount = Integer.parseInt(scanner.nextLine());
			Case thisCase = new Case(zombiesCount);
			
			// add all zombies to this case
			for(int j = 0; j < zombiesCount; j++) {
				thisCase.zombies.add(Zombie.FactoryFromInput(scanner.nextLine()));
			}
			
			cases.add(thisCase);
			
		}
	}

}
