package logic.brick;

public class AbstractBrick implements Brick {


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
        return false;
    }

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    @Override
    public int getScore() {
        return 0;
    }

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    @Override
    public int remainingHits() {
        return 0;
    }
}
