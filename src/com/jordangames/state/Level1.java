package com.jordangames.state;

import com.jordangames.entity.Player;
import com.jordangames.main.LevelPanel;
import com.jordangames.tilemap.TileMapManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Level1 extends LevelState
{
    private Player p;
    public Level1(LevelManager lm)
    {
        super(lm);
        p = new Player();
    }
    
    private void init()
    {
        
    }
    
    @Override
    public void keyPressed(int keyCode)
    {
        if(keyCode == KeyEvent.VK_A)
            p.moveLeft(true);
        
        if(keyCode == KeyEvent.VK_D)
            p.moveRight(true);
    }
    
}
