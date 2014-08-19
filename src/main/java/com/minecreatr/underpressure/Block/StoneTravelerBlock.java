package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.ITravelerBlock;
import com.minecreatr.underpressure.reference.Textures;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created on 7/27/2014
 */
public class StoneTravelerBlock extends BasicBlock implements ITravelerBlock{

    public StoneTravelerBlock(){
        super(Material.rock, ModBlocks.STONE_TRAVELER_BLOCK_NAME, Textures.CHAMBER_BLOCK_TEXTURE);
    }

//    @Override
//    public void onEntityCollidedWithBlock(World worldObj, int x, int y, int z, Entity entity){
//        if (entity instanceof EntityPlayer){
//            EntityPlayer player = (EntityPlayer) entity;
//            if (isNextTo(x, y, z, r(player.posX), r(player.posY), r(player.posZ))){
//                player.setPositionAndUpdate(player.posX, player.posY+1, player.posZ);
//            }
//        }
//    }
//
//    public boolean isNextTo(int x1, int y1, int z1, int x2, int y2, int z2){
//        if (y1!=y2){
//            return false;
//        }
//        else {
//            boolean isX = false;
//            boolean isZ = false;
//            for (int i=-1;i<1;i++){
//                if (x1+i==x2){
//                    isX = true;
//                }
//            }
//            for (int i=-1;i<1;i++){
//                if (z1+i==z2){
//                    isZ = true;
//                }
//            }
//            return (isX&&isZ);
//        }
//    }
    //round
    public int r(double in){
        return (int) Math.round(in);
    }
}
