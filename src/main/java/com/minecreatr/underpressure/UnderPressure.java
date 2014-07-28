package com.minecreatr.underpressure;

import com.minecreatr.underpressure.Block.EssenceCollectorBlock;
import com.minecreatr.underpressure.Block.ModBlocks;
import com.minecreatr.underpressure.Commands.CommandListPortals;
import com.minecreatr.underpressure.Items.ModItems;
import com.minecreatr.underpressure.effect.ModPotions;
import com.minecreatr.underpressure.event.MainModEventListener;
import com.minecreatr.underpressure.network.PacketHandler;
import com.minecreatr.underpressure.reference.ExternalMods;
import com.minecreatr.underpressure.reference.Reference;
import com.minecreatr.underpressure.tile.ChamberTile;
import com.minecreatr.underpressure.tile.EssenseCollectorTile;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created on 6/23/2014
 */

@Mod(modid= Reference.MODID, name="Under Pressure", version="Alpha 0.01a")
public class UnderPressure {

    public boolean isBuildcraftEnabled;
    public static CreativeTabs tab;
    public static String prefix = "[UPMagic] ";

    @Mod.Instance
    public static UnderPressure instance = new UnderPressure();

    @SidedProxy(clientSide="com.minecreatr.underpressure.client.ClientProxy", serverSide="com.minecreatr.underpressure.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        if (Loader.isModLoaded(ExternalMods.BUILDCRAFT)){
            this.isBuildcraftEnabled=true;
        }
        else {
            this.isBuildcraftEnabled=false;
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        //setupPotions();
        PacketHandler.init();
        //ModPotions.initPotions();
        tab = new UnderPressureCreativeTab("underpressure.tabDefualt");
        ModBlocks.init();
        ModItems.init();
        GameRegistry.registerTileEntity(ChamberTile.class, "underpressure.chamberTile");
        GameRegistry.registerTileEntity(EssenseCollectorTile.class, "underpressure.essenceCollectorTile");
        InfusionRegistry.addInfusion(Items.iron_ingot, Items.diamond, 1000);
        MinecraftForge.EVENT_BUS.register(new MainModEventListener().initStuff());
    }

    public void setupPotions(){
        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[]) f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception e) {
                System.err.println(prefix + "Severe error, please report the following error to the mod author (minecreatr):");
                System.err.println(e);
            }
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandListPortals());
    }
}
