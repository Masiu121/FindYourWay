package com.oxology.findyourway.utils.blocksystem.blocks;

import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.blocksystem.Paper;

public class DivideBlock extends Block {
    public DivideBlock(float x, float y, Paper paper, FindYourWay game) {
        super(x, y, GameData.DIVIDE_BLOCK, BlockType.DIVIDE, GameData.BLOCK_SHADOW, paper, game);
    }
}
