package com.jordangames.state;

import com.jordangames.entity.Collectable;
import com.jordangames.entity.Enemy;
import com.jordangames.entity.Player;
import com.jordangames.entity.Sprite;
import com.jordangames.entity.SpriteLoader;
import com.jordangames.main.LevelPanel;
import com.jordangames.tilemap.TileMapManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Level1 extends LevelState 
{
    private Player p;
    private boolean win;
    
    private TileMapManager tmm;
    
    private Enemy[] enemies;
    
    private Collectable[] collectables;

    public Level1(LevelManager lm)
    {
        super(lm);
        tmm = new TileMapManager();
        p = new Player(tmm); //    
        
        win=false;
        init();
        initEnemies();
        initCollectables();
        loadPlayerResources();
    }

    private void init()
    {

    }
    
    private void initEnemies()
    {
        enemies = new Enemy[5];
        
        for(int i = 0; i < enemies.length; i++)
            enemies[i] = new Enemy("/Images/enemy.png", tmm);
    }
    
    private void initCollectables()
    {
        collectables = new Collectable[5];
        
        for(int i = 0; i < collectables.length; i++)
            collectables[i] = new Collectable("/Images/collectable.png", tmm);
    }

    @Override
    public void keyPressed(int keyCode)
    {
        if(keyCode == KeyEvent.VK_A)
            p.moveLeft(true);

        if(keyCode == KeyEvent.VK_D)
            p.moveRight(true);
    }

    @Override
    public void keyReleased(int keyCode)
    {
        if(keyCode == KeyEvent.VK_A)
            p.moveLeft(false);

        if(keyCode == KeyEvent.VK_D)
            p.moveRight(false);
    }

    @Override
    public void update()
    {
        p.update();
        p.checkEnemyCollision(enemies);
        p.checkCollectableCollision(collectables);
        //Update Computer moves
        //Check collisions
        //Other updates

        tmm.setCameraPosition((int)LevelPanel.PANEL_WIDTH/2 - p.getX(), (int)LevelPanel.PANEL_WIDTH/2 - p.getY());
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, LevelPanel.PANEL_WIDTH, LevelPanel.PANEL_HEIGHT);
        tmm.draw(g);
        //draw player
        p.draw(g);
        //draw enemies
    }

    private void loadPlayerResources()
    {
        Sprite s;
        SpriteLoader sm = new SpriteLoader("/Resources/images/level1/character");
        s = sm.loadFileSequence("walk_right", 6, "png");
         p.setAnimations("RIGHT", s);
        s = sm.loadFileSequence("walk_left", 6, "png");
         p.setAnimations("LEFT", s);
         s = sm.loadFileSequence("static", 1, "png");
        p.setAnimations("STATIC", s);
    }
}
