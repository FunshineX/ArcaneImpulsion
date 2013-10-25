package net.funshinex.arcaneimpulsion.item;

import java.util.List;

import net.funshinex.arcaneimpulsion.ArcaneImpulsion;
import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.funshinex.arcaneimpulsion.client.keybind.KeyBindWrenchMode;
import net.funshinex.arcaneimpulsion.util.ArcaneWrenchModes;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArcaneWrench extends Item {
	
	public static final int MAX_DAMAGE = 1000;
	public static final double UPGRADE_COST = 0.33;
	public static final int ATTUNE_COST = 1;
	
	public ItemArcaneWrench(int id) {
		super(id);
		
		setMaxStackSize(1);
        setUnlocalizedName(ItemInfo.ARCANE_WRENCH_UNLOCALIZED_NAME);
        setCreativeTab(ArcaneImpulsion.tabArcaneImpulsion);
        setMaxDamage(MAX_DAMAGE);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister register) {
        itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.ARCANE_WRENCH_ICON);
    }
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		initStackTag(stack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean useExtraInformation) {
		if (stack.stackTagCompound == null) {
			return;
		}
		
		list.add("IMs: " + stack.stackTagCompound.getInteger("IMs") + "/" + stack.stackTagCompound.getInteger("MaxIMs"));
		list.add("Mode: " + stack.stackTagCompound.getInteger("WrenchMode"));
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
		if(!world.isRemote) {
			if(KeyBindWrenchMode.keyPressed) {
				KeyBindWrenchMode.keyPressed = false;
				
				EntityPlayer player = (EntityPlayer)entity;
				
				int nmode = stack.stackTagCompound.getInteger("WrenchMode");     
				
				nmode += 1;
	    		if (nmode >= ArcaneWrenchModes.values().length) nmode = 0;
	    		
	    		ArcaneWrenchModes mode = ArcaneWrenchModes.values()[nmode];
	    		player.addChatMessage("Arcane Wrench now in \u00a79" + mode.name() + "\u00a7f mode.");        		
	    	  
	    	
	    		stack.stackTagCompound.setInteger("WrenchMode", nmode);
			}
		}
	}
	
	@Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
        if(!world.isRemote) {
        	if (stack.stackTagCompound == null) {
        		initStackTag(stack);
        	}
        	
        	int ims = stack.stackTagCompound.getInteger("IMs");
        	int maxIMs = stack.stackTagCompound.getInteger("MaxIMs");
        	int nmode = stack.stackTagCompound.getInteger("WrenchMode");
        	ArcaneWrenchModes mode = ArcaneWrenchModes.values()[nmode];
        	
        	int blockId = world.getBlockId(x,y,z);
        	
        	if (player.isSneaking()) {
        		return true;     		
        	}        	
        	else if (mode == ArcaneWrenchModes.attune && BlockInfo.isAttuneableBlock(blockId)) {

	            int meta = world.getBlockMetadata(x,y,z) == 0 ? 1 : 0;	        	
	            
	            if (ims > 0) {
	            	world.setBlockMetadataWithNotify(x,y,z,meta,3);
	            	ims -= ATTUNE_COST;
	            }    
	        }
        	else if (mode == ArcaneWrenchModes.extract) {
        		
        		ims = maxIMs;

        	}
        	else if (mode == ArcaneWrenchModes.upgrade) {
        		
        		if (ims == maxIMs) {
    				maxIMs = (int)Math.ceil((double)maxIMs * (1+UPGRADE_COST));
    				ims = (int)(maxIMs * (1-UPGRADE_COST));
        		}        			
        	}
        	else if (mode == ArcaneWrenchModes.replicate) {
        		int cost = 0;

        		if (blockId == Block.cobblestone.blockID) {
					cost = 1;
				} else if (blockId == Block.dirt.blockID) {
					cost = 2;
				}
        		
        		if (cost > 0 && ims >= cost) {
        			ims = ims - cost;
        			Entity e = new EntityItem(world,x+.5,y+1.5,z+.5,new ItemStack(blockId,1,1));
        			e.motionX = 0;
        			e.motionY = 0;
        			e.motionZ = 0;
        			world.spawnEntityInWorld(e);
        		}
        	}
        	
        	stack.stackTagCompound.setInteger("IMs", ims);
        	stack.stackTagCompound.setInteger("MaxIMs", maxIMs);
        	stack.stackTagCompound.setInteger("WrenchMode", nmode);
        	
        	int percentFull = MAX_DAMAGE * ims/maxIMs ;
            stack.setItemDamage(MAX_DAMAGE - percentFull);
        	
        	return true;
        } 
        else {
            return false;
        }
    }
	
	private void initStackTag(ItemStack stack) {

		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.stackTagCompound.setInteger("IMs", 10);
		stack.stackTagCompound.setInteger("MaxIMs", 10);
		stack.stackTagCompound.setInteger("WrenchMode", ArcaneWrenchModes.wrench.ordinal());
	}
}