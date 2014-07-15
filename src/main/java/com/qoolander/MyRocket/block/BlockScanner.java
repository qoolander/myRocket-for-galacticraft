package com.qoolander.MyRocket.block;


import com.qoolander.MyRocket.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import scala.reflect.macros.runtime.Names;

import javax.swing.*;

public class BlockScanner extends BlockMyRocket{
    public BlockScanner(){
        super();
        this.setBlockName("scanner");
        GameRegistry.registerTileEntity(com.qoolander.MyRocket.tileEntity.scanner.class, "scanner");
    }

    @SideOnly(Side.CLIENT)
    public static Icon topIcon;
    @SideOnly(Side.CLIENT)
    public static Icon bottomIcon;
    @SideOnly(Side.CLIENT)
    public static Icon sideIcon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon) {
        topIcon = icon.registerIcon((this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") +1) +"_top"));
        bottomIcon = icon.registerIcon((this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") +1) + "_bottom"));
        sideIcon = icon.registerIcon((this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") +1) + "_side"));
    }
}
