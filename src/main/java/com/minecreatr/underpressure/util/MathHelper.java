package com.minecreatr.underpressure.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created on 7/10/2014
 */
public class MathHelper {

    public static int getDistance(int xd, int yd, int zd){
        int t = Math.abs(sqr(xd)+sqr(yd)+sqr(zd));
        return (int) Math.abs(Math.sqrt(t));
    }

    public static int getDistance(int[] locs){
        return getDistance(locs[0], locs[1], locs[2]);
    }

    public static int sqr(int s){
        return s*s;
    }

    public static List getEntitysWithinRadius(World world, int x, int y, int z, int r, Class type){
        return world.getEntitiesWithinAABB(type, AxisAlignedBB.getBoundingBox(x-r, y-r, z-r, x+r, y+r, z+r));
    }

    public static List getEntitysWithinRadius(World world, BlockData block, int r, Class type){
        return getEntitysWithinRadius(world, block.x, block.y, block.z, r, type);
    }
}
