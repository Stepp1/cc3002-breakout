package logic.level;

import logic.brick.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConcreteLevel extends AbstractLevel implements Level{
    /**
     * The number of obtainable points in level
     */
    private int points;

    public ConcreteLevel() {
        super();
        setNextLevel(new nullLevel());
    }

    /**
     * Sets the probabilities of the glass bricks and metal bricks in the level.
     * After that it calculates and sets the number of each brick type.
     * The seed is used to have the same number of brick types in a given instance.
     *
     * @param probOfGlass the probability of having a glass brick
     * @param probOfMetal the probability of having a glass brick
     * @param seed        the seed that sets the number of bricks in a given instance
     */
    public void setProbs(double probOfGlass, double probOfMetal, int seed) {
        Random rng = new Random(seed);
        List<Brick> bricks = new ArrayList<>();
        int points = 0;
        for (int i = 0; i < getNumberOfBricks(); i++){
            double random = rng.nextDouble();
            if (random <= probOfGlass){
                GlassBrick gb = new GlassBrick();
                points += gb.getPoints();

            }
            else{
                WoodenBrick wb = new WoodenBrick();
                points += wb.getPoints();
            }
        }

        for (int i = 0; i < getNumberOfBricks(); i++){
            double random = rng.nextDouble();
            if (random <= probOfMetal){
                MetalBrick mb = new MetalBrick();
                points += mb.getPoints();
            }
        }

        setPoints(points);
    }

    private void setPoints(int points) {
        this.points = points;
    }
    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    public boolean isPlayableLevel() {
        return true;
    }

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    public List<Brick> getBricks() {
        return null;
    }

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    public boolean hasNextLevel() {
        return true;
    }

    /**
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    public Level addPlayingLevel(Level level) {
        return this.getNextLevel().addPlayingLevel(level);
    }
}
