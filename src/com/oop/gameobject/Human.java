package com.oop.gameobject;

import java.awt.Rectangle;


public abstract class Human extends ParticularObject{
	private boolean isDash=false;
    private boolean isJumping;
    
    private boolean isBounceBack=false;
    private long startBounceBack;
    
    private boolean isLanding;

    public Human(float x, float y, int width, int height, float mass, int blood, GameWorld gameWorld) {
        super(x, y, width, height, mass, blood, gameWorld);
        setState(ALIVE);
    }
    
    public boolean isDash() {
		return isDash;
	}

	public void setDash(boolean isDash) {
		this.isDash = isDash;
	}

	public abstract void run(int dir);
    
    public abstract void jump();
    
    
    
    
    
    public abstract void stopRun();

    public boolean getIsJumping() {
        return isJumping;
    }
    
    public void setIsLanding(boolean b){
        isLanding = b;
    }
    
    public boolean getIsLanding(){
        return isLanding;
    }
    
    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

 
    
    
    public boolean isBounceBack() {
		return isBounceBack;
	}

	public void setBounceBack(boolean isBounceBack) {
		this.isBounceBack = isBounceBack;
	}

	public void bounceBack(int dir)
    {
    	if(getState()!=ParticularObject.INVICIBLE)
    	{
    		isBounceBack=true;
    	
	    	isJumping=true;
	    	startBounceBack=System.nanoTime();
	    	setSpeedY(-3);
	    	if (dir==LEFT_DIR) setSpeedX(-2);
	    	else setSpeedX(2);
    	}
    	
    }
    @Override
    public void Update(){
        
        super.Update();
        
        if(getState() == ALIVE || getState() == FREEZE|| getState() == BEHURT || getState() == NOBEHURT){
        	
        	
            if(!isLanding){
            	if(isDash) {
            		if(super.getDirection()==LEFT_DIR)
            			setPosX(getPosX() - 12);
            		if(super.getDirection()==RIGHT_DIR)
            			setPosX(getPosX() + 12);
            	}
                setPosX(getPosX() + getSpeedX());


                if(getDirection() == LEFT_DIR && 
                        getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectLeftWall = getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width + getWidth()/2);

                }
                if(getDirection() == RIGHT_DIR && 
                        getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null){

                    Rectangle rectRightWall = getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth()/2);

                }



                /**
                 * Codes below check the posY of megaMan
                 */
                // plus (+2) because we must check below the character when he's speedY = 0

                Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
                boundForCollisionWithMapFuture.y += (getSpeedY()!=0?getSpeedY(): 2);
                Rectangle rectLand = getGameWorld().physicalMap.haveCollisionWithLand(boundForCollisionWithMapFuture);
                
                Rectangle rectTop = getGameWorld().physicalMap.haveCollisionWithTop(boundForCollisionWithMapFuture);
                
                if(rectTop !=null){
                   
                    setSpeedY(0);
                    setPosY(rectTop.y + getGameWorld().physicalMap.getTileSize() + getHeight()/2);
                    
                }else if(rectLand != null){
                    setIsJumping(false);
                    if(isBounceBack==true) {
                    	setSpeedX(0);
                    	isBounceBack=false;
                    }
                    if(getSpeedY() > 0) setIsLanding(true);
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight()/2 - 1);
                }else{
                    setIsJumping(true);
                    setSpeedY(getSpeedY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                }
            }
        }
    }
    
}
