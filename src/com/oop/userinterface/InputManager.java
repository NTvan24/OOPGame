package com.oop.userinterface;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.oop.effect.FrameImage;
import com.oop.gameobject.GameWorld;
import com.oop.gameobject.ParticularObject;



public class InputManager {
	
	
	private GameWorld gameWorld;
	private GamePanel gamePanel;
	
	public InputManager(GameWorld gameWorld, GamePanel gamePanel) {
		this.gameWorld=gameWorld;
		this.gamePanel=gamePanel;
	}
	
	public void setPressedButton (int keyCode ){
		switch(keyCode) {
		case KeyEvent.VK_W:
			System.out.println("press up");
			
			break;
		case KeyEvent.VK_G:
			gameWorld.megaman.cast();
			
			break;	
		case KeyEvent.VK_S:
			//gameWorld.megaman.dick();
			
			break;
		case KeyEvent.VK_D:
			gameWorld.megaman.setDirection(ParticularObject.RIGHT_DIR);
			gameWorld.megaman.run();
			
			break;
		case KeyEvent.VK_A:
			gameWorld.megaman.setDirection(ParticularObject.LEFT_DIR);
			gameWorld.megaman.run();
			
			break;
		case KeyEvent.VK_R:
			gameWorld.megaman.meleeAttack(gameWorld.megaman2,120,10,false);
			
			
			break;
		case KeyEvent.VK_ENTER:
			if(gamePanel.getState()==GamePanel.PICKMAP)
			{
				gameWorld.setMapIndex(gamePanel.getMapCurrent());
				gameWorld.resetMap();
				gamePanel.setPanelState(GamePanel.GAMEPLAY);
			}
			else 
			if(gamePanel.getState()==GamePanel.MENU) {
				if(gamePanel.getMenuCurrent()==0) gamePanel.setPanelState(GamePanel.PICKMAP);
			}
			else if(gamePanel.getState()==GamePanel.GAMEPLAY)
			{
				if (gameWorld.state==GameWorld.PAUSEGAME)
				{
					if (gameWorld.getPauseGameChoose()==0)
						gameWorld.switchState(GameWorld.GAMEPLAY);
					else if(gameWorld.getPauseGameChoose()==1)
						gamePanel.setPanelState(GamePanel.MENU);
				}
			}
			
			/*
			if(gameWorld.state == gameWorld.PAUSEGAME) {
				if (gameWorld.previousState==gameWorld.GAMEPLAY)
						gameWorld.switchState(gameWorld.GAMEPLAY);
				else gameWorld.switchState(gameWorld.TUTORIAL);
				
				gameWorld.bgMusic.loop();
				gameWorld.bgMusic.play();
			}
			if(gameWorld.state==gameWorld.TUTORIAL && gameWorld.storyTutorial>=1) {
				if(gameWorld.storyTutorial<=3) {
					gameWorld.storyTutorial++;
					gameWorld.currentSize=1;
					gameWorld.textTutorial = gameWorld.texts1[gameWorld.storyTutorial-1];
				}else {
					gameWorld.switchState(gameWorld.GAMEPLAY);
				}
				
				if (gameWorld.tutorialState == gameWorld.MEETFINALBOSS) {
					gameWorld.switchState(gameWorld.GAMEPLAY);
				}
			}
			*/
			break;
			
		case KeyEvent.VK_SPACE:
			gameWorld.megaman.jump();
			break;
		case KeyEvent.VK_E:
			gameWorld.megaman.attack();;
			break;
		case KeyEvent.VK_F:
			gameWorld.megaman.dash();
			break;
		
	case KeyEvent.VK_UP:
		if(gamePanel.getState()==GamePanel.MENU)
			gamePanel.increaseMenuCurrent();
		else if (gamePanel.getState()==GamePanel.GAMEPLAY) {
			if (gameWorld.state==GameWorld.GAMEPLAY)
				gameWorld.megaman2.jump();
			else if (gameWorld.state==GameWorld.PAUSEGAME)
				gameWorld.incresePausePick();
		}
		
		break;
	case KeyEvent.VK_DOWN:
		if(gamePanel.getState()==GamePanel.MENU)
			gamePanel.decreaseMenuCurrent();
		else if (gamePanel.getState()==GamePanel.GAMEPLAY) {
			if (gameWorld.state==GameWorld.GAMEPLAY) {
				
			}
				
			else if (gameWorld.state==GameWorld.PAUSEGAME)
				gameWorld.decresePausePick();
		}
		
		break;
	case KeyEvent.VK_RIGHT:
		if(gamePanel.getState()==GamePanel.PICKMAP)
			gamePanel.increaseMap();
		else if(gamePanel.getState()==GamePanel.GAMEPLAY) {
			gameWorld.megaman2.setDirection(ParticularObject.RIGHT_DIR);
			gameWorld.megaman2.run();
		}
		else if(gamePanel.getState()==GamePanel.MENU)
			gamePanel.increaseMenuCurrent();
		
		break;
	case KeyEvent.VK_LEFT:
		if(gamePanel.getState()==GamePanel.PICKMAP)
			gamePanel.decreaseMap();
		else if(gamePanel.getState()==GamePanel.GAMEPLAY) {
			gameWorld.megaman2.setDirection(ParticularObject.LEFT_DIR);
			gameWorld.megaman2.run();
		} 
		else if(gamePanel.getState()==GamePanel.MENU)
			gamePanel.decreaseMenuCurrent();
		break;
	case KeyEvent.VK_O:
		gameWorld.megaman2.attack();
		break;
	case KeyEvent.VK_P:
		gameWorld.megaman2.meleeAttack(gameWorld.megaman,120,10,true);;
		break;
	case KeyEvent.VK_ESCAPE:
		if(gamePanel.getState()==GamePanel.PICKMAP)
		{
			gamePanel.setPanelState(GamePanel.MENU);
		}
		else if(gamePanel.getState()==GamePanel.GAMEPLAY)
		{
			gameWorld.switchState(GameWorld.PAUSEGAME);
			//gamePanel.setPanelState(GamePanel.MENU);
		}
	}
		
	}
	public void setReleasedButton (int keyCode ){
		switch(keyCode) {
		case KeyEvent.VK_G:
			gameWorld.megaman.unCast();
			
			break;	
		case KeyEvent.VK_UP:
			
			break;
		case KeyEvent.VK_DOWN:
			
			//gameWorld.megaman.standUp();;
			break;
		case KeyEvent.VK_RIGHT:
			if(gameWorld.megaman2.getSpeedX()>0)
				gameWorld.megaman2.stopRun();
			break;
		case KeyEvent.VK_LEFT:
			if(gameWorld.megaman2.getSpeedX()<0)
				gameWorld.megaman2.stopRun();
			break;
		case KeyEvent.VK_ENTER:
			break;
		case KeyEvent.VK_SPACE:
			break;
		
		case KeyEvent.VK_W:
			System.out.println("press up");
			
			break;
		
		case KeyEvent.VK_D:
			if(gameWorld.megaman.getSpeedX()>0)
				gameWorld.megaman.stopRun();
			
			break;
		case KeyEvent.VK_A:
		
			if(gameWorld.megaman.getSpeedX()<0)
				gameWorld.megaman.stopRun();
			break;
		}
		
	}
}
