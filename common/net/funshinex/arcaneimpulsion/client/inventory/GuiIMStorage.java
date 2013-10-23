package net.funshinex.arcaneimpulsion.client.inventory;

import net.funshinex.arcaneimpulsion.tileentity.TileEntityIMStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiIMStorage extends GuiContainer {

private TileEntityIMStorage imStorage;
	
	public GuiIMStorage(InventoryPlayer invPlayer, TileEntityIMStorage imStorage) {
		super(new ContainerIMStorage(invPlayer, imStorage));
		
		this.imStorage = imStorage;
		
		xSize = 176;
		ySize = 166;
	}
	
	private static final ResourceLocation texture = new ResourceLocation("arcaneimpulsion", "textures/gui/imstorage.png");
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRenderer.drawString("IM Storage", 8, 6, 0x404040);
		
		fontRenderer.drawString(imStorage.getInternalStorage() + " IMs", 20, 20, 0x404040);
		
	}

}
