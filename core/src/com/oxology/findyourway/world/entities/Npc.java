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

    int walkTicks;

    private int questScale;

    public int npcNum;

    TextureRegion[][] idleAnimationFrames1;
    Animation<TextureRegion> idleAnimation1;

    public Npc(int x, int y, Texture texture, float scale, FindYourWay game, Quest quest , boolean questVisibility , int npcNum) {
        super(x, y, texture, scale, 9, true, game);

        this.questScale = 1;

        this.npcNum = npcNum;

        this.questVisibility = questVisibility;
        if(quest != null) {
            questMark = new Entity(x, y +47, GameData.QUEST_MARK, questScale, 2, true, game);
        }

        this.quest = quest;

        random = new Random();
        walkTicks = 60;

        if(npcNum == 0){
            this.idleAnimationFrames1 = TextureRegion.split(GameData.NPC_1, 64, 64);
            this.idleAnimation1 = new Animation<TextureRegion>(1f, idleAnimationFrames1[0]);
        } else if(npcNum == 1){
            this.idleAnimationFrames1 = TextureRegion.split(GameData.HOTDOGS, 64, 64);
            this.idleAnimation1 = new Animation<TextureRegion>(1f, idleAnimationFrames1[0]);
        }
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

        setAnimation(idleAnimation1, 64, 64, true);
    }
}
