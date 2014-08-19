package com.minecreatr.underpressure;

import net.minecraft.util.IIcon;

/**
 * Created on 8/16/2014
 */
public interface IConnectedTextureBlock {

    public IIcon getConnectedIcon(IconType type);

    public IIcon getIconFrom(int connections);

    public IIcon getIconFrom(int side, boolean north, boolean south, boolean east, boolean west, boolean up, boolean down);

    public enum IconType{
            NON_CONNECTED, PILLAR, UP_RIGHT_CORNER, LINE_BOTTOM, ALL_CONNECTED, ONE_CONNECT
    }
}
