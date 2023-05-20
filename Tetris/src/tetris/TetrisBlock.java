
package tetris;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author xusm
 */
public class TetrisBlock {
    
    private int[][] shape;
    private Color color;
    private int x, y;
    private int[][][] shapes;
    private int currentRotation;
    
    
    private final Color[] availableColors = { Color.BLUE, 
                                              Color.GREEN, 
                                              Color.ORANGE, 
                                              Color.MAGENTA,
                                              Color.PINK,
                                              Color.RED, 
                                              Color.YELLOW, 
                                              Color.CYAN };
    
    
    public TetrisBlock(int[][] shape){
        this.shape = shape;
        //this.color = color;
        
        initShapes();          
    }
    
    private void initShapes(){
        shapes = new int[4][][];
        
        for (int i = 0; i < 4; i++){
            int rows = shape[0].length;
            int cols = shape.length;
            shapes[i] = new int[rows][cols];
            for (int r = 0; r < rows; r++){
                for (int c = 0; c < cols; c++){
                    shapes[i][r][c] = shape[cols - c - 1][r];
                }
            }
            shape = shapes[i];
        }
    }
    
    public void spawn(int gridCols){
        
        Random random = new Random();
        color = availableColors[random.nextInt(availableColors.length)];
        
        //currentRotation = random.nextInt(shapes.length);
        currentRotation = 3;
        shape = shapes[currentRotation];
        
        
        y = -getRows();
        x = (gridCols - getCols()) / 2;
    }
    
    
    public int[][] getShape(){
        return shape;
    }
    
    public Color getColor(){
        return color;
    }
    
    public int getRows(){
        return shape.length;
    }
    
    public int getCols(){
        return shape[0].length;
    }
    
    public int getX(){
        return x;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void moveDown(){
        y++;
    }
    
    public void moveLeft(){
        x--;
    }
    
    public void moveRight(){
        x++;
    }
    
    public void rotate(){
        currentRotation++;
        if (currentRotation > 3) currentRotation = 0;
        shape = shapes[currentRotation];
    }
    
    public int getBottomEdge(){
        return y + getRows();
    }
    
    public int getLeftEdge(){
        return x;
    }
    
    public int getRightEdge(){
        return x + getCols();
    }
}
