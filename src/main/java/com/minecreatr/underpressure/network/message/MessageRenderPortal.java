package com.minecreatr.underpressure.network.message;

import com.minecreatr.underpressure.util.BlockData;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import sun.plugin2.message.Message;

/**
 * Created on 7/25/2014
 */
public class MessageRenderPortal implements IMessage, IMessageHandler<MessageRenderPortal, IMessage>  {
    public BlockData block;

    public MessageRenderPortal(){

    }

    public MessageRenderPortal(BlockData block){
        this.block = block;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        byte type = buf.readByte();
        this.block = new BlockData(x, y, z, type);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(block.x);
        buf.writeInt(block.y);
        buf.writeInt(block.z);
        buf.writeByte(block.type);
    }
    @Override
    public IMessage onMessage(MessageRenderPortal message, MessageContext ctx){
        BlockData data = message.block;
        ctx.getServerHandler().playerEntity.worldObj.spawnParticle("reddust", data.x, data.y, data.z, 0, 0, 0);
        return null;
    }

    @Override
    public String toString(){
        return "X"+block.x+"Y"+block.y+"Z"+block.z+"T"+block.type;
    }

}
