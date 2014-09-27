import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	int testCasesCount;
	ArrayList<Case> cases = new ArrayList<Case>();

	public Game() {
		this.loadScenario();

		for(Case currentCase : cases) {
			currentCase.play();
		}
		
		this.saveCases();
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
		
		if(testCasesCount < 1 || testCasesCount > 100) {
			throw new RuntimeException("The number of cases must be between at least 1 and at most 100");
		}
		
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
	
	private void saveCases() {
		PrintWriter outputFile;
		
		try {
			outputFile = new PrintWriter("output.txt", "UTF-8");
			
			for(Case currentCase : this.cases) {
				Integer caseNumber = this.cases.indexOf(currentCase) + 1;				
				String caseSummary = "Case #" + caseNumber + ": " + currentCase.maxSmashes;
				
				// Output to file
				outputFile.println(caseSummary);
				
				// Output to console
				System.out.println(caseSummary);
			}
			
			outputFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
