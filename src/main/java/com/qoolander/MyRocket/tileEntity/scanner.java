package com.qoolander.MyRocket.tileEntity;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.Packet.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class scanner extends TileEntity{

    public int test;

    @Override
    public void writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);
        par1.setInteger("Test", test);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);
        this.test = par1.getInteger("customField");
    }



}
