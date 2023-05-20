
package tetris;

import javax.swing.JOptionPane;

/**
 *
 * @author xusm
 */
public class Tetris {
    
    private static GameForm gameForm;
    private static LeaderboardForm leaderboardForm;
    private static StartupForm startupForm;
    private static RuleForm controlOptionsForm;
    
    private static AudioPlayer audio = new AudioPlayer();
    
    public static void start(){
        playGameStart();
        gameForm.setVisible(true);
        gameForm.startGame();
    }
    
    public static void showLeaderboard(){
        leaderboardForm.setVisible(true);
    }
    
    public static void showControlOptions(){
        controlOptionsForm.setVisible(true);
    }
    
    public static void showStratup(){
        startupForm.setVisible(true);
    }
    
    public static void gameOver(int score){
         playGameOver();
         
         String playerName = JOptionPane.showInputDialog(null, 
                 " Game Over! \n Please enter your name to save your score.", 
                 "NAME");
         gameForm.setVisible(false);
         //System.out.println("playerName=" + playerName + "!");
         if (playerName == null) showStratup();
         else leaderboardForm.addPlayer(playerName, score);
    }
    
    public static void playGameStart(){
        audio.playGameStart();
    }
    
    public static void playGameOver(){
        audio.playGameOver();
    }
    
    public static void playGamePause(){
        audio.playGamePause();
    }
    
    public static void playRotate(){
        audio.playRotate();
    }
    
    public static void playMove(){
        audio.playMove();
    }
    
    public static void playSoftDrop(){
        audio.playSoftDrop();
    }
    
    public static void playHardDrop(){
        audio.playHardDrop();
    }
    
    public static void playLanding(){
        audio.playLanding();
    }    
    
    public static void playClearSingle(){
        audio.playClearSingle();
    }
    
    public static void playClearDouble(){
        audio.playClearDouble();
    }
    
    public static void playClearTriple(){
        audio.playClearTriple();
    }
    
    public static void playClearTetris(){
        audio.playClearTetris();
    }
    
    public static void playNextLevel(){
        audio.playNextLevel();
    }

    
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gameForm = new GameForm();
                startupForm = new StartupForm();
                leaderboardForm = new LeaderboardForm();
                controlOptionsForm = new RuleForm();

                startupForm.setVisible(true);
            }
        });
    }
    
}
