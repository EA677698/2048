import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block {
	
	private int x;
	private int y;
	private int value;
	private int ID;
	private boolean wasCombined;
	
	public Block(int inputX, int inputY, int inputValue, boolean inwascombined, int inputID) {
		ID = inputID;
		x = inputX;
		y = inputY;
		value = inputValue;
		wasCombined = inwascombined;
		int[] accept = {0,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192};
		for(int i = 0; value!=accept[i]; i++) {
			if(i>accept.length-2) {
				System.out.println("Value Not Valid!");
				System.exit(1);
			}
		}
			
	}
	
	
	
	public Block(Block block) {
		x = block.x;
		y = block.y;
		value = block.value;
		ID = block.ID;
		wasCombined = block.wasCombined;
	}



	public void setX(int inputX) {
		x = inputX;
	}
	
	public void setY(int inputY) {
		y = inputY;
	}
	
	public void setValue(int inputValue) {
		value = inputValue;
	}
	public void setbool(boolean input) {
		wasCombined = input;
	}
	public void setID(int inputID) {
		ID = inputID;
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getValue() {
		return value;
	}
	public boolean getbool() {
		return wasCombined;
	}
	public int getID() {
		return ID;
	}
	
	
	public String toString() {
		return String.valueOf(value)+","+ID;//"("+x+","+y+","+value+")";//"Current postion of block "+Block.class+":"+"("+String.valueOf(x)+
			//","+String.valueOf(y)+")"+". The value of this block is "+value;
	}

	
}
