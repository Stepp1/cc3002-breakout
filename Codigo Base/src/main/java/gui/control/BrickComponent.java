package gui.control;

import com.almasb.fxgl.entity.component.Component;
import logic.brick.AbstractBrick;

/**
        * Component class responsible for keeping a reference to a {@link AbstractBrick} instance
        * for an {@link com.almasb.fxgl.entity.Entity}.
        *
        * @author Victor Faraggi
        *
        * @see Component
        */
public class BrickComponent extends Component {

    /**
     * Reference to a brick object.
     */
    private final AbstractBrick brick;

    /**
     * Constructor ensuring the hittable is initialized.
     *
     * @param linked_game_object    Hittable to store.
     */
    public BrickComponent(AbstractBrick linked_game_object) { this.brick = linked_game_object; }


    /**
     * Grants access to the hittable reference, so that changes may be made to it, or info may be extracted.
     *
     * @return  The stored hittable.
     */
    public AbstractBrick getBrick() {
        return brick;
    }
}