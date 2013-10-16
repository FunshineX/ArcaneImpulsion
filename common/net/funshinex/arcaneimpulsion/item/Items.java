package net.funshinex.arcaneimpulsion.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {
	public static Item arcaneTemplate;

    public static void init() {
    	arcaneTemplate = new ItemArcaneTemplate(ItemInfo.ARCANE_TEMPLATE_ID);

    }

    public static void addNames() {
        LanguageRegistry.addName(arcaneTemplate, ItemInfo.ARCANE_TEMPLATE_NAME);
    }
}
