package com.qoolander.MyRocket.block;


import com.qoolander.MyRocket.tileEntity.TileEntityScanner;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockScanner extends BlockMyRocket{
    public BlockScanner(){
        super();
        this.setBlockName("scanner");
        GameRegistry.registerTileEntity(TileEntityScanner.class, "scanner");
    }

    public List<PosBlock> blocksPartOfFrame = new ArrayList<PosBlock>();

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

       Vector3[] blocksAdjacent;

        blocksAdjacent = new Vector3[6];


        blocksAdjacent[0] = new Vector3(x+1, y, z);
        blocksAdjacent[1] = new Vector3(x-1, y, z);
        blocksAdjacent[2] = new Vector3(x, y+1, z);
        blocksAdjacent[3] = new Vector3(x, y-1, z);
        blocksAdjacent[4] = new Vector3(x, y, z+1);
        blocksAdjacent[5] = new Vector3(x, y, z-1);

        boolean foundFrame = false;


        Vector3 FramePoint;
        for(int f = 0; f<6; f++){
            Block blockToTest = world.getBlock(blocksAdjacent[f].x, blocksAdjacent[f].y, blocksAdjacent[f].z);
            if(blockToTest instanceof BlockFrame){
                foundFrame =true;
                FramePoint = blocksAdjacent[f];
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

    boolean allreadyExists;

    public boolean IsNextToCuboidFrames(Vector3 blockPos, World world){
        List<PosBlock> frames = new ArrayList<PosBlock>();


        Vector3[] adjacentPositions;
        adjacentPositions = new Vector3[6];

        int x = blockPos.x;
        int y = blockPos.y;
        int z = blockPos.z;

        adjacentPositions[0] = new Vector3(x+1, y, z);
        adjacentPositions[1] = new Vector3(x-1, y, z);
        adjacentPositions[2] = new Vector3(x, y+1, z);
        adjacentPositions[3] = new Vector3(x, y-1, z);
        adjacentPositions[4] = new Vector3(x, y, z+1);
        adjacentPositions[5] = new Vector3(x, y, z-1);
        for (int i = 0; i < adjacentPositions.length; i++) {
            Block blockToTest = world.getBlock(adjacentPositions[i].x, adjacentPositions[i].y, adjacentPositions[i].z);

            if(blockToTest instanceof BlockFrame){
                PosBlock posBlock = new PosBlock(adjacentPositions[i], blockToTest);

                allreadyExists = false;
                for(int l = 0; l < blocksPartOfFrame.size(); l++){
                    if(blocksPartOfFrame.toArray()[l] == posBlock){
                        allreadyExists = true;
                        break;
                    }
                }
                if(!allreadyExists){
                    frames.add(posBlock);
                }
            }
        }

        return false;
    }
}

class PosBlock {
    Vector3 position;
    Block block;


    public PosBlock(Vector3 Pposition, Block Pblock){
        position = Pposition;
        block = Pblock;
    }

    public PosBlock(){

    }
}

class Vector3{
    int x;
    int y;
    int z;

    Vector3(int _x, int _y, int _z){
        x = _x;
        y = _y;
        z = _z;
    }

}