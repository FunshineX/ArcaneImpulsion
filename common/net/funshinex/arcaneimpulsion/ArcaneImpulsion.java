package net.funshinex.arcaneimpulsion;

import net.funshinex.arcaneimpulsion.block.Blocks;
import net.funshinex.arcaneimpulsion.client.inventory.GuiHandler;
import net.funshinex.arcaneimpulsion.config.ConfigHandler;
import net.funshinex.arcaneimpulsion.config.IMValues;
import net.funshinex.arcaneimpulsion.item.Items;
import net.funshinex.arcaneimpulsion.network.PacketHandler;
import net.funshinex.arcaneimpulsion.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=ModInformation.ID, name=ModInformation.NAME, version=ModInformation.VERSION)
@NetworkMod(channels = {ModInformation.CHANNEL}, clientSideRequired=true, serverSideRequired=false, packetHandler=PacketHandler.class)
public class ArcaneImpulsion {
	
	@Instance(ModInformation.ID)
	public static ArcaneImpulsion instance;
	
	@SidedProxy(clientSide="net.funshinex.arcaneimpulsion.proxy.ClientProxy", serverSide="net.funshinex.arcaneimpulsion.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tabArcaneImpulsion = new CreativeTabArcaneImpulsion(CreativeTabs.getNextID(), ModInformation.NAME);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		IMValues.init(event.getSuggestedConfigurationFile());
		
		Items.init();
		Blocks.init();
		
		proxy.initSounds();
        proxy.initRenderers();
        proxy.addRecipes();
        proxy.addStringLocalizations();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		Items.addNames();
		Blocks.addNames();
		
		Blocks.registerTileEntities();	
		
        new GuiHandler();
        
        proxy.addKeyBindings();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
