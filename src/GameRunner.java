import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameRunner extends JPanel implements KeyListener {
	
	public static JFrame frame;
	private String dir = System.getProperty("user.dir")+"\\src\\graphics\\";
	private boolean start = false;
	public Timer wait = new Timer();
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Mechanics.generateblocks();
		Mechanics.start();
		Mechanics.display();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameRunner window = new GameRunner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public GameRunner() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage(dir+"favicon.png");
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(706, 736);
		frame.getContentPane().add(new GUI("background.png"));
		frame.setTitle("2048 (v1.0.0)");
		frame.setIconImage(icon);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		addKeyListener(this);
		this.requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SPACE: GUI.title = false; start = true;
		break;
		case KeyEvent.VK_UP: if(start) {Mechanics.up(); Mechanics.toDo();}
		break;
		case KeyEvent.VK_DOWN: if(start) { Mechanics.down();Mechanics.toDo();}
		break;
		case KeyEvent.VK_LEFT: if(start) { Mechanics.left();Mechanics.toDo();}
		break;
		case KeyEvent.VK_RIGHT: if(start) { Mechanics.right();Mechanics.toDo();}
		break;
		case KeyEvent.VK_R: if(start) { Mechanics.reset();Mechanics.toDo();}
		}
		frame.repaint();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
