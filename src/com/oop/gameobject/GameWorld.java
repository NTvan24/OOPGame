package com.oop.gameobject;

import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.oop.effect.Animation;
import com.oop.effect.CacheDataLoader;
import com.oop.effect.FrameImage;
import com.oop.userinterface.GameFrame;



public class GameWorld {
	
	public Megaman megaman,megaman2;
	private BufferedImage bufferedImage;
	private BufferedImage KOImage,player1win,player2win,star;
	public PhysicalMap physicalMap;
	public ParticularObjectManager particularObjectManager;
	public Camera camera;
	public Animation runForwardAnim,runBackAnim;
	public BufferedImage buffI;
	public BulletManager bulletManager;
	public Animation forwardBulletAnim ;
	//public BackgroundMap backgroundMap;
	private BufferedImage resumeImage,quitImage,resumePickImage,quitPickImage,pauseTitle;
	
    
    public static final int PAUSEGAME = 0;
    
    public static final int GAMEPLAY = 2;
    public static final int PLAYER1WIN = 6;
    public static final int PLAYER2WIN = 7;
    public static final int GAMEWIN = 4;
    public static final int ENDGAME = 5;
    
  
	
    
    public int openIntroGameY = 0;
    public int state = PLAYER1WIN;
    public int previousState = state;
    
    
    private int player1Point=0;
    private int player2Point=0;
    
    private int mapIndex=2;
    private int pauseGameChoose=0;
    
    
    private int testAni=0;
    
	public AudioInputStream bgMusic;
	
	@SuppressWarnings("deprecation")
	public GameWorld() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		 
		player1Point=0;
	    player2Point=0;
		bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		megaman = new Megaman(400, 400, this, ParticularObject.LEAGUE_TEAM);
        megaman2= new Megaman(600,400,this,ParticularObject.ENEMY_TEAM);
        //backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 50, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        bulletManager = new BulletManager(this);
        
        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(megaman);
        particularObjectManager.addObject(megaman2);
        
        runForwardAnim = CacheDataLoader.getInstance().getAnimation("skill1");
        runForwardAnim.flipAllImage();
        
