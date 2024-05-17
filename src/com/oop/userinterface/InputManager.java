package com.oop.userinterface;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.oop.effect.FrameImage;
import com.oop.gameobject.GameWorld;
import com.oop.gameobject.ParticularObject;
import com.oop.gameobject.PickSkillMenu;



public class InputManager {
	
	
	Map<Integer, Runnable> press = new HashMap<>();
	Map<Integer, Runnable> unPress = new HashMap<>();
	
	
	private GameWorld gameWorld;
	private GamePanel gamePanel;
	private PickSkillMenu pickSkillMenu;
	
	public InputManager(GameWorld gameWorld, GamePanel gamePanel, PickSkillMenu pickSkillMenu) {
		this.gameWorld=gameWorld;
		this.gamePanel=gamePanel;
		this.pickSkillMenu=pickSkillMenu;
		
		press.put(KeyEvent.VK_R, () -> gameWorld.megaman.cast());
		press.put(KeyEvent.VK_T, () -> gameWorld.megaman.skill2(gameWorld.megaman2));
		press.put(KeyEvent.VK_Y, () -> gameWorld.megaman.skill4(gameWorld.megaman2));
        unPress.put(KeyEvent.VK_R, () -> gameWorld.megaman.unCast(gameWorld.megaman2));
	}
	
	public void setPressR(String str) {
		if (str=="cast")
			press.put(KeyEvent.VK_R, () -> gameWorld.megaman.cast());
		else if (str=="skill1")
			press.put(KeyEvent.VK_R, () -> gameWorld.megaman.skill1(gameWorld.megaman2));
			
	}
	
	public void setPressT(String str) {
		if (str=="cast")
			press.put(KeyEvent.VK_T, () -> gameWorld.megaman.cast());
		else if (str=="skill1")
			press.put(KeyEvent.VK_T, () -> gameWorld.megaman.skill1(gameWorld.megaman2));
			
	}
	
	public void setPressedButton (int keyCode ){
		switch(keyCode) {
		
		case KeyEvent.VK_R:
			//gameWorld.megaman.cast();
			if (press.containsKey(KeyEvent.VK_R))
                press.get(KeyEvent.VK_R).run();
			break;
		case KeyEvent.VK_T:
			if (press.containsKey(KeyEvent.VK_T))
                press.get(KeyEvent.VK_T).run();
			
			break;
		case KeyEvent.VK_Y:
			if (press.containsKey(KeyEvent.VK_Y))
                press.get(KeyEvent.VK_Y).run();
			
			break;
		case KeyEvent.VK_W:
			//System.out.println("press up");
			if(gamePanel.getState()==GamePanel.PICKSKILL)
				pickSkillMenu.decreaseChoose1(true);
			break;
		case KeyEvent.VK_S:
			//gameWorld.megaman.dick();
			if(gamePanel.getState()==GamePanel.PICKSKILL)
				pickSkillMenu.increaseChoose1(true);
			break;
		case KeyEvent.VK_D:
			//gameWorld.megaman.setDirection(ParticularObject.RIGHT_DIR);
			if(gamePanel.getState()==GamePanel.PICKSKILL)
				pickSkillMenu.increaseChoose1(false);
			else {
				gameWorld.megaman.run(ParticularObject.RIGHT_DIR);
			}
			
			break;
		case KeyEvent.VK_A:
			//gameWorld.megaman.setDirection(ParticularObject.LEFT_DIR);
			if(gamePanel.getState()==GamePanel.PICKSKILL)
				pickSkillMenu.decreaseChoose1(false);
			else {
				gameWorld.megaman.run(ParticularObject.LEFT_DIR);
			}
			break;
		case KeyEvent.VK_F:
			gameWorld.megaman.normalAttack(gameWorld.megaman2);
			
			
			break;
		case KeyEvent.VK_ENTER:
			if(gamePanel.getState()==GamePanel.PICKMAP)
			{
				gameWorld.setMapIndex(gamePanel.getMapCurrent());
				gameWorld.resetMap();
				gameWorld.newGame();
				gamePanel.setPanelState(GamePanel.GAMEPLAY);
			}
			else 
			if(gamePanel.getState()==GamePanel.MENU) {
				if(gamePanel.getMenuCurrent()==0) gamePanel.setPanelState(GamePanel.PICKMAP);
			}
			else if(gamePanel.getState()==GamePanel.GAMEPLAY)
			{
				if (gameWorld.state==GameWorld.ENDGAME) {
					gameWorld.resetMap();
					gameWorld.switchState(GameWorld.GAMEPLAY);
				}
					
				else if (gameWorld.state==GameWorld.PAUSEGAME)
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
		case KeyEvent.VK_H:
			gameWorld.megaman.attack();;
			break;
		case KeyEvent.VK_G:
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
		else if (gamePanel.getState()==GamePanel.PICKSKILL) {
			pickSkillMenu.decreaseChoose2(true);
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
		else if (gamePanel.getState()==GamePanel.PICKSKILL) {
			pickSkillMenu.increaseChoose2(true);
		}
		
		break;
	case KeyEvent.VK_RIGHT:
		if(gamePanel.getState()==GamePanel.PICKMAP)
			gamePanel.increaseMap();
		else if(gamePanel.getState()==GamePanel.GAMEPLAY) {
			//gameWorld.megaman2.setDirection(ParticularObject.RIGHT_DIR);
			gameWorld.megaman2.run(ParticularObject.RIGHT_DIR);
		}
		else if(gamePanel.getState()==GamePanel.MENU)
			gamePanel.increaseMenuCurrent();
		else if (gamePanel.getState()==GamePanel.PICKSKILL) {
			pickSkillMenu.increaseChoose2(false);
		}
		break;
	case KeyEvent.VK_LEFT:
		if(gamePanel.getState()==GamePanel.PICKMAP)
			gamePanel.decreaseMap();
		else if(gamePanel.getState()==GamePanel.GAMEPLAY) {
			//gameWorld.megaman2.setDirection(ParticularObject.LEFT_DIR);
			gameWorld.megaman2.run(ParticularObject.LEFT_DIR);
		} 
		else if(gamePanel.getState()==GamePanel.MENU)
			gamePanel.decreaseMenuCurrent();
		else if (gamePanel.getState()==GamePanel.PICKSKILL) {
			pickSkillMenu.decreaseChoose2(false);
		}
		break;
	case KeyEvent.VK_O:
		gameWorld.megaman2.attack();
		break;
	case KeyEvent.VK_P:
		gameWorld.megaman2.normalAttack(gameWorld.megaman);
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
		case KeyEvent.VK_R:
			if (unPress.containsKey(KeyEvent.VK_R))
                unPress.get(KeyEvent.VK_R).run();
			//gameWorld.megaman.unCast();
			//gameWorld.megaman.meleeAttack(gameWorld.megaman2,120,10,false);
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
