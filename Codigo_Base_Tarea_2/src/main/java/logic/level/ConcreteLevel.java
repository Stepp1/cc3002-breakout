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

    /**
     * The list with the Brick of this level
     */
    private List<Brick> bricks = new ArrayList<>();


    /**
     * Constructor of this class
     */
    public ConcreteLevel() {
        super();
        setNextLevel(new NullLevel());
    }

    /**
     * Sets the probabilities of the glass bricks and metal bricks in the level.
     * After that it calculates and sets the number of each brick type.
     * The seed is used to have the same number of brick types in a given instance.
     *
     * @param probOfGlass the probability of having a glass brick
     * @param probOfMetal the probability of having a glass brick
     * @param seed        the seed that sets the number of bricks in a given instance
     * @param numberOfBricks    the number of bricks of wood or glass and the number of metal bricks
     */
    public void setProbs(double probOfGlass, double probOfMetal, int seed, int numberOfBricks) {
        Random rng = new Random(seed);

        int points = 0;
        for (int i = 0; i < numberOfBricks; i++){
            double random = rng.nextDouble();
            if (random <= probOfGlass){
                GlassBrick gb = new GlassBrick();
                points += gb.getPoints();
                bricks.add(gb);

            }
            else{
                WoodenBrick wb = new WoodenBrick();
                points += wb.getPoints();
                bricks.add(wb);
            }
        }

        for (int i = 0; i < numberOfBricks; i++){
            double random = rng.nextDouble();
            if (random <= probOfMetal){
                MetalBrick mb = new MetalBrick();
                points += mb.getPoints();
                bricks.add(mb);
            }
        }

        setPoints(points);
    }

    /**
     * Method that overrides the superclass behavior
     *
     * @return the number of Brick in a concrete level
     */
    @Override
    public int getNumberOfBricks() {
        return this.getBricks().size();
    }

    private void setPoints(int points) {
        this.points = points;
    }
    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    public List<Brick> getBricks() {
        return this.bricks;
    }

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    public boolean hasNextLevel() {
        return this.getNextLevel().isPlayableLevel();
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
        if(!this.getNextLevel().isPlayableLevel()){
            return this.setNextLevel(level);
        }
        else{
            return this.getNextLevel().addPlayingLevel(level);
        }
    }
}
