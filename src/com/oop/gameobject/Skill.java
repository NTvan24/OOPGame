package com.oop.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Skill extends ParticularObject {
	int dmgdef;
    public Skill( int damage,int dmgdef, GameWorld gameWorld) {
            super(0, 0, 1, 1, 1, 1, gameWorld);
            setDamage(damage);
            this.dmgdef=dmgdef;
    }
    
    public void setRect(Rectangle rect) {
    	setPosX(rect.x+rect.width/2);
    	setPosY(rect.y+rect.height/2);
    	setWidth(rect.width);
    	setHeight(rect.height);
    	
    	
    }
    
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        return getBoundForCollisionWithMap();
    }
    public void reset() {
    	setPosX(0);
    	setPosY(0);
    	setWidth(1);
    	setHeight(1);
    }
    public void Update(){
        //super.Update();
        
        
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object!=null && (object.getState() == ALIVE || object.getState() == NOBEHURT || object.getState() == BEHURT)){
            setState(DEATH);
            object.beHurt(getDamage(),false,true,dmgdef);
            Human human = (Human) object;
            
            if (getDirection()==RIGHT_DIR)
            	human.bounceBack(RIGHT_DIR);
            else if (getDirection()==LEFT_DIR)
            	human.bounceBack(LEFT_DIR);
            
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
