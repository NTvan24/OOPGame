package com.oop.gameobject;

import java.awt.Graphics2D;



public abstract class Bullet extends ParticularObject {
	
    public Bullet(float x, float y, int width, int height, float mass, int damage, GameWorld gameWorld) {
            super(x, y, width, height, mass, 1, gameWorld);
            setDamage(damage);
            
    }
    
    public abstract void draw(Graphics2D g2d);

    public void Update(){
        super.Update();
        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());
        
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object!=null && (object.getState() == ALIVE || object.getState()==NOBEHURT)){
            setBlood(0);
            setState(DEATH);
            boolean shot = object.beHurt(getDamage(),false);
            if(object.getTeamType()==ParticularObject.LEAGUE_TEAM&&shot==true) {
            	gameWorld.megaman2.manaIncrease(5);
            }
            else if(object.getTeamType()==ParticularObject.ENEMY_TEAM&&shot==true) {
            	gameWorld.megaman.manaIncrease(5);
            }
           
        }
        
    }
    
}
