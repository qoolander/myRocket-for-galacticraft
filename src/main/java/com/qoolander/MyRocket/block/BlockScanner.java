package com.qoolander.MyRocket.block;


import com.qoolander.MyRocket.tileEntity.TileEntityScanner;
import com.qoolander.MyRocket.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import io.netty.handler.logging.LogLevel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
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

        LogHelper.info(IsNextToCuboidFrames(new Vector3(x, y, z), world) ? "found cube!" : "Cube not found");

    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.topIcon : (side == 0 ? this.bottomIcon : (side != metadata ? sideIcon : frontIcon));
    }

    boolean allreadyExists;

    public boolean IsNextToCuboidFrames(Vector3 blockPos, World world){
        List<PosBlock> frames = new ArrayList<PosBlock>();



        int x = blockPos.x;
        int y = blockPos.y;
        int z = blockPos.z;

        LogHelper.info("Adjacent frames: " +getNumberOfAdjacentFrames(blockPos, world));

        PosBlock innitialBlock = getAdjacentBlock(blockPos, world);

        PosBlock firstCorner = new PosBlock();

        if(innitialBlock != null){
            //If next to a block, continue checking

            if(!isCorner(innitialBlock.position, world)){
                //Not On a corner
                firstCorner.position = getCorner(innitialBlock.position, world, Dir.posX);
                if(firstCorner.position==null){
                    firstCorner.position = getCorner(innitialBlock.position, world, Dir.posY);
                }
                if(firstCorner.position==null){
                    firstCorner.position = getCorner(innitialBlock.position, world, Dir.posZ);
                }
            }else{
                //Is on a corner
                firstCorner = innitialBlock.clone();
            }

            return firstCorner.position != null;
        }else{
            return false;
        }
    }

    public int getNumberOfAdjacentFrames(Vector3 blockPos, World world){
        Vector3[] adjacentBlocks = new Vector3[6];

        int result = 0;

        int x = blockPos.x;
        int y = blockPos.y;
        int z = blockPos.z;

        adjacentBlocks[0] = new Vector3(x+1, y, z);
        adjacentBlocks[1] = new Vector3(x-1, y, z);
        adjacentBlocks[2] = new Vector3(x, y+1, z);
        adjacentBlocks[3] = new Vector3(x, y-1, z);
        adjacentBlocks[4] = new Vector3(x, y, z+1);
        adjacentBlocks[5] = new Vector3(x, y, z-1);

        for(int i = 0; i < adjacentBlocks.length; i++){
            if(world.getBlock(adjacentBlocks[i].x, adjacentBlocks[i].y, adjacentBlocks[i].z) instanceof BlockFrame){
                result++;
            }
        }

        return result;
    }

    PosBlock getAdjacentBlock(Vector3 blockPos, World world){
        Vector3[] adjacentBlocks = new Vector3[6];

        int x = blockPos.x;
        int y = blockPos.y;
        int z = blockPos.z;

        adjacentBlocks[0] = new Vector3(x+1, y, z);
        adjacentBlocks[1] = new Vector3(x-1, y, z);
        adjacentBlocks[2] = new Vector3(x, y+1, z);
        adjacentBlocks[3] = new Vector3(x, y-1, z);
        adjacentBlocks[4] = new Vector3(x, y, z+1);
        adjacentBlocks[5] = new Vector3(x, y, z-1);

        for(int i = 0; i < adjacentBlocks.length; i++){
            if(world.getBlock(adjacentBlocks[i].x, adjacentBlocks[i].y, adjacentBlocks[i].z) instanceof BlockFrame){
                return new PosBlock(adjacentBlocks[i], world.getBlock(adjacentBlocks[i].x, adjacentBlocks[i].y, adjacentBlocks[i].z));
            }
        }
        return null;
    }

    enum Dir {
        posX, posY, posZ, negX, negY, negZ
    }

    Vector3 getNextFrame(Vector3 pos, World world, Dir dir) {
        Vector3 posToTest = pos.clone();
        switch (dir) {
            case posX:
                posToTest.x++;
                break;
            case posY:
                posToTest.y++;
                break;
            case posZ:
                  posToTest.z++;
                break;
            case negX:
                posToTest.x--;
                break;
            case negY:
                posToTest.y--;
                break;
            case negZ:
                posToTest.z--;
                break;
        }
        if (world.getBlock(posToTest.x, posToTest.y, posToTest.z) instanceof BlockFrame) {
            return posToTest;
        }else{
            return null;
        }
    }

    public Vector3 getCorner(Vector3 pos, World world, Dir dir) {
        Vector3 result = pos.clone();
        Vector3 nextFramePos = pos.clone();
        while (true) {
            nextFramePos = getNextFrame(nextFramePos, world, dir);
            if (nextFramePos == null) return null;

            if (isCorner(nextFramePos, world)) {
                return nextFramePos;
            }
        }
    }

    public boolean isCorner(Vector3 pos, World world){
        if(getNumberOfAdjacentFrames(pos, world) == 3) {
            if ((getSidesOfConnectionsFromFrames(pos, world).contains(Dir.posX) || getSidesOfConnectionsFromFrames(pos, world).contains(Dir.negX)) && (getSidesOfConnectionsFromFrames(pos, world).contains(Dir.posY) || getSidesOfConnectionsFromFrames(pos, world).contains(Dir.negY)) && (getSidesOfConnectionsFromFrames(pos, world).contains(Dir.posZ) || getSidesOfConnectionsFromFrames(pos, world).contains(Dir.negZ))) {
                return true;
            }
        }
        return false;
    }

    public EnumSet<Dir> getSidesOfConnectionsFromFrames(Vector3 pos, World world){
        EnumSet<Dir> result = EnumSet.of(Dir.negX,Dir.posX);
        result.clear();

        if(world.getBlock(pos.x + 1, pos.y, pos.z) instanceof BlockFrame){
            result.add(Dir.posX);
        }
        if(world.getBlock(pos.x - 1, pos.y, pos.z) instanceof BlockFrame){
            result.add(Dir.negX);
        }
        if(world.getBlock(pos.x, pos.y + 1, pos.z) instanceof BlockFrame){
            result.add(Dir.posY);
        }
        if(world.getBlock(pos.x, pos.y - 1, pos.z) instanceof BlockFrame){
            result.add(Dir.negY);
        }
        if(world.getBlock(pos.x, pos.y, pos.z + 1) instanceof BlockFrame){
            result.add(Dir.posZ);
        }
        if(world.getBlock(pos.x, pos.y, pos.z - 1) instanceof BlockFrame){
            result.add(Dir.negZ);
        }

        return result;

    }

}

class PosBlock {
    Vector3 position;
    Block block;

    public PosBlock(Vector3 Pposition, Block Pblock){
        position = Pposition;
        block = Pblock;
    }

    public PosBlock clone(){
        return new PosBlock(this.position, this.block);
    }

    public PosBlock(){

    }
}

