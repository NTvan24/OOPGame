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
	Map<String,Integer> skill = new HashMap<>();
	
	private GameWorld gameWorld;
	private GamePanel gamePanel;
	private PickSkillMenu pickSkillMenu;
	
	public InputManager(GameWorld gameWorld, GamePanel gamePanel, PickSkillMenu pickSkillMenu) {
		this.gameWorld=gameWorld;
		this.gamePanel=gamePanel;
		this.pickSkillMenu=pickSkillMenu;
		/*
		press.put(KeyEvent.VK_R, () -> gameWorld.megaman.cast());
		press.put(KeyEvent.VK_T, () -> gameWorld.megaman.skill1(gameWorld.megaman2));
		press.put(KeyEvent.VK_Y, () -> gameWorld.megaman.skill3(gameWorld.megaman2));
        unPress.put(KeyEvent.VK_R, () -> gameWorld.megaman.unCast(gameWorld.megaman2));
        */
	}
	
	public boolean checkSkill() {
		if(press.get(KeyEvent.VK_R)!=null &&
				press.get(KeyEvent.VK_T)!=null &&
				press.get(KeyEvent.VK_Y)!=null &&
				press.get(KeyEvent.VK_U)!=null &&
				press.get(KeyEvent.VK_I)!=null &&
				press.get(KeyEvent.VK_O)!=null)
		return true;
		else return false;
	}
	
	public void reset() {
		press.clear();
		unPress.clear();
		skill.clear();
	}
	
	public int getForIcon(String c) {
		if(skill.get(c)!=null)
			return skill.get(c);
		else return -1;
	}
	public boolean setPressR(int indexSkill) {
		if (press.get(KeyEvent.VK_R)!=null) return false;
		if (indexSkill==0) {
			press.put(KeyEvent.VK_R, () -> gameWorld.megaman.cast());
			unPress.put(KeyEvent.VK_R, () -> gameWorld.megaman.unCast(gameWorld.megaman2));
			skill.put("R", 0);
		}
		else if (indexSkill==1)
			{
				press.put(KeyEvent.VK_R, () -> gameWorld.megaman.skill1(gameWorld.megaman2));
				skill.put("R", 1);
			}
		else if (indexSkill==2)
			{
				press.put(KeyEvent.VK_R, () -> gameWorld.megaman.skill2(gameWorld.megaman2));
				skill.put("R", 2);
			}
		else if (indexSkill==3)
			{
				press.put(KeyEvent.VK_R, () -> gameWorld.megaman.skill3(gameWorld.megaman2));
				skill.put("R", 3);
			}
		else if (indexSkill==4)
			{
				press.put(KeyEvent.VK_R, () -> gameWorld.megaman.skill4(gameWorld.megaman2));
				skill.put("R", 4);
			}
		else if (indexSkill==5)
			{
				press.put(KeyEvent.VK_R, () -> gameWorld.megaman.skill5(gameWorld.megaman2));
				skill.put("R", 5);
			}
		return true;
	}
	
	public boolean setPressT(int indexSkill) {
		if (press.get(KeyEvent.VK_T)!=null) return false;
		if (indexSkill==0) {
			press.put(KeyEvent.VK_T, () -> gameWorld.megaman.cast());
			unPress.put(KeyEvent.VK_T, () -> gameWorld.megaman.unCast(gameWorld.megaman2));
			skill.put("T", 0);
		}
		else if (indexSkill==1)
			{
				press.put(KeyEvent.VK_T, () -> gameWorld.megaman.skill1(gameWorld.megaman2));
				skill.put("T", 1);
			}
		else if (indexSkill==2)
			{
				press.put(KeyEvent.VK_T, () -> gameWorld.megaman.skill2(gameWorld.megaman2));
				skill.put("T", 2);
			}
		else if (indexSkill==3)
			{
				press.put(KeyEvent.VK_T, () -> gameWorld.megaman.skill3(gameWorld.megaman2));
				skill.put("T", 3);
			}
		else if (indexSkill==4)
			{
				press.put(KeyEvent.VK_T, () -> gameWorld.megaman.skill4(gameWorld.megaman2));
				skill.put("T", 4);
			}
		else if (indexSkill==5)
			{
				press.put(KeyEvent.VK_T, () -> gameWorld.megaman.skill5(gameWorld.megaman2));
				skill.put("T", 5);
			}
		return true;
			
	}
	public boolean setPressY(int indexSkill) {
		if (press.get(KeyEvent.VK_Y)!=null) return false;
		if (indexSkill==0) {
			press.put(KeyEvent.VK_Y, () -> gameWorld.megaman.cast());
			unPress.put(KeyEvent.VK_Y, () -> gameWorld.megaman.unCast(gameWorld.megaman2));
			skill.put("Y", 0);
		}
		else if (indexSkill==1)
			{
				press.put(KeyEvent.VK_Y, () -> gameWorld.megaman.skill1(gameWorld.megaman2));
				skill.put("Y", 1);
			}
		else if (indexSkill==2)
			{
				press.put(KeyEvent.VK_Y, () -> gameWorld.megaman.skill2(gameWorld.megaman2));
				skill.put("Y", 2);
			}
		else if (indexSkill==3)
			{
				press.put(KeyEvent.VK_Y, () -> gameWorld.megaman.skill3(gameWorld.megaman2));
				skill.put("Y", 3);
			}
		else if (indexSkill==4)
			{
				press.put(KeyEvent.VK_Y, () -> gameWorld.megaman.skill4(gameWorld.megaman2));
				skill.put("Y", 4);
			}
		else if (indexSkill==5)
			{
				press.put(KeyEvent.VK_Y, () -> gameWorld.megaman.skill5(gameWorld.megaman2));
				skill.put("Y", 5);
			}
		return true;
			
	}
	public boolean setPress2(int indexSkill,String c) {
		int key = 0;
		if(c=="U") {
			key = KeyEvent.VK_U;
		}
		else if(c=="I") {
			key = KeyEvent.VK_I;
		}else if(c=="O") {
			key = KeyEvent.VK_O;
		}
		if (press.get(key)!=null) return false;
		if (indexSkill==0) {
			press.put(key, () -> gameWorld.megaman2.cast());
			unPress.put(key, () -> gameWorld.megaman2.unCast(gameWorld.megaman));
			skill.put(c, 0);
		}
		else if (indexSkill==1)
			{
				press.put(key, () -> gameWorld.megaman2.skill1(gameWorld.megaman));
				skill.put(c, 1);
			}
		else if (indexSkill==2)
			{
				press.put(key, () -> gameWorld.megaman2.skill2(gameWorld.megaman));
				skill.put(c, 2);
			}
		else if (indexSkill==3)
			{
				press.put(key, () -> gameWorld.megaman2.skill3(gameWorld.megaman));
				skill.put(c, 3);
			}
		else if (indexSkill==4)
			{
				press.put(key, () -> gameWorld.megaman2.skill4(gameWorld.megaman));
				skill.put(c, 4);
			}
		else if (indexSkill==5)
			{
				press.put(key, () -> gameWorld.megaman2.skill5(gameWorld.megaman));
				skill.put(c, 5);
			}
		return true;
	}
	public boolean setPress1(int indexSkill,String c) {
		int key = 0;
		if(c=="R") {
			key = KeyEvent.VK_R;
		}
		else if(c=="T") {
			key = KeyEvent.VK_T;
		}else if(c=="Y") {
			key = KeyEvent.VK_Y;
		}
		if (press.get(key)!=null) return false;
		if (indexSkill==0) {
			press.put(key, () -> gameWorld.megaman.cast());
			unPress.put(key, () -> gameWorld.megaman.unCast(gameWorld.megaman2));
			skill.put(c, 0);
		}
		else if (indexSkill==1)
			{
				press.put(key, () -> gameWorld.megaman.skill1(gameWorld.megaman2));
				skill.put(c, 1);
			}
		else if (indexSkill==2)
			{
				press.put(key, () -> gameWorld.megaman.skill2(gameWorld.megaman2));
				skill.put(c, 2);
			}
		else if (indexSkill==3)
			{
				press.put(key, () -> gameWorld.megaman.skill3(gameWorld.megaman2));
				skill.put(c, 3);
			}
		else if (indexSkill==4)
			{
				press.put(key, () -> gameWorld.megaman.skill4(gameWorld.megaman2));
				skill.put(c, 4);
			}
		else if (indexSkill==5)
			{
				press.put(key, () -> gameWorld.megaman.skill5(gameWorld.megaman2));
				skill.put(c, 5);
			}
		return true;
	}
	public void setPressedButton (int keyCode ){
		switch(keyCode) {
		case KeyEvent.VK_E:
			//gameWorld.megaman.cast();
			gameWorld.megaman.skill5(gameWorld.megaman2);
			break;
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
			else if (gamePanel.getState()==GamePanel.GAMEPLAY)
				gameWorld.megaman.def();
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
			if(gamePanel.getState()==GamePanel.PICKSKILL)
			{
				gameWorld.setMapIndex(gamePanel.getMapCurrent());
				pickSkillMenu.press();
				
			}
			else if(gamePanel.getState()==GamePanel.PICKMAP)
			{
				gameWorld.setMapIndex(gamePanel.getMapCurrent());
				gameWorld.resetMap();
				gameWorld.newGame();
				gamePanel.setPanelState(GamePanel.PICKSKILL);
				pickSkillMenu.resetMenu();
				reset();
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
				gameWorld.megaman2.def();
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
	case KeyEvent.VK_L:
		gameWorld.megaman2.attack();
		break;
	case KeyEvent.VK_K:
		gameWorld.megaman2.dash();
		break;
	case KeyEvent.VK_J:
		gameWorld.megaman2.normalAttack(gameWorld.megaman);
		break;
	case KeyEvent.VK_U:
		if (press.containsKey(KeyEvent.VK_R))
            press.get(KeyEvent.VK_U).run();
		break;
		
		
	case KeyEvent.VK_I:
		if (press.containsKey(KeyEvent.VK_R))
            press.get(KeyEvent.VK_I).run();
		
		break;
	case KeyEvent.VK_O:
		if (press.containsKey(KeyEvent.VK_R))
            press.get(KeyEvent.VK_O).run();
		
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
			
			break;	
		case KeyEvent.VK_T:
			if (unPress.containsKey(KeyEvent.VK_T))
                unPress.get(KeyEvent.VK_T).run();
			
			break;
		case KeyEvent.VK_Y:
			if (unPress.containsKey(KeyEvent.VK_Y))
                unPress.get(KeyEvent.VK_Y).run();
			
			break;
		case KeyEvent.VK_U:
			if (unPress.containsKey(KeyEvent.VK_U))
                unPress.get(KeyEvent.VK_U).run();
			
			break;
		case KeyEvent.VK_I:
			if (unPress.containsKey(KeyEvent.VK_I))
                unPress.get(KeyEvent.VK_I).run();
			
			break;
		case KeyEvent.VK_O:
			if (unPress.containsKey(KeyEvent.VK_O))
                unPress.get(KeyEvent.VK_O).run();
			
			break;
		case KeyEvent.VK_UP:
			
			break;
		
		case KeyEvent.VK_S:
			if (gamePanel.getState()==GamePanel.GAMEPLAY)
				gameWorld.megaman.unDef();
			break;
		case KeyEvent.VK_DOWN:
			gameWorld.megaman2.unDef();			//gameWorld.megaman.standUp();;
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
