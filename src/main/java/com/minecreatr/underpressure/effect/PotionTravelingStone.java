package com.minecreatr.underpressure.effect;

import net.minecraft.potion.Potion;

/**
 * Created on 7/27/2014
 */
public class PotionTravelingStone extends Potion{

    public PotionTravelingStone(int par1, boolean par2, int par3)
    {
        super(par1, par2, par3);
    }

    public Potion setIconIndex(int par1, int par2)
    {
        super.setIconIndex(par1, par2);
        return this;
    }
}
