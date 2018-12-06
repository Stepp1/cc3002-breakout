package gui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import gui.control.BrickComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

public class BreakoutEntityFactory {


    public static Entity newBackground() {
        return Entities.builder()
                .viewFromNode(new Rectangle(800, 800, Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    /**
     * Creates an entity with the walls for the board.
     *
     * @return  Invisible collidable walls.
     */
    public static Entity newWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(BreakoutGameApp.Type.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }



    //  Moving game entities
    protected final static int playerWidth= 150;
    protected final static int playerHeigth= 10;

    public static Entity newPlayer(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1.1f)
                        .density(0.1f)
                        .friction(0f)
        );
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameApp.Type.PLAYER)
                .bbox(new HitBox("player", BoundingShape.box(playerWidth, playerHeigth)))
                .with(physics, new CollidableComponent(true))
                .viewFromNode(new Rectangle(playerWidth, playerHeigth, Color.GREY))
                .build();
    }


    public static Entity newBall(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setOnPhysicsInitialized(
                () -> physics.setLinearVelocity(5 * 60, -5 * 60));
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1f)
                        .density(0.1f));

        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameApp.Type.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .with(physics, new CollidableComponent(true))
                .viewFromNode(new Circle(10, Color.LIGHTCORAL))
                .build();
    }

    /**
     * Creates a new collide entity holding a {@link GlassBrick} at the specified location and with the desired size.
     *
     * @param x Horizontal coordinate.
     * @param y Vertical coordinate.
     * @param owner Hittable containing the logic for the entity.
     * @return  Entity holding the owner.
     */
    public static Entity newGlassBrick(double x, double y, GlassBrick owner) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1.1f)
                        .density(0.1f)
        );
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameApp.Type.GLASS_BRICK)
                .bbox(new HitBox("GlassBrick", BoundingShape.box(100, 50)))
                .viewFromNode(new Rectangle(100, 50, Color.TURQUOISE))
                .with(physics, new CollidableComponent(true), new BrickComponent(owner))
                .build();
    }

    /**
     * Creates a new collide entity holding a {@link MetalBrick} at the specified location and with the desired size.
     *
     * @param x Horizontal coordinate.
     * @param y Vertical coordinate.
     * @param owner Hittable containing the logic for the entity.
     * @return  Entity holding the owner.
     */
    public static Entity newMetalBrick(double x, double y, MetalBrick owner) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1.1f)
                        .density(0.1f)
        );
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameApp.Type.METAL_BRICK)
                .bbox(new HitBox("MetalBrick", BoundingShape.box(100, 50)))
                .viewFromNode(new Rectangle(100, 50, Color.DARKGREY))
                .with(physics, new CollidableComponent(true), new BrickComponent(owner))
                .build();
    }

    /**
     * Creates a new collide entity holding a {@link WoodenBrick} at the specified location and with the desired size.
     *
     * @param x Horizontal coordinate.
     * @param y Vertical coordinate.
     * @param owner Hittable containing the logic for the entity.
     * @return  Entity holding the owner.
     */
    public static Entity newWoodenBrick(double x, double y, WoodenBrick owner) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1.1f)
                        .density(0.1f)
        );
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameApp.Type.WOODEN_BRICK)
                .bbox(new HitBox("WoodenBrick", BoundingShape.box(100, 50)))
                .viewFromNode(new Rectangle(100, 50, Color.CHOCOLATE))
                .with(physics, new CollidableComponent(true), new BrickComponent(owner))
                .build();
    }

}
