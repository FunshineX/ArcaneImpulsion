package net.funshinex.arcaneimpulsion.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArcaneTemplate extends Item {
	
	public ItemArcaneTemplate(int id) {
		super(id);
		
		setMaxStackSize(1);
        setUnlocalizedName(ItemInfo.ARCANE_TEMPLATE_UNLOCALIZED_NAME);
        setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.ARCANE_TEMPLATE_ICON);
    }
}
