package net.funshinex.arcaneimpulsion.client.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiArcaneWrench extends GuiContainer {

	public GuiArcaneWrench(InventoryPlayer invPlayer, InventoryArcaneWrench invWrench) { 
		super(new ContainerArcaneWrench(invPlayer, invWrench));
		
		xSize = 176;
		ySize = 166;
	}
	
	private static final ResourceLocation texture = new ResourceLocation("arcaneimpulsion", "textures/gui/arcanewrench.png");
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
				
	}
}