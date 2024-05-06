package com.oop.userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.oop.effect.FrameImage;
import com.oop.gameobject.GameWorld;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener{

    GameWorld gameWorld;
    public static final int NUMBER_OF_MAP = 2;
    InputManager inputManager;
    
    public FrameImage maps[] = new FrameImage[NUMBER_OF_MAP];
    
    Thread gameThread;
    BufferedImage mapPickImage,menuImage,menuTitle,playImage,exitImage,playPickImage,exitPickImage;
    public static final int MENU = 0;
    //public static final int TUTORIAL = 1;
    public static final int PICKMAP = 10;
    public static final int GAMEPLAY = 20;
    
    private int menuCurrent=0;
    private int mapCurrent=1;
    private int state=MENU;
    public boolean isRunning = true;

    public GamePanel(){
    	try {
    		for(int i=0;i<NUMBER_OF_MAP;i++) {
    		FrameImage frame = new FrameImage();
    		BufferedImage image = ImageIO.read(new File("data/background"+(i+1)+".png"));
    		
    		frame.setImage(image);
			maps[i]=frame;
    		}
    		mapPickImage = ImageIO.read(new File("data/menu_map.png"));
    		menuImage = ImageIO.read(new File("data/menu.png"));
    		menuTitle = ImageIO.read(new File("data/menu_title2.png"));
    		
    		playPickImage = menuTitle.getSubimage(123, 57, 134, 52);
    		exitPickImage = menuTitle.getSubimage(136, 118, 108, 46);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //gameState = new MenuState(this);
    	gameWorld = new GameWorld();
        if(state== GAMEPLAY) gameWorld.resetMap();
        inputManager = new InputManager(gameWorld,this);
        //inputManager = new InputManager();

    }

    public int getMapCurrent() {
		return mapCurrent;
	}

	public void setMapCurrent(int mapCurrent) {
		this.mapCurrent = mapCurrent;
	}

	public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    int a = 0;
    @Override
    public void run() {

        long previousTime = System.nanoTime();
        long currentTime;
        long sleepTime;

        long period = 1000000000/80;

        while(isRunning){
        	
        	
        	
        	
        	Update();
        	Render();
        	repaint();

            currentTime = System.nanoTime();
            sleepTime = period - (currentTime - previousTime);
            try{

                    if(sleepTime > 0)
                            Thread.sleep(sleepTime/1000000);
                    else Thread.sleep(period/2000000);

            }catch(Exception e){}

            previousTime = System.nanoTime();
        }

    }

    public void paint(Graphics g){
    	Graphics2D g2 = (Graphics2D) g;
    	
    	if(state==GAMEPLAY)
    		g.drawImage(gameWorld.getBufferedImage(), 0, 0, this);
    	else if(state==PICKMAP) {
    		//g.setColor(Color.white);
        	//g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
    		g.drawImage(mapPickImage, 0, 0,GameFrame.SCREEN_WIDTH , GameFrame.SCREEN_HEIGHT, null);
    			
    		maps[mapCurrent-1].draw(g2,501,157, 276,160);
    		//System.out.println("state=map");
    		}
    	else if(state==MENU) {
    		g.drawImage(menuImage, 0, 0,GameFrame.SCREEN_WIDTH , GameFrame.SCREEN_HEIGHT-30, null);
    		//System.out.println("state=menu");
    		if(menuCurrent==0)
    			g.drawImage(playPickImage,331,293,360,135,null);
    		else if (menuCurrent==1)
    			g.drawImage(exitPickImage, 354, 430, 300, 135, null);
    	}
    	}

    
    
    public void Update(){
    	if(state==GAMEPLAY)
    		gameWorld.Update();
    	
    }
    public void Render(){
    	if(state==GAMEPLAY)
    		gameWorld.Render();
    	else if(state==PICKMAP) {
    		
    	}

    }
    
    public int getState() {
		return state;
	}

	public void setPanelState(int state) {
		this.state = state;
	}
	public void increaseMap() {
		mapCurrent++;
		if(mapCurrent>NUMBER_OF_MAP) mapCurrent=1;
	}
	public void decreaseMap() {
		mapCurrent--;
		if(mapCurrent<1) mapCurrent=NUMBER_OF_MAP;
	}
	public void increaseMenuCurrent() {
		menuCurrent++;
		if(menuCurrent>1) menuCurrent=0;
	}
	public void decreaseMenuCurrent() {
		menuCurrent--;
		if(menuCurrent<0) menuCurrent=1;
	}
	
	public int getMenuCurrent() {
		return menuCurrent;
	}

	public void setMenuCurrent(int menuCurrent) {
		this.menuCurrent = menuCurrent;
	}

	@Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.setPressedButton(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.setReleasedButton(e.getKeyCode());
    }

    
    
}