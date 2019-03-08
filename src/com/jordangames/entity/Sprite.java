package com.jordangames.entity;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Sprite 
{
    private int stepCount;
    private int currentFrame;
    private int animationSpeed;
    private int numberOfFrames;
    private boolean isAnimated;
    private int width;
    private int height;
    
    private ArrayList<BufferedImage> animation;
    
    public Sprite( int animSpeed)
    {
        stepCount = 0;
        currentFrame = 0;
        numberOfFrames = 0;
        isAnimated = false;
        animationSpeed = animSpeed;
        animation = new ArrayList();
    }
    
    public void addFrame(BufferedImage frame)
    {
        animation.add(frame);
        
        if(animation.size()>1)
            isAnimated = true;
    }
    
    private void update()
    {
        width = animation.get(0).getWidth();
        height = animation.get(0).getHeight();
    }
   
    public BufferedImage getSprite()
    {
       return isAnimated ? getNextFrame() : animation.get(0);
    }
    
    private BufferedImage getNextFrame()
    {
        BufferedImage frame = animation.get(currentFrame);
        stepCount++;
        
        if(stepCount % animationSpeed == 0)
        currentFrame++;
        
        if(currentFrame == numberOfFrames)
            currentFrame = 0;
        
        return frame;
        
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
       
}
