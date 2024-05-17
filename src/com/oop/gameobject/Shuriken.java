package com.oop.gameobject;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.oop.effect.Animation;
import com.oop.effect.CacheDataLoader;

public class Shuriken extends Bullet {
private Animation forwardShurikenAnim, backShurikenAnim;
    
    public Shuriken(float x, float y, GameWorld gameWorld) {
        super(x, y, 60, 30, 1.0f, 10, gameWorld);
        forwardShurikenAnim = CacheDataLoader.getInstance().getAnimation("shuriken");
        backShurikenAnim = CacheDataLoader.getInstance().getAnimation("shuriken");
        backShurikenAnim.flipAllImage();
    }

    
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
            // TODO Auto-generated method stub
        if(getSpeedX() > 0){
            
                
            forwardShurikenAnim.Update(System.nanoTime());
            forwardShurikenAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
        }else{
            
            backShurikenAnim.Update(System.nanoTime());
            backShurikenAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
        }
        //drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
            // TODO Auto-generated method stub
        
        setPosX(getPosX() + getSpeedX());
        //System.out.print(getSpeedX());
        super.Update();
        
    }

    @Override
    public void attack() {}
}
