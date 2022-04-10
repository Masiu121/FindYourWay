package com.oxology.findyourway.world.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.Quest;

import java.util.Random;

public class Npc extends Entity {
    Quest quest;
    Entity questMark;
    Random random;
    int walkTicks;

    public Npc(int x, int y, Texture texture, float scale, FindYourWay game, Quest quest) {
        super(x, y, texture, scale, 9, true, game);
        if(quest != null) {
            questMark = new Entity(x+GameData.QUEST_MARK.getWidth()/2, y+47, GameData.QUEST_MARK, scale, 2, true, game);
        }

        this.quest = quest;

        random = new Random();
        walkTicks = 60;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(quest != null) {
            questMark.draw(batch);
        }
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
        questMark.update(deltaTime);

        if(random.nextFloat() > 0.99f) {
            if(walkTicks > 0) {
                setxSpeed(getDefaultXSpeed());
                walkTicks--;
            } else {
                setxSpeed(0);
                walkTicks = 500;
            }
        }
    }
}