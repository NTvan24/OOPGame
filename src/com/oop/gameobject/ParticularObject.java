package com.oop.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.oop.effect.Animation;



public abstract class ParticularObject extends GameObject {

    public static final int LEAGUE_TEAM = 1;
    public static final int ENEMY_TEAM = 2;
    
    public static final int LEFT_DIR = 0;
    public static final int RIGHT_DIR = 1;

    public static final int ALIVE = 0;
    public static final int BEHURT = 1;
    public static final int FEY = 2; //sap chet
    public static final int DEATH = 3;
    //public static final int NOBEHURT = 4; //bat tu tam thoi sau khi hoisinh
    public static final int FREEZE = 4;
    private int state = ALIVE;
    
    private int width;
    private int height;
    private float mass;
    private float speedX;
    private float speedY;
    private int blood;
    
    private boolean bleeding = false;
    
    
    private long startTimeBleeding;
    private int numberBleeding;
    private long timeEachBleeding;
    private int hpEachBleeding=3;
    
    private int damage;
    
    private int direction;
    
    protected Animation behurtForwardAnim, behurtBackAnim;
    
    private int teamType;
    
    private long startTimeFreeze;
    private long timeForFreeze;

    public ParticularObject(float x, float y, int width, int height, float mass, int blood, GameWorld gameWorld){

        // posX and posY are the middle coordinate of the object
        super(x, y, gameWorld);
        setWidth(width);
        setHeight(height);
        setMass(mass);
        setBlood(blood);
        
        direction = RIGHT_DIR;

    }
    
    

	public boolean isBleeding() {
		return bleeding;
	}

	public void setBleeding(boolean bleeding) {
		this.bleeding = bleeding;
	}

	
    
    public long getStartTimeFreeze() {
		return startTimeFreeze;
	}

	public void setStartTimeFreeze(long startTimeFreeze) {
		this.startTimeFreeze = startTimeFreeze;
	}

	public long getTimeForFreeze() {
		return timeForFreeze;
	}

	public void setTimeForFreeze(long timeForFreeze) {
		this.timeForFreeze = timeForFreeze;
	}

	public void setState(int state){
        this.state = state;
    }
    
    public int getState(){
        return state;
    }
    
    public void setDamage(int damage){
            this.damage = damage;
    }

    public int getDamage(){
            return damage;
    }

    
    public void setTeamType(int team){
        teamType = team;
    }
    
    public int getTeamType(){
        return teamType;
    }
    
    public void setMass(float mass){
        this.mass = mass;
    }

    public float getMass(){
            return mass;
    }

    public void setSpeedX(float speedX){
        this.speedX = speedX;
    }

    public float getSpeedX(){
        return speedX;
    }

    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }

    public float getSpeedY(){
        return speedY;
    }

    public void setBlood(int blood){
        if(blood>=0)
                this.blood = blood;
        else this.blood = 0;
    }

    public int getBlood(){
        return blood;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getWidth(){
        return width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }
    
    public void setDirection(int dir){
        direction = dir;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public abstract void attack();
    
    
    public boolean isObjectOutOfCameraView(){
        if(getPosX() - getGameWorld().camera.getPosX() > getGameWorld().camera.getWidthView() ||
                getPosX() - getGameWorld().camera.getPosX() < -50
            ||getPosY() - getGameWorld().camera.getPosY() > getGameWorld().camera.getHeightView()
                    ||getPosY() - getGameWorld().camera.getPosY() < -50)
            return true;
        else return false;
    }
    
    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }

    public void beHurt(int damgeEat, boolean bleed){
        setBlood(getBlood() - damgeEat);
        state = BEHURT;
        if (bleed == true) 
        	{
        		System.out.println("set bleed");
        		bleeding=true;
        		numberBleeding = 5 ;
        		startTimeBleeding = System.nanoTime();
        	}
        hurtingCallback();
    }
    
    
    
    public void pushBack(int damage, int push) {
    	
    }
    
    @Override
    public void Update(){
        switch(state){
            case ALIVE:
                
                // note: SET DAMAGE FOR OBJECT NO DAMAGE
                ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
                if(object!=null){
                    
                    
                    if(object.getDamage() > 0){

                        // switch state to fey if object die
                        
                        
                        System.out.println("eat damage.... from collision with enemy........ "+object.getDamage());
                        beHurt(object.getDamage(),false);
                    }
                    
                }
                if(bleeding==true && numberBleeding>0 && System.nanoTime()-startTimeBleeding > 1000*1000000)
                {
                	setBlood(getBlood() - hpEachBleeding);
                	numberBleeding--;
                	startTimeBleeding=System.nanoTime();
                }
                if(numberBleeding ==0 ) bleeding=false;
                
                break;
                
            case BEHURT:
                if(behurtBackAnim == null){
                    state = FREEZE;
                    startTimeFreeze = System.nanoTime();
                    if(getBlood() == 0)
                            state = FEY;
                    
                } else {
                    behurtForwardAnim.Update(System.nanoTime());
                    state = FREEZE;
                    startTimeFreeze = System.nanoTime();
                    if(behurtForwardAnim.isLastFrame()){
                        behurtForwardAnim.reset();
                        
                        if(getBlood() == 0)
                            state = FEY;
                        
                    }
                }
                
                break;
                
            case FEY:
                
                state = DEATH;
                
                break;
            
            case DEATH:
                
                
                break;
                
            case FREEZE:
            	
            	if(behurtForwardAnim.isLastFrame()){
                    behurtForwardAnim.reset();
                    
                    if(getBlood() == 0)
                        state = FEY;
                    
                }
                if(System.nanoTime() - startTimeFreeze > 750*1000000)
                    state = ALIVE;
                
                
                break;
                
        }
        
    }
    
    public void drawBoundForCollisionWithMap(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithMap();
        g2.setColor(Color.BLUE);
        g2.drawRect(rect.x , rect.y , rect.width, rect.height);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public void drawBoundForCollisionWithEnemy(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setColor(Color.RED);
        g2.drawRect(rect.x  , rect.y  , rect.width, rect.height);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();

    public abstract void draw(Graphics2D g2);
    
    public void hurtingCallback(){};
	
}

