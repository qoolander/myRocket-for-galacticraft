package com.qoolander.MyRocket;

import com.qoolander.MyRocket.handler.ConfigurationHandler;
import com.qoolander.MyRocket.init.ModBlocks;
import com.qoolander.MyRocket.init.ModItems;
import com.qoolander.MyRocket.proxy.IProxy;
import com.qoolander.MyRocket.reference.Reference;
import com.qoolander.MyRocket.utility.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

import java.net.NetworkInterface;

@Mod(modid = Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION)
public class MyRocket {

    @Mod.Instance(Reference.MOD_ID)
    public static MyRocket instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event) {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        ModItems.init();
        ModBlocks.init();
        LogHelper.info("Pre initialization complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LogHelper.info("Initialization complete!");
    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {

        LogHelper.info("Post initialization complete!");
    }

}
