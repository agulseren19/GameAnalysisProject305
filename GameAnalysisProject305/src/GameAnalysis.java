import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class GameAnalysis {
	HashMap<int[],Boolean> hashMap=new HashMap<int[],Boolean>();
	static int[] availableCells;
	static int[] leftNumbers;
	static int rounds;
	static int[] cells;
	static int[] possiblecells;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//readTest("test1.txt");
		//readTest("test2.txt");
		//readTest("test4.txt");
		//readTest("test5.txt");
		System.out.println(readTest("test3.txt"));

	}
	public static boolean EnolaWins() {
		for(int i=0;i<cells.length;i++) {
			if(Math.abs(cells[i]-cells[i+1])==1) {
				return true;
			}
		}
		return false;
	}
	public static boolean SherlockWins() {
		for(int i=0;i<cells.length;i++) {
			if(cells[i]==0) {
				return false;
			}
		}
		return true;
	}
	public static boolean EnolaOptimalMove() {
		for(int i=0; i<cells.length;i++) {
			if(cells[i]==0&&cells[i+1]!=0&&leftNumbers[cells[i+1]]!=0) { 
				cells[i]=cells[i+1]+1;
				leftNumbers[cells[i+1]]=0;
			}
			else if(cells[i]==0&&cells[i+1]!=0&&leftNumbers[cells[i+1]-2]!=0) {
				cells[i]=cells[i+1]-1;
				leftNumbers[cells[i+1]-2]=0;
			}
			else if(cells[i]==0&&cells[i-1]!=0&&leftNumbers[cells[i-1]]!=0) { 
				cells[i]=cells[i-1]+1;
				leftNumbers[cells[i-1]]=0;
			}
			else if(cells[i]==0&&cells[i-1]!=0&&leftNumbers[cells[i-1]-2]!=0) {
				cells[i]=cells[i-1]-1;
				leftNumbers[cells[i-1]-2]=0;
			}
			else {
				return false; //no optimal move available
			}
		}
		return true;
	}
	public static boolean SherlockOptimalMove(int[] cells) {
		for(int i=0; i<cells.length;i++) {
			if(cells[i]==0&&cells[i+1]!=0&&leftNumbers[cells[i+1]]!=0) { 
				cells[i]=cells[i+1]+1;
				leftNumbers[cells[i+1]]=0;
			}
			else if(cells[i]==0&&cells[i+1]!=0&&leftNumbers[cells[i+1]-2]!=0) {
				cells[i]=cells[i+1]-1;
				leftNumbers[cells[i+1]-2]=0;
			}
			else if(cells[i]==0&&cells[i-1]!=0&&leftNumbers[cells[i-1]]!=0) { 
				cells[i]=cells[i-1]+1;
				leftNumbers[cells[i-1]]=0;
			}
			else if(cells[i]==0&&cells[i-1]!=0&&leftNumbers[cells[i-1]-2]!=0) {
				cells[i]=cells[i-1]-1;
				leftNumbers[cells[i-1]-2]=0;
			}
			else {
				return false;
			}
		}
				return true;
	}
	public static int readTest(String textName) throws IOException {
		//find max in the file so that I know the array size
		int size=0;
		Scanner scannermax=new Scanner(Paths.get(textName));
		while(scannermax.hasNext()) {
			int first=Integer.parseInt(scannermax.next());
			if(first>size) {
				size=first;
			}
		}
		scannermax.close();
		Scanner scanner=new Scanner(Paths.get(textName));
		rounds=Integer.parseInt(scanner.nextLine());
		cells=new int[size];
		possiblecells=new int[size];
		availableCells=new int[size];
		int mistake=0;
		int i=0;
		while(scanner.hasNext()&&i<rounds) {
		int cell=Integer.parseInt(scanner.next());
		int number=Integer.parseInt(scanner.next());
		i++;
		int turn=i%2; //if turn==0, its Enola's turn, if ==1, it is sherlock's turn
		if(i>rounds/3) {
			//no mistake in first round/3 rounds because there will be 2 spaces left adjacently in every case. 
			//At worst case, 2 of 3 adjacent cells will be empty. 
			//As pdf suggests Enola always starts the game with the winning state if she plays optimally
	//	boolean enolaWouldHaveWonOptimal=EnolaOptimal(cells);
	//	boolean sherlockWouldHaveWonOptimal=SherlockOptimal(cells);
		if(turn==0) {
			if(EnolaOptimalMove()) { //could win
				//real move
				cells[cell-1]=number;
				possiblecells[cell-1]=number;
				//if can't win anymore
				if(!EnolaOptimalMove()) {
					mistake++;
				}
				return mistake;
			}
			else {
				//Enola can't win
				return mistake;
			}
		}

	/*	if(enolaWouldHaveWonOptimal&&!EnolaOptimal(cells)) {
			mistake++;
		}
		if(sherlockWouldHaveWonOptimal&&!SherlockOptimal(cells)) {
			mistake++;
		}*/
		}
		else {
			cells[cell-1]=number;
			possiblecells[cell-1]=number;

		}
		}

		scanner.close();
		return 0;
	}
}
