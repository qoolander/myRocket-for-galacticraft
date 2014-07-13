package com.qoolander.MyRocket.block;


import com.qoolander.MyRocket.creativeTab.CreativeTabMyRocket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFrame extends BlockMyRocket {
    public BlockFrame(){
        super();
        this.setBlockName("buildingFrame");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube(){
        return false;
    }

}
