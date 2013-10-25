package net.funshinex.arcaneimpulsion.proxy;

import net.funshinex.arcaneimpulsion.block.Blocks;
import net.funshinex.arcaneimpulsion.client.keybind.KeyBindWrenchMode;
import net.funshinex.arcaneimpulsion.item.Items;
import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {
	
	public void initSounds() {
    }

    public void initRenderers() {
    }
    
    public void addKeyBindings() {
    	KeyBinding[] keys = {new KeyBinding("ArcaneWrenchModeToggle", Keyboard.KEY_M)};
    	boolean[] repeats = {false};
    	KeyBindingRegistry.registerKeyBinding(new KeyBindWrenchMode(keys, repeats));
    }
    
    public void addRecipes() {
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(Items.arcaneTemplate),
    			new Object[] {
    				Item.paper,
    				Items.arcaneOrb}
				);
    	
    	GameRegistry.addRecipe(new ItemStack(Items.arcaneOrb), 
    			" G ", 
    			"GPG", 
    			" G ", 
    			'G', Block.thinGlass,
    			'P', new ItemStack(Item.dyePowder,1,5));
    	
    	GameRegistry.addRecipe(new ItemStack(Items.arcaneWrench),
    			"IOI",
    			" I ",
    			" I ",
    			'I', Item.ingotIron,
    			'O', Items.arcaneOrb);
    	
    	GameRegistry.addRecipe(new ItemStack(Blocks.impulsionDriveBasic),
    			"PPP",
    			"C|C",
    			"C_C",
    			'P', Block.planks,
    			'C', Block.cobblestone,
    			'|', Item.stick,
    			'_', Block.pressurePlatePlanks);    	

    	GameRegistry.addRecipe(new ItemStack(Blocks.arcaneCollector),
    			"IGI",
    			"S S",
    			"SSS",
    			'I', Item.ingotIron,
    			'G', Block.thinGlass,
    			'S', Block.stone);
    	
    	GameRegistry.addRecipe(new ItemStack(Blocks.arcaneExtractor),
    			"OGO",
    			"GIG",
    			"OGO",
    			'O', Block.obsidian,
    			'G', Block.thinGlass,
    			'I', Blocks.impulsionDriveBasic);
    	
    	GameRegistry.addRecipe(new ItemStack(Blocks.arcaneCondenser),
    			"OGO",
    			"GCG",
    			"OGO",
    			'O', Block.obsidian,
    			'G', Block.thinGlass,
    			'C', Blocks.arcaneCollector);
    	
    	GameRegistry.addRecipe(new ItemStack(Blocks.imStorage),
    			"NGN",
    			"GOG",
    			"NGN",
    			'N', Item.goldNugget,
    			'G', Block.thinGlass,
    			'O', Items.arcaneOrb);
    	
    }
    
    public void addStringLocalizations() {
    	LanguageRegistry.instance().addStringLocalization("itemGroup.ArcaneImpulsion", "en_US", "Arcane Impulsion");
    }
}
