package logic.level;

import logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class nullLevel extends AbstractLevel implements Level {

    public nullLevel(){
        super();
        setNextLevel(this);
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
        List<Brick> emptyList = new ArrayList<>();
        return emptyList;
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
     */
    @Override
    public void setProbs(double probOfGlass, double probOfMetal, int seed) {

    }

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    @Override
    public Level addPlayingLevel(Level level) {
        level.setNextLevel(new nullLevel());
        return level;
    }
}
