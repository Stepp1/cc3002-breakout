package logic.level;

import logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NullLevel extends AbstractLevel implements Level {


    public NullLevel(){
        super();
        super.nextLevel = this;
    }


    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    @Override
    public List<Brick> getBricks() {
        return new ArrayList<>();
    }

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    @Override
    public boolean hasNextLevel() {
        return false;
    }

    /**
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    @Override
    public int getPoints() {
        return 0;
    }


    /**
     * A nullLevel cannot create bricks. This method does nothing.
     *
     * @param probOfGlass the probability of having a glass brick
     * @param probOfMetal the probability of having a glass brick
     * @param seed        the seed that sets the number of bricks in a given instance
     * @param numberOfBricks    the number of bricks of wood or glass and the number of metal bricks
     */
    @Override
    public void setProbs(double probOfGlass, double probOfMetal, int seed, int numberOfBricks) {
        // Does nothing
    }

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    @Override
    public Level addPlayingLevel(Level level) {
       return level;
    }
}
