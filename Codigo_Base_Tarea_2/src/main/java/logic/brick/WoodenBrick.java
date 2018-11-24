package logic.brick;

import controller.Game;

/**
 * Brick type class for use in {@link Game}.
 *
 * WoodenBrick gives a score of 200 points when it's been destroyed.
 * WoodenBrick can be destroyed after 3 hits.
 *
 * @author Victor Faraggi
 * @see Brick
 * @see AbstractBrick
 * @see GlassBrick
 * @see MetalBrick
 */
public class WoodenBrick extends AbstractBrick{
    //       Default values of this Brick

     private static int  pointsWoodenBrick = 200;
     private static int hits_to_destroy_Wooden = 3;

    /**
     * Default constructor method for this Brick
     */
    public WoodenBrick() {
        this(false);
    }

    /**
     * Constructor method of this class
     *
     * @param destroyed indicator of the status of this Brick
     */
    private WoodenBrick(boolean destroyed) {
        super(pointsWoodenBrick, hits_to_destroy_Wooden, destroyed);
    }

    //          Visitable Element
    /*
    public void accept(GameVisitor v){
        v.visitWoodenBrick(this);
    }
    */
}
