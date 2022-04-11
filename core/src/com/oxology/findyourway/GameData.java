package com.oxology.findyourway;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.w3c.dom.Text;

public interface GameData {

    FileHandle FONT = Gdx.files.internal("Menu/Font/PixelFont.fnt");

    //Audio
    FileHandle BACKGROUND_MUSIC = Gdx.files.internal("Menu/Audio/MenuMusic.mp3");
    FileHandle FIRE_START_SOUND = Gdx.files.internal("Game/Audio/FireStartSound.mp3");
    FileHandle FIRE_SOUND = Gdx.files.internal("Game/Audio/FireSound.mp3");
    FileHandle ZIPPO_SOUND = Gdx.files.internal("Game/Audio/ZippoSound.mp3");

    //Textures
    Texture LOGO = new Texture("Menu/Logo.png");
    Texture MENU_BUTTON = new Texture("Menu/Buttons/MenuButton.png");
    Texture MENU_BUTTON_HOVER = new Texture("Menu/Buttons/MenuButtonHover.png");

    Texture VIGNETTE = new Texture("Game/Vignette.png");
    Texture GAME_BACKGROUND = new Texture("Game/Background.png");
    Texture MENU_BACKGROUND = new Texture("Menu/MenuBackground.png");

    Texture MAIN_CHAR_IDLE_1 = new Texture("Game/Sprites/MainCharacterIdle1.png");
    Texture MAIN_CHAR_IDLE_2 = new Texture("Game/Sprites/MainCharacterIdle2.png");
    Texture MAIN_CHAR_WALK_1 = new Texture("Game/Sprites/MainCharacterWalk.png");
    Texture MAIN_CHAR_WALK_2 = new Texture("Game/Sprites/MainCharacterWalk2.png");

    Texture QUEST_MARK = new Texture("Game/Sprites/QuestMark.png");

    Texture BARREL = new Texture("Game/Sprites/Barrel.png");
    Texture FIRE_START = new Texture("Game/Sprites/FireStart.png");
    Texture FIRE_END = new Texture("Game/Sprites/FireEnd.png");
    Texture FIRE = new Texture("Game/Sprites/Fire.png");

    Texture MESSAGE_RIGHT = new Texture("Game/Sprites/Message/MessageRight.png");
    Texture MESSAGE_MIDDLE = new Texture("Game/Sprites/Message/MessageMiddle.png");

    Texture START_BLOCK = new Texture("Game/Blocks/blockStart.png");
    Texture STOP_BLOCK = new Texture("Game/Blocks/blockStop.png");
    Texture STOP_BLOCK_SHADOW = new Texture("Game/Blocks/blockStop.png");

    Texture ARROW_RIGHT = new Texture("Menu/Buttons/Arrow.png");
    Texture ARROW_RIGHT_HOVER = new Texture("Menu/Buttons/ArrowHover.png");
    Texture ARROW_LEFT = new Texture("Menu/Buttons/Arrow2.png");
    Texture ARROW_LEFT_HOVER = new Texture("Menu/Buttons/ArrowHover2.png");

    Texture PAPER = new Texture("Game/Sprites/Paper.png");
}
