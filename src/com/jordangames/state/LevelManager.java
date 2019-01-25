package com.jordangames.state;

import java.awt.Graphics2D;

public class LevelManager 
{
    private LevelState[] gameStateList;
    private int currentState;
    
    public static final int MENU_SCREEN = 0;
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int VICTORY_SCREEN = 3;
    public static final int DEATH_SCREEN = 4;
    
    public LevelManager()
    {
        currentState = 1;
        gameStateList = new LevelState[5];
        
        gameStateList[0] = new Menu(this);
        gameStateList[1] = new Level1(this);
    }
    
    public void setCurrentState(int state)
    {
        this.currentState = state;
    }
    
    public int getCurrentState()
    {
        return this.currentState;
    }
    
    public void update()
    {
        gameStateList[currentState].update;
    }
    
    public void goToState(int state)
    {
        this.currentState = state;
    }
    
    public void nextState()
    {
        this.currentState++;
    }
    
    public void previousState()
    {
        this.currentState--;
    }
    
    public void updateScreenBuffer(Graphics2D g)
    {
        gameStateList[currentState].draw(g);
    }
    
    public void keyPressed(int keyCode)
    {
        gameStateList[currentState].keyPressed(keyCode);
    }
    
    public void keyReleased(int keyCode)
    {
        gameStateList[currentState].keyReleased(keyCode);

    }
}
