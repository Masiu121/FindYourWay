package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Quest;

import java.util.Random;

public class Npc extends Entity {
    Quest quest;
    Entity questMark;

    public boolean questVisibility;
    Random random;
    Random questRandomX = new Random();

    Random questMapVisibilityNum = new Random();

    int walkTicks;

    private int questScale;

    private int direction;

    TextureRegion[][] idleAnimationFrames1;
    Animation<TextureRegion> idleAnimation1;

    TextureRegion[][] idleAnimationFrames2;
    Animation<TextureRegion> idleAnimation2;

    public int questX = questRandomX.nextInt(-200 , 200);
    public int questMapVisibility = questMapVisibilityNum.nextInt(0 ,200);

    public Npc(int x, int y, Texture texture, float scale, FindYourWay game, Quest quest , boolean questVisibility) {
        super(x, y, texture, scale, 9, true, game);

        this.questScale = 1;

        this.questVisibility = questVisibility;
        if(quest != null) {
            questMark = new Entity(questX, y +47, GameData.QUEST_MARK, questScale, 2, true, game);
        }

        this.quest = quest;

        random = new Random();
        walkTicks = 60;
        this.direction = 0;

        this.idleAnimationFrames1 = TextureRegion.split(GameData.MAIN_CHAR_IDLE_1, 14, 45);
        this.idleAnimation1 = new Animation<TextureRegion>(1f/4f, idleAnimationFrames1[0]);

        this.idleAnimationFrames2 = TextureRegion.split(GameData.MAIN_CHAR_IDLE_2, 14, 45);
        this.idleAnimation2 = new Animation<TextureRegion>(1f/4f, idleAnimationFrames2[0]);
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(quest != null && questVisibility) {
            questMark.draw(batch);
        }
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        questMark.update(deltaTime);

        if(random.nextFloat() > 0.999f) {
            if(direction == 0) {
                direction = 1;
            } else {
                direction = 0;
            }
        }

        if(direction == 0) {
            setAnimation(idleAnimation1, 14, 45, true);
        } else if(direction == 1) {
            setAnimation(idleAnimation2, 14, 45, true);
        }
    }

    public int getQuestX() {
        return questX;
    }
}
