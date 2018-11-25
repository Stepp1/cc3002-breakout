package controller;

import logic.brick.Brick;
import logic.level.ConcreteLevel;
import logic.level.Level;
import logic.level.NullLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {
    /**
     * The number of balls in the game.
     */
    private int numberBalls;

    /**
     * The default level.
     */
    private Level initLevel = new NullLevel();

    /**
     * The current level.
     */
    private Level currentLevel;

    /**
     * The accumulated points of past and current levels.
     */
    private int numberPoints;

    /**
     * A list containing all the levels in this game.
     */
    private List<Level> levels = new ArrayList<>();


    /**
     *  Constructor of a Game
     *
     * @param balls the number of balls given to this Game instance
     */
    public Game(int balls) {
        this.numberBalls = balls;
        this.currentLevel = initLevel;
        this.numberPoints = 0;
    }

    public boolean winner() {
        return false;
    }


    public boolean isGameOver() {
        return getBallsLeft() <= 0;
    }


    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        Level level = new ConcreteLevel();

        level.setNumberOfBricks(numberOfBricks);
        level.setName(name);
        level.setProbs(probOfGlass, probOfMetal, seed, numberOfBricks);
        level.setNumberOfBricks(this.getBricks().size());

        return level;
    }

    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        Level level = new ConcreteLevel();

        level.setNumberOfBricks(numberOfBricks);
        level.setName(name);
        level.setProbs(probOfGlass, 0, seed, numberOfBricks);

        return level;
    }

    public int getNumberOfBricks() {
        return getCurrentLevel().getNumberOfBricks();
    }

    public List<Brick> getBricks() {
        return getCurrentLevel().getBricks();
    }

    public boolean hasNextLevel() {
        return getCurrentLevel().hasNextLevel();
    }

    public void goNextLevel() {
        setCurrentLevel(getCurrentLevel().getNextLevel());
    }

    public boolean currentLevel() {
        return getCurrentLevel().isPlayableLevel();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level level) {
        currentLevel = level;
    }

    public int getCurrentPoints() {
        return numberPoints;
    }

    public void addPlayingLevel(Level level) { currentLevel.addPlayingLevel(level);}

    public int getBallsLeft() {
        return numberBalls;
    }

    public int setNumberOfBalls(int i) {
        if (i < 0)
            return this.numberBalls = 0;

        return this.numberBalls = i;
    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
