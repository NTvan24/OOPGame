package com.oop.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Skill extends ParticularObject {

    public Skill( int damage, GameWorld gameWorld) {
            super(0, 0, 1, 1, 1, 1, gameWorld);
            setDamage(damage);
    }
    
    public void setRect(Rectangle rect) {
    	setPosX(rect.x+rect.width/2);
    	setPosY(rect.y+rect.height/2);
    	setWidth(rect.width);
    	setHeight(rect.height);
    	
    	System.out.println(getTeamType()+"----");
    }
    
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        return getBoundForCollisionWithMap();
    }
    public void Update(){
        //super.Update();
        
        
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object!=null && object.getState() == ALIVE){
            setState(DEATH);
            object.beHurt(getDamage(),false);
            Human human = (Human) object;
            
            if (getDirection()==RIGHT_DIR)
            	human.bounceBack(RIGHT_DIR);
            else if (getDirection()==LEFT_DIR)
            	human.bounceBack(LEFT_DIR);
            System.out.println("skill set behurt for enemy");
        }
        
    }

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
    
}
