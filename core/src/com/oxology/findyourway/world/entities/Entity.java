package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.oxology.findyourway.utils.GameObject;

public class Entity extends GameObject {

    private boolean jump;

    private float xSpeed;
    private float ySpeed;

    private final float defaultXSpeed;
    private final float defaultYSpeed;

    public Entity(int x, int y, Texture texture) {
        super(x, y, texture);

        this.defaultXSpeed = 5f;
        this.defaultYSpeed = 5f;

        this.jump = false;
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.xSpeed = -defaultXSpeed;
        } if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.xSpeed = defaultXSpeed;
        } else {
            this.xSpeed = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.jump = true;
        }

        move((int) (super.getX() + xSpeed*deltaTime), (int) (super.getY() + ySpeed*deltaTime));
    }
}
