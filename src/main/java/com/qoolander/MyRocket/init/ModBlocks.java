package com.qoolander.MyRocket.init;

import com.qoolander.MyRocket.block.BlockFrame;
import com.qoolander.MyRocket.block.BlockMyRocket;
import com.qoolander.MyRocket.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockMyRocket frame = new BlockFrame();

    public static void init(){
        GameRegistry.registerBlock(frame, "buildingFrame");
    }
}
