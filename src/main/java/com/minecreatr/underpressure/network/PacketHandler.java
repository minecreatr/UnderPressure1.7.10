package com.minecreatr.underpressure.network;

/**
 * Created on 7/25/2014
 */
import com.minecreatr.underpressure.UnderPressure;
import com.minecreatr.underpressure.network.message.MessageRenderPortal;
import com.minecreatr.underpressure.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import io.netty.channel.ChannelHandler;

@ChannelHandler.Sharable
public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID.toLowerCase());

    public PacketHandler(){

    }

    public static void init(){
        INSTANCE.registerMessage(MessageRenderPortal.class, MessageRenderPortal.class, 0, Side.CLIENT);
    }
}