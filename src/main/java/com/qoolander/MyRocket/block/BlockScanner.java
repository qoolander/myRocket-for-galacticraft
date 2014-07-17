package com.qoolander.MyRocket.block;


import com.qoolander.MyRocket.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import javax.swing.Icon;

import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import scala.reflect.macros.runtime.Names;

import javax.swing.*;

public class BlockScanner extends BlockMyRocket{
    public BlockScanner(){
        super();
        this.setBlockName("scanner");
        GameRegistry.registerTileEntity(com.qoolander.MyRocket.tileEntity.scanner.class, "scanner");
    }

    @SideOnly(Side.CLIENT)
    public static IIcon topIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon bottomIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon sideIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon frontIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon backIcon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
       topIcon = icon.registerIcon("myrocket:scanner_top");
       bottomIcon = icon.registerIcon("myrocket:scanner_bottem");
       sideIcon = icon.registerIcon("myrocket:scanner_sides");
       frontIcon = icon.registerIcon("myrocket:scanner_front");
       backIcon = icon.registerIcon("myrocket:scanner_back");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.topIcon : (side == 0 ? this.bottomIcon : (side != metadata ? sideIcon : frontIcon));
    }

}
