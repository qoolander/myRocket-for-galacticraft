package com.qoolander.MyRocket.item;


import com.qoolander.MyRocket.creativeTab.CreativeTabMyRocket;
import com.qoolander.MyRocket.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemBlueprint extends ItemMyRocket {

    public ItemBlueprint(){
        super();
        this.setUnlocalizedName("bluePrint");
    }

    public String ModelLoction = "Error: cannot find file";

    //Set what data goes into the item
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
        //LogHelper.info("onCreated entered!");
        par1ItemStack.stackTagCompound = new NBTTagCompound();
        par1ItemStack.stackTagCompound.setString("owner", par2EntityPlayer.getDisplayName());
        par1ItemStack.stackTagCompound.setInteger("code", (int)(Math.random()*Integer.MAX_VALUE));
        par1ItemStack.stackTagCompound.setString("model", this.ModelLoction);
        return true;
    }

    //display daata in tooltip when item selected
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        if (itemStack.stackTagCompound != null) {
            String owner = itemStack.stackTagCompound.getString("owner");
            String modelLocation = itemStack.stackTagCompound.getString("model");
            int code = itemStack.stackTagCompound.getInteger("code");
            list.add("model : " + modelLocation);
            list.add("owner: " + owner);
            if (owner.equals(player.getDisplayName())) {
                list.add(EnumChatFormatting.GREEN + "code: " + code);
            } else {
                list.add(EnumChatFormatting.RED + "code: "
                        + EnumChatFormatting.OBFUSCATED + code);
            }
        }
       //LogHelper.error("addInfo method entered");
    }



}
