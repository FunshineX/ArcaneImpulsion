package net.funshinex.arcaneimpulsion;

import net.funshinex.arcaneimpulsion.item.Items;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabArcaneImpulsion extends CreativeTabs {

	public CreativeTabArcaneImpulsion(int tabID, String tabLabel) {
		super(tabID, tabLabel);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return Items.arcaneOrb.itemID;
	}
	
	

}
