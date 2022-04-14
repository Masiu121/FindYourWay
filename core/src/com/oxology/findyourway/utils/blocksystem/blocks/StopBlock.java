package com.oxology.findyourway.utils.blocksystem.blocks;

import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.blocksystem.Paper;

public class StopBlock extends Block {
    public StopBlock(int x, int y, Paper paper, FindYourWay game) {
        super(x, y, GameData.STOP_BLOCK, BlockType.STOP, GameData.STOP_BLOCK_SHADOW, paper, game);
    }
}
