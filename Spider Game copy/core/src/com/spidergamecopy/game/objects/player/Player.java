package com.spidergamecopy.game.objects.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.spidergamecopy.game.helper.AnimationHandler;
import com.spidergamecopy.game.helper.Constants;

public class Player extends GameEntity {

    private AnimationHandler animationHandler;
    private final String WALK_UP = "walk_up";
    private final String WALK_DOWN = "walk_down";
    private final String WALK_SIDE = "walk_side";
    private SpriteBatch batch = new SpriteBatch();
    private float x, y;

    public Player(float width, float height, Body body) {
        super(width, height, body);
        speed = 1.75f;
        animationHandler = new AnimationHandler();
        // Find regions works fine
        animationHandler.add(WALK_UP, new Animation<>(FRAME_TIME, textureAtlas.findRegions("Walking Up"), Animation.PlayMode.LOOP));
        animationHandler.add(WALK_DOWN, new Animation<>(FRAME_TIME, textureAtlas.findRegions("Walking Down"), Animation.PlayMode.LOOP));
        animationHandler.add(WALK_SIDE, new Animation<>(FRAME_TIME, textureAtlas.findRegions("Walking Side"), Animation.PlayMode.LOOP));
        animationHandler.setCurrent(WALK_DOWN);
    }

    public void update(float deltaTime) {
        x = body.getPosition().x * Constants.PPM;
        y = body.getPosition().y * Constants.PPM;

        checkUserInput();
        render();
    }
    @Override
    public void render() {
        // x and y is correct
        // current frame is not null
        TextureRegion currentFrame = animationHandler.getFrame();
        // Could be something with drawing
        batch.begin();
        batch.draw(currentFrame, x, y); // Draw at position (x, y)
        batch.end();
    }

    private void checkUserInput() {
        // Timer is not being constantly set to 0
        velX = 0;
        velY = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velX = 1;
            animationHandler.setCurrent(WALK_SIDE);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velX = -1;
            animationHandler.setCurrent(WALK_SIDE);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            velY = 1;
            animationHandler.setCurrent(WALK_UP);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            velY = -1;
            animationHandler.setCurrent(WALK_DOWN);
        }

        body.setLinearVelocity(velX * speed, velY * speed);
    }
}
