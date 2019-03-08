package com.jordangames.entity;

import com.jordangames.entity.Sprite;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SpriteLoader
{
    private String spritePath = "/images";
    
    public SpriteLoader(String spritePath)
    {
        this.spritePath = spritePath;
    }
    
    public Sprite loadSpriteSheet(String uri, int tileSize)
    {
        Sprite s = new Sprite(10);
        
        
        return s;
    }
    
    /**
     * Uses the sprite path to read a set of images named sequentially
     * @param fileName
     * @param numberOfFrames
     * @param type
     * @return 
     */
    public Sprite loadFileSequence(String fileName, int numberOfFrames, String type)
    {
        Sprite s = new Sprite(10);
        BufferedImage bi;
        String tempFName;
  
        try
        {
            for(int i = 1; i <= numberOfFrames; i++)
            {
                tempFName = spritePath + "/" +fileName + "_" + i + "." +type;
                bi = ImageIO.read(getClass().getResourceAsStream(tempFName));
                s.addFrame(bi);
            }
        }catch(IOException ex)
        {
            System.err.println("loadFileSequence");
            System.err.println("Error loading sprite image");
            ex.getMessage();
        }
        
        return s;
    }
}



