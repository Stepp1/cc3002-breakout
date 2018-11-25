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

    //          Fields

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

    //          Constructor

    /**
     *  Constructor of a Breakout Game
     *
     * @param balls the number of balls given to this Game instance
     */
    public Game(int balls) {
        this.numberBalls = balls;
        this.currentLevel = initLevel;
        this.numberPoints = 0;
    }



    /**
     * Method that checks if the game is over
     * This happens when there are no ball left to keep playing
     *
     * @return false if it's over, true if it isn't
     */
    public boolean isGameOver() {
        return getBallsLeft() <= 0;
    }

    /**
     * Generates a new Level that has Glass, Wooden and Metal Bricks
     *
     * @param name              the name of the Level
     * @param numberOfBricks    the number of Bricks that this Level will have
     * @param probOfGlass       the probability of having a Glass or a Wooden Brick
     * @param probOfMetal       the probability of having a Metal Brick
     * @param seed              the seed is used to always generate the same number of each type of Brick and keeps it random
     * @return                  the Level created
     */
    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        Level level = new ConcreteLevel();

        level.setNumberOfBricks(numberOfBricks);
        level.setName(name);
        level.setProbs(probOfGlass, probOfMetal, seed, numberOfBricks);
        level.setNumberOfBricks(this.getBricks().size());

        return level;
    }

    /**
     * Generates a new Level that has Glass and Wooden Bricks
     * It does not have Metal Bricks
     *
     * @param name              the name of the Level
     * @param numberOfBricks    the number of Bricks that this Level will have
     * @param probOfGlass       the probability of having a Glass or a Wooden Brick
     * @param seed              the seed is used to always generate the same number of each type of Brick and keeps it random
     * @return                  the Level created
     */
    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        Level level = new ConcreteLevel();

        level.setNumberOfBricks(numberOfBricks);
        level.setName(name);
        level.setProbs(probOfGlass, 0, seed, numberOfBricks);

        return level;
    }

    /**
     * A method used to get the number of Bricks in the current Level
     *
     * @return  the number of Bricks
     */
    public int getNumberOfBricks() {
        return getCurrentLevel().getNumberOfBricks();
    }

    /**
     * A method used to get a List with all of the Bricks in the current Level
     *
     * @return  the List with all the Levels
     */
    public List<Brick> getBricks() {
        return getCurrentLevel().getBricks();
    }

    /**
     * A method used to check if the current Level has a next playable Level
     * A non playable Level indicates that this Level is the last of the Game
     *
     * @return true if it's playable, false if it isn't
     */
    public boolean hasNextLevel() {
        return getCurrentLevel().hasNextLevel();
    }


    /**
     * Changes the current Level to the next one
     */
    public void goNextLevel() {
        setCurrentLevel(getCurrentLevel().getNextLevel());
    }


    /**
     * Checks if the current Level is playable or not
     *
     * @return true if the Level is playable, false if it isn't
     */
    public boolean currentLevel() {
        return getCurrentLevel().isPlayableLevel();
    }


    /**
     * Method used to get the current Level
     *
     * @return the current Level
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Changes the current to one given
     *
     * @param level the Level to be changed to
     */
    public void setCurrentLevel(Level level) {
        currentLevel = level;
    }

    /**
     * Method used to get the number of points obtained through the Game
     *
     * @return  the number of points
     */
    public int getCurrentPoints() {
        return numberPoints;
    }

    /**
     * Adds a Level to be played
     *
     * @param level the Level to be added
     */
    public void addPlayingLevel(Level level) { currentLevel.addPlayingLevel(level);}

    /**
     * A method used to get the number of balls left to play with
     *
     * @return the number of balls left
     */
    public int getBallsLeft() {
        return numberBalls;
    }

    /**
     * Changes the number of balls left to the number indicated
     *
     * @param i new number of balls
     * @return  the remaining number of balls
     */
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
