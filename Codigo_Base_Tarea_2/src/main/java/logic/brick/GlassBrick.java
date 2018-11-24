package logic.brick;

import controller.Game;

/**
 * Brick type class for use in {@link Game}.
 *
 * GlassBrick gives a score of 50 points when it's been destroyed.
 * GlassBrick can be destroyed after 1 hits.
 *
 * @author Victor Faraggi
 * @see Brick
 * @see AbstractBrick
 * @see GlassBrick
 * @see MetalBrick
 */
public class GlassBrick extends AbstractBrick{
    //       Default values of this Brick

    private static int pointsGlassBrick = 50;
    private static int hits_to_destroy_Glass = 1;

    /**
     * Default constructor method for this Brick
     */
    public GlassBrick() {
        this(false);
    }

    /**
     * Constructor method of this class
     *
     * @param destroyed indicator of the status of this Brick
     */
    private GlassBrick(boolean destroyed) {
        super(pointsGlassBrick, hits_to_destroy_Glass, false);
    }

    //          Visitable Element

    /*
    public void accept(GameVisitor v){
        v.visitGlassBrick(this);
    }
    */
}
