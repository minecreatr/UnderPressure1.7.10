package com.minecreatr.underpressure.effect;

import com.minecreatr.underpressure.reference.Reference;
import net.minecraft.potion.Potion;

/**
 * Created on 7/27/2014
 */
public class ModPotions {

    public static Potion travelingPotion;

    public static void initPotions(){
        travelingPotion = new PotionTravelingStone(Reference.POTION_TRAVEL_ID, false, 0x8BCFFB).setPotionName("underpressure.travelingPotion");
    }
}
