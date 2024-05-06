package com.oop.gameobject;

import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.oop.effect.Animation;
import com.oop.effect.CacheDataLoader;
import com.oop.effect.FrameImage;
import com.oop.userinterface.GameFrame;



public class GameWorld {
	
	public Megaman megaman,megaman2;
	private BufferedImage bufferedImage;
	public PhysicalMap physicalMap;
	public ParticularObjectManager particularObjectManager;
	public Camera camera;
	public Animation runForwardAnim,runBackAnim;
	public BufferedImage buffI;
	public BulletManager bulletManager;
	public Animation forwardBulletAnim ;
	public BackgroundMap backgroundMap;
	private BufferedImage resumeImage,quitImage,resumePickImage,quitPickImage,pauseTitle;
	//public static final int finalBossX = 3600;
    
    public static final int PAUSEGAME = 0;
    //public static final int TUTORIAL = 1;
    public static final int GAMEPLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int GAMEWIN = 4;
    //public static final int PAUSEGAME = 5;
    
    //public static final int INTROGAME = 0;
    //public static final int MEETFINALBOSS = 1;
	
    
    public int openIntroGameY = 0;
    public int state = GAMEPLAY;
    public int previousState = state;
    //public int tutorialState = INTROGAME;
    
    //public int storyTutorial = 0;
    //public String[] texts1 = new String[4];

    //public String textTutorial;
    //public int currentSize = 1;
    
    //private boolean finalbossTrigger = true;
    //ParticularObject boss;
    
    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");
    
    private int numberOfLife = 3;
    private int mapIndex=2;
    private int pauseGameChoose=0;
    
	public AudioClip bgMusic;
	
