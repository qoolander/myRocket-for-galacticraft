package com.qoolander.MyRocket.init;

import com.qoolander.MyRocket.item.ItemBlueprint;
import com.qoolander.MyRocket.item.ItemMyRocket;
import com.qoolander.MyRocket.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {
    public static final ItemMyRocket bluePrint = new ItemBlueprint();

    public static void init(){
        GameRegistry.registerItem(bluePrint, "blueprint");
    }
}
