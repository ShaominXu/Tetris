
package tetris;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author xusm
 */
public class AudioPlayer {
    
    private String soundFolder = "sounds" + File.separator;
    
    private String gameStartPath = soundFolder + "me_game_start2.wav";
    private String gameOverPath = soundFolder + "me_game_gameover.wav";
    private String gamePausePath = soundFolder + "se_game_pause.wav";
    
    private String rotatePath = soundFolder + "se_game_rotate.wav";
    private String movePath = soundFolder + "se_game_move.wav";
    private String softDropPath = soundFolder + "se_game_softdrop.wav";
    private String hardDropPath = soundFolder + "se_game_harddrop.wav";
    private String landingPath = soundFolder + "se_game_landing.wav";
    
    private String clearSinglePath = soundFolder + "se_game_single.wav";
    private String clearDoublePath = soundFolder + "se_game_double.wav";
    private String clearTriplePath = soundFolder + "se_game_Triple.wav";
    private String clearTetrisPath = soundFolder + "se_game_Tetris.wav";
 
    private String nextLevelPath = soundFolder + "me_game_plvup.wav";
    
    private Clip gameStartSound, gameOverSound, gamePauseSound;
    private Clip rotateSound, moveSound, softDropSound, hardDropSound;
    private Clip clearSingleSound, clearDoubleSound, clearTripleSound, clearTetrisSound, landingSound;
    private Clip nextLevelSound;
    
    
    
    public AudioPlayer(){
        try {
            gameStartSound = AudioSystem.getClip();
            gameOverSound = AudioSystem.getClip();
            gamePauseSound = AudioSystem.getClip();
            
            rotateSound = AudioSystem.getClip();
            moveSound = AudioSystem.getClip();
            softDropSound = AudioSystem.getClip();
            hardDropSound = AudioSystem.getClip();
            landingSound = AudioSystem.getClip();
            
            clearSingleSound = AudioSystem.getClip();
            clearDoubleSound = AudioSystem.getClip();
            clearTripleSound = AudioSystem.getClip();
            clearTetrisSound = AudioSystem.getClip();
            
            nextLevelSound = AudioSystem.getClip();
            
            gameStartSound.open(AudioSystem.getAudioInputStream(new File(gameStartPath).getAbsoluteFile()));
            gameOverSound.open(AudioSystem.getAudioInputStream(new File(gameOverPath).getAbsoluteFile()));
            gamePauseSound.open(AudioSystem.getAudioInputStream(new File(gamePausePath).getAbsoluteFile()));
            
            rotateSound.open(AudioSystem.getAudioInputStream(new File(rotatePath).getAbsoluteFile()));
            moveSound.open(AudioSystem.getAudioInputStream(new File(movePath).getAbsoluteFile()));
            softDropSound.open(AudioSystem.getAudioInputStream(new File(softDropPath).getAbsoluteFile()));
            hardDropSound.open(AudioSystem.getAudioInputStream(new File(hardDropPath).getAbsoluteFile()));
            landingSound.open(AudioSystem.getAudioInputStream(new File(landingPath).getAbsoluteFile()));
            
            clearSingleSound.open(AudioSystem.getAudioInputStream(new File(clearSinglePath).getAbsoluteFile()));
            clearDoubleSound.open(AudioSystem.getAudioInputStream(new File(clearDoublePath).getAbsoluteFile()));
            clearTripleSound.open(AudioSystem.getAudioInputStream(new File(clearTriplePath).getAbsoluteFile()));
            clearTetrisSound.open(AudioSystem.getAudioInputStream(new File(clearTetrisPath).getAbsoluteFile()));
            
            nextLevelSound.open(AudioSystem.getAudioInputStream(new File(nextLevelPath).getAbsoluteFile()));
            
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playGameStart(){
        gameStartSound.setFramePosition(0);
        gameStartSound.start();
    }
    
    public void playGameOver(){
        gameOverSound.setFramePosition(0);
        gameOverSound.start();
    }
    
    public void playGamePause(){
        gamePauseSound.setFramePosition(0);
        gamePauseSound.start();
    }
    
    public void playRotate(){
        rotateSound.setFramePosition(0);
        rotateSound.start();
    }

    public void playMove(){
        moveSound.setFramePosition(0);
        moveSound.start();
    }

    public void playSoftDrop(){
        softDropSound.setFramePosition(0);
        softDropSound.start();
    }
    
    public void playHardDrop(){
        hardDropSound.setFramePosition(0);
        hardDropSound.start();
    }
    
    public void playLanding(){
        landingSound.setFramePosition(0);
        landingSound.start();
    }
    
    public void playClearSingle(){
        clearSingleSound.setFramePosition(0);
        clearSingleSound.start();
    }
    
    public void playClearDouble(){
        clearDoubleSound.setFramePosition(0);
        clearDoubleSound.start();
    }

    public void playClearTriple(){
        clearTripleSound.setFramePosition(0);
        clearTripleSound.start();
    }

    public void playClearTetris(){
        clearTetrisSound.setFramePosition(0);
        clearTetrisSound.start();
    }
    
    public void playNextLevel(){
        nextLevelSound.setFramePosition(0);
        nextLevelSound.start();
    }
}