	public GameWorld() {
		/*
		texts1[0] = "We are heros, and our mission is protecting our Home\nEarth....";
        texts1[1] = "There was a Monster from University on Earth in 10 years\n"
                + "and we lived in the scare in that 10 years....";
        texts1[2] = "Now is the time for us, kill it and get freedom!....";
        texts1[3] = "      LET'S GO!.....";
        textTutorial = texts1[0];
		*/
		bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		megaman = new Megaman(400, 400, this, ParticularObject.LEAGUE_TEAM);
        megaman2= new Megaman(600,400,this,ParticularObject.ENEMY_TEAM);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 50, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        bulletManager = new BulletManager(this);
        
        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(megaman);
        particularObjectManager.addObject(megaman2);
        
        runForwardAnim = CacheDataLoader.getInstance().getAnimation("ulti2");
        runForwardAnim.flipAllImage();
        
        try {
			pauseTitle=ImageIO.read(new File("data/pause_title.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        quitPickImage= pauseTitle.getSubimage(50, 179, 276, 22);
        quitImage = pauseTitle.getSubimage(346, 179, 276, 22);
        resumePickImage = pauseTitle.getSubimage(50, 71, 144, 19);
        resumeImage = pauseTitle.getSubimage(346, 71, 144, 19);
        
        bgMusic = CacheDataLoader.getInstance().getSound("bgmusic");
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
           
                
            case GAMEPLAY:
                particularObjectManager.UpdateObjects();
                bulletManager.UpdateObjects();
        
                physicalMap.Update();
                camera.Update();
                
                /*
                if(megaman.getPosX() > finalBossX && finalbossTrigger){
                    finalbossTrigger = false;
                    switchState(TUTORIAL);
                    tutorialState = MEETFINALBOSS;
                    storyTutorial = 0;
                    openIntroGameY = 550;
                    
                    boss = new FinalBoss(finalBossX + 700, 460, this);
                    boss.setTeamType(ParticularObject.ENEMY_TEAM);
                    boss.setDirection(ParticularObject.LEFT_DIR);
                    particularObjectManager.addObject(boss);

                }
                */
                if(megaman.getState() == ParticularObject.DEATH){
                    numberOfLife --;
                    if(numberOfLife >= 0){
                        megaman.setBlood(100);
                        megaman.setPosY(megaman.getPosY() - 50);
                        megaman.setState(ParticularObject.FREEZE);
                        particularObjectManager.addObject(megaman);
                    }else{
                        switchState(GAMEOVER);
                        bgMusic.stop();
                    }
                }
                /*
                if(!finalbossTrigger && boss.getState() == ParticularObject.DEATH)
                    switchState(GAMEWIN);
                */
                break;
            case GAMEOVER:
                
                break;
            case GAMEWIN:
                
                break;
        }
        
        //runForwardAnim.Update(System.nanoTime());
    }
	


public void Render(){

    Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
    
    
    if(g2!=null){

        // NOTE: two lines below make the error splash white screen....
        // need to remove this line
        
        //g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
        //physicalMap.draw(g2);
        
        camera.lock();
        
        switch(state){
        case PAUSEGAME:
            g2.setColor(Color.black);
            
            float alpha = (float) 0.5; //draw half transparent
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
            g2.setComposite(ac);
            
            g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
            
            g2.drawImage(resumeImage, GameFrame.SCREEN_WIDTH/2-resumeImage.getWidth()/2, GameFrame.SCREEN_HEIGHT/2-resumeImage.getHeight()/2-200, null);
            g2.drawImage(quitImage, GameFrame.SCREEN_WIDTH/2-quitImage.getWidth()/2, GameFrame.SCREEN_HEIGHT/2-resumeImage.getHeight()/2-100, null);
            if (pauseGameChoose==0 ) 
            	g2.drawImage(resumePickImage, GameFrame.SCREEN_WIDTH/2-resumeImage.getWidth()/2, GameFrame.SCREEN_HEIGHT/2-resumeImage.getHeight()/2-200, null);
            else if(pauseGameChoose==1 )
            	g2.drawImage(quitPickImage, GameFrame.SCREEN_WIDTH/2-quitImage.getWidth()/2, GameFrame.SCREEN_HEIGHT/2-resumeImage.getHeight()/2-100, null);
            /*
            case TUTORIAL:
                backgroundMap.draw(g2);
                if(tutorialState == MEETFINALBOSS){
                    particularObjectManager.draw(g2);
                }
                TutorialRender(g2);
                
                break;
                */
            case GAMEWIN:
            case GAMEPLAY:
            	g2.setColor(Color.WHITE);
                g2.drawImage(buffI,0 ,0 , GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, null);
                //backgroundMap.draw(g2);
                particularObjectManager.draw(g2);  
                bulletManager.draw(g2);
                
                g2.setColor(Color.GRAY);
                g2.fillRect(19, 59, 102, 22);
                g2.setColor(Color.red);
                g2.fillRect(20, 60, megaman.getBlood(), 20);
                
                g2.setColor(Color.GRAY);
                g2.fillRect(809, 59, 102, 22);
                g2.setColor(Color.red);
                g2.fillRect(810, 60, megaman2.getBlood(), 20);
                
                
                
                for(int i = 0; i < numberOfLife; i++){
                    g2.drawImage(CacheDataLoader.getInstance().getFrameImage("hearth").getImage(), 20 + i*40, 18, null);
                }
                
                if (megaman.isBleeding()==true ) 
                	g2.drawImage(CacheDataLoader.getInstance().getFrameImage("bleed").getImage(), 20, 90, 35, 35, null);
                
                if(state == GAMEWIN){
                    g2.drawImage(CacheDataLoader.getInstance().getFrameImage("gamewin").getImage(), 300, 300, null);
                }
                
                break;
            
            case GAMEOVER:
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
                g2.setColor(Color.WHITE);
                g2.drawString("GAME OVER!", 450, 300);
                break;

        }
        

    }
    
    //FrameImage subImage = CacheDataLoader.getInstance().getFrameImage("blade3");
	//g2.drawImage(subImage.getImage(), 100,100,200,200, null);
    
    //runForwardAnim.draw(100, 300, 1,g2);
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
