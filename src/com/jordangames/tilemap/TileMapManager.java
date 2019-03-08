package com.jordangames.tilemap;

import com.jordangames.main.LevelPanel;
import java.awt.Graphics2D;
import java.awt.Color; 
import java.awt.Font;
import javax.imageio.ImageIO;

public class TileMapManager 
{
    // This value represents the size of the tile (height and width
    // This represents a TILE SIZE of 64x64  
    private final int TILE_SIZE = 64;
    
    // This array stores the tiles in use in the game
    private Tile[] tiles;
    
    // This array represents the array map in use for the level 
    // Eventually the data in this array can be stored in a text file and
    // loaded in to the game
    // The number in the array is mapped to the array of tiles
    private final int[][] map = 
    {
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0 ,0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 2, 2},
        {2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2}
    };
    // These variables are used as the centre point for the camera
    // We use doubles so that we can calculate positions to sub-pixel position
    private double cameraX; // Camera X - Camera coordinates differ from screen coordinates 
    private double cameraY; // Camera Y
    
     // These variables control which rows and columns of the tilemap are going to be displaye d on screen.
    
    //These variables set the bounds of the camera
    //
    private int xMax; // The maximum X size of the level
    private int yMax; // The maximum Y size of the level
    private int xMin;
    private int yMin;
    
    private int numberOfColumns; // Number of rows in the tilemap
    private int numberOfRows; // Number of columns in the tilemap
    
    private int rowOffset;
    private int colOffset;
    
    private int numColumnsToDraw; // This value indicates the number of columns to draw
    private int numRowsToDraw;  // This value indicates the number of rows to draw 
    
    public TileMapManager()
    {
        int width;
        int height;
        
        numberOfRows = map.length;  // Quick hack to find number of rows dynamically - t his can be set in a file
        numberOfColumns = map[0].length; // Quick hack to find number of columns dynami cally - this can be set in a file
        
        // Work out the number of columns to draw on screen - don't bother with parts o f the tile map 
         
        // that are currently off screen 
        numColumnsToDraw = LevelPanel.PANEL_WIDTH / TILE_SIZE + 2; // + 2 ensures that there is no gap at the edge of the screen where no tile is drawn
        numRowsToDraw = LevelPanel.PANEL_HEIGHT / TILE_SIZE + 2; 
        
        width = numberOfColumns * TILE_SIZE;
        height = numberOfRows * TILE_SIZE;
        
        xMin = LevelPanel.PANEL_WIDTH - width;
        xMax = 0;
        yMin = LevelPanel.PANEL_HEIGHT - height;
        yMax = 0;
        cameraX = 0;
        cameraY = 0;
        loadMap();
        loadTiles();
    }
    
    private void loadMap()
    {
        
    }
    
    private void loadTiles()
    {
        tiles = new Tile[3];
        try
        {
            tiles[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Resources/images/col1.png")), Tile.TYPE_NORMAL);
            tiles[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Resources/images/col2.png")), Tile.TYPE_BLOCKED);
            tiles[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/Resources/images/col3.png")), Tile.TYPE_BLOCKED);
        }catch(java.io.IOException ex)
        {
            System.err.println("Error loading tiles");
        }
    }
    
    public Tile getTileAt(double x, double y)
    {
         // Incoming X and Y coordinates are screen coordinates - must convert to camera coo rdinates or we will return the wrong tile.
        x = x - cameraX; // Convert to world coordinates so that we get the correct tile
        y = y - cameraY; // Convert to world coordinates so that we get the correct tile
        
        int row = (int) y / TILE_SIZE;
        int col = (int) x / TILE_SIZE;
        
        int tileID = map[row][col];
        
        tileID = matchTile(tileID);
        System.out.println("X: " + x + "Y: " + y);
        System.out.println("In tile: " + row + ", " + col);
        return tiles[tileID];
    }
    
    public void setCameraPosition(double x, double y)
    {
        cameraX += (x - cameraX);
        cameraY += (y - cameraY);
        
        fixCameraBounds();
        
        colOffset = (int) -cameraX / TILE_SIZE;
        rowOffset = (int) -cameraY / TILE_SIZE;
    }
    
    private void fixCameraBounds()
    {
        if(cameraX < xMin)
            cameraX = xMin;
        
        if(cameraX > xMax)
            cameraX = xMax;
        
         if(cameraY < yMin)
             cameraY = yMin;
         
         if(cameraY > yMax)
             cameraY = yMax;
    }
    
    private int matchTile(int tileMapID)
    {
        int mappedTile = 0;
        
        switch(tileMapID)
        {
            case 1:
                mappedTile = 0;
                break;
            case 2:
                mappedTile = 1;
                break;
            case 3:
                mappedTile = 2;
        }
        
        return mappedTile;
    }
    
    public void draw(Graphics2D g)
    {
        int tileImage;
        double tempX;
        double tempY;// These two variables are offsets from the camera start position 
        
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.WHITE);
        g.drawString("Camera X: " + cameraX + " Camera Y: " + cameraY, 20, 20);
        
        g.setColor(Color.BLACK);
        for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++)
        {
            if(row >= numberOfRows) break;  // Break causes the current iteration of the loop to be skipped
            tempY = cameraY + row * TILE_SIZE;
            for(int col = colOffset; col < colOffset + numColumnsToDraw; col++)
            {
                if(col >= numberOfColumns)break; // Break causes the current iteration of t he loop to be skipped
                tempX = cameraX + col * TILE_SIZE;
                // 0 indicates no tile - so skip and don't draw
                if(map[row][col] != 0)
                {
                    tileImage = matchTile(map[row][col]);// Get the tile represented by the current row and column
                    g.drawImage(tiles[tileImage].getImage(), (int)tempX, (int)tempY, null);
                    g.drawRect((int)tempX, (int)tempY, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }
    
    public double getCameraX()
    {
        return cameraX;
    }
    
    public double getCameraY()
    {
        return cameraY;
    }
}
