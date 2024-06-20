package com.oop.effect;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.oop.gameobject.Megaman;
import com.oop.gameobject.ParticularObject;
import com.oop.gameobject.Skill;



public class Animation {
    
    private String name;
    
    private boolean isRepeated;
    
    private ArrayList<FrameImage> frameImages;
    private int currentFrame;
    
    private ArrayList<Boolean> ignoreFrames;
    
    private ArrayList<Double> delayFrames;
    private long beginTime;

    private boolean drawRectFrame;
    
    public Animation(){
        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;

        ignoreFrames = new ArrayList<Boolean>();
        
        frameImages = new ArrayList<FrameImage>();
        
        drawRectFrame = false;
        
        isRepeated = true;
    }
    
    public Animation(Animation animation){
        
        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;
        
        delayFrames = new ArrayList<Double>();
        for(Double d : animation.delayFrames){
            delayFrames.add(d);
        }
        
        ignoreFrames = new ArrayList<Boolean>();
        for(boolean b : animation.ignoreFrames){
            ignoreFrames.add(b);
        }
        
        frameImages = new ArrayList<FrameImage>();
        for(FrameImage f : animation.frameImages){
            frameImages.add(new FrameImage(f));
        }
    }
    
    public void setIsRepeated(boolean isRepeated){
        this.isRepeated = isRepeated;
    }
    
    public boolean getIsRepeated(){
        return isRepeated;
    }
    
    public boolean isIgnoreFrame(int id){
        return ignoreFrames.get(id);
    }
    
    public void setIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, true);
    }
    
    public void unIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, false);
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    public void setCurrentFrame(int currentFrame){
        if(currentFrame >= 0 && currentFrame < frameImages.size())
            this.currentFrame = currentFrame;
        else this.currentFrame = 0;
    }
    public int getCurrentFrame(){
        return this.currentFrame;
    }
    
    public void reset(){
        currentFrame = 0;
        beginTime = 0;
    }
    
    public void add(FrameImage frameImage, double timeToNextFrame){

        ignoreFrames.add(false);
        frameImages.add(frameImage);
        delayFrames.add(new Double(timeToNextFrame));
        
    }
    
    public void setDrawRectFrame(boolean b){
        drawRectFrame = b;
    }

    
    public BufferedImage getCurrentImage(){
        return frameImages.get(currentFrame).getImage();
    }
    
    public int getWidth() {
    	return frameImages.get(0).getImage().getWidth();
    }
    
    public int getHeight() {
    	return frameImages.get(0).getImage().getWidth();
    }
    
    public void Update(long deltaTime){
        
        if(beginTime == 0) beginTime = deltaTime;
        else{
            
            if(deltaTime - beginTime > delayFrames.get(currentFrame)){
                nextFrame();
                beginTime = deltaTime;
            }
        }
        
    }

    
    public boolean isLastFrame(){
        if(currentFrame == frameImages.size() - 1)
            return true;
        else return false;
    }
    
    private void nextFrame(){
        
        if(currentFrame >= frameImages.size() - 1){
            
            if(isRepeated) currentFrame = 0;
        }
        else currentFrame++;
        
        if(ignoreFrames.get(currentFrame)) nextFrame();
        
    }
    
    
    
    public void flipAllImage(){
        
        for(int i = 0;i < frameImages.size(); i++){
            
            BufferedImage image = frameImages.get(i).getImage();
            
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);

            AffineTransformOp op = new AffineTransformOp(tx,
            AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            
            frameImages.get(i).setImage(image);
            
        }
        
    }
    
    public void draw(int x, int y,int ratio,int ratio2,Graphics2D g2){
        
        BufferedImage image = getCurrentImage();
        g2.drawImage(image, x - image.getWidth()/2/ratio*ratio2, y - image.getHeight()/2/ratio*ratio2, image.getWidth()/ratio*ratio2, image.getHeight()/ratio*ratio2, null);
        //g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        if(drawRectFrame)
            g2.drawRect(x - image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
        
        
    }
    public void draw(int x, int y,int ratio,int ratio2,Graphics2D g2, Skill skill){
        
        BufferedImage image = getCurrentImage();
        g2.drawImage(image, x - image.getWidth()/2/ratio*ratio2, y - image.getHeight()/2/ratio*ratio2, image.getWidth()/ratio*ratio2, image.getHeight()/ratio*ratio2, null);
        //g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        if(drawRectFrame)
            g2.drawRect(x - image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
        
        Rectangle rect = new Rectangle(x - image.getWidth()/2/ratio*ratio2 ,y - image.getHeight()/2/ratio*ratio2, image.getWidth()/ratio*ratio2, image.getHeight()/ratio*ratio2);
        skill.setRect(rect);
        
        //skill.drawBoundForCollisionWithMap(g2);
    }
    public void draw(int x, int y,int ratio,int ratio2,Graphics2D g2, Skill skill, boolean skill5){
        
        BufferedImage image = getCurrentImage();
        g2.drawImage(image, x - image.getWidth()/2/ratio*ratio2, y - image.getHeight()/2/ratio*ratio2, image.getWidth()/ratio*ratio2, image.getHeight()/ratio*ratio2, null);
        //g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        if(drawRectFrame)
            g2.drawRect(x - image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
        
        Rectangle rect = new Rectangle(x - image.getWidth()/2/ratio*ratio2 ,y - image.getHeight()/2/ratio*ratio2+100, image.getWidth()/ratio*ratio2, image.getHeight()/ratio*ratio2-100);
        skill.setRect(rect);
        
        //skill.drawBoundForCollisionWithMap(g2);
    }
    public void draw(int x, int y,int ratio,Graphics2D g2){
        
        BufferedImage image = getCurrentImage();
        g2.drawImage(image, x - image.getWidth()/2/ratio, y - image.getHeight()/2/ratio, image.getWidth()/ratio, image.getHeight()/ratio, null);
        //g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        if(drawRectFrame)
            g2.drawRect(x - image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
        
    }
    
}

