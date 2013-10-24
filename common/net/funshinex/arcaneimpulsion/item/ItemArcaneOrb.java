package net.funshinex.arcaneimpulsion.item;

import net.funshinex.arcaneimpulsion.ArcaneImpulsion;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArcaneOrb extends Item {
	
	public ItemArcaneOrb(int id) {
		super(id);
		
		setMaxStackSize(64);
        setUnlocalizedName(ItemInfo.ARCANE_ORB_UNLOCALIZED_NAME);
        setCreativeTab(ArcaneImpulsion.tabArcaneImpulsion);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.ARCANE_ORB_ICON);
    }
}