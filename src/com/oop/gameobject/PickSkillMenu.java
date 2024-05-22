package com.oop.gameobject;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.oop.effect.CacheDataLoader;
import com.oop.effect.FrameImage;
import com.oop.userinterface.GameFrame;
import com.oop.userinterface.GamePanel;

public class PickSkillMenu {
	
	GamePanel gamePanel;
	private BufferedImage bufferedImage;

    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");
    
    BufferedImage pickSkillMenu,title1,title2;
    BufferedImage skill0,skill1,skill2,skill3,skill4,skill5;
    ArrayList <BufferedImage> skillImg ;
    private int testAni=1;
    private int currentChoose1=0;
    private int currentChoose2=4;
    private int maxSkill=5;
    private int countSkill1;
    private int countSkill2;
    
	public AudioClip bgMusic;
	
	public PickSkillMenu(GamePanel gamePanel ) {
		this.gamePanel=gamePanel;
		countSkill1=0;
		countSkill2=0;
		try {
			pickSkillMenu=ImageIO.read(new File("data/pickskill.png"));
			title1=ImageIO.read(new File("data/pickskilltitle.png"));
			title2=ImageIO.read(new File("data/titlegreen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		skillImg = new ArrayList<>();
		skill0=pickSkillMenu.getSubimage(215, 428, 41, 45);
		skill1=pickSkillMenu.getSubimage(357, 427, 45, 47);
		skill2=pickSkillMenu.getSubimage(507, 427, 43, 49);
		skill3=pickSkillMenu.getSubimage(656, 434, 38, 45);
		skill4=pickSkillMenu.getSubimage(797, 434, 42, 45);
		skill5=pickSkillMenu.getSubimage(216, 513, 40, 49);
		skillImg.add(skill0);
		skillImg.add(skill1);
		skillImg.add(skill2);
		skillImg.add(skill3);
		skillImg.add(skill4);
		skillImg.add(skill5);
		bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);  
	}
	
	public void newMenu()
	{
		countSkill1=0;
		countSkill2=0;
	}
	
	public void resetMenu() {
		
		countSkill1=0;
		countSkill2=0;
		//bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        
       
        
	}
	private void drawString(Graphics2D g2, String text, int x, int y){
        for(String str : text.split("\n"))
            g2.drawString(str, x, y+=g2.getFontMetrics().getHeight());
    }
	
	
public void Update(){
        
}

public void drawIcon(int x,int y, int w, int h,String c, Graphics2D g2) {
	int index = gamePanel.getInputManager().getForIcon(c);
	if (index!=-1)
	{
		g2.drawImage(skillImg.get(index),x, y, w, h, null);
	}
}
public void Render(){

    Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
    Graphics g = bufferedImage.getGraphics();
    
    if(g2!=null){
    	g2.setColor(Color.YELLOW);
    	g2.drawImage(pickSkillMenu, 0, 0, GameFrame.SCREEN_WIDTH-18, GameFrame.SCREEN_HEIGHT-38, null);
    	int toadoX1 = 187 + (currentChoose1%5)*130;
    	int toadoY1 = 360 + (currentChoose1/5)*74;
    	
    	int toadoX2 = 187 + (currentChoose2%5)*130-2;
    	int toadoY2 = 360 + (currentChoose2/5)*74-2;
    	g2.drawImage(title1, toadoX1, toadoY1, null); // dau tien ben trai
    	
    	g2.drawImage(title2, toadoX2, toadoY2, null); //hang 1 thu 2
    	
    	drawIcon ( 16,124,32,32,"R",g2);
    	drawIcon ( 64,125,32,32,"T",g2);
    	drawIcon ( 112,125,32,32,"Y",g2);
    	
    	drawIcon ( 846,126,32,32,"U",g2);
    	drawIcon ( 894,126,32,32,"I",g2);
    	drawIcon ( 940,126,32,32,"O",g2);
        }
        

    }

public void increaseChoose1(boolean doc) {
	if(doc==false) {
		currentChoose1++;
		if(currentChoose1>=maxSkill) currentChoose1=0;
	}
	else if(doc==true) {
		if(currentChoose1+5>=maxSkill)
			currentChoose1 = currentChoose1 % 5;
		else currentChoose1=currentChoose1+5;
		
	}
}
public void decreaseChoose1(boolean doc) {
	if(doc==false) {
		currentChoose1--;
		if(currentChoose1<0) currentChoose1=maxSkill-1;
	}
	else if(doc==true) {
		if(currentChoose1-5<0) {
			int temp = currentChoose1 % 5;
			if(maxSkill%5<temp) {
				currentChoose1 = (maxSkill/5-1)*5+temp; 
			}
			else currentChoose1 = (maxSkill/5)*5+temp;
		}
		else currentChoose1=currentChoose1-5;
		
	}
}
public void increaseChoose2(boolean doc) {
	if(doc==false) {
		currentChoose2++;
		if(currentChoose2>=maxSkill) currentChoose2=0;
	}
	else if(doc==true) {
		if(currentChoose2+5>maxSkill)
			currentChoose2 = currentChoose2 % 5;
		else currentChoose2=currentChoose2+5;
		
	}
}
public void decreaseChoose2(boolean doc) {
	if(doc==false) {
		currentChoose2--;
		if(currentChoose2<0) currentChoose2=maxSkill-1;
	}
	else if(doc==true) {
		if(currentChoose2-5<0) {
			int temp = currentChoose2 % 5;
			if(maxSkill%5<temp) {
				currentChoose2 = (maxSkill/5-1)*5+temp; 
			}
			else currentChoose2 = (maxSkill/5)*5+temp;
		}
		else currentChoose2=currentChoose2-5;
		
	}
}

public void press() {
	if(gamePanel.getInputManager().checkSkill()==true) {
		
		gamePanel.getGameWorld().resetMap();
		gamePanel.getGameWorld().newGame();
		gamePanel.setPanelState(GamePanel.GAMEPLAY);
	}
	else {
		
		if (countSkill1==0) gamePanel.getInputManager().setPress1(currentChoose1,"R");
		else if(countSkill1==1) gamePanel.getInputManager().setPress1(currentChoose1,"T");
		else if(countSkill1==2) gamePanel.getInputManager().setPress1(currentChoose1,"Y");
		if (countSkill2==0) gamePanel.getInputManager().setPress2(currentChoose2,"U");
		else if(countSkill2==1) gamePanel.getInputManager().setPress2(currentChoose2,"I");
		else if(countSkill2==2) gamePanel.getInputManager().setPress2(currentChoose2,"O");
		countSkill1++;
		countSkill2++;
	}
		
}
public BufferedImage getBufferedImage(){
    return bufferedImage;
}
}
