package net.funshinex.arcaneimpulsion.client.interfaces;

import org.lwjgl.opengl.GL11;

import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneCollector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiArcaneCollector extends GuiContainer {

private TileEntityArcaneCollector arcaneCollector;
	
	public GuiArcaneCollector(InventoryPlayer invPlayer, TileEntityArcaneCollector arcaneCollector) {
		super(new ContainerArcaneCollector(invPlayer, arcaneCollector));
		
		this.arcaneCollector = arcaneCollector;
		
		xSize = 176;
		ySize = 166;
	}
	
	private static final ResourceLocation texture = new ResourceLocation("arcaneimpulsion", "textures/gui/arcanecollector.png");
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString("Arcane Collector", 8, 6, 0x404040);
		
		
		
		fontRenderer.drawString(arcaneCollector.getInternalStorage() + " IMs", 20, 20, 0x404040);
	}

}
