package com.oop.gameobject;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import com.oop.effect.Animation;
import com.oop.effect.CacheDataLoader;
import com.oop.userinterface.GameFrame;



public class Megaman extends Human {

    public static final int RUNSPEED = 3;
    private Megaman enemy;
    
    
    private Animation runForwardAnim, runBackAnim;
    private Animation idleForwardAnim, idleBackAnim;
    //private Animation dickForwardAnim, dickBackAnim;
    private Animation flyForwardAnim, flyBackAnim;
    private Animation shotAnim, shotBackAnim;
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
    private Animation skill1,skill1Back;
    private Animation skill2,skill2Back;
    private Animation skill3,skill3Back;
    private Animation skill4,skill4Back;
    private Animation skill5,skill5Back;
    private Animation def,defBack;
    
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
    private boolean isDef =false;
    
    private Skill skillObject1;
    private Skill skillObject2;
    private Skill skillObject3;
    private Skill skillObject4;
    private Skill skillObject5;
    
    private Map<String, Boolean> megamanState = new HashMap<>();
    private Map<String, Long> startTime = new HashMap<>();
    
    private int meleeAttackCombo=-1;
    
    
    private AudioClip hurtingSound;
    private AudioClip shooting1;
    private AudioClip punchSound,bgMusic;
    
