package com.oop.gameobject;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.oop.effect.CacheDataLoader;
import com.oop.effect.FrameImage;
import com.oop.userinterface.GameFrame;

public class PickSkillMenu {
	
	
	private BufferedImage bufferedImage;

    FrameImage avatar = CacheDataLoader.getInstance().getFrameImage("avatar");

    BufferedImage pickSkillMenu,title1,title2;
    
    private int testAni=1;
    private int currentChoose1=0;
    private int currentChoose2=4;
    private int maxSkill=11;
    
	public AudioClip bgMusic;
	
	public PickSkillMenu() {
		
		
		try {
			pickSkillMenu=ImageIO.read(new File("data/pickskill.png"));
			title1=ImageIO.read(new File("data/pickskilltitle.png"));
			title2=ImageIO.read(new File("data/titlegreen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);  
	}
	
	public void newMenu()
	{
		
	}
	
	public void resetMenu() {
		

		//bufferedImage = new BufferedImage(GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        
       
        
	}
	private void drawString(Graphics2D g2, String text, int x, int y){
        for(String str : text.split("\n"))
            g2.drawString(str, x, y+=g2.getFontMetrics().getHeight());
    }
	
	
public void Update(){
        
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
		if(currentChoose2-5<1) {
			int temp = currentChoose2 % 5;
			if(maxSkill%5<temp) {
				currentChoose2 = (maxSkill/5-1)*5+temp; 
			}
			else currentChoose2 = (maxSkill/5)*5+temp;
		}
		else currentChoose2=currentChoose2-5;
		
	}
}
public BufferedImage getBufferedImage(){
    return bufferedImage;
}
}
