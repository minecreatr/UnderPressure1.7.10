package com.minecreatr.underpressure.client.render;

import com.minecreatr.underpressure.IConnectedTextureBlock;
import com.minecreatr.underpressure.client.ClientProxy;
import com.minecreatr.underpressure.reference.Textures;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created on 8/16/2014
 */
public class RenderConnectedTexture implements ISimpleBlockRenderingHandler{

    public int getRenderId(){
        return ClientProxy.connectedTexturesRenderId;
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer){
        if (block instanceof IConnectedTextureBlock){
            IConnectedTextureBlock connectedBlock = (IConnectedTextureBlock) block;
            //Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);


            for (int side=0;side<6;side++) {
                int connections = 0;
                boolean north = false;
                boolean south = false;
                boolean east = false;
                boolean west = false;
                boolean up = false;
                boolean down = false;

                boolean excludeX = false;
                boolean excludeY = false;
                boolean excludeZ = false;

                if (side==0||side==1){
                    excludeY = true;
                }
                else if (side==2||side==3){
                    excludeZ = true;
                }
                else if (side==4||side==5){
                    excludeX = true;
                }
                else {
                    excludeX = true;
                    excludeY = true;
                    excludeZ = true;
                }

                //north
                if (world.getBlock(x, y, z - 1) == block && !excludeZ) {
                    connections++;
                    north = true;
                }
                //south
                if (world.getBlock(x, y, z + 1) == block && !excludeZ) {
                    connections++;
                    south = true;
                }
                //east
                if (world.getBlock(x + 1, y, z) == block && !excludeX) {
                    connections++;
                    east = true;
                }
                //west
                if (world.getBlock(x - 1, y, z) == block && !excludeX) {
                    connections++;
                    west = true;
                }
                //up
                if (world.getBlock(x, y + 1, z) == block && !excludeY) {
                    connections++;
                    up = true;
                }
                //down
                if (world.getBlock(x, y - 1, z) == block && !excludeY) {
                    connections++;
                    down = true;
                }

                IIcon icon;
                if (connections==2){
                    icon = connectedBlock.getIconFrom(side, north, south, east, west, up, down);
                }
                else {
                    icon = connectedBlock.getIconFrom(connections);
                }
                if (side==0||side==1){
                    //4 north
                    //3 south
                    //2 east
                    //1 west

                    if (connections==1) {
                        if (north){
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                        }

                        if (south) {
                            renderer.uvRotateBottom=3;
                            renderer.uvRotateTop=3;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom=0;
                        }
                        else if (east) {
                            renderer.uvRotateBottom = 2;
                            renderer.uvRotateTop=1;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom = 0;
                        }
                        else if (west) {
                            renderer.uvRotateBottom = 1;
                            renderer.uvRotateTop=2;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom = 0;
                        }
                    }
                    else if (connections==2){
                        if (north&&south){
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                        }
                        else if (east&&west){
                            renderer.uvRotateBottom=2;
                            renderer.uvRotateTop=1;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom=0;
                        }
                        else if (north&&east){
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                        }
                        else if (east&&south){
                            renderer.uvRotateBottom=2;
                            renderer.uvRotateTop=1;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom=0;
                        }
                        else if (south&&west){
                            renderer.uvRotateBottom=3;
                            renderer.uvRotateTop=3;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom=0;
                        }
                        else if (west&&north){
                            renderer.uvRotateBottom=1;
                            renderer.uvRotateTop=2;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom=0;
                        }
                    }
                    else if (connections==3){
                        if (north&&east&&west){
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                        }
                        else if (east&&north&&south){
                            renderer.uvRotateBottom=2;
                            renderer.uvRotateTop=1;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom=0;
                        }
                        else if (east&&west&&south){
                            renderer.uvRotateBottom=3;
                            renderer.uvRotateTop=3;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom=0;
                        }
                        else if (north&&south&&west){
                            renderer.uvRotateBottom=1;
                            renderer.uvRotateTop=2;
                            renderer.renderFaceYNeg(block, x, y, z, icon);
                            renderer.renderFaceYPos(block, x, y, z, icon);
                            renderer.uvRotateTop=0;
                            renderer.uvRotateBottom=0;
                        }
                    }
                    else if (connections==4){
                        renderer.renderFaceYNeg(block, x, y, z, icon);
                        renderer.renderFaceYPos(block, x, y, z, icon);
                    }
                    else {
                        renderer.renderFaceYNeg(block, x, y, z, connectedBlock.getConnectedIcon(IConnectedTextureBlock.IconType.NON_CONNECTED));
                        renderer.renderFaceYPos(block, x, y, z, connectedBlock.getConnectedIcon(IConnectedTextureBlock.IconType.NON_CONNECTED));
                    }
                }
            }





            //Tessellator tessellator = Tessellator.instance;
//            tessellator.addTranslation(x, y, z);
//            tessellator.addVertexWithUV(0, 1, 1, u, v);
//            tessellator.addVertexWithUV(1, 1, 1, u, V);
//            tessellator.addVertexWithUV(1, 1, 0, U, V);
//            tessellator.addVertexWithUV(0, 1, 0, U, v);

//            tessellator.addTranslation(x, y, z);
//            tessellator.addVertexWithUV(0, 1, 1, U, V);
//            tessellator.addVertexWithUV(1, 1, 1, U, v);
//            tessellator.addVertexWithUV(1, 1, 0, u, v);
//            tessellator.addVertexWithUV(0, 1, 0, u, V);

//            tessellator.addVertexWithUV(0, 1, 1, u, V);
//            tessellator.addVertexWithUV(1, 1, 1, U, V);
//            tessellator.addVertexWithUV(1, 1, 0, U, v);
//            tessellator.addVertexWithUV(0, 1, 0, u, v);
            //Minecraft.getMinecraft().renderEngine.deleteTexture(new ResourceLocation(Textures.GLASS_NO_CONNECT));
            //tessellator.addTranslation(-x, -y, -z);
            return true;
        }
        else {
            return false;
        }
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer){
        //renderBlockAsItem(block, metadata, modelId);
    }

    public boolean shouldRender3DInInventory(int modelid){
        return true;
    }
}
