package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Decorator.BothDecorator;
import Decorator.BroccoliDecorator;
import Decorator.HotdogDecorator;
import Decorator.NoodleInterface;
import Decorator.OilStain;
import Decorator.Steam;
import assets.MinimHelper;
import kitchen.Eggs;
import kitchen.Kitchen;
import kitchen.Lighting;
import kitchen.NoodleBowl;
import kitchen.Noodles;
import kitchen.Pan;
import kitchen.Pot;
import kitchen.Sink;
import kitchen.StartButton;
import kitchen.Stove;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

/* Project created by Michael Su
 * Apr 17 2022
 * SFU IAT265 
 * 
 * ECO credits
 * 2 - sophisticated complex shapes that are well polished and sensible to the app
 * 2 - preferred decorator pattern features
 * 2 - all images used are self-created with excellent quality
 * 6 total
 * 
 * Panel class controls the application, mouse events, 
 * finite states, and audio. 
 */

public class NoodlePanel extends JPanel implements ActionListener {

	public static int W_WIDTH = 1280;
	public static int W_HEIGHT = 720;

	private double mouseX;
	private double mouseY;

	// interface thingy
	private JFrame frame = new JFrame();
	private int state = -1;
	private StartButton start, startBG;

	// Kitchen stuff
	private Kitchen kitchen;
	private Lighting lightSw;
	private Pot pot;
	private Pan pan;
	private Sink sink;
	private Stove stoveSmall, stoveBig;

	// Food items
	private Eggs egg, eggCarton;
	private Noodles noodles;
	private NoodleBowl bowl;

	// decorate stuff
	private Steam steam;
	private OilStain oil;
	private NoodleInterface decorator;
	JButton broccoli, hotdog, both;

	private String instructions = "";
	private Timer timer;
	// PERLIN NOISE STUFF
	private int cookTimer = 0;
	private boolean halfDone = false;
	private int cookingTime = 200;

	private Minim minim;
	private AudioPlayer bkmusic, click, water, potDrag, panDrag, panSizzle, eggCrack, bagOpen, bowlPour, waterBoil;

