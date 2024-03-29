package com.minecreatr.underpressure;

import com.minecreatr.underpressure.util.BlockData;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.Constants;

import java.util.*;

/**
 * Created on 7/9/2014
 */
public class ModWorldData extends WorldSavedData{

    final static String key = "underpressure.worldData";
    public ArrayList<BlockData> portals = new ArrayList<BlockData>();
    public HashMap<UUID, Boolean> traveling = new HashMap<UUID, Boolean>();

    public ModWorldData(){
        super(key);
    }
    public ModWorldData(String nothing){
        super(key);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        //int portalsN = nbt.getInteger("portalsN");
        NBTTagList portalsL = (NBTTagList)nbt.getTag("portals");
//        System.out.println("=================================");
//        System.out.println("TagCount: "+portalsL.tagCount());
//        System.out.println("Is Working: "+nbt.getBoolean("testing"));
//        System.out.println("=================================");
        for (int i=0;i<portalsL.tagCount();i++){
            portals.add(BlockData.fromPrim(portalsL.getCompoundTagAt(i).getIntArray("locs"), portalsL.getCompoundTagAt(i).getByte("type")));
        }
        NBTTagList travelersL = (NBTTagList)nbt.getTag("travelers");
        for (int i=0;i<travelersL.tagCount();i++){
            NBTTagCompound cur = travelersL.getCompoundTagAt(i);
            traveling.put(new UUID(cur.getLong("UUIDMost"), cur.getLong("UUIDLeast")), cur.getBoolean("isTraveling"));
        }
        //System.out.println("READING NBT");
    }

    public boolean isTraveling(UUID id){
        if (traveling.get(id)==null){
            traveling.put(id, false);
        }
        return traveling.get(id);
    }
    public void startTraveling(UUID id){
        traveling.put(id, true);
    }
    public void stopTraveling(UUID id){
        traveling.put(id, false);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagList portalsL = new NBTTagList();
        //nbt.setInteger("portalsN", portals.size());
        for (int i=0;i<portals.size();i++){
            NBTTagCompound cur = new NBTTagCompound();
            cur.setIntArray("locs", portals.get(i).toPrim());
            cur.setByte("type", portals.get(i).type);
            portalsL.appendTag(cur);
        }
        nbt.setTag("portals", portalsL);
        Iterator<Map.Entry<UUID, Boolean>> t = traveling.entrySet().iterator();
        NBTTagList travelersL = new NBTTagList();
        while (t.hasNext()){
            NBTTagCompound cur = new NBTTagCompound();
            Map.Entry<UUID, Boolean> e = t.next();
            cur.setLong("UUIDLeast", e.getKey().getLeastSignificantBits());
            cur.setLong("UUIDMost", e.getKey().getMostSignificantBits());
            cur.setBoolean("isTraveling", e.getValue());
            travelersL.appendTag(cur);
        }
        nbt.setTag("travelers", travelersL);
        //nbt.setBoolean("testing", true);
        //System.out.println("WRITING NBT");
    }

    public static ModWorldData forWorld(World world) {
        // Retrieves the MyWorldData instance for the given world, creating it if necessary
        MapStorage storage = world.perWorldStorage;
        ModWorldData result = (ModWorldData)storage.loadData(ModWorldData.class, key);
        if (result == null) {
            result = new ModWorldData();
            storage.setData(key, result);
        }
        return result;
    }

    public ArrayList<BlockData> getPortals(){
        return this.portals;
    }
}
