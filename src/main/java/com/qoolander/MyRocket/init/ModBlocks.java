package com.qoolander.MyRocket.init;

import com.qoolander.MyRocket.block.BlockFrame;
import com.qoolander.MyRocket.block.BlockMyRocket;
import com.qoolander.MyRocket.block.BlockScanner;
import com.qoolander.MyRocket.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockSand;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockMyRocket frame = new BlockFrame();
    public static final BlockMyRocket scanner = new BlockScanner();

    public static void init(){
        GameRegistry.registerBlock(frame, "buildingFrame");
        GameRegistry.registerBlock(scanner, "scanner");
    }
}
