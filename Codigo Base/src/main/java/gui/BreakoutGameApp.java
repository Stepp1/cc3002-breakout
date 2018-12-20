package gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import controller.Game;
import facade.HomeworkTwoFacade;
import gui.control.BrickComponent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.Level;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static gui.BreakoutEntityFactory.*;


public class BreakoutGameApp extends GameApplication {


    public enum Type {
        PLAYER,
        BALL,
        WALL,
        BRICK
    }


    //  Instancing reusable objects

    /**
     * Instance of main {@link Game} controller.
     * <p>
     * Keeps score and ball counters, along with individual Hittable behaviour.
     */
    private HomeworkTwoFacade breakout = new HomeworkTwoFacade();

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
    //  private Music background_song = getAssetLoader().loadMusic("hansatom_-_Drugs_of_Choice_2.mp3");

    /**
     * Random Number Generator
      */
    private Random random_number_generator = new Random();

    /**
     * Stores the levels to be played
     */
    private List<Level> levels = new LinkedList<>();

    /**
     * Stores a boolean that indicates if the ball has been launched
     */
    private boolean launched = false;

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

        breakout = new HomeworkTwoFacade();
        List<Entity> entities = new LinkedList<>();
        entities.add(newBackground());
        Entity ball = newBall(350, 759);
        entities.add(ball);
        entities.add(newWalls());
        entities.add(newPlayer(playerX, playerY));
        startGame();

        //resetGame();
        /*
        if (!mute) {
            double volume = 0.6;
            getAudioPlayer().setGlobalMusicVolume(volume);
            FXGL.getAudioPlayer().playMusic("hansatom_-_Drugs_of_Choice_2.mp3");
        }*/
        getAudioPlayer().setGlobalMusicVolume(0.6);
        FXGL.getAudioPlayer().loopBGM("hansatom_-_Drugs_of_Choice_2.mp3");

    }


    protected void initInput() {
        Input input = getInput();

        input.addAction(MoveRightAction, KeyCode.D);

        input.addAction(MoveLeftAction, KeyCode.A);

        input.addAction(LaunchBall, KeyCode.SPACE);

        input.addAction(MakeLevel, KeyCode.N);
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
                            breakout.dropBall();
                            launched = false;
                            if(breakout.isGameOver()) {
                                //newLife();
                            }
                        }
                    }
                }
        );



        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(Type.BALL, Type.BRICK) {
                    @Override
                    protected void onCollision(Entity ball, Entity brick) {
                        brick.getComponent(BrickComponent.class).getBrick().hit();
                        if(brick.getComponent(BrickComponent.class).getBrick().isDestroyed()){
                            getGameWorld().removeEntity(brick);
                        }
                    }
                }
        );

    }


    private void startGame() {
        List<Entity> old_entities = new LinkedList<>(getGameWorld().getEntities());
        getGameWorld().removeEntities(old_entities);


        Level lvl = breakout.newLevelWithBricksFull("level", 30, 0.5, 0.34, 0);
        breakout.setCurrentLevel(lvl);

        List<Entity> entities = new LinkedList<>();

        entities.add(newBackground());
        entities.add(newBall(350, 759));
        entities.add(newWalls());
        entities.add(newPlayer(playerX, playerY));

        int posX = 25;
        int posY = 30;

        Collections.shuffle(lvl.getBricks());
        for(Brick brick : lvl.getBricks()){
            if(brick.getClass() == GlassBrick.class){
                entities.add(newGlassBrick(posX, posY, (GlassBrick) brick));
            }
            else if(brick.getClass() == WoodenBrick.class){
                entities.add(newWoodenBrick(posX, posY, (WoodenBrick) brick));
            }
            else{
                entities.add(newMetalBrick(posX, posY, (MetalBrick) brick));
            }

            if(posX > 800 - playerX/2 + 50){
                posX = 25;
                posY += 20;
            }
            else {
                posX += 76;
            }
        }

        for (Entity e : entities) {
            getGameWorld().addEntity(e);
        }

        /*
        if (!mute) {
            getAudioPlayer().stopMusic(background_song);
            getAudioPlayer().playMusic(background_song);
        }*/

    }


    public static void main(String... args) {
        launch(args);
    }


// Actions

    private UserAction MakeLevel = new UserAction("Make a new Level") {
        @Override
        protected void onAction() {

        }
    };

    private UserAction MoveRightAction = new UserAction("Move Right") {
        @Override
        protected void onAction() {

            getGameWorld().getEntitiesByType(Type.PLAYER)
                    .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(new Point2D(e.getX() + 5, playerY))); // move right 5 pixels
            getGameWorld().getEntitiesByType(Type.PLAYER)
                    .forEach(this::verifyPositionRight);
            if (!launched){
                getGameWorld().getEntitiesByType(Type.BALL)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(new Point2D(e.getX() + 5, e.getY()))); // move right 5 pixels
            }
        }

        private void verifyPositionRight(Entity e) {
            if (e.getX() + playerWidth >= gameSizeX) {
                e.getComponent(PhysicsComponent.class).reposition(new Point2D(gameSizeX - playerWidth, playerY));
            }
        }
    };

    private UserAction MoveLeftAction = new UserAction("Move Left") {
        @Override
        protected void onAction() {
            getGameWorld().getEntitiesByType(Type.PLAYER)
                    .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(new Point2D(e.getX() - 5, playerY))); // move left 5 pixels
            getGameWorld().getEntitiesByType(Type.PLAYER)
                    .forEach(this::verifyPositionLeft);
            if (!launched){
                getGameWorld().getEntitiesByType(Type.BALL)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(new Point2D(e.getX() - 5, e.getY()))); // move right 5 pixels
            }
        }

        private void verifyPositionLeft(Entity e) {
            if (e.getX() <= 0) {
                e.getComponent(PhysicsComponent.class).reposition(new Point2D(0, playerY));
            }
        }
    };

    private UserAction LaunchBall = new UserAction("Launch Ball") {
        @Override
        protected void onActionBegin() {
                getGameWorld().getEntitiesByType(Type.BALL).forEach(this::ballLauncher);
                launched = true;
        }

        private void ballLauncher(Entity e) {
            int X = random_number_generator.nextInt(50) + 250;
            int Y = random_number_generator.nextInt(50) + 250;

            e.getComponent(PhysicsComponent.class).setLinearVelocity(X, Y);

        }
    };
}


