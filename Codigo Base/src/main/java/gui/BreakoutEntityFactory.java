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

class BreakoutEntityFactory {


    static Entity newBackground() {
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
    static Entity newWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(BreakoutGameApp.Type.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }



    //  Moving game entities
    final static int playerWidth= 150;
    private final static int playerHeight= 10;

    static Entity newPlayer(double x, double y) {
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
                .bbox(new HitBox("player", BoundingShape.box(playerWidth, playerHeight)))
                .with(physics, new CollidableComponent(true))
                .viewFromNode(new Rectangle(playerWidth, playerHeight, Color.GREY))
                .build();
    }


    static Entity newBall(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();

        physics.setOnPhysicsInitialized(
                () -> physics.setLinearVelocity(0, 0));
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1f)
                        .density(0.1f));

        return Entities.builder()
                .at(x + playerWidth/2.0 - 5, y)
                .type(BreakoutGameApp.Type.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(7)))
                .with(physics, new CollidableComponent(true))
                .viewFromNode(new Circle(7, Color.LIGHTCORAL))
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
    static Entity newGlassBrick(int x, int y, GlassBrick owner) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1.1f)
                        .density(0.1f)
        );
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameApp.Type.BRICK)
                .bbox(new HitBox("GlassBrick", BoundingShape.box(76, 20)))
                .viewFromNode(new Rectangle(76, 20, Color.TURQUOISE))
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
    public static Entity newMetalBrick(int x, int y, MetalBrick owner) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1.1f)
                        .density(0.1f)
        );
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameApp.Type.BRICK)
                .bbox(new HitBox("MetalBrick", BoundingShape.box(76, 20)))
                .viewFromNode(new Rectangle(76, 20, Color.DARKGREY))
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
    public static Entity newWoodenBrick(int x, int y, WoodenBrick owner) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(1.1f)
                        .density(0.1f)
        );
        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameApp.Type.BRICK)
                .bbox(new HitBox("WoodenBrick", BoundingShape.box(76, 20)))
                .viewFromNode(new Rectangle(76, 20, Color.CHOCOLATE))
                .with(physics, new CollidableComponent(true), new BrickComponent(owner))
                .build();
    }

}
