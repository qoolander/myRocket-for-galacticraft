package com.qoolander.MyRocket.init;

import com.qoolander.MyRocket.item.ItemBlueprint;
import com.qoolander.MyRocket.item.ItemMyRocket;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Sam on 12/07/2014.
 */
public class ModItems {
    public static final ItemMyRocket bluePrint = new ItemBlueprint();

    public static void init(){
        GameRegistry.registerItem(bluePrint, "blueprint");
    }
}
