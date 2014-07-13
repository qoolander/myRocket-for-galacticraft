package com.qoolander.MyRocket.block;

import com.qoolander.MyRocket.creativeTab.CreativeTabMyRocket;
import com.qoolander.MyRocket.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;


public class BlockMyRocket extends Block {
    public BlockMyRocket(Material material){
        super(material);
        this.setCreativeTab(CreativeTabMyRocket.MyRocketTab);
    }
    public BlockMyRocket(){
        super(Material.rock);
        this.setCreativeTab(CreativeTabMyRocket.MyRocketTab);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
