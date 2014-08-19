package com.minecreatr.underpressure.entity;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;

/**
 * Created on 7/28/2014
 */
public class EntityMonorail extends EntityMinecart{

    public EntityMonorail(World world){
        super(world);
    }

    @Override
    public void onUpdate(){
        this.setRollingAmplitude(100);
        super.onUpdate();
    }

    public int getMinecartType(){
        return 0;
    }
}
