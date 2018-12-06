package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import controller.Game;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import java.util.LinkedList;
import java.util.List;

import static gui.BreakoutEntityFactory.*;


public class BreakoutGameApp extends GameApplication {




    public enum Type {
        PLAYER,
        BALL,
        WALL,
        GLASS_BRICK,
        METAL_BRICK,
        WOODEN_BRICK
    }



    //  Instancing reusable objects

    /**
     * Instance of main {@link Game} controller.
     *
     * Keeps score and ball counters, along with individual Hittable behaviour.
     */
    private Game breakout = new Game(3);

    /**
     * Original x coordinate of the Player
     */
    private final int playerX = 350;

    /**
     * Original y coordinate of the Player
     */
    private final int playerY = 770;

    /**
     * Height of the game window
     */
    private final int gameSizeX = 800;

    /**
     * Width of the game window
     */
    private final int gameSizeY = 800;

    /**
     * For muting game audio.
     */
    private boolean mute = false;

    /**
     * Background music for the game.
     *
     * Loaded from resources/assets/music/
     */
    // private Music background_song = getAssetLoader().loadMusic("hansatom_-_Drugs_of_Choice_2.mp3");

    //  Overridden methods

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(gameSizeX);
        gameSettings.setHeight(gameSizeY);
        gameSettings.setTitle("Breakout Game App");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        Entity bg = newBackground();
        Entity ball = newBall(350,759);
        Entity wall = newWalls();
        Entity player = newPlayer(playerX, playerY);
        getGameWorld().addEntities(bg, player, wall, ball);

        resetGame();
        /*
        if (!mute) {
            double volume = 0.6;
            getAudioPlayer().setGlobalMusicVolume(volume);
            FXGL.getAudioPlayer().playMusic("hansatom_-_Drugs_of_Choice_2.mp3");
        }*/
        
    }



    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(Type.PLAYER)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(new Point2D(e.getX() + 5, 770))); // move right 5 pixels
                getGameWorld().getEntitiesByType(Type.PLAYER)
                        .forEach(e -> verifyPositionRight(e));
            }

            private void verifyPositionRight(Entity e) {
                if(e.getX() + playerWidth >= gameSizeX){
                    e.getComponent(PhysicsComponent.class).reposition(new Point2D(gameSizeX - playerWidth, playerY));
                }
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(Type.PLAYER)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(new Point2D(e.getX() - 5, playerY))); // move left 5 pixels
                getGameWorld().getEntitiesByType(Type.PLAYER)
                        .forEach(e -> verifyPositionLeft(e));
            }

            private void verifyPositionLeft(Entity e) {
                if(e.getX() <= 0){
                    e.getComponent(PhysicsComponent.class).reposition(new Point2D( 0, playerY));
                }
            }
        }, KeyCode.A);

        //input.addAction(MakeBallAction, KeyCode.SPACE);

    }



    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0, 0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Type.BALL, Type.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall,
                                                   HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            ball.removeFromWorld();
                            if(breakout.playing()){
                                newLife();
                            }
                        }
                    }
                });

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Type.BALL, Type.WALL) {
                    @Override
                    protected void onCollision(Entity a, Entity b) {
                        super.onCollision(a, b);
                    }
                }
        );
    }

    private void newLife() {

    }

    private void resetGame() {
        List<Entity> old_entities = new LinkedList<>(getGameWorld().getEntities());
        getGameWorld().removeEntities(old_entities);

        breakout = new Game(3);

        List<Entity> entities = new LinkedList<>();

        entities.add(newBackground());
        entities.add(newBall(350,759));
        entities.add(newWalls());
        entities.add(newPlayer(playerX, playerY));


        for(Entity e : entities){
            getGameWorld().addEntity(e);
        }
        /*
        if (!mute) {
            getAudioPlayer().stopMusic(background_song);
            getAudioPlayer().playMusic(background_song);
        }*/

    }


    /*
        /**
         * Generates a new ball if there's none already on screen, and if the ball counter allows it.
    
        private UserAction MakeBallAction = new UserAction("Make Ball") {
    
            @Override
            protected void onActionBegin() {
                if (getGameWorld().getEntitiesByType(Type.BALL).isEmpty() && breakout.getBalls() != 0) {
                    Entity ball = BreakoutEntityFactory.newBall();
                    getGameWorld().addEntity(ball);
                    breakout.dropBall();
                }
            }
    
        };
    */
    public static void main(String... args) {
        launch(args);
    }
}
