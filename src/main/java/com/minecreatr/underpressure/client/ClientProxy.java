package com.minecreatr.underpressure.client;

import com.minecreatr.underpressure.Block.ChamberBlock;
import com.minecreatr.underpressure.CommonProxy;
import com.minecreatr.underpressure.client.render.InfusionRenderer;
import com.minecreatr.underpressure.client.render.RenderConnectedTexture;
import com.minecreatr.underpressure.tile.ChamberTile;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.resources.Language;
import org.omg.PortableInterceptor.ClientRequestInfo;

/**
 * Created on 6/23/2014
 */
public class ClientProxy extends CommonProxy{
    public static int connectedTexturesRenderId = RenderingRegistry.getNextAvailableRenderId();

    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(ChamberTile.class, new InfusionRenderer());

        ISimpleBlockRenderingHandler renderConnected = new RenderConnectedTexture();
        RenderingRegistry.registerBlockHandler(connectedTexturesRenderId, renderConnected);
    }
}
