package logic.level;

import logic.brick.Brick;

import java.util.List;

/**
 * Interface that represents the basics of a level to be played on.
 *
 * @author Juan-Pablo Silva
 */
public interface Level {
    /**
     * Gets the level's name. Each level must have a name.
     *
     * @return the table's name
     */
    String getName();

    /**
     * Gets the number of {@link Brick} in the level.
     *
     * @return the number of Bricks in the level
     */
    int getNumberOfBricks();

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    List<Brick> getBricks();

    /**
     * Gets the next level of a level object. Each level have a reference to the next level to play.
     *
     * @return the next level
     */
    Level getNextLevel();

    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    boolean isPlayableLevel();

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    boolean hasNextLevel();

    /**
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    int getPoints();

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    Level addPlayingLevel(Level level);

    /**
     * Sets the reference for the next level of a level object.
     *
     * @param level the next level of a level object
     */
    void setNextLevel(Level level);

    /**
     * Gets the level name.
     *
     * @return the level name
     */
    String getLevelName();

    /**
     * Sets the number of bricks in a level.
     *
     * @param numberOfBricks the number of bricks to be set
     */
    void setNumberOfBricks(int numberOfBricks);

    /**
     * Sets the name of the level.
     *
     * @param name the name of the level
     */
    void setName(String name);

    /**
     * Sets the probabilities of the glass bricks and metal bricks in the level.
     * After that it calculates and sets the number of each brick type.
     * The seed is used to have the same number of brick types in a given instance.
     *
     * @param probOfGlass the probability of having a glass brick
     * @param probOfMetal the probability of having a glass brick
     * @param seed the seed that sets the number of bricks in a given instance
     */
    void setProbs(double probOfGlass, double probOfMetal, int seed);
}
