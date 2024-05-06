package com.oop.gameobject;

import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.oop.effect.Animation;
import com.oop.effect.CacheDataLoader;



public class Megaman extends Human {

    public static final int RUNSPEED = 3;
    
    private Animation runForwardAnim, runBackAnim, runShootingForwarAnim, runShootingBackAnim;
    private Animation idleForwardAnim, idleBackAnim, idleShootingForwardAnim, idleShootingBackAnim;
    private Animation dickForwardAnim, dickBackAnim;
    private Animation flyForwardAnim, flyBackAnim, flyShootingForwardAnim, flyShootingBackAnim;
    private Animation landingForwardAnim, landingBackAnim;
    private Animation kickAttackAnim, kickAttackBackAnim;
    private Animation punchAttackAnim, punchAttackBackAnim;
    private Animation kneeStrikeAttackAnim, kneeStrikeAttackBackAnim;
    private Animation dashAnim, dashBackAnim;
    private Animation climWallForward, climWallBack;
    private Animation bounceForward,bounceBack;
    private Animation castLow,castLowBack;
    private Animation castHigh,castHighBack;
    private Animation ultiLow,ultiLowBack;
    private Animation ultiHigh,ultiHighBack;
    
    
    private long lastShootingTime;
    private long lastMeleeAttack;
    private long lastDash;
    private long lastFreeze;
    private long lastCast;
    private long startUlti;
    
    private boolean isShooting = false;
    private boolean isMeleeAttack=false;
    private boolean isFreeze=false;
    private boolean isCastingLow=false;
    private boolean isCastingHigh=false;
    private boolean isUltiLow =false;
    private boolean isUltiHigh =false;
    
    
    private int meleeAttackCombo=-1;
    
    
    private AudioClip hurtingSound;
    private AudioClip shooting1;
    
