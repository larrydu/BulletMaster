package bulletmaster;


import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Frame;
import java.net.URL;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartingClass extends Applet implements Runnable, KeyListener{

	//Make a hero 
	private Hero bulletMonk;
	private Hornworm hrn1,hrn2;
	private Image image, character,background, hornworm;
	private URL base;
	private Graphics second;
	private static Background bg1, bg2;

	//Note that init(),start(),stop(), and destroy are methods
	//inherited from Applet

	@Override
	public void init() {

		/*Initializing*/
		//System.out.println("Initializing!\n");

		/*Set screen size*/
		setSize(800,480);
		/*Set background*/
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		/*Assign the applet window to the frame*/
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Bullet Master");
		try{
			base = getCodeBase();
	
		}catch (Exception e){
			//TODO: handle exception
		}

		//Image setups
		character = getImage(base,"../img/proto.png");
		background = getImage(base,"../img/background.png");
		hornworm = getImage(base,"../img/protobaddie.png");


	}

	@Override
	public void start() {
		bg1 = new Background(0,0);
		bg2 = new Background(2160,0);
		hrn1 = new Hornworm(340,360);
		hrn2 = new Hornworm(400,360);
		bulletMonk = new Hero(); //initialize bulletMonk hero
		Thread thread = new Thread(this);
		thread.start();
	}

	
	
	
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
	
	}

	
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}


	@Override
	public void run() {

		while (true) {
			bulletMonk.update();
			hrn1.update();
			hrn2.update();
			bg1.update();
			bg2.update();
			repaint();
			try{
				Thread.sleep(17);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}//while loop end
	}

	
	//Update graphics with double buffering system
	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, bg1.getBgX(),bg1.getBgY(),this);
		g.drawImage(background, bg2.getBgX(),bg2.getBgY(),this);

		g.drawImage(hornworm, hrn1.getCenterX() - 48, hrn1.getCenterY() - 48, this);
		g.drawImage(hornworm, hrn2.getCenterX() - 48, hrn2.getCenterY() - 48, this);
		g.drawImage(character,bulletMonk.getCenterX()-61,bulletMonk.getCenterY()-63,this);
	} 
	

	//Handle input
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");	
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("Move down");
			break;

		case KeyEvent.VK_LEFT:
			System.out.println("Move left");
			bulletMonk.moveLeft();
			break;

		case KeyEvent.VK_RIGHT:
			System.out.println("Move right");
			bulletMonk.moveRight();
			break;

		case KeyEvent.VK_SPACE:
			System.out.println("Jump");
			bulletMonk.moveJump();
			break;
		}	

	}

	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			System.out.println("Stop moving down");
			break;

		case KeyEvent.VK_LEFT:
			bulletMonk.moveStop();
			System.out.println("Stop moving left");
			break;

		case KeyEvent.VK_RIGHT:
			bulletMonk.moveStop();
			System.out.println("Stop moving right");
			break;

		case KeyEvent.VK_SPACE:
			System.out.println("Stop jumping");
			break;

		}	    
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }




}
