package com.oxology.findyourway;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public interface GameData {

    FileHandle FONT = Gdx.files.internal("Menu/Font/PixelFont.fnt");

    // File CHARACTER_NUM = Gdx.files.internal("Menu/Font/PixelFont.fnt");

    //Audio
    FileHandle BACKGROUND_MUSIC = Gdx.files.internal("Menu/Audio/MenuMusic.mp3");
    FileHandle FIRE_START_SOUND = Gdx.files.internal("Game/Audio/FireStartSound.mp3");
    FileHandle FIRE_SOUND = Gdx.files.internal("Game/Audio/FireSound.mp3");
    FileHandle ZIPPO_SOUND = Gdx.files.internal("Game/Audio/ZippoSound.mp3");

    FileHandle TRAIN_START = Gdx.files.internal("Game/Audio/TrainStart.mp3");
    FileHandle TRAIN_STOP = Gdx.files.internal("Game/Audio/TrainStop.mp3");

    //Textures
    Texture LOGO = new Texture("Menu/Logo.png");
    Texture MENU_BUTTON = new Texture("Menu/Buttons/MenuButton.png");
    Texture MENU_BUTTON_HOVER = new Texture("Menu/Buttons/MenuButtonHover.png");

    Texture VIGNETTE = new Texture("Game/Vignette.png");
    Texture GAME_BACKGROUND = new Texture("Game/Background.png");
    Texture MENU_BACKGROUND = new Texture("Menu/MenuBackground.png");

    Texture BG_RAILINGS = new Texture("Game/Background/Railings.png");
    Texture BG_BUILDING_3 = new Texture("Game/Background/Building3.png");
    Texture BG_BUILDING_2 = new Texture("Game/Background/Building2.png");
    Texture BG_BUILDING_1 = new Texture("Game/Background/Building1.png");
    Texture BG_GRADIENT = new Texture("Game/Background/Gradient.png");
    Texture BG_SUN = new Texture("Game/Background/Sun.png");
    Texture BG_TREE = new Texture("Game/Background/Tree.png");

    Texture METRO_BRICKS = new Texture("Game/Metro/MetroStationBricks.png");
    Texture METRO_TILES = new Texture("Game/Metro/MetroStationTiles.png");
    Texture METRO_PLATFORM = new Texture("Game/Metro/MetroStationPlatform.png");
    Texture METRO_TRAIN = new Texture("Game/Metro/MetroTrain.png");
    Texture METRO_DOOR_LEFT = new Texture("Game/Metro/MetroTrainDoorLeft.png");
    Texture METRO_DOOR_RIGHT = new Texture("Game/Metro/MetroTrainDoorRight.png");
    Texture METRO_ENTRY = new Texture("Game/MetroEntry.png");
    Texture METRO_EXIT = new Texture("Game/MetroExit.png");
    Texture METRO_TUNNEL = new Texture("Game/Metro/MetroTunnel.png");
    Texture METRO_TUNNEL_BLACKOUT = new Texture("Game/Metro/MetroTunnelBlackout.png");

    // First player animation
    Texture MAIN_CHAR_1_IDLE_1 = new Texture("Game/Sprites/MainCharacterIdle1.png");
    Texture MAIN_CHAR_1_IDLE_2 = new Texture("Game/Sprites/MainCharacterIdle2.png");
    Texture MAIN_CHAR_1_WALK_1 = new Texture("Game/Sprites/MainCharacterWalk.png");
    Texture MAIN_CHAR_1_WALK_2 = new Texture("Game/Sprites/MainCharacterWalk2.png");

    // Second player animation
    Texture MAIN_CHAR_2_IDLE_1 = new Texture("Game/Sprites/SecondCharacterIdle1.png");
    Texture MAIN_CHAR_2_IDLE_2 = new Texture("Game/Sprites/SecondCharacterIdle2.png");
    Texture MAIN_CHAR_2_WALK_1 = new Texture("Game/Sprites/SecondCharacterWalk.png");
    Texture MAIN_CHAR_2_WALK_2 = new Texture("Game/Sprites/SecondCharacterWalk2.png");

    // Third player animation
    Texture MAIN_CHAR_3_IDLE_1 = new Texture("Game/Sprites/ThirdCharacterIdle1.png");
    Texture MAIN_CHAR_3_IDLE_2 = new Texture("Game/Sprites/ThirdCharacterIdle2.png");
    Texture MAIN_CHAR_3_WALK_1 = new Texture("Game/Sprites/ThirdCharacterWalk.png");
    Texture MAIN_CHAR_3_WALK_2 = new Texture("Game/Sprites/ThirdCharacterWalk2.png");

    // First player animation choose
    Texture MAIN_CHAR_IDLE_CHOOSE_1 = new Texture("Game/Sprites/hero1.png");

    // Second player animation choose
    Texture MAIN_CHAR_IDLE_CHOOSE_2 = new Texture("Game/Sprites/hero2.png");

    // Third player animation choose
    Texture MAIN_CHAR_IDLE_CHOOSE_3 = new Texture("Game/Sprites/hero3.png");

    Texture QUEST_MARK = new Texture("Game/Sprites/QuestMark.png");

    Texture BARREL = new Texture("Game/Sprites/Barrel.png");
    Texture FIRE_START = new Texture("Game/Sprites/FireStart.png");
    Texture FIRE_END = new Texture("Game/Sprites/FireEnd.png");
    Texture FIRE = new Texture("Game/Sprites/Fire.png");

    Texture MESSAGE_RIGHT = new Texture("Game/Sprites/Message/MessageRight.png");
    Texture MESSAGE_MIDDLE = new Texture("Game/Sprites/Message/MessageMiddle.png");

    Texture TEXT_CARD = new Texture("Game/Sprites/talking.png");

    Texture START_BLOCK = new Texture("Game/Blocks/blockStart.png");
    Texture STOP_BLOCK = new Texture("Game/Blocks/blockStop.png");
    Texture DIVIDE_BLOCK = new Texture("Game/Blocks/DivideBlock.png");
    Texture A_DECLARATION_BLOCK = new Texture("Game/Blocks/ADeclarationBlock.png");
    Texture B_DECLARATION_BLOCK = new Texture("Game/Blocks/BDeclarationBlock.png");
    Texture STOP_BLOCK_SHADOW = new Texture("Game/Blocks/blockStopShadow.png");
    Texture BLOCK_SHADOW = new Texture("Game/Blocks/BlockShadow.png");

    Texture ARROW_RIGHT = new Texture("Menu/Buttons/Arrow.png");
    Texture ARROW_RIGHT_HOVER = new Texture("Menu/Buttons/ArrowHover.png");
    Texture ARROW_LEFT = new Texture("Menu/Buttons/Arrow2.png");
    Texture ARROW_LEFT_HOVER = new Texture("Menu/Buttons/ArrowHover2.png");

    Texture PAPER = new Texture("Game/Sprites/Paper.png");
    Texture INTRO_PAPER = new Texture("Game/Sprites/Intro_paper.png");

    Texture STORY_PAPER = new Texture("Game/Sprites/Paper.png");

    Texture BOOK_OPEN_ANIMATION = new Texture("Game/Sprites/book1.png");
    Texture INTRO_BG = new Texture("Game/Sprites/stol.png");
    Texture BOOK_NEXT_PAGE_ANIMATION = new Texture("Game/Sprites/nextpage1.png");

    Texture BLACK = new Texture("Game/Black2.png");

    Texture HOTDOGS = new Texture("Game/Sprites/HotDog.png");
    Texture NPC_1 = new Texture("Game/Sprites/Npc1.png");

    Texture PAUSE_VIGNETTE = new Texture("Game/PauseVignette.png");
}
