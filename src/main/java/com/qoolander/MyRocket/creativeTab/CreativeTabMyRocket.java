package com.qoolander.MyRocket.creativeTab;


import com.qoolander.MyRocket.init.ModItems;
import com.qoolander.MyRocket.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMyRocket {
    public static final CreativeTabs MyRocketTab = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return ModItems.bluePrint;
        }


        @Override
        public String getTranslatedTabLabel() {
            return "My rocket";
        }
    };
}
