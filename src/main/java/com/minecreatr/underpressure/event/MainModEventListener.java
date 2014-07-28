package com.minecreatr.underpressure.event;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.minecreatr.underpressure.ITravelerBlock;
import com.minecreatr.underpressure.ModWorldData;
import com.minecreatr.underpressure.effect.ModPotions;
import com.minecreatr.underpressure.network.PacketHandler;
import com.minecreatr.underpressure.network.message.MessageRenderPortal;
import com.minecreatr.underpressure.reference.Reference;
import com.minecreatr.underpressure.util.BlockData;
import com.minecreatr.underpressure.util.MathHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 7/24/2014
 */
public class MainModEventListener {

    Multimap<String, AttributeModifier> speed = HashMultimap.create();

    public MainModEventListener initStuff(){
        speed.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(new UUID(47389342, 4307324), "Traveler Effect", 1, 0));
        return this;
    }
//    @SubscribeEvent
//    public void onRenderWorldLast(RenderWorldLastEvent event){
//        ModWorldData worldData = ModWorldData.forWorld(Minecraft.getMinecraft().theWorld);
//        for (int i=0;i<worldData.getPortals().size();i++){
//            BlockData cur = worldData.getPortals().get(i);
//            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Rendering Portal"));
//            event.context.spawnParticle("reddust", cur.x, cur.y, cur.z, 0, 0, 0);
//        }
//    }
//
//    @SubscribeEvent
//    public void onWorldTick(TickEvent.WorldTickEvent event){
//        ArrayList<BlockData> blocks = ModWorldData.forWorld(event.world).getPortals();
//        for (int i=0;i<blocks.size();i++){
//            BlockData block = blocks.get(i);
//            PacketHandler.INSTANCE.sendToAllAround(new MessageRenderPortal(block), new NetworkRegistry.TargetPoint(event.world.provider.dimensionId, block.x, block.y, block.z, 30));
//        }
//    }
    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event){
        EntityLivingBase entity = event.entityLiving;
        if (entity.worldObj.getBlock(r(entity.posX), r(entity.posY-1), r(entity.posZ)) instanceof ITravelerBlock){
            entity.getAttributeMap().applyAttributeModifiers(speed);
            ModWorldData.forWorld(entity.worldObj).startTraveling(entity.getUniqueID());
        }
        else if (ModWorldData.forWorld(entity.worldObj).isTraveling(entity.getUniqueID())){
            entity.getAttributeMap().removeAttributeModifiers(speed);
            ModWorldData.forWorld(entity.worldObj).stopTraveling(entity.getUniqueID());
        }
    }

    //round
    public int r(double in){
        return (int) Math.round(in);
    }
}
