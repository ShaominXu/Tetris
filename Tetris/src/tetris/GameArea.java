
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import tetrisblocks.*;

/**
 *
 * @author xusm
 */
public class GameArea extends JPanel {
    
    private int gridRows;
    private int gridCols;
    private int gridCellSize;
    private Color[][] background;
    
    private TetrisBlock block;
    private TetrisBlock[] blocks =  new TetrisBlock[]{ new IBlock(), 
                                    new JBlock(), 
                                    new LBlock(),
                                    new OBlock(), 
                                    new SBlock(), 
                                    new TBlock(), 
                                    new ZBlock() };;
    
    public GameArea(JPanel panel, int columns){
        // panel.setVisible(false);
        this.setBounds(panel.getBounds());
        this.setBackground(panel.getBackground());
        this.setBorder(panel.getBorder());
        
        gridCols = columns;
        gridCellSize = this.getBounds().width / gridCols;
        gridRows = this.getBounds().height / gridCellSize;

    }
    
    public void initBackground(){
        background = new Color[gridRows][gridCols];
    }
    
    public void spawnBlock(){
        Random random = new Random();
        block = blocks[random.nextInt(blocks.length)];
        block.spawn(gridCols);
    }

    
    public boolean overBounds(){
        if (block.getY() < 0){
            block = null;
            return true;
        }
        
        return false;
    }
    
    public boolean moveBlockDown(){
        
        if (!checkBottom()) {
            // moveBlockToBackground();
            // clearLines();
            // Tetris.playLanding();
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }
    
    public void moveBlockRight(){
        if (block == null) return;
        if (!checkRight()) return;
        block.moveRight();
        repaint();
    }
    
    public void moveBlockLeft(){
        if (block == null) return;
        if(!checkLeft()) return;
        block.moveLeft();
        repaint();
    }
    
    public void rotateBlock(){
        if (block == null) return;
        block.rotate();
        if (block.getLeftEdge() < 0) block.setX(0);
        if (block.getRightEdge() >= gridCols) block.setX(gridCols - block.getCols());
        if (block.getBottomEdge() >= gridRows) block.setY(gridRows - block.getRows());
        repaint();
    }
    
    public void dropBlock(){
        if (block == null) return;
        while (checkBottom()){
            block.moveDown();
        }
        repaint(); 
    }
    
    private boolean checkBottom(){
        if (block.getBottomEdge() == gridRows){
            return false;
        }
        
        for (int col = 0; col < block.getCols(); col++){
            for (int row = block.getRows() - 1; row >= 0; row--){
                if (block.getShape()[row][col] != 0){
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;  
                }
            }
        }
        return true;
    }
    
    private boolean checkLeft(){
        if (block.getLeftEdge() == 0) {
            return false;
        }      
        
        for (int row = 0; row < block.getRows(); row++){
            for (int col = 0; col < block.getCols(); col++){
                if (block.getShape()[row][col] != 0){
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;  
                }
            }
        }
        
        return true;
    }
    
    private boolean checkRight(){
        if (block.getRightEdge() == gridCols){
            return false;
        }
        
        for (int row = 0; row < block.getRows(); row++){
            for (int col = block.getCols() - 1; col >= 0; col--){
                if (block.getShape()[row][col] != 0){
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) {
                        return false;
                    }
                    break;  
                }
            }
        }
        
        return true;
    }
    
    public int clearLines(){ 
        boolean lineFilled;  
        int linesClearedOnce = 0;
        
        for (int row = gridRows -1; row >= 0; row--){          
            lineFilled = true; 
            
            for (int col = 0; col < gridCols; col++){
                if (background[row][col] == null){
                    lineFilled = false;
                    break;
                }
            } 
            
            if (lineFilled){
                linesClearedOnce++;
                clearLine(row);
                shiftDown(row);
                clearLine(0);               
                row++;
                repaint();
            }
        }
        
        switch (linesClearedOnce) {
            case 1: Tetris.playClearSingle(); break;
            case 2: Tetris.playClearDouble(); break;
            case 3: Tetris.playClearTriple(); break;
            case 4: Tetris.playClearTetris(); break;    
        }
        
        return linesClearedOnce;
    }
    
    private void clearLine(int row){
        for (int i = 0; i < gridCols; i++){
            background[row][i] = null;
        }
    }
    
    private void shiftDown(int row){
        for (int r = row; r > 0; r--){
            System.arraycopy(background[r - 1], 0, background[r], 0, gridCols);
        }
    }
    
    
    public void moveBlockToBackground(){
        for (int row = 0; row < block.getRows(); row++){
            for (int col = 0; col < block.getCols(); col++){
                if (block.getShape()[row][col] == 1){
                    background[row + block.getY()][col + block.getX()] = block.getColor();
                }
            }
        }
    }
    
    private void drawBlock(Graphics g){
        for (int row = 0; row < block.getRows(); row++){
            for (int col = 0; col < block.getCols(); col++){
                if (block.getShape()[row][col] == 1){
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    drawGridSquare(g, block.getColor(), x, y);
                }
            }
        }
    }
    
    private void drawBackground(Graphics g){
        Color color;
        for (int row = 0; row < gridRows; row++){
            for (int col = 0; col < gridCols; col++){
                color = background[row][col];
                if(color != null){
                    int x = col * gridCellSize;
                    int y = row * gridCellSize;
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y){
        g.setColor(color);
        g.fillRect( x, y, gridCellSize, gridCellSize);
        g.setColor(Color.BLACK);
        g.drawRect( x, y, gridCellSize, gridCellSize);
    }
    
    @Override
    protected void paintComponent (Graphics g){
       super.paintComponent(g);
       /*
        for (int y = 0; y < gridRows; y++){
            for (int x = 0; x < gridColumns; x++){
                g.drawRect(x * gridCellSize, y * gridCellSize, gridCellSize, gridCellSize);
            }
        }*/
        
       drawBackground(g);
       drawBlock(g);
    }
}
