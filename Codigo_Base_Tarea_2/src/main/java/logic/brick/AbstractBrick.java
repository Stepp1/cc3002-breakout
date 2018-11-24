package logic.brick;

public class AbstractBrick implements Brick {

    /**
     * The default number of given points
     */
    private int default_points;

    /**
     * Number of times the brick must hit to be destroyed
     */
    private int hits_to_destroy;

    /**
     * Indicates if the brick has been destroyed
     */
    private boolean destroyed;

    /**
     * Constructor method to be used by the subclasses
     *
     * @param default_points    the number of points that a brick gives when it's been destroyed
     * @param hits_to_destroy   the number of hits left to be destroyed
     * @param destroyed         Indicator of the status of a brick
     */
    AbstractBrick(int default_points, int hits_to_destroy, boolean destroyed) {
        this.default_points = default_points;
        this.hits_to_destroy = hits_to_destroy;
        this.destroyed = destroyed;
    }

    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    @Override
    public void hit() {

    }

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    @Override
    public boolean isDestroyed() {
        return this.destroyed;
    }

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    @Override
    public int getScore() {
        return this.default_points;
    }

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    @Override
    public int remainingHits() {
        return this.hits_to_destroy;
    }

    /**
     * Method that returns the number of points that a Brick gives gives when it has been destroyed
     *
     * @return the points that this Brick gives when it has been destroyed
     */
    public int getPoints() {
        return this.getScore();
    }
}