	NoodlePanel(JFrame frame) {
		this.frame = frame;
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));

		// start/bg/end
		start = new StartButton("assets/start.png", W_WIDTH / 2, W_HEIGHT - 100);
		startBG = new StartButton("assets/startBG.png", W_WIDTH / 2, W_HEIGHT / 2);
		kitchen = new Kitchen("assets/kitchen.png");

		// clickable items
		lightSw = new Lighting("assets/lightSwitch.png", 50, 300);
		stoveSmall = new Stove("assets/stoveSmall0.png", 249, 442);
		stoveBig = new Stove("assets/stoveBig0.png", 392, 431);
		bowl = new NoodleBowl("assets/bowl0.png", W_WIDTH / 2, W_HEIGHT / 2);
		pot = new Pot("assets/pot.png", 769, 540);
		pan = new Pan("assets/pan.png", 630, 540);
		sink = new Sink("assets/sink.png", 1000, 340);
		eggCarton = new Eggs("assets/eggs0.png", 592, 100);
		egg = new Eggs("assets/egg.png", 550, 100);
		noodles = new Noodles("assets/noodles0.png", 900, 100);

		// decorating stuff
		steam = new Steam((float) stoveBig.getXPos() - 60, (float) stoveBig.getYPos() - 100, 100, 30);
		broccoli = new JButton("Add Broccoli");
		hotdog = new JButton("Add Hotdog");
		both = new JButton("Add Both");

		broccoli.setBackground(new Color(0, 255, 0));
		hotdog.setBackground(new Color(255, 169, 169));
		both.setBackground(new Color(255, 255, 169));
		
		add(broccoli);
		add(hotdog);
		add(both);
		
		broccoli.addActionListener(this);
		hotdog.addActionListener(this);
		both.addActionListener(this);
		
		//mouse and system stuff
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);

		MyMouseMotionListener mml = new MyMouseMotionListener();
		addMouseMotionListener(mml);

		timer = new Timer(30, this);
		timer.start();

		// MINIM
		minim = new Minim(new MinimHelper());
		bkmusic = minim.loadFile("music.mp3");
		click = minim.loadFile("tap.mp3");
		water = minim.loadFile("water.wav");
		potDrag = minim.loadFile("potdrag.wav");
		panDrag = minim.loadFile("pandrag.wav");
		panSizzle = minim.loadFile("pansizzle.wav");
		eggCrack = minim.loadFile("eggcrack.mp3");
		waterBoil = minim.loadFile("waterBoil.mp3");
		bagOpen = minim.loadFile("bagOpen.mp3");
		bowlPour = minim.loadFile("bowlPour.mp3");
		//

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		kitchen.draw(g2);

		// FRACTAL DESIGN
		if (state >= 0 && state != 9) {
			AffineTransform at = g2.getTransform();
			oil = new OilStain();
			g2.translate(360, 255);
			oil.drawOilStain(g2, 50);
			g2.setTransform(at);
		}

		stoveSmall.draw(g2);
		stoveBig.draw(g2);
		sink.draw(g2);
		bowl.draw(g2);
		pot.draw(g2);
		pan.draw(g2);
		eggCarton.draw(g2);
		noodles.draw(g2);
		lightSw.draw(g2);

		// Starting screen
		if (state == -1) {
			startBG.draw(g2);
			start.draw(g2);
		}
		// lights off
		if (state == 0) {
			lightSw.setLightOn(0);
		}
		// lights on
		else if (state >= 1) {
			lightSw.setLightOn(1);
			pot.underSink(sink);
		}
		// pot is filled with water place onto stove
		if (state == 2) {
			water.pause();
			pot.onStove();
			if (pot.isOnStove() && stoveBig.isHot()) {
				pot.setPos(394, 400);
				state = 3;
			}
			
		}
		// turn on stove, heat pan, crack egg
		if (state == 3) {
			pan.onStove();
			// Open egg
			if (eggCarton.isOpen())
				egg.draw(g2);
			// turn on stove, place pan
			if (pan.isOnStove() && stoveSmall.isHot()) {
				pan.setPos(230, 430);
				egg.onPan();
				// crack the egg onto pan
				if (egg.isOnPan()) {
					eggCrack.play();
					panSizzle.play();
					pan.setImg(1);
					state = 4;
				}
			}
		
		}
		// start cooking the noodles by dragging onto pot
		if (state == 4) {
			potDrag.rewind();
			panDrag.rewind();
			noodles.onPot();
			pan.setPos(230, 430);
			if (noodles.isOnPot()) {
				waterBoil.play();
				state = 5;
			}
			steam.drawSteam(g2);
			
		}
		//Noodles are cooking, wait for timer to end before proceeding
		if (state == 5) {
			steam.drawSteam(g2);
			noodles.setImg(3);
			pan.setPos(230, 430);
			if (cookingTime <= 0) {
				state = 6;
			}
		}
		//pot can be taken off and poured onto bowl
		if (state == 6) {
			waterBoil.pause();
			pot.takeOffStove();
			pot.topOfBowl();
			pan.setPos(230, 430);
			if (pot.inBowl()) {
				bowlPour.play();
				stoveBig.setImg(0);
				state = 7;
			}
		}
		//pan can be taken off and poured into bowl
		if (state == 7) {
			bowl.setImg(1);
			pan.topOfBowl();
			if (pan.inBowl()) {
				stoveSmall.setImg(2);
				state = 8;
			}
		}
		//when both noodles and eggs are in, change image and decorate
		if (state == 8) {
			panSizzle.pause();
			bowl.setImg(2);
			
			if (decorator != null)
				decorator.decorate(g2);
		}

		//DRAW INFORMATION BAR THING
		if (state != -1) {
			drawInfo(g2);
			
		}

		// RETRY SCREEN
		if (state == 9) {
			startBG.setImg(4);
			startBG.draw(g2);
			start.setImg(3);
			start.draw(g2);
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (state == 5) {
			steam.setWidth(cookTimer * 3 / 2);
			steam.setHeight(cookTimer * 3 / 10);
			if (!halfDone) {
				cookTimer++;
				cookingTime--;
				if (cookTimer >= 100) {
					cookTimer = 100;
					halfDone = true;
				}
			} else {
				cookTimer--;
				cookingTime--;
				if (cookTimer <= 0) {
					cookTimer = 0;
					state = 6;
				}
			}
		}
		if (state == 8) {
			if (e.getActionCommand() == "Add Broccoli") {
				decorator = new BroccoliDecorator(decorator, W_WIDTH / 2, W_HEIGHT / 2);
			}
			if (e.getActionCommand() == "Add Hotdog") {
				decorator = new HotdogDecorator(decorator, W_WIDTH / 2, W_HEIGHT / 2);
			}
			if (e.getActionCommand() == "Add Both") {
				decorator = new BothDecorator(decorator, W_WIDTH / 2, W_HEIGHT / 2);

			}
		}

		repaint();
	}

	private void drawInfo(Graphics2D g) {
		Font f = new Font("Arial", Font.TRUETYPE_FONT, 24);
		g.setFont(f);

		g.setColor(new Color(128, 128, 128, 169));
		g.fillRect(0, W_HEIGHT - 50, W_WIDTH, 50);

		if (state == 0) {
			instructions = "Flip the lightswitch to turn on the light";
		}
		if (state == 1) {
			instructions = "Fill up the pot with water from the sink";
		}
		if (state == 2) {
			instructions = "Turn on the big stove element and heat up the water";
		}
		if (state == 3) {
			instructions = "While waiting for water to boil, heat up a pan, and cook an egg";
		}
		if (state == 4) {
			instructions = "The water is boiling, cook the noodles";
		}
		if (state == 5) {
			instructions = "Wait for noodles to finish cooking: " + cookingTime / 33;
		}
		if (state == 6) {
			instructions = "Pour the noodles into the bowl";
		}
		if (state == 7) {
			instructions = "Flip the egg into the bowl";
		}
		if (state == 8) {
			instructions = "Decorate the bowl with some veggies or meat, then click to finish";
		}

		g.setColor(new Color(255, 255, 255));
		g.drawString(instructions, 100, W_HEIGHT - 20);
	}

	public class MyMouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (state == -1 && start.clicked(mouseX, mouseY)) {
				state = 0;
				
			}

			if (state == 0 && lightSw.clicked(mouseX, mouseY)) {
				state = 1;
				bkmusic.loop();
				click.play(0);
				
			} else if (state == 1 && lightSw.clicked(mouseX, mouseY)) {
				state = 0;
				click.play(0);
			}

			if (state == 1 && sink.clicked(mouseX, mouseY)) {
				sink.setImg(1);
				water.play(0);
			}

			if (state == 2 && stoveBig.clicked(mouseX, mouseY)) {
				stoveBig.setImg(1);
			}
			if (state == 3) {
				if (eggCarton.clicked(mouseX, mouseY))
					eggCarton.setImg(1);
				if (stoveSmall.clicked(mouseX, mouseY))
					stoveSmall.setImg(3);
			}
			if (state == 4) {
				if (noodles.clicked(mouseX, mouseY)) {
					noodles.setImg(1);
					bagOpen.play();
				}
			}
			if (state == 8) {
				if (bowl.clicked(mouseX, mouseY)) {
					bkmusic.pause();
					bkmusic.rewind();
					state = 9;
				}
			}

			if (state == 9 && start.clicked(mouseX, mouseY)) {
				frame.dispose();
				new NoodleApp("Broke College Student Lunch");
			}

		}

		public void mouseReleased(MouseEvent e) {
		}
	}

	public class MyMouseMotionListener extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (state >= 1) {
				if (pot.clicked(mouseX, mouseY)) {
					if (state < 3 || state > 5) {
						potDrag.play();
						pot.setPos(mouseX, mouseY);
					}
				}
				if (state < 4 && pot.state == 1)
					state = 2;
				if (pan.clicked(mouseX, mouseY)) {
					panDrag.play();
					pan.setPos(mouseX, mouseY);
				}
				if (egg.clicked(mouseX, mouseY)) {
					egg.setPos(mouseX, mouseY);
				}
				if (noodles.isOpen())
					if (noodles.clicked(mouseX, mouseY)) {
						noodles.setPos(mouseX, mouseY);
					}
//				if (bowl.clicked(mouseX, mouseY)) {
//					bowl.setPos(mouseX, mouseY);
//				}
			}

		}
		

	}
}
