package com.oxology.findyourway.utils.blocksystem.blocks;

import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.blocksystem.Paper;

public class StartBlock extends Block {
    public StartBlock(int x, int y, Paper paper, FindYourWay game) {
        super(x, y, GameData.START_BLOCK, BlockType.START, null, paper, game);
    }
}
