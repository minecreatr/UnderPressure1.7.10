package com.minecreatr.underpressure.Items;

import com.minecreatr.underpressure.ModWorldData;
import com.minecreatr.underpressure.UnderPressure;
import com.minecreatr.underpressure.reference.Reference;
import com.minecreatr.underpressure.util.BlockData;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created on 7/10/2014
 */
public class Debugger extends Item{

    public Debugger(){
        setMaxStackSize(1);
        setCreativeTab(UnderPressure.tab);
        setUnlocalizedName(ModItems.DEBUG_ITEM_NAME);
    }
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
//        ModWorldData data = ModWorldData.forWorld(world);
//        data.portals.add(new BlockData(x, y, z));
//        data.markDirty();
//        player.addChatMessage(new ChatComponentText("New Portal Has been Created at: x=" + x + " y=" + y + " z=" + z));
//        player.addPotionEffect(new PotionEffect(Reference.POTION_TRAVEL_ID, 1));
//        return true;
        return false;
    }
}