    public Megaman(float x, float y, GameWorld gameWorld, int team) {
        super(x, y, 70, 100, 0.1f, 100, gameWorld);
        if(team==ParticularObject.LEAGUE_TEAM)
        {
        shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
        hurtingSound = CacheDataLoader.getInstance().getSound("megamanhurt");
        
        setTeamType(team);

        setTimeForFreeze(750*1000000);
        
        dashAnim = CacheDataLoader.getInstance().getAnimation("dash");
        dashBackAnim=CacheDataLoader.getInstance().getAnimation("dash");
        dashBackAnim.flipAllImage();
        
        runForwardAnim = CacheDataLoader.getInstance().getAnimation("st_run");
        runBackAnim= CacheDataLoader.getInstance().getAnimation("st_run");
        runBackAnim.flipAllImage(); 
        
        idleForwardAnim = CacheDataLoader.getInstance().getAnimation("idle");
        idleBackAnim = CacheDataLoader.getInstance().getAnimation("idle");
        idleBackAnim.flipAllImage();
        
        
        
        dickForwardAnim = CacheDataLoader.getInstance().getAnimation("dick");
        dickBackAnim = CacheDataLoader.getInstance().getAnimation("dick");
        dickBackAnim.flipAllImage();
        
        flyForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
        flyForwardAnim.setIsRepeated(false);
        flyBackAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
        flyBackAnim.setIsRepeated(false);
        flyBackAnim.flipAllImage();
        
        landingForwardAnim = CacheDataLoader.getInstance().getAnimation("landing");
        landingBackAnim = CacheDataLoader.getInstance().getAnimation("landing");
        landingBackAnim.flipAllImage();
        
        climWallBack = CacheDataLoader.getInstance().getAnimation("clim_wall");
        climWallForward = CacheDataLoader.getInstance().getAnimation("clim_wall");
        climWallForward.flipAllImage();
        
        behurtForwardAnim = CacheDataLoader.getInstance().getAnimation("behurt");
        behurtBackAnim = CacheDataLoader.getInstance().getAnimation("behurt");
        behurtBackAnim.flipAllImage();
        
        idleShootingForwardAnim = CacheDataLoader.getInstance().getAnimation("idleshoot");
        idleShootingBackAnim = CacheDataLoader.getInstance().getAnimation("idleshoot");
        idleShootingBackAnim.flipAllImage();
        
        runShootingForwarAnim = CacheDataLoader.getInstance().getAnimation("runshoot");
        runShootingBackAnim = CacheDataLoader.getInstance().getAnimation("runshoot");
        runShootingBackAnim.flipAllImage();
        
        flyShootingForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingupshoot");
        flyShootingBackAnim = CacheDataLoader.getInstance().getAnimation("flyingupshoot");
        flyShootingBackAnim.flipAllImage();
        
        kickAttackAnim = CacheDataLoader.getInstance().getAnimation("st_kick");
        kickAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_kick");
        kickAttackBackAnim.flipAllImage();
        
        punchAttackAnim = CacheDataLoader.getInstance().getAnimation("st_punch");
        punchAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_punch");
        punchAttackBackAnim.flipAllImage();
        
        kneeStrikeAttackAnim = CacheDataLoader.getInstance().getAnimation("st_jumpkick");
        kneeStrikeAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_jumpkick");
        kneeStrikeAttackBackAnim.flipAllImage();
        
        bounceForward = CacheDataLoader.getInstance().getAnimation("bounce_back");
        bounceBack= CacheDataLoader.getInstance().getAnimation("bounce_back");
        bounceBack.flipAllImage();
        
        castLow = CacheDataLoader.getInstance().getAnimation("cast_low");
        castLowBack = CacheDataLoader.getInstance().getAnimation("cast_low");
        castLowBack.flipAllImage();
        
        castHigh = CacheDataLoader.getInstance().getAnimation("cast_high");
        castHighBack = CacheDataLoader.getInstance().getAnimation("cast_high");
        castHighBack.flipAllImage();
        
        ultiLow = CacheDataLoader.getInstance().getAnimation("ulti1");
        ultiLowBack = CacheDataLoader.getInstance().getAnimation("ulti1");
        ultiLowBack.flipAllImage();
        ultiLow.setIsRepeated(false);
        ultiLowBack.setIsRepeated(false);
        
        
        ultiHigh = CacheDataLoader.getInstance().getAnimation("ulti2");
        ultiHighBack = CacheDataLoader.getInstance().getAnimation("ulti2");
        ultiHighBack.flipAllImage();
        ultiHigh.setIsRepeated(false);
        ultiHighBack.setIsRepeated(false);
        
        }
        else if(team==ParticularObject.ENEMY_TEAM)
        {
        	shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
            hurtingSound = CacheDataLoader.getInstance().getSound("megamanhurt");
            
            setTeamType(team);

            setTimeForFreeze(2000*1000000);
            
            
            runForwardAnim = CacheDataLoader.getInstance().getAnimation("st_run");
            runBackAnim= CacheDataLoader.getInstance().getAnimation("st_run");
            runBackAnim.flipAllImage(); 
            
            idleForwardAnim = CacheDataLoader.getInstance().getAnimation("idle");
            idleBackAnim = CacheDataLoader.getInstance().getAnimation("idle");
            idleBackAnim.flipAllImage();
            
            
            
            dickForwardAnim = CacheDataLoader.getInstance().getAnimation("dick");
            dickBackAnim = CacheDataLoader.getInstance().getAnimation("dick");
            dickBackAnim.flipAllImage();
            
            flyForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
            flyForwardAnim.setIsRepeated(false);
            flyBackAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
            flyBackAnim.setIsRepeated(false);
            flyBackAnim.flipAllImage();
            
            landingForwardAnim = CacheDataLoader.getInstance().getAnimation("landing");
            landingBackAnim = CacheDataLoader.getInstance().getAnimation("landing");
            landingBackAnim.flipAllImage();
            
            climWallBack = CacheDataLoader.getInstance().getAnimation("clim_wall");
            climWallForward = CacheDataLoader.getInstance().getAnimation("clim_wall");
            climWallForward.flipAllImage();
            
            behurtForwardAnim = CacheDataLoader.getInstance().getAnimation("behurt");
            behurtBackAnim = CacheDataLoader.getInstance().getAnimation("behurt");
            behurtBackAnim.flipAllImage();
            
            idleShootingForwardAnim = CacheDataLoader.getInstance().getAnimation("idleshoot");
            idleShootingBackAnim = CacheDataLoader.getInstance().getAnimation("idleshoot");
            idleShootingBackAnim.flipAllImage();
            
            runShootingForwarAnim = CacheDataLoader.getInstance().getAnimation("runshoot");
            runShootingBackAnim = CacheDataLoader.getInstance().getAnimation("runshoot");
            runShootingBackAnim.flipAllImage();
            
            flyShootingForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingupshoot");
            flyShootingBackAnim = CacheDataLoader.getInstance().getAnimation("flyingupshoot");
            flyShootingBackAnim.flipAllImage();
            
            kickAttackAnim = CacheDataLoader.getInstance().getAnimation("st_kick");
            kickAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_kick");
            kickAttackBackAnim.flipAllImage();
            
            punchAttackAnim = CacheDataLoader.getInstance().getAnimation("st_punch");
            punchAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_punch");
            punchAttackBackAnim.flipAllImage();
            
            kneeStrikeAttackAnim = CacheDataLoader.getInstance().getAnimation("st_jumpkick");
            kneeStrikeAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_jumpkick");
            kneeStrikeAttackBackAnim.flipAllImage();
            
            bounceForward = CacheDataLoader.getInstance().getAnimation("bounce_back");
            bounceBack= CacheDataLoader.getInstance().getAnimation("bounce_back");
            bounceBack.flipAllImage();
            
            castLow = CacheDataLoader.getInstance().getAnimation("cast_low");
            castLowBack = CacheDataLoader.getInstance().getAnimation("cast_low");
            castLowBack.flipAllImage();
            
            castHigh = CacheDataLoader.getInstance().getAnimation("cast_high");
            castHighBack = CacheDataLoader.getInstance().getAnimation("cast_high");
            castHighBack.flipAllImage();
            
        }
    }

