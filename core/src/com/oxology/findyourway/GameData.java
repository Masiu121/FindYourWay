package com.oxology.findyourway;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public interface GameData {
    Texture MENU_BACKGROUND = new Texture("Menu/MenuBackground.png");
    Texture MENU_BUTTON = new Texture("Menu/Buttons/MenuButton.png");
    Texture MENU_BUTTON_HOVER = new Texture("Menu/Buttons/MenuButtonHover.png");
    Texture GAME_BACKGROUND = new Texture("Game/Background.png");
    Texture MAIN_CHAR_IDLE_1 = new Texture("Game/Sprites/MainCharacterIdle1.png");
    Texture MAIN_CHAR_IDLE_2 = new Texture("Game/Sprites/MainCharacterIdle2.png");
    Texture MAIN_CHAR_WALK_1 = new Texture("Game/Sprites/MainCharacterWalk.png");
    Texture MAIN_CHAR_WALK_2 = new Texture("Game/Sprites/MainCharacterWalk2.png");
    Texture BARREL = new Texture("Game/Sprites/Barrel.png");
    Texture FIRE_START = new Texture("Game/Sprites/FireStart.png");
    Texture FIRE_END = new Texture("Game/Sprites/FireEnd.png");
    Texture FIRE = new Texture("Game/Sprites/Fire.png");
    Texture VIGNETTE = new Texture("Game/Vignette.png");
    Texture QUEST_MARK = new Texture("Game/Sprites/QuestMark.png");
    Texture PAPER = new Texture("Game/Sprites/Paper.png");
    Texture MESSAGE_RIGHT = new Texture("Game/Sprites/Message/MessageRight.png");
    Texture MESSAGE_MIDDLE = new Texture("Game/Sprites/Message/MessageMiddle.png");
    Texture LOGO = new Texture("Menu/Logo.png");
    Texture ARROW_NEXT = new Texture("Menu/Buttons/next.png");
    Texture ARROW_PREVIOUS = new Texture("Menu/Buttons/previous.png");
    FileHandle FONT = Gdx.files.internal("Menu/Font/PixelFont.fnt");

    FileHandle BACKGROUND_MUSIC = Gdx.files.internal("Menu/Audio/MenuMusic.mp3");
    FileHandle ZIPPO_SOUND = Gdx.files.internal("Game/Audio/ZippoSound.mp3");
    FileHandle FIRE_SOUND = Gdx.files.internal("Game/Audio/FireSound.mp3");
    FileHandle FIRE_START_SOUND = Gdx.files.internal("Game/Audio/FireStartSound.mp3");

}