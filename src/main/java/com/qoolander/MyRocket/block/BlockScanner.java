package com.qoolander.MyRocket.block;


import com.qoolander.MyRocket.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import scala.reflect.macros.runtime.Names;

import javax.swing.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        Block[] blocksAdjacent;

        blocksAdjacent = new Block[6];


        blocksAdjacent[0] = world.getBlock(x+1, y,z);
        blocksAdjacent[1] = world.getBlock(x-1, y,z);
        blocksAdjacent[2] = world.getBlock(x, y+1,z);
        blocksAdjacent[3] = world.getBlock(x, y-1,z);
        blocksAdjacent[4] = world.getBlock(x, y,z+1);
        blocksAdjacent[5] = world.getBlock(x, y,z-1);

        boolean foundFrame = false;

        for(int f = 0; f<6; f++){
            if(blocksAdjacent[f] instanceof BlockFrame){
                foundFrame =true;
            }
        }
        if(foundFrame){
            System.out.println("Found frame next to scanner. Scanner position x: " +x +", y: " +y +", z: " +z);
        }else{
            System.out.println("No frames found by scanner!");
        }

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.topIcon : (side == 0 ? this.bottomIcon : (side != metadata ? sideIcon : frontIcon));
    }


}