    public boolean isFreeze() {
		return isFreeze;
	}

	public void setFreeze(boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	@Override
    public void Update() {

        super.Update();
        if(isFreeze) {
        	if(System.nanoTime() - lastFreeze > 750*1000000){
                isFreeze = false;
            }
        }
        if(isShooting){
            if(System.nanoTime() - lastShootingTime > 500*1000000){
                isShooting = false;
            }
        }
        if (isMeleeAttack) {
        	if(System.nanoTime() - lastMeleeAttack > 500*1000000){
        		isMeleeAttack = false;
            } 
        }
        if (isDash()) {
        	if(System.nanoTime() - lastDash > 250*1000000)
        		setDash(false);
        }
        if(getIsLanding()){
            landingBackAnim.Update(System.nanoTime());
            if(landingBackAnim.isLastFrame()) {
                setIsLanding(false);
                landingBackAnim.reset();
                runForwardAnim.reset();
                runBackAnim.reset();
            }
        }
        if(isCastingLow==true&&isCastingHigh==false) {
        	if(System.nanoTime() - lastCast > 1000*1000000)
        		{
        			isCastingLow=false;
        			isCastingHigh=true;
        		}
        }
        if(isUltiLow==true)
        	if(System.nanoTime() - startUlti > 350*1000000)
    		{
        		isUltiHigh=false;
    			isUltiLow=false;
    			
    		}
        if(isUltiHigh==true)
        	if(System.nanoTime() - startUlti > 450*1000000)
    		{
        		isUltiLow=false;
    			isUltiHigh=false;
    			
    		}
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        Rectangle rect = getBoundForCollisionWithMap();
        
        if(getIsDicking()){
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 20;
            rect.width = 44;
            rect.height = 65;
        }else{
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 40;
            rect.width = 44;
            rect.height = 80;
        }
        
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        
        switch(getState()){
        
            case ALIVE:
            
                if(getState() == FREEZE && (System.nanoTime()/10000000)%2!=1)
                {
                    //System.out.println("Plash...");
                }else{
                    
                    if(getIsLanding()){

                        if(getDirection() == RIGHT_DIR){
                            landingForwardAnim.setCurrentFrame(landingBackAnim.getCurrentFrame());
                            landingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - landingForwardAnim.getCurrentImage().getHeight()/2),1,
                                    g2);
                        }else{
                            landingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - landingBackAnim.getCurrentImage().getHeight()/2),1,
                                    g2);
                        }

                    }else if(getIsJumping()){

                        if(getDirection() == RIGHT_DIR){
                            flyForwardAnim.Update(System.nanoTime());
                            if(isShooting){
                                flyShootingForwardAnim.setCurrentFrame(flyForwardAnim.getCurrentFrame());
                                flyShootingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()) + 10, (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                            }else
                                flyForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                        }else{
                            flyBackAnim.Update(System.nanoTime());
                            if(isShooting){
                                flyShootingBackAnim.setCurrentFrame(flyBackAnim.getCurrentFrame());
                                flyShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()) - 10, (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                            }else
                            flyBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                        }

                    }else if(getIsDicking()){

                        if(getDirection() == RIGHT_DIR){
                            dickForwardAnim.Update(System.nanoTime());
                            dickForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - dickForwardAnim.getCurrentImage().getHeight()/2),
                                    1,g2);
                        }else{
                            dickBackAnim.Update(System.nanoTime());
                            dickBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - dickBackAnim.getCurrentImage().getHeight()/2),
                                    1,g2);
                        }

                    }else{
                    	if(isDash()) {
                    		if(getDirection()==LEFT_DIR) {
                            	dashBackAnim.Update(System.nanoTime());
                            	dashBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                            }else {
                            	dashAnim.Update(System.nanoTime());
                            	dashAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                            }
                        }else
                        if(getSpeedX() > 0){
                        	runForwardAnim.Update(System.nanoTime());
                        	
                            if(isShooting){
                                runShootingForwarAnim.setCurrentFrame(runForwardAnim.getCurrentFrame() - 1);
                                runShootingForwarAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 1,g2);
                            }else
                                runForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                            if(runForwardAnim.getCurrentFrame() == 1) runForwardAnim.setIgnoreFrame(0);
                        }else if(getSpeedX() < 0){
                            runBackAnim.Update(System.nanoTime());
                            
                            if(isShooting){
                                runShootingBackAnim.setCurrentFrame(runBackAnim.getCurrentFrame() - 1);
                                runShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                            }else
                            	
                                runBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                            if(runBackAnim.getCurrentFrame() == 1) runBackAnim.setIgnoreFrame(0);
                        }else{
                            if(getDirection() == RIGHT_DIR){
                            	if (isMeleeAttack) {
                            		System.out.print("\nmeelee FORWARD");
                            		if(meleeAttackCombo==0) {
                            		kickAttackAnim.Update(System.nanoTime());
                            		kickAttackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5, g2);
                            		}
                            		else if(meleeAttackCombo==1) {
                            			punchAttackAnim.Update(System.nanoTime());
                                		punchAttackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5, g2);
                            		}
                            		else if(meleeAttackCombo==2) {
                            			kneeStrikeAttackAnim.Update(System.nanoTime());
                            			kneeStrikeAttackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY()-30,5, g2);
                            		}
                            	}
                            	else if(isShooting){
                                    idleShootingForwardAnim.Update(System.nanoTime());
                                    idleShootingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 1,g2);
                                }else if(isCastingLow) {
                                		castLow.Update(System.nanoTime());
                                		castLow.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                		if(castLow.getCurrentFrame() == 1) castLow.setIgnoreFrame(0);
                                	}
                                	else if (isCastingHigh) {
                                		castHigh.Update(System.nanoTime());
                                		castHigh.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                		if(castHigh.getCurrentFrame() == 1) castHigh.setIgnoreFrame(0);
                                	}
                                	else if (isUltiLow){
                                		System.out.println("isultilow");
                                		ultiLow.Update(System.nanoTime());
                                		ultiLow.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                	}
                                	else if (isUltiHigh)
                                	{
                                		System.out.println("isultihig");
                                		ultiHigh.Update(System.nanoTime());
                                		ultiHigh.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                	}
                                	else {
                                    idleForwardAnim.Update(System.nanoTime());
                                    idleForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5, g2);
                                	}
                                
                            }
                            else{
                            	if (isMeleeAttack) {
                            		System.out.print("\nmeelee BACK");
                            		if(meleeAttackCombo==0) {
                                		kickAttackBackAnim.Update(System.nanoTime());
                                		kickAttackBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5, g2);
                                		}
                                		else if(meleeAttackCombo==1) {
                                			punchAttackBackAnim.Update(System.nanoTime());
                                    		punchAttackBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5, g2);
                                		}
                                		else if(meleeAttackCombo==2) {
                                			kneeStrikeAttackBackAnim.Update(System.nanoTime());
                                			kneeStrikeAttackBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY()-30,5, g2);
                                		}
                            	}
                            	else
                                if(isShooting){
                                    idleShootingBackAnim.Update(System.nanoTime());
                                    idleShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                }else if(isCastingLow) {
                                		castLowBack.Update(System.nanoTime());
                                		castLowBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                		if(castLowBack.getCurrentFrame() == 1) castLowBack.setIgnoreFrame(0);
                                }
                                	else if (isCastingHigh) {
                                		castHighBack.Update(System.nanoTime());
                                		castHighBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                		if(castHighBack.getCurrentFrame() == 1) castHighBack.setIgnoreFrame(0);
                                	}else if (isUltiLow){
                                		System.out.println("isultilow:"+isUltiLow);
                                		ultiLowBack.Update(System.nanoTime());
                                		ultiLowBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                	}
                                	else if (isUltiHigh)
                                	{
                                		System.out.println("isultihig:"+isUltiHigh);
                                		ultiHighBack.Update(System.nanoTime());
                                		ultiHighBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                	}
                                	
                                	else {
                                    idleBackAnim.Update(System.nanoTime());
                                    idleBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5, g2);
                                	}
                                }
                            }
                        }            
                    }
                
     
                break;
            case FREEZE	:
            case BEHURT:
            	if(isBounceBack()) {
                	if(getDirection() == RIGHT_DIR){
                            bounceForward.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                    }else{
                        bounceBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                    }
                }
                else {
                	if(getDirection() == RIGHT_DIR){
                		behurtForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                	}else{
                		behurtBackAnim.setCurrentFrame(behurtForwardAnim.getCurrentFrame());
                		behurtBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                	}
                }
                break;
             
            case FEY:
                
                break;

        }
        
        //drawBoundForCollisionWithMap(g2);
        //drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void run() {
        if(getDirection() == LEFT_DIR)
            setSpeedX(-3);
        else setSpeedX(3);
    }

    @Override
    public void jump() {

        if(!getIsJumping()){
            setIsJumping(true);
            setSpeedY(-5.0f);           
            flyBackAnim.reset();
            flyForwardAnim.reset();
        }
        // for clim wall
        else{
            Rectangle rectRightWall = getBoundForCollisionWithMap();
            rectRightWall.x += 1;
            Rectangle rectLeftWall = getBoundForCollisionWithMap();
            rectLeftWall.x -= 1;
            
            if(getGameWorld().physicalMap.haveCollisionWithRightWall(rectRightWall)!=null && getSpeedX() > 0){
                setSpeedY(-5.0f);
                //setSpeedX(-1);
                flyBackAnim.reset();
                flyForwardAnim.reset();
                //setDirection(LEFT_DIR);
            }else if(getGameWorld().physicalMap.haveCollisionWithLeftWall(rectLeftWall)!=null && getSpeedX() < 0){
                setSpeedY(-5.0f);
                //setSpeedX(1);
                flyBackAnim.reset();
                flyForwardAnim.reset();
                //setDirection(RIGHT_DIR);
            }
                
        }
    }

    @Override
    public void dick() {
        if(!getIsJumping())
            setIsDicking(true);
    }

    @Override
    public void standUp() {
        setIsDicking(false);
        idleForwardAnim.reset();
        idleBackAnim.reset();
        dickForwardAnim.reset();
        dickBackAnim.reset();
    }

    @Override
    public void stopRun() {
        setSpeedX(0);
        runForwardAnim.reset();
        runBackAnim.reset();
        runForwardAnim.unIgnoreFrame(0);
        runBackAnim.unIgnoreFrame(0);
    }
    
    public void cast() {
    	if(isCastingLow==false&&isCastingHigh==false) {
    		castLow.unIgnoreFrame(0);
    		castLowBack.unIgnoreFrame(0);
    		castHigh.unIgnoreFrame(0);
    		castHighBack.unIgnoreFrame(0);
    		castLow.reset();
    		castHigh.reset();
    		castLowBack.reset();
    		castHighBack.reset();
    		
    		ultiLow.reset();
    		ultiLowBack.reset();
    		ultiHigh.reset();
    		ultiHighBack.reset();
    		
    		isCastingLow=true;
    		lastCast=System.nanoTime();
    	}
    	
    }
    public void unCast() {
    	if(isCastingLow==true)
    	{	startUlti=System.nanoTime();
    		isCastingLow=false;
    		isCastingHigh=false;
    		castSkillLow(); 
    	}
    	else if (isCastingHigh==true)
    	{
    		startUlti=System.nanoTime();
    		isCastingLow=false;
    		isCastingHigh=false;
    		castSkillHigh();
    	}
    	
    }
    public void castSkillLow() {
    	isUltiLow=true;
    	startUlti=System.nanoTime();
    }
    
    public void castSkillHigh() {
    	isUltiHigh=true;
    	startUlti=System.nanoTime();
    }
    @Override
    public void attack() {
    	
        if(!isShooting && !getIsDicking() && !isMeleeAttack){
            
            shooting1.play();
            
            Bullet bullet = new BlueFire(getPosX(), getPosY(), getGameWorld());
            if(getDirection() == LEFT_DIR) {
                bullet.setSpeedX(-10);
                bullet.setPosX(bullet.getPosX() - 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() - 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }else {
                bullet.setSpeedX(10);
                bullet.setPosX(bullet.getPosX() + 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() + 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }
            if(getIsJumping())
                bullet.setPosY(bullet.getPosY() - 20);
            
            bullet.setTeamType(getTeamType());
            getGameWorld().bulletManager.addObject(bullet);
            
            lastShootingTime = System.nanoTime();
            isShooting = true;
            
        }
    
    }
    
    public void meleeAttack(Megaman enemy, double range, int dmg, boolean bleed) {
    	if (isMeleeAttack==false) {
    	if (getDirection()==RIGHT_DIR) {
    		//meleeAttackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
    		if (enemy.getPosX()-getPosX()<range && enemy.getPosY()-getPosY()<70 && enemy.getPosY()-getPosY()>-70 &&enemy.getPosX()-getPosX()>=0) 
    			{
    				enemy.beHurt(dmg,bleed);
    				if(meleeAttackCombo==1) enemy.bounceBack(RIGHT_DIR);
    			}
    	}
    	if (getDirection()==LEFT_DIR) {
    		if (enemy.getPosX()-getPosX()>-range && enemy.getPosY()-getPosY()<70 && enemy.getPosY()-getPosY()>-70 &&enemy.getPosX()-getPosX()<=0) 
    			{
    				enemy.beHurt(dmg,bleed);
    				if(meleeAttackCombo==1) enemy.bounceBack(LEFT_DIR);
    			}
    	}
    	isMeleeAttack=true;
    	meleeAttackCombo++;
    	if(meleeAttackCombo>2)  meleeAttackCombo=0;
    	lastMeleeAttack= System.nanoTime();
    	}
    }
    
    public void dash() {
    	if(System.nanoTime()-lastDash>1000*1000000)
    	if(!getIsJumping()) {
    	setDash(true);
    	lastDash=System.nanoTime();
    	}
    }
    @Override
    public void hurtingCallback(){
        System.out.println("Call back hurting");
        hurtingSound.play();
    }

}
