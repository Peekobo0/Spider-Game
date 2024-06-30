package com.spidergamecopy.game.objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Body;

import java.nio.charset.Charset;

public abstract class GameEntity {
    protected float x, y, velX, velY, speed;
    protected float width, height;
    protected Body body;
    protected TextureAtlas textureAtlas;
    protected final float FRAME_TIME = 1/8f;

    public GameEntity(float width, float height, Body body) {
        this.width = width;
        this.height = height;
        this.body = body;
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.speed = 0;
        this.velX = 0;
        this.velY = 0;

        // Loading texture atlas does not cause any errors
        textureAtlas = new TextureAtlas("assets/graphics/walking-animation.atlas");
    }

    public abstract void update(float deltaTime);

    public abstract void render();

    public Body getBody() {
        return body;
    }

}
