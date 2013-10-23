package net.funshinex.arcaneimpulsion.client.render;

import net.funshinex.arcaneimpulsion.item.Items;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RendererArcaneTemplate implements IItemRenderer {
	private static RenderItem renderItem = new RenderItem();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return item.itemID == Items.arcaneTemplate.itemID;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
		
		Icon icon = item.getIconIndex();
		renderItem.renderIcon(0, 0, icon, 16, 16);		
		
		String text = "";
		switch(item.getItemDamage()) {
		case 1:			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glScalef(.5F, .5F, .5F);
			text = "cobble";
			fontRenderer.drawStringWithShadow(text, 7, 13, 0xFFFFFF);
			break;
		
		case 2:
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glScalef(.5F, .5F, .5F);
			text = "dirt";
			fontRenderer.drawStringWithShadow(text, 7, 13, 0xFFFFFF);
			break;
			
		case 3:
		default:
			icon = new ItemStack(1,1,1).getIconIndex();
			GL11.glScalef(.75F, .75F, .75F);
			renderItem.renderIcon(3, 3, icon, 8, 16);
			break;
		}
		
	}

}
