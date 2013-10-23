package net.funshinex.arcaneimpulsion.proxy;

import net.funshinex.arcaneimpulsion.client.render.RendererArcaneTemplate;
import net.funshinex.arcaneimpulsion.item.Items;
import net.minecraftforge.client.MinecraftForgeClient;


public class ClientProxy extends CommonProxy {
	@Override
    public void initSounds() {

    }

    @Override
    public void initRenderers() {
        MinecraftForgeClient.registerItemRenderer(Items.arcaneTemplate.itemID, new RendererArcaneTemplate());
    }
}
