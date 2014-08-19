package com.minecreatr.underpressure.Block;

import com.minecreatr.underpressure.IConnectedTextureBlock;
import com.minecreatr.underpressure.client.ClientProxy;
import com.minecreatr.underpressure.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created on 8/16/2014
 */
public class ConnectedGlass extends BasicBlock implements IConnectedTextureBlock{

    private IIcon glassNoConnect;
    private IIcon glassPillar;
    private IIcon glassUpRightCorner;
    private IIcon glassLineBottom;
    private IIcon glassAllConnect;
    private IIcon glassUpOnly;

    public ConnectedGlass(){
        super(Material.glass, ModBlocks.CONNECTED_GLASS_NAME, Textures.GLASS_NO_CONNECT);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register){
        glassNoConnect = register.registerIcon(Textures.GLASS_NO_CONNECT);
        glassPillar = register.registerIcon(Textures.GLASS_PILLAR);
        glassUpRightCorner = register.registerIcon(Textures.GLASS_UP_RIGHT_CORNER);
        glassLineBottom = register.registerIcon(Textures.GLASS_LINE_BOTTOM);
        glassAllConnect = register.registerIcon(Textures.TRANSPARENT_TEXTURE);
        glassUpOnly = register.registerIcon(Textures.GLASS_UP_ONLY);
    }

    public IIcon getConnectedIcon(IconType type){
        switch (type){
            case NON_CONNECTED: return glassNoConnect;
            case PILLAR: return glassPillar;
            case UP_RIGHT_CORNER: return glassUpRightCorner;
            case LINE_BOTTOM: return glassLineBottom;
            case ALL_CONNECTED: return glassAllConnect;
            case ONE_CONNECT: return glassUpOnly;
            default: return glassNoConnect;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return getConnectedIcon(IconType.NON_CONNECTED);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side){
        int connections = 0;
        boolean north = false;
        boolean south = false;
        boolean east = false;
        boolean west = false;
        boolean up = false;
        boolean down = false;
        if (side==0){
            //north
            if (world.getBlock(x, y, z-1) == this){
                connections++;
                north = true;
            }
            //south
            if (world.getBlock(x, y, z+1) == this){
                connections++;
                south = true;
            }
            //east
            if (world.getBlock(x+1, y, z) == this){
                connections++;
                east = true;
            }
            //west
            if (world.getBlock(x-1, y, z) == this){
                connections++;
                west = true;
            }
            IIcon curIcon;
            if (connections==2){
                curIcon = getIconFrom(side, north, south, east, west, up, down);
            }
            else {
                curIcon = getIconFrom(connections);
            }

            if (!north && south){
            }


            return curIcon;
        }
        else {
            return getConnectedIcon(IconType.NON_CONNECTED);
        }
    }

    //get icon from number of connections except 2
    public IIcon getIconFrom(int connections){
        if (connections==0){
            return getConnectedIcon(IconType.NON_CONNECTED);
        }
        else if (connections==1){
            return getConnectedIcon(IconType.ONE_CONNECT);
        }
        else if (connections==3){
            return getConnectedIcon(IconType.LINE_BOTTOM);
        }
        else if (connections==4){
            return getConnectedIcon(IconType.ALL_CONNECTED);
        }
        else {
            return getConnectedIcon(IconType.NON_CONNECTED);
        }
    }
    //get icon for 2 connections
    public IIcon getIconFrom(int side, boolean north, boolean south, boolean east, boolean west, boolean up, boolean down){
        if (side==1||side==0){
            if (north==south || west==east){
                return getConnectedIcon(IconType.PILLAR);
            }
            else {
                return getConnectedIcon(IconType.UP_RIGHT_CORNER);
            }
        }
        else if (side==2||side==3){
            if (east==west || up==down){
                return getConnectedIcon(IconType.PILLAR);
            }
            else {
                return getConnectedIcon(IconType.UP_RIGHT_CORNER);
            }
        }
        else if (side==4||side==5){
            if (north==south || up==down){
                return getConnectedIcon(IconType.PILLAR);
            }
            else {
                return getConnectedIcon(IconType.UP_RIGHT_CORNER);
            }
        }
        else {
            return getConnectedIcon(IconType.NON_CONNECTED);
        }
    }

    @Override
    public int getRenderType(){
        return ClientProxy.connectedTexturesRenderId;
    }

    @Override
    public boolean isOpaqueCube(){
        return false;
    }

    @Override
    public int getRenderBlockPass(){
        return 1;
    }
}
