package logic.level;

import logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class AbstractLevel {

    /**
     * The name of the level.
     */
    private String name;

    /**
     * The number of bricks.
     */
    private int numberBricks;

    /**
     * The probability of a metal brick.
     */
    private int probMetalBrick;

    /**
     * The probability of a glass brick.
     */
    private int probGlassBrick;

    /**
     * A link to the next level.
     */
    private Level nextLevel;



    public AbstractLevel(){
        this.name = "";
        this.numberBricks = 0;

    }
    /**
     * Gets the level's name. Each level must have a name.
     *
     * @return the level's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of {@link Brick} in the level.
     *
     * @return the number of Bricks in the level
     */
    public int getNumberOfBricks() {
        return numberBricks;
    }

    /**
     * Sets the reference for the next level of a level object.
     *
     * @param level the next level of a level object
     */
    public void setNextLevel(Level level) {
        this.nextLevel = level;
    }

    /**
     * Returns the name of the level
     *
     * @return the name of the level
     */
    public String getLevelName() {
        return name;
    }

    /**
     * Sets the number of bricks in a level.
     *
     * @param numberOfBricks the number of bricks to be set
     */
    public void setNumberOfBricks(int numberOfBricks) {
        this.numberBricks = numberOfBricks;
    }

    /**
     * Sets the name of the level.
     *
     * @param name the name of the level
     */
    public void setName(String name) {

    }


    /**
     * Gets the next level of a level object. Each level have a reference to the next level to play.
     *
     * @return the next level
     */
    public Level getNextLevel() {
        return nextLevel;
    }
}
