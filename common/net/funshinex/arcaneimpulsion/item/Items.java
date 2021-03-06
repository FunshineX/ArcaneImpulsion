package net.funshinex.arcaneimpulsion.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {
	public static Item arcaneTemplate;
	public static Item arcaneOrb;
	public static Item arcaneWrench;

    public static void init() {
    	arcaneTemplate = new ItemArcaneTemplate(ItemInfo.ARCANE_TEMPLATE_ID);
    	arcaneOrb = new ItemArcaneOrb(ItemInfo.ARCANE_ORB_ID);
    	arcaneWrench = new ItemArcaneWrench(ItemInfo.ARCANE_WRENCH_ID);

    }

    public static void addNames() {
        LanguageRegistry.addName(arcaneTemplate, ItemInfo.ARCANE_TEMPLATE_NAME);
        LanguageRegistry.addName(arcaneOrb, ItemInfo.ARCANE_ORB_NAME);
        LanguageRegistry.addName(arcaneWrench, ItemInfo.ARCANE_WRENCH_NAME);
    }
}
