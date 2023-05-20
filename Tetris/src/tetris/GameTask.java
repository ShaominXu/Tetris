
package tetris;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xusm
 */
public class GameTask implements Runnable{
    
    private GameArea gameArea;
    private GameForm gameForm;
    private int linesClearedOnce;
    private int linesCleared;
    private int score;
    private int line;
    private int level = 1;
    private final int linesPerLevel = 10;
    private final int maxLevel = 10;
    
    private int sleepTime = 1000;
    private final int speedupPerLevel = sleepTime / maxLevel;
    
    public GameTask(GameArea gameArea, GameForm gameForm){
        this.gameArea = gameArea;
        this.gameForm = gameForm;
        gameForm.updateScore(score);
        gameForm.updateLine(line);
        gameForm.updateLevel(level);
    }
    
    @Override
    public void run(){
        while (true){
            gameArea.spawnBlock();
            while (gameArea.moveBlockDown()){
                try {
                    Thread.sleep(sleepTime);
                    while (gameForm.paused) {
                        synchronized(this){
                            wait(100);
                        }
                    }
                } catch (InterruptedException ex) {
                    // Logger.getLogger(GameTask.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
            }
            
            if (gameArea.overBounds()){
                Tetris.gameOver(score);
                break;
            }
            
            gameArea.moveBlockToBackground();
            
            linesClearedOnce = gameArea.clearLines();
            linesCleared += linesClearedOnce;
            gameForm.updateLine(linesCleared);
            
            // update score
            score += 10 * level;
            switch (linesClearedOnce) {
                case 1: score += 100 * level; break;
                case 2: score += 200 * level; break;
                case 3: score += 400 * level; break;
                case 4: score += 800 * level; break;    
            }
            gameForm.updateScore(score);
            
            // update level
            int lvl = linesCleared / linesPerLevel + 1;
            if (lvl > level && lvl <= maxLevel){
                level = lvl;
                gameForm.updateLevel(level);
                sleepTime -= speedupPerLevel;
                Tetris.playNextLevel();
            }
        }
    }
}
