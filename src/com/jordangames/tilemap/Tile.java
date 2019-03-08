package com.jordangames.tilemap;

import java.awt.image.BufferedImage;

public class Tile 
{
    private int type;
    
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_BLOCKED = 1;
    public static final int TYPE_SLOW = 3;
    
    private BufferedImage tileImage;
    
    
    public Tile()
    {
        type = Tile.TYPE_NORMAL;
        tileImage = null;
    }
    
    public Tile(BufferedImage image, int type)
    {
       this.tileImage = image;
       this.type = type;
        
    }
    
    public void setType(int type)
    {
        this.type = type;
        
    }
    
    
    public int getType()
    {
        return this.type;
    }
    
    public void setImage(BufferedImage tileImage)
    {
        this.tileImage = tileImage;
        
    }
    
    public BufferedImage getImage()
    {
        return this.tileImage;
    }
}
