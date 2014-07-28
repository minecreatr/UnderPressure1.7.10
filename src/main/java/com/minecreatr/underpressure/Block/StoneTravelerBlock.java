package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.ITravelerBlock;
import com.minecreatr.underpressure.reference.Textures;
import net.minecraft.block.material.Material;

/**
 * Created on 7/27/2014
 */
public class StoneTravelerBlock extends BasicBlock implements ITravelerBlock{

    public StoneTravelerBlock(){
        super(Material.rock, ModBlocks.STONE_TRAVELER_BLOCK_NAME, Textures.CHAMBER_BLOCK_TEXTURE);
    }
}
