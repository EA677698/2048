
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.*;
import java.util.Timer;

public class GUI extends JPanel {
	

	  private String dir = System.getProperty("user.dir")+"\\src\\graphics\\";
	  public static Block[][] grid = Mechanics.grid;
	  private static Image backgroundImage;
	  private static Image one;
	  private static Image two;
	  private static Image three;
	  private static Image four;
	  private static Image five;
	  private static Image six;
	  private static Image seven;
	  private static Image eight;
	  private static Image nine;
	  private static Image ten;
	  private static Image eleven;
	  private static Image twelve;
	  private static Image thirteen;
	  private static Image fourteen;
	  private static Image fifteen;
	  public static boolean title = true;

	  
	  // Some code to initialize the background image.
	  // Here, we use the constructor to load the image. This
	  // can vary depending on the use case of the panel.
	  public GUI(String fileName) throws IOException {
		  System.out.println(dir);
	    backgroundImage = ImageIO.read(new File(dir+"background.png"));
	    one = ImageIO.read(new File(dir+"2block.png"));
	    two = ImageIO.read(new File(dir+"4block.png"));
	    three = ImageIO.read(new File(dir+"8block.png"));
	    four = ImageIO.read(new File(dir+"16block.png"));
	    five = ImageIO.read(new File(dir+"32block.png"));
	    six = ImageIO.read(new File(dir+"64block.png"));
	    seven = ImageIO.read(new File(dir+"128block.png"));
	    eight = ImageIO.read(new File(dir+"256block.png"));
	    nine = ImageIO.read(new File(dir+"512block.png"));
	    ten = ImageIO.read(new File(dir+"1024block.png"));
	    eleven = ImageIO.read(new File(dir+"2048block.png"));
	    twelve = ImageIO.read(new File(dir+"4096block.png"));
	    thirteen = ImageIO.read(new File(dir+"8192block.png"));
	    fourteen = ImageIO.read(new File(dir+"gameover.png"));
	    fifteen = ImageIO.read(new File(dir+"TitleScreen.png"));
	  }

	  public void paintComponent(java.awt.Graphics g) {
	    super.paintComponent(g);
	    // Draw the background image.
	    
	    g.drawImage(fifteen, 0, 0, this);
	    if(!title) {
		    g.drawImage(backgroundImage, 0, 0, this);
		    for(int i = 0; i<grid.length; i++) {
		    	for(int e = 0; e<grid.length; e++) {
		    		if(grid[i][e].getValue()!=0) {
			    	    g.drawImage(translatorV(grid[i][e].getValue()), translatorXY(grid[i][e].getY()), translatorXY(grid[i][e].getX()), this);
		    		}
		    	}
			repaint();
		    }
		    if(Mechanics.gameOver()) {
		    	g.drawImage(fourteen, 17, 250, this);
		    }
	    }
	  }
	  
		//These are used to convert 1-16 x and y grid array to pixel x and y
		public static int translatorXY(int xinput) {
			int x;
			switch(xinput) {
			case 0: x = 37;
			break;
			case 1: x = 200;
			break;
			case 2: x = 360;
			break;
			case 3: x = 522;
			break;
			default: x = 37;
			}
			return x;
		}
		//assigns an image to a block depending on its value
		public static Image translatorV(int inputvalue) {
			Image assign = null;
			switch(inputvalue) {
			case 2: assign = one;
			break;
			case 4: assign = two;
			break;
			case 8: assign = three;
			break;
			case 16: assign = four;
			break;
			case 32: assign = five;
			break;
			case 64: assign = six;
			break;
			case 128: assign = seven;
			break;
			case 256: assign = eight;
			break;
			case 512: assign = nine;
			break;
			case 1024: assign = ten;
			break;
			case 2048: assign = eleven;
			break;
			case 4096: assign = twelve;
			break;
			case 8192: assign = thirteen;
			break;
			}
			return assign;
		}
	
}
