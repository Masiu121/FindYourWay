package com.oxology.findyourway.utils.blocksystem.blocks;

import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.blocksystem.Paper;

public class BDeclarationBlock extends Block {
    public BDeclarationBlock(float x, float y, Paper paper, FindYourWay game) {
        super(x, y, GameData.B_DECLARATION_BLOCK, BlockType.DECLARATION, GameData.BLOCK_SHADOW, paper, game);
    }
}