        try {
			pauseTitle=ImageIO.read(new File("data/pause_title.png"));
			KOImage = ImageIO.read(new File("data/KO.png"));
			player1win= ImageIO.read(new File("data/1win.png"));
			player2win= ImageIO.read(new File("data/2win.png"));
			star = ImageIO.read(new File("data/star.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        quitPickImage= pauseTitle.getSubimage(50, 179, 276, 22);
        quitImage = pauseTitle.getSubimage(346, 179, 276, 22);
        resumePickImage = pauseTitle.getSubimage(50, 71, 144, 19);
        resumeImage = pauseTitle.getSubimage(346, 71, 144, 19);
        File bgPath = new File("data/bgmusic2.wav");
        bgMusic = AudioSystem.getAudioInputStream(bgPath);
        Clip clip = AudioSystem.getClip();
        clip.open(bgMusic);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
	}
	
	public void newGame()
	{
		player1Point=0;
	    player2Point=0;
	   
	}
	
	public void resetMap() {
		state=GAMEPLAY;
		particularObjectManager.RemoveObject(megaman);
		particularObjectManager.RemoveObject(megaman2);
		megaman = new Megaman(400, 400, this, ParticularObject.LEAGUE_TEAM);
        megaman2= new Megaman(600,400,this,ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(megaman);
        particularObjectManager.addObject(megaman2);
		try {
			buffI=ImageIO.read(new File("data/background"+mapIndex+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        
        physicalMap = new PhysicalMap(0, 0, this,mapIndex);
        
	}
	
	
	public int getMapIndex() {
		return mapIndex;
	}


	public void setMapIndex(int mapIndex) {
		this.mapIndex = mapIndex;
	}


	public void switchState(int state){
        previousState = this.state;
        this.state = state;
    }
	
	
	
	private void drawString(Graphics2D g2, String text, int x, int y){
        for(String str : text.split("\n"))
            g2.drawString(str, x, y+=g2.getFontMetrics().getHeight());
    }
	
	
public void Update(){
        
        switch(state){
            case PAUSEGAME:
                
                break;
            case ENDGAME:
            	break;
                
            case GAMEPLAY:
                particularObjectManager.UpdateObjects();
                bulletManager.UpdateObjects();
        
                physicalMap.Update();
                camera.Update();
                
                
                if(megaman.getState() == ParticularObject.DEATH && state==GAMEPLAY){
                	if(player2Point==0) {
                		player2Point=1;
                		//resetMap();
                		switchState(ENDGAME);
                	}
                	else if (player2Point==1) {
                		switchState(PLAYER2WIN);
                	}
                }	
                	
                if(megaman2.getState() == ParticularObject.DEATH && state==GAMEPLAY ){
                	if(player1Point==0) {
                		player1Point=1;
                		//resetMap();
                		switchState(ENDGAME);
                	}
                	else if (player1Point==1) {
                		switchState(PLAYER1WIN);
                	}
                }	    
                
                
                //test sth
                if(testAni==1)
                	runForwardAnim.Update(System.nanoTime());
                
                break;
            case PLAYER1WIN:
                
                break;
            case PLAYER2WIN:
                
                break;
            case GAMEWIN:
                
                break;
        }
        
        
    }
	


public void Render(){

    Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
    Graphics g = bufferedImage.getGraphics();
    
    if(g2!=null){

        // NOTE: two lines below make the error splash white screen....
        // need to remove this line
        
        //g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        
        
        camera.lock();
        
        switch(state){
        
            case GAMEWIN:
            case GAMEPLAY:
            	if(state==PAUSEGAME) {
            		g2.setColor(Color.black);
            		
                    float alpha = (float) 0.5; //draw half transparent
                    AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
                    g2.setComposite(ac);
                    
                    
            	}
            	g2.setColor(Color.WHITE);
                g2.drawImage(buffI,0 ,0 , GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, null);
                //backgroundMap.draw(g2);
                particularObjectManager.draw(g2);  
                bulletManager.draw(g2);
                
                g2.setColor(Color.GRAY);
                g2.fillRect(19, 30, 102, 22);
                g2.setColor(Color.red);
                g2.fillRect(20, 31, megaman.getBlood(), 20);
                
                g2.setColor(Color.GRAY);
                g2.fillRect(19, 70, 102, 22);
                g2.setColor(Color.blue);
                g2.fillRect(20, 71, megaman.getMana(), 20);
                
                g2.setColor(Color.GRAY);
                g2.fillRect(809, 30, 102, 22);
                g2.setColor(Color.red);
                g2.fillRect(810, 31, megaman2.getBlood(), 20);
                
                g2.setColor(Color.GRAY);
                g2.fillRect(809, 70, 102, 22);
                g2.setColor(Color.blue);
                g2.fillRect(810, 71, megaman2.getMana(), 20);
                
                for(int i = 0; i < player1Point; i++){
                	g2.drawImage(star, 135, 23,51,34, null);
                }
                for(int i = 0; i < player2Point; i++){
                g2.drawImage(star, 740, 23,51,34, null);
                }
                if (megaman.isBleeding()==true ) 
                	g2.drawImage(CacheDataLoader.getInstance().getFrameImage("bleed").getImage(), 20, 90, 35, 35, null);
                
                if(state == GAMEWIN){
                    g2.drawImage(CacheDataLoader.getInstance().getFrameImage("gamewin").getImage(), 300, 300, null);
                }
                
                //test sth
                if(testAni==1)
                	runForwardAnim.draw(200, 300, 1,g2);
                if(state!=PAUSEGAME) break;
            case PAUSEGAME:
            	g2.setColor(Color.black);
        		
                float alpha = (float) 0.02; //draw half transparent
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
                g2.setComposite(ac);
            	g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
            	
            	float alpha2 = (float) 1; //draw half transparent
                AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha2);
                g2.setComposite(ac2);
            	//g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
                g2.drawImage(resumeImage, GameFrame.SCREEN_WIDTH/2-resumeImage.getWidth()/2, GameFrame.SCREEN_HEIGHT/2-resumeImage.getHeight()/2-200, null);
                g2.drawImage(quitImage, GameFrame.SCREEN_WIDTH/2-quitImage.getWidth()/2, GameFrame.SCREEN_HEIGHT/2-resumeImage.getHeight()/2-100, null);
                if (pauseGameChoose==0 ) 
                	g2.drawImage(resumePickImage, GameFrame.SCREEN_WIDTH/2-resumeImage.getWidth()/2, GameFrame.SCREEN_HEIGHT/2-resumeImage.getHeight()/2-200, null);
                else if(pauseGameChoose==1 )
                	g2.drawImage(quitPickImage, GameFrame.SCREEN_WIDTH/2-quitImage.getWidth()/2, GameFrame.SCREEN_HEIGHT/2-resumeImage.getHeight()/2-100, null);
                
               break;
            case ENDGAME:
            	g2.drawImage(KOImage, 260, 100,440,160, null);
            	break;
            case PLAYER1WIN:
                g2.drawImage(player1win, 260, 100,440,260, null);
                break;
            case PLAYER2WIN:
            	g2.drawImage(player2win, 260, 100,440,260, null);
                break;
        }
        
        //physicalMap.draw(g2);
    }
    
    
    
    
}

public int getPauseGameChoose() {
	return pauseGameChoose;
}


public void setPauseGameChoose(int pauseGameChoose) {
	this.pauseGameChoose = pauseGameChoose;
}


public void incresePausePick()
{
	pauseGameChoose ++;
	if(pauseGameChoose>1) pauseGameChoose=0;
}
public void decresePausePick()
{
	pauseGameChoose --;
	if(pauseGameChoose<0) pauseGameChoose=1;
}
public BufferedImage getBufferedImage(){
    return bufferedImage;
}
}
