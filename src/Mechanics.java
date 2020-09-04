import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class Mechanics {
	
	public static Block[][] old = new Block[4][4];
	public static Block[][] grid = new Block[4][4];
	private static boolean fail;
	// Prints the 4x4 grid
	public static void display() {
		System.out.println();
		System.out.println(Arrays.toString(grid[0]));
		System.out.println(Arrays.toString(grid[1]));
		System.out.println(Arrays.toString(grid[2]));
		System.out.println(Arrays.toString(grid[3]));
	}
	public static void toDo() {
		Mechanics.valueAssigner();
		Mechanics.gameOver();
		GameRunner.frame.repaint();
		Mechanics.display();
		Mechanics.copier();
	}
	public static boolean gameOver() {
		return (fail);
	}
	public static void copier() {
		for(int i = 0; i<grid.length; i++) {
			for(int e = 0; e<grid.length; e++) {
				old[i][e] = grid[i][e];
			}
		}
		System.out.println("OLD");
		System.out.println(Arrays.toString(grid[0]));
		System.out.println(Arrays.toString(grid[1]));
		System.out.println(Arrays.toString(grid[2]));
		System.out.println(Arrays.toString(grid[3]));
	}
	//Resets all block values to 0 and sets one random block's value to 2 or 4
	public static void reset() {
		for(int i = 0; i<grid.length; i++) {
			for(int e = 0; e<grid.length; e++) {
					grid[i][e].setValue(0);	
				}
			}
		fail = false;
		start();
	}
	public static void generateblocks() {
		//Generates 16 new blocks with all having the value of 0
		for(int i = 0; i<grid.length; i++) {
			for(int j = 0; j<grid.length; j++) {
				grid[i][j] = new Block(i, j, 0, false, 0);
				System.out.println(grid[i][j]);
			}
		}
	}
	private static void idAssign() {
		ArrayList<Integer> ID = new ArrayList<Integer>();
		for(int i = 0; i<grid.length; i++) {
			for(int e = 0; e<grid.length; e++) {
				ID.add(grid[i][e].getID());
			}
		}
		for(int l = 0; l<ID.size(); l++) {
			for(int i = 0; i<ID.size(); i++) {
				for(int e = i+1; e<ID.size(); e++) {
					if(ID.get(i)==ID.get(e)) {
						ID.set(e, (int)(Math.random()*24));
					}
				}
			}	
		}
		int count = 0;
		for(int i = 0; i<grid.length; i++) {
			for(int e = 0; e<grid.length; e++) {
				grid[i][e].setID(ID.get(count));
				count += 1;
			}
		}
		System.out.println(ID);
	}
	//Assigns either 2 or 4 to a random block on the grid
	public static void valueAssigner() {
		fail = false;
		int count = 0;
		for(int i = 0; i<grid.length; i++) {
			for(int e = 0; e<grid.length; e++) {
				if(grid[i][e].getValue()!=0) {
					count += 1;
				}
			}
		}
		if(count!=16) {
			int x = (int)(Math.random()*4);
			int y = (int)(Math.random()*4);
			if(grid[x][y].getValue()==0) {
				grid[x][y].setValue(randomizer());
			}
			else {
				valueAssigner();
			}
		} else {
			fail = true;
		}
	}
	
	//Randomizer for specific set of values
	private static int randomizer() {
		int outcome;
		switch((int)(Math.random()*3)) {
			case 1: outcome = 2;
			break;
			case 2: outcome = 4;
			break;
			default: outcome = 2;
		}
		return outcome;
	}
	//Places a randomized block at the begining of the game
	public static void start() {
		valueAssigner();	
		idAssign();
		copier();
	}
	//Checks to see if two block's values are equal to each other
	private static boolean canCombine(Block a, Block b) {
		return a.getValue() == b.getValue();
	}
	
	public static void down() {
		int temp;
		gameOver();
		try {
			  for(int m = 0; m<4; m++) { //Cheap Move to do the operation 3 times
					for(int i = 2; i>=0; i--) {//Starts are row 2 and stops at row 0
						for(int e = 0; e<grid.length; e++) { //starts at column 0 and goes to 3
							if(grid[i][e].getValue()!=0) { //Looks for a block that doesn't have a value of 0
		/* DEBUG
								System.out.println("X of block: "+grid[i][e].getX());
								System.out.println("Value of selected block: "+grid[i][e].getValue());
								System.out.println("Value of Block Below: "+grid[grid[i][e].getX()+1][e].getValue());
								Scanner go = new Scanner(System.in);
								String nothing = go.nextLine();
								*/
								if(i+1<=3&&grid[i+1][e].getValue()==0) {
									//Checks to see if it's still in bounds and the value below is 0
									temp = grid[i+1][e].getID();
									grid[i+1][e].setID(grid[i][e].getID());
									grid[i+1][e].setValue(grid[i][e].getValue());
									//Give the bottom block the same values as the top one
									grid[i][e].setValue(0);
									grid[i][e].setID(temp);
									//empties the top space
								}
								if(i!=3&&grid[i][e].getbool()==false&&canCombine(grid[i][e],grid[i+1][e])) {
									//checks blocks around it and checks if they can be combined
									//by removing the one above and setting the bottom block to double the amount
									grid[i][e].setValue(0);
									grid[i+1][e].setValue(grid[i+1][e].getValue()*2);
									grid[i+1][e].setbool(true);
								}
							}
						}
					}
				}
				//makes sure blocks can't be combined more than once per move
				for(int i = 0; i<grid.length; i++) {
					for(int e = 0; e<grid.length; e++) {
						grid[i][e].setbool(false);
					}
				}
		} catch(Exception e){
		}
	}
	//REFER TO DOWN METHOD ON HOW IT WORKS AND ADJUST BASED ON DIRECTION
	public static void up() {
		int temp;
		gameOver();
		try {
			for(int m = 0; m<4; m++) {
				for(int i = 3; i>=0; i--) {
					for(int e = 0; e<grid.length; e++) {
						if(grid[i][e].getValue()!=0) { 
							if(i-1>=0&&grid[i-1][e].getValue()==0) {
								temp = grid[i-1][e].getID();
								grid[i-1][e].setID(grid[i][e].getID());
								grid[i-1][e].setValue(grid[i][e].getValue());
								grid[i][e].setValue(0);
								grid[i][e].setID(temp);
							}
							if(i!=0&&grid[i][e].getbool()==false&&canCombine(grid[i][e],grid[i-1][e])) {
								grid[i][e].setValue(0);
								grid[i-1][e].setValue(grid[i-1][e].getValue()*2);
								grid[i-1][e].setbool(true);
							}
						}
					}
				}	
			}
			for(int i = 0; i<grid.length; i++) {
				for(int e = 0; e<grid.length; e++) {
					grid[i][e].setbool(false);
				}
			  }
		} catch(Exception e) {
		}
	}
	//REFER TO DOWN METHOD ON HOW IT WORKS AND ADJUST BASED ON DIRECTION
	public static void left() {
		int temp;
		gameOver();
		try {
			for(int m = 0; m<4; m++) {
				for(int i = 0; i<grid.length; i++) {
					for(int e = 1; e<grid.length; e++) {
						if(grid[i][e].getValue()!=0) {
							if(e-1>=0&&grid[i][e-1].getValue()==0) {
								temp = grid[i][e-1].getID();
								grid[i][e-1].setID(grid[i][e].getID());
								grid[i][e-1].setValue(grid[i][e].getValue());
								grid[i][e].setValue(0);
								grid[i][e].setID(temp);
							}
							if(e!=0&&grid[i][e].getbool()==false&&canCombine(grid[i][e],grid[i][e-1])) {
								grid[i][e].setValue(0);
								grid[i][e-1].setValue(grid[i][e-1].getValue()*2);
								grid[i][e-1].setbool(true);
							}
						}
					}
				}	
			}
			for(int i = 0; i<grid.length; i++) {
				for(int e = 0; e<grid.length; e++) {
					grid[i][e].setbool(false);
				}
			}
		}
		catch(Exception e) {
		}
	}
	//REFER TO DOWN METHOD ON HOW IT WORKS AND ADJUST BASED ON DIRECTION
	public static void right() {
		int temp;
		gameOver();
		try {
			for(int m = 0; m<5; m++) { 
				for(int i = 0; i<grid.length; i++) {
					for(int e = 2; e>=0; e--) {
						if(grid[i][e].getValue()!=0) {
							if(e+1<=3&&grid[i][e+1].getValue()==0) {
								temp = grid[i][e+1].getID();
								grid[i][e+1].setID(grid[i][e].getID());
								grid[i][e+1].setValue(grid[i][e].getValue());
								grid[i][e].setValue(0);
								grid[i][e].setID(temp);
							}
							if(e!=3&&grid[i][e].getbool()==false&&canCombine(grid[i][e],grid[i][e+1])) {
								grid[i][e].setValue(0);
								grid[i][e+1].setValue(grid[i][e+1].getValue()*2);
								grid[i][e+1].setbool(true);
							}
						}
					}
				}	
			}
			for(int i = 0; i<grid.length; i++) {
				for(int e = 0; e<grid.length; e++) {
					grid[i][e].setbool(false);
				}
			}
		} catch(Exception e) {
		}
	}
}
