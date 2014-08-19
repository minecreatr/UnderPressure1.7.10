package com.minecreatr.underpressure.network.message;

import com.minecreatr.underpressure.util.BlockData;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created on 7/28/2014
 */
public class MessageChangeStepHeight implements IMessage, IMessageHandler<MessageChangeStepHeight, IMessage> {

    public float height;

    public MessageChangeStepHeight(){
        this.height=0.5f;
    }
    public MessageChangeStepHeight(float height){
        this.height = height;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.height = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(height);
    }
    @Override
    public IMessage onMessage(MessageChangeStepHeight message, MessageContext ctx){
        //Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Changing step high to: "+message.height));
        Minecraft.getMinecraft().thePlayer.stepHeight=message.height;
        return null;
    }

    @Override
    public String toString(){
        return "changeStepHeight"+this.height;
    }
}