    public Megaman(float x, float y, GameWorld gameWorld, int team) {
        super(x, y, 70, 100, 0.1f, 100, gameWorld);
        
        
        skillObject1 = new Skill(35,10, getGameWorld());
        skillObject1.setTeamType(team);
        skillObject2 = new Skill(20,5, getGameWorld());
        skillObject2.setTeamType(team);
        skillObject3 = new Skill(30,5, getGameWorld());
        skillObject3.setTeamType(team);
        skillObject4 = new Skill(15,3, getGameWorld());
        skillObject4.setTeamType(team);
        skillObject5 = new Skill(50,20, getGameWorld());
        skillObject5.setTeamType(team);
        
        megamanState.put("skill1", false);
        megamanState.put("skill2", false);
        megamanState.put("skill3", false);
        megamanState.put("skill4", false);
        megamanState.put("skill5", false);
        bgMusic = CacheDataLoader.getInstance().getSound("bgmusic");
        bgMusic.play();
        if(team==ParticularObject.LEAGUE_TEAM)
        {
        shooting1 = CacheDataLoader.getInstance().getSound("shuriken");
        hurtingSound = CacheDataLoader.getInstance().getSound("getpunch");
        punchSound = CacheDataLoader.getInstance().getSound("punch");
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
        
        shotAnim=CacheDataLoader.getInstance().getAnimation("shot");
        shotBackAnim=CacheDataLoader.getInstance().getAnimation("shot");
        shotBackAnim.flipAllImage();
        shotAnim.setIsRepeated(false);
        shotBackAnim.setIsRepeated(false);
        
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
        
        skill1 = CacheDataLoader.getInstance().getAnimation("skill1");
        skill1Back = CacheDataLoader.getInstance().getAnimation("skill1");
        skill1Back.flipAllImage();
        skill1.setIsRepeated(false);
        skill1Back.setIsRepeated(false);
        
        skill2 = CacheDataLoader.getInstance().getAnimation("skill2");
        skill2Back = CacheDataLoader.getInstance().getAnimation("skill2");
        skill2Back.flipAllImage();
        skill2.setIsRepeated(false);
        skill2Back.setIsRepeated(false);
        
        skill3 = CacheDataLoader.getInstance().getAnimation("skill3");
        skill3Back = CacheDataLoader.getInstance().getAnimation("skill3");
        skill3Back.flipAllImage();
        skill3.setIsRepeated(false);
        skill3Back.setIsRepeated(false);
        
        skill4 = CacheDataLoader.getInstance().getAnimation("skill4");
        skill4Back = CacheDataLoader.getInstance().getAnimation("skill4");
        skill4Back.flipAllImage();
        skill4.setIsRepeated(false);
        skill4Back.setIsRepeated(false);
        
        skill5 = CacheDataLoader.getInstance().getAnimation("skill5");
        skill5Back = CacheDataLoader.getInstance().getAnimation("skill5");
        skill5Back.flipAllImage();
        skill5.setIsRepeated(false);
        skill5Back.setIsRepeated(false);
        
        def = CacheDataLoader.getInstance().getAnimation("def");
        defBack = CacheDataLoader.getInstance().getAnimation("def");
        defBack.flipAllImage();
        }
        else if(team==ParticularObject.ENEMY_TEAM)
        {
        	shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
            hurtingSound = CacheDataLoader.getInstance().getSound("getpunch");
            punchSound = CacheDataLoader.getInstance().getSound("punch");
            setTeamType(team);

            setTimeForFreeze(2000*1000000);
            
            
            runForwardAnim = CacheDataLoader.getInstance().getAnimation("st_run_g");
            runBackAnim= CacheDataLoader.getInstance().getAnimation("st_run_g");
            runBackAnim.flipAllImage(); 
            
            idleForwardAnim = CacheDataLoader.getInstance().getAnimation("idle_g");
            idleBackAnim = CacheDataLoader.getInstance().getAnimation("idle_g");
            idleBackAnim.flipAllImage();
            
            dashAnim = CacheDataLoader.getInstance().getAnimation("dash_g");
            dashBackAnim=CacheDataLoader.getInstance().getAnimation("dash_g");
            dashBackAnim.flipAllImage();
            
           
            
            flyForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingup_g");
            flyForwardAnim.setIsRepeated(false);
            flyBackAnim = CacheDataLoader.getInstance().getAnimation("flyingup_g");
            flyBackAnim.setIsRepeated(false);
            flyBackAnim.flipAllImage();
            
            landingForwardAnim = CacheDataLoader.getInstance().getAnimation("landing_g");
            landingBackAnim = CacheDataLoader.getInstance().getAnimation("landing_g");
            landingBackAnim.flipAllImage();
            
            climWallBack = CacheDataLoader.getInstance().getAnimation("clim_wall");
            climWallForward = CacheDataLoader.getInstance().getAnimation("clim_wall");
            climWallForward.flipAllImage();
            
            behurtForwardAnim = CacheDataLoader.getInstance().getAnimation("behurt_g");
            behurtBackAnim = CacheDataLoader.getInstance().getAnimation("behurt_g");
            behurtBackAnim.flipAllImage();
            
            shotAnim=CacheDataLoader.getInstance().getAnimation("shot_g");
            shotBackAnim=CacheDataLoader.getInstance().getAnimation("shot_g");
            shotBackAnim.flipAllImage();
            shotAnim.setIsRepeated(false);
            shotBackAnim.setIsRepeated(false);
            
            kickAttackAnim = CacheDataLoader.getInstance().getAnimation("st_kick_g");
            kickAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_kick_g");
            kickAttackBackAnim.flipAllImage();
            
            punchAttackAnim = CacheDataLoader.getInstance().getAnimation("st_punch_g");
            punchAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_punch_g");
            punchAttackBackAnim.flipAllImage();
            
            kneeStrikeAttackAnim = CacheDataLoader.getInstance().getAnimation("st_jumpkick_g");
            kneeStrikeAttackBackAnim = CacheDataLoader.getInstance().getAnimation("st_jumpkick_g");
            kneeStrikeAttackBackAnim.flipAllImage();
            
            bounceForward = CacheDataLoader.getInstance().getAnimation("bounce_back_g");
            bounceBack= CacheDataLoader.getInstance().getAnimation("bounce_back_g");
            bounceBack.flipAllImage();
            
            castLow = CacheDataLoader.getInstance().getAnimation("cast_low_g");
            castLowBack = CacheDataLoader.getInstance().getAnimation("cast_low_g");
            castLowBack.flipAllImage();
            
            castHigh = CacheDataLoader.getInstance().getAnimation("cast_high_g");
            castHighBack = CacheDataLoader.getInstance().getAnimation("cast_high_g");
            castHighBack.flipAllImage();
            
            ultiLow = CacheDataLoader.getInstance().getAnimation("ulti1_g");
            ultiLowBack = CacheDataLoader.getInstance().getAnimation("ulti1_g");
            ultiLowBack.flipAllImage();
            ultiLow.setIsRepeated(false);
            ultiLowBack.setIsRepeated(false);
            
            
            ultiHigh = CacheDataLoader.getInstance().getAnimation("ulti2_g");
            ultiHighBack = CacheDataLoader.getInstance().getAnimation("ulti2_g");
            ultiHighBack.flipAllImage();
            ultiHigh.setIsRepeated(false);
            ultiHighBack.setIsRepeated(false);
            
            skill1 = CacheDataLoader.getInstance().getAnimation("skill1_g");
            skill1Back = CacheDataLoader.getInstance().getAnimation("skill1_g");
            skill1Back.flipAllImage();
            skill1.setIsRepeated(false);
            skill1Back.setIsRepeated(false);
            
            skill2 = CacheDataLoader.getInstance().getAnimation("skill2_g");
            skill2Back = CacheDataLoader.getInstance().getAnimation("skill2_g");
            skill2Back.flipAllImage();
            skill2.setIsRepeated(false);
            skill2Back.setIsRepeated(false);
            
            skill3 = CacheDataLoader.getInstance().getAnimation("skill3_g");
            skill3Back = CacheDataLoader.getInstance().getAnimation("skill3_g");
            skill3Back.flipAllImage();
            skill3.setIsRepeated(false);
            skill3Back.setIsRepeated(false);
            
            skill4 = CacheDataLoader.getInstance().getAnimation("skill4_g");
            skill4Back = CacheDataLoader.getInstance().getAnimation("skill4_g");
            skill4Back.flipAllImage();
            skill4.setIsRepeated(false);
            skill4Back.setIsRepeated(false);
            
            skill5 = CacheDataLoader.getInstance().getAnimation("skill5");
            skill5Back = CacheDataLoader.getInstance().getAnimation("skill5");
            skill5Back.flipAllImage();
            skill5.setIsRepeated(false);
            skill5Back.setIsRepeated(false);
            
            def = CacheDataLoader.getInstance().getAnimation("def_g");
            defBack = CacheDataLoader.getInstance().getAnimation("def_g");
            defBack.flipAllImage();
        }
    }

    public Megaman getEnemy() {
		return enemy;
	}

	public void setEnemy(Megaman enemy) {
		this.enemy = enemy;
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
        if(getPosY()>GameFrame.SCREEN_HEIGHT+30)
        	setState(ParticularObject.DEATH);
        if(isFreeze) {
        	if(System.nanoTime() - lastFreeze > 750*1000000){
                isFreeze = false;
            }
        }
        if(isShooting){
            if(System.nanoTime() - lastShootingTime > 400*1000000){
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
        if(megamanState.get("skill1")==true)
        	if(System.nanoTime()-startTime.get("skill1")>1200*1000000)
        	{
        		megamanState.put("skill1", false);
        		skillObject1.setState(ParticularObject.DEATH);
        	}
        if(megamanState.get("skill5")==true)
        	if(System.nanoTime()-startTime.get("skill5")>1500*1000000)
        	{
        		megamanState.put("skill5", false);
        		skillObject5.setState(ParticularObject.DEATH);
        	}
        if(megamanState.get("skill2")==true)
        	if(System.nanoTime()-startTime.get("skill2")>800*1000000)
        	{
        		megamanState.put("skill2", false);
        		skillObject2.setState(ParticularObject.DEATH);
        	}
        
        if(megamanState.get("skill3")==true) {
        	if(getDirection()==RIGHT_DIR)
        		setSpeedX(5);
        	else setSpeedX(-5);
        	if(System.nanoTime()-startTime.get("skill3")>600*1000000)
        	{
        		megamanState.put("skill3", false);
        		setSpeedX(0);
        		skillObject3.setState(ParticularObject.DEATH);
        	}
        }
        if(megamanState.get("skill4")==true) {
        	if(getDirection()==RIGHT_DIR)
        		setSpeedX(5);
        	else setSpeedX(-5);
        	if(System.nanoTime()-startTime.get("skill4")>450*1000000)
        	{
        		megamanState.put("skill4", false);
        		setSpeedX(0);
        		skillObject4.setState(ParticularObject.DEATH);
        	}
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        Rectangle rect = getBoundForCollisionWithMap();
        
        if(true){
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 20;
            rect.width = 44;
            rect.height = 65;
        }
        
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        
        switch(getState()){
        	case NOBEHURT:
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

                    }
                    else if(getIsJumping()){

                        if(getDirection() == RIGHT_DIR){
                            flyForwardAnim.Update(System.nanoTime());
                            if(isShooting){
                                shotAnim.Update(System.nanoTime());
                                shotAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()) + 10, (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                            }else
                                flyForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                        }else{
                            flyBackAnim.Update(System.nanoTime());
                            if(isShooting){
                                shotBackAnim.Update(System.nanoTime());
                                shotBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()) - 10, (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                            }else
                            flyBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                        }

                    }
                    else {
                    	if(isDef==true) {
                    		if (getDirection()==LEFT_DIR) {
                    			defBack.Update(System.nanoTime());
                    			defBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                    		} else if (getDirection()==RIGHT_DIR) {
                    			def.Update(System.nanoTime());
                    			def.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                        	}
                    	}
                    	else if(isDash()) {
                    		if(getDirection()==LEFT_DIR) {
                            	dashBackAnim.Update(System.nanoTime());
                            	dashBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                            }else {
                            	dashAnim.Update(System.nanoTime());
                            	dashAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                            }
                        }else
                        if(getSpeedX() > 0){
                        	if(megamanState.get("skill3")==true) {
                            	skill3.Update(System.nanoTime());
                            	skill3.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2,skillObject3);
                            	
                            }else if(megamanState.get("skill4")==true) {
                                	skill4.Update(System.nanoTime());
                                	skill4.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2,skillObject4);
                                	
                            }else{
                        	runForwardAnim.Update(System.nanoTime());
                        	
                            if(isShooting){
                                shotAnim.Update(System.nanoTime());
                                shotAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 1,g2);
                            }else
                                runForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 5,6,g2);
                            if(runForwardAnim.getCurrentFrame() == 1) runForwardAnim.setIgnoreFrame(0);
                            }
                        }else if(getSpeedX() < 0){
                        	if(megamanState.get("skill3")==true) {
                            	skill3Back.Update(System.nanoTime());
                            	skill3Back.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2,skillObject3);
                            	
                            }
                        	else if(megamanState.get("skill4")==true) {
                            	skill4Back.Update(System.nanoTime());
                            	skill4Back.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2,skillObject4);
                            	
                            }else{
                            runBackAnim.Update(System.nanoTime());
                            
                            if(isShooting){
                                shotBackAnim.Update(System.nanoTime());
                                shotBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                            }else
                            	
                                runBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                            if(runBackAnim.getCurrentFrame() == 1) runBackAnim.setIgnoreFrame(0);
                        	}
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
                                    shotAnim.Update(System.nanoTime());
                                    shotAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), 1,g2);
                                }else if(isCastingLow) {
                                		castLow.Update(System.nanoTime());
                                		castLow.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                		if(castLow.getCurrentFrame() == 1) castLow.setIgnoreFrame(0);
                                	}
                                else if (isCastingHigh) {
                                	castHigh.Update(System.nanoTime());
                                	castHigh.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                	if(castHigh.getCurrentFrame() == 1) castHigh.setIgnoreFrame(0);
                                }
                                else if (isUltiLow){
                                	//System.out.println("isultilow");
                                	ultiLow.Update(System.nanoTime());
                                	ultiLow.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                }
                                else if (isUltiHigh)
                                {
                                	//System.out.println("isultihig");
                                	ultiHigh.Update(System.nanoTime());
                                	ultiHigh.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                }
                                else if(megamanState.get("skill1")==true) {
                                	skill1.Update(System.nanoTime());
                                	skill1.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2,skillObject1);
                                }
                                else if(megamanState.get("skill2")==true) {
                                	skill2.Update(System.nanoTime());
                                	skill2.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2,skillObject2);
                                }
                                else if(megamanState.get("skill3")==true) {
                                	skill3.Update(System.nanoTime());
                                	skill3.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                }else if(megamanState.get("skill5")==true) {
                                	skill5.Update(System.nanoTime());
                                	if (skill5.getCurrentFrame()==7) {
                                		skill5.draw((int) (getPosX() - getGameWorld().camera.getPosX()+200), (int) getPosY() - (int) getGameWorld().camera.getPosY()-30,5,6, g2,skillObject5,true);
                                	}
                                		else skill5.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY()-30,5,6, g2);
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
                                    shotBackAnim.Update(System.nanoTime());
                                    shotBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),1, g2);
                                }else if(isCastingLow) {
                                		castLowBack.Update(System.nanoTime());
                                		castLowBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                		if(castLowBack.getCurrentFrame() == 1) castLowBack.setIgnoreFrame(0);
                                }
                                else if (isCastingHigh) {
                                		castHighBack.Update(System.nanoTime());
                                		castHighBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                		if(castHighBack.getCurrentFrame() == 1) castHighBack.setIgnoreFrame(0);
                                }else if (isUltiLow){
                                		//System.out.println("isultilow:"+isUltiLow);
                                		ultiLowBack.Update(System.nanoTime());
                                		ultiLowBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                }
                                else if (isUltiHigh)
                                {
                                		//System.out.println("isultihig:"+isUltiHigh);
                                		ultiHighBack.Update(System.nanoTime());
                                		ultiHighBack.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                }
                                else if(megamanState.get("skill1")==true) {
                                	skill1Back.Update(System.nanoTime());
                                	skill1Back.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2,skillObject1);
                                	
                                }
                                else if(megamanState.get("skill2")==true) {
                                	skill2Back.Update(System.nanoTime());
                                	skill2Back.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2,skillObject2);
                                	
                                }
                                else if(megamanState.get("skill3")==true) {
                                	skill3Back.Update(System.nanoTime());
                                	skill3Back.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(),5,6, g2);
                                	
                                }else if(megamanState.get("skill5")==true) {
                                	skill5Back.Update(System.nanoTime());
                                	
                                	if (skill5Back.getCurrentFrame()==7) {
                                		skill5Back.draw((int) (getPosX() - getGameWorld().camera.getPosX()-200), (int) getPosY() - (int) getGameWorld().camera.getPosY()-30,5,6, g2,skillObject5,true);
                                	}
                                		else skill5Back.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY()-30,5,6, g2,skillObject5);
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
    public void run(int dir) {
    	
    	if(freeze()==false) {
    		if(dir==ParticularObject.RIGHT_DIR)
    			setDirection(ParticularObject.RIGHT_DIR);
    		else setDirection(ParticularObject.LEFT_DIR);
    		
	        if(getDirection() == LEFT_DIR)
	            setSpeedX(-3);
	        else setSpeedX(3);
    	}
    }

    @Override
    public void jump() {
    	
	        if(!getIsJumping()&&freeze()==false){
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
    public void stopRun() {
        setSpeedX(0);
        runForwardAnim.reset();
        runBackAnim.reset();
        runForwardAnim.unIgnoreFrame(0);
        runBackAnim.unIgnoreFrame(0);
    }
    
    
    
    public void cast() {
    	if(freeze()==false) {
    		stopRun();
	    	if(isCastingLow==false&&isCastingHigh==false&&manaDecrease(33)) {
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
    }
    public void unCast(Megaman enemy) {
    	int range=180;
    	int dmg=10;
    	boolean bleed=false;
    	
    	if(isCastingLow==true) {
    		range=110;
    		dmg = 15;
    		meleeAttack(enemy, range, dmg, bleed, false);
    		startUlti=System.nanoTime();
    		isCastingLow=false;
    		isCastingHigh=false;
    		castSkillLow(); 
    	}
    	else if (isCastingHigh==true)
    	{
    		range=160;
    		dmg = 35;
    		meleeAttack(enemy, range, dmg, bleed, true);
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
    	
        if(!isShooting && !isMeleeAttack && freeze()==false){
            
            shooting1.play();
            
            shotAnim.reset();
            shotBackAnim.reset();
           Shuriken shuriken = new Shuriken(getPosX(), getPosY(), getGameWorld());
            if(getDirection() == LEFT_DIR) {
            	shuriken.setSpeedX(-3);
            	shuriken.setPosX(shuriken.getPosX() - 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                	shuriken.setPosX(shuriken.getPosX() - 10);
                	shuriken.setPosY(shuriken.getPosY() - 5);
                }
            }else {
            	shuriken.setSpeedX(3);
            	shuriken.setPosX(shuriken.getPosX() + 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                	shuriken.setPosX(shuriken.getPosX() + 10);
                	shuriken.setPosY(shuriken.getPosY() - 5);
                }
            }
            if(getIsJumping())
            	shuriken.setPosY(shuriken.getPosY() - 20);
            
            shuriken.setTeamType(getTeamType());
            getGameWorld().bulletManager.addObject(shuriken);
            
            lastShootingTime = System.nanoTime();
            isShooting = true;
            
        }
    
    }
    
    public void normalAttack(Megaman enemy) {
    	int range=120;
    	int dmg=10;
    	boolean bleed=false;
    	
    	if(freeze()==false) {
    		stopRun();
	    	if (isMeleeAttack==false) {
	    		punchSound.play();
	    	
	    		//meleeAttackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
	    		/*
	    		if (enemy.getPosX()-getPosX()<range && enemy.getPosY()-getPosY()<70 && enemy.getPosY()-getPosY()>-70 &&enemy.getPosX()-getPosX()>=0) 
	    			{
	    				enemy.beHurt(dmg,bleed);
	    				if(meleeAttackCombo==1) enemy.bounceBack(RIGHT_DIR);
	    			}
	    		*/
		    	if(meleeAttackCombo==1) this.meleeAttack(enemy, range, 5, bleed, true);
		    	else this.meleeAttack(enemy, range, 3, bleed, false);
		    	
		    	isMeleeAttack=true;
		    	meleeAttackCombo++;
		    	if(meleeAttackCombo>2)  meleeAttackCombo=0;
		    	lastMeleeAttack= System.nanoTime();
	    	}
    	}
    }
    public void meleeAttack(Megaman enemy, double range, int dmg, boolean bleed, boolean bounce) {
    	if (getDirection()==RIGHT_DIR) {
    		//meleeAttackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
    		if (enemy.getPosX()-getPosX()<range && enemy.getPosY()-getPosY()<70 && enemy.getPosY()-getPosY()>-70 &&enemy.getPosX()-getPosX()>=0) 
    			{
    				
    				boolean denied = enemy.beHurt(dmg,bleed);
    				if(denied == true) manaIncrease(5);
    				if(bounce==true) enemy.bounceBack(RIGHT_DIR);
    			}
    	}
    	if (getDirection()==LEFT_DIR) {
    		if (enemy.getPosX()-getPosX()>-range && enemy.getPosY()-getPosY()<70 && enemy.getPosY()-getPosY()>-70 &&enemy.getPosX()-getPosX()<=0) 
    			{
    				
    				boolean denied = enemy.beHurt(dmg,bleed);
    				if(denied == true) manaIncrease(5);
    				if(bounce==true) enemy.bounceBack(LEFT_DIR);
    			}
    	}
    }
    
    
    public void dash() {
    	if(freeze()==false)
    		if(System.nanoTime()-lastDash>1000*1000000)
    				if(!getIsJumping()) {
    					setDash(true);
    					lastDash=System.nanoTime();
    				}
    }
    public boolean freeze() {
    	
    	if(		isFreeze==true||
    			isCastingLow==true||
    			isCastingHigh==true||
    			isUltiLow==true||
    			isUltiHigh==true||
    			isShooting == true||
        		isMeleeAttack==true||
        		isDef == true||
        		getState()==FREEZE||
        		getState()==BEHURT||
        		megamanState.get("skill1") == true ||
        		megamanState.get("skill2") == true ||
        		megamanState.get("skill3") == true ||
        		megamanState.get("skill4") == true 
    			)
    		return true;
    	else return false;
    }
    
    @Override
    public void hurtingCallback(){
        System.out.println("Call back hurting");
        hurtingSound.play();
    }
    public void skill1(Megaman enemy) {
    	//skill ban ra con rong nuoc
    	if(megamanState.get("skill1")==false&&freeze()==false&&manaDecrease(66)) {
    		stopRun();
    		megamanState.put("skill1", true);
    		startTime.put("skill1", System.nanoTime());
    		skill1.reset();
    		skill1Back.reset();
    		/*
    		int range=210;
    		int dmg = 20;
    		meleeAttack(enemy, range, dmg, false, true);
    		*/
    		
    		skillObject1.setState(ParticularObject.ALIVE);
    		skillObject1.setDirection(getDirection());
            getGameWorld().bulletManager.addObject(skillObject1);
    	}
    }
    
    public void skill3(Megaman enemy) {
    	//skill dam ra mauxanh
    	if(megamanState.get("skill3")==false&&freeze()==false&&manaDecrease(66)) {
    		stopRun();
    		megamanState.put("skill3", true);
    		startTime.put("skill3", System.nanoTime());
    		skill3.reset();
    		skill3Back.reset();
    		
    		
    		skillObject3.setState(ParticularObject.ALIVE);
    		skillObject3.setDirection(getDirection());
            getGameWorld().bulletManager.addObject(skillObject3);
    	}
    }
    
    public void skill2(Megaman enemy) {
    	//skill dam ra mauxanh
    	if(megamanState.get("skill2")==false&&freeze()==false&&manaDecrease(33)) {
    		stopRun();
    		megamanState.put("skill2", true);
    		startTime.put("skill2", System.nanoTime());
    		skill2.reset();
    		skill2Back.reset();
    		
    		
    		skillObject2.setState(ParticularObject.ALIVE);
    		skillObject2.setDirection(getDirection());
            getGameWorld().bulletManager.addObject(skillObject2);
    	}
    }
    
    public void skill4(Megaman enemy) {
    	//skill dam ra mauxanh
    	if(megamanState.get("skill4")==false&&freeze()==false&&manaDecrease(33)) {
    		stopRun();
    		megamanState.put("skill4", true);
    		startTime.put("skill4", System.nanoTime());
    		skill4.reset();
    		skill4Back.reset();
    		
    		
    		skillObject4.setState(ParticularObject.ALIVE);
    		skillObject4.setDirection(getDirection());
            getGameWorld().bulletManager.addObject(skillObject4);
    	}
    }
    public void skill5(Megaman enemy) {
    	//skill dam ra mauxanh
    	if(megamanState.get("skill5")==false&&freeze()==false&&manaDecrease(100)) {
    		stopRun();
    		megamanState.put("skill5", true);
    		startTime.put("skill5", System.nanoTime());
    		skill5.reset();
    		skill5Back.reset();
    		skillObject5.reset();
    		skillObject5.setState(ParticularObject.ALIVE);
    		//skillObject5.setState(ParticularObject.ALIVE);
    		skillObject5.setDirection(getDirection());
            getGameWorld().bulletManager.addObject(skillObject5);
    	}
    }
    public void def() {
    	if(freeze()==false) {
    		isDef=true;
    		setState(NOBEHURT);
    		stopRun();
    	}
    }
    public void unDef() {
    	isDef=false;
    	setState(ALIVE);
    	
    }
    
    
    
    public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public void drawBoundForCollisionWithSkill(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setColor(Color.RED);
        
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }
    
    
}
