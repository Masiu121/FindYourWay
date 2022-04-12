package com.oxology.findyourway.utils;

import com.badlogic.gdx.graphics.Texture;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.world.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class Background {
    List<BackgroundElement> levels;
    Player player;

    public Background(Player player) {
        this.levels = new ArrayList<>();
//        levels.add(GameData.BG_SUN);
//        levels.add(GameData.GRADI);
//        levels.add(GameData.BLOKI_3);
//        levels.add(GameData.BLOKI_2);
//        levels.add(GameData.BLOKI_1);
//        levels.add(GameData.BARIERKI);

        this.player = player;
    }

    public void update(float deltaTime) {
        if(player.getxSpeed() != 0) {

        }
    }
}
