package net.funshinex.arcaneimpulsion.client.interfaces;

import net.funshinex.arcaneimpulsion.tileentity.TileEntityArcaneExtractor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiArcaneExtractor extends GuiContainer {

private TileEntityArcaneExtractor arcaneExtractor;
	
	public GuiArcaneExtractor(InventoryPlayer invPlayer, TileEntityArcaneExtractor arcaneExtractor) {
		super(new ContainerArcaneExtractor(invPlayer, arcaneExtractor));
		
		this.arcaneExtractor = arcaneExtractor;
		
		xSize = 176;
		ySize = 166;
	}
	
	private static final ResourceLocation texture = new ResourceLocation("arcaneimpulsion", "textures/gui/arcaneextractor.png");
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString("Arcane Extractor", 8, 6, 0x404040);
	}

}
