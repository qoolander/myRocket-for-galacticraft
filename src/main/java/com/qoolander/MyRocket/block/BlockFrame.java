package com.qoolander.MyRocket.block;


import com.qoolander.MyRocket.creativeTab.CreativeTabMyRocket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class BlockFrame extends BlockMyRocket {
    public BlockFrame(){
        super();
        this.setBlockName("buildingFrame");
        this.canRenderInPass(1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube(){
        return false;
    }



}
