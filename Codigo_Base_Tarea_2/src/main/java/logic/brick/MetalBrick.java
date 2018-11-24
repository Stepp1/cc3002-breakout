package logic.brick;

import controller.Game;

/**
 * Brick type class for use in {@link Game}.
 *
 * MetalBrick gives a score of 0 points when it's been destroyed.
 * MetalBrick can be destroyed after 3 hits.
 * MetalBrick gives an extra ball when it's destroyed.
 *
 *
 * @author Victor Faraggi
 * @see Brick
 * @see AbstractBrick
 * @see GlassBrick
 * @see MetalBrick
 */
public class MetalBrick extends AbstractBrick{
    //          Default values of this Brick

    private static int pointsMetalBrick = 0;
    private static int hits_to_destroy_Metal = 10;

    /**
     * Default constructor method for this Brick
     */
    public MetalBrick() {
        this(false);
    }

    /**
     * Constructor method of this class
     *
     * @param destroyed indicator of the status of this Brick
     */
    private MetalBrick(boolean destroyed) {
        super(pointsMetalBrick, hits_to_destroy_Metal, destroyed);
    }

    //          Visitable Element

    /*
    public void accept(GameVisitor v){
        v.visitMetalBrick(this);
    }
    */
}
