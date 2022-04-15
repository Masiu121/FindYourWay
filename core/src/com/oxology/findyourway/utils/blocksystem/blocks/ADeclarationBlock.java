package com.oxology.findyourway.utils.blocksystem.blocks;

import com.oxology.findyourway.FindYourWay;
import com.oxology.findyourway.GameData;
import com.oxology.findyourway.utils.blocksystem.Paper;

public class ADeclarationBlock extends Block {
    public ADeclarationBlock(float x, float y, Paper paper, FindYourWay game) {
        super(x, y, GameData.A_DECLARATION_BLOCK, BlockType.DECLARATION, GameData.BLOCK_SHADOW, paper, game);
    }
}
