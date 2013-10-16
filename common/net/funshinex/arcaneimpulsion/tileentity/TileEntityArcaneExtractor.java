package net.funshinex.arcaneimpulsion.tileentity;

import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityArcaneExtractor extends TileEntity implements IInventory {

	private static final int MAX_STORAGE = 256;
	private static final int DEFAULT_EXTRACT_INTERVAL = 100;
	
	private int internalStorage;	
	private int extractInterval;
	
	private ItemStack[] items;
	
	public TileEntityArcaneExtractor() {
		extractInterval = DEFAULT_EXTRACT_INTERVAL;
		
		items = new ItemStack[1];
	}
	
	@Override
    public void updateEntity() {
		if (!worldObj.isRemote) {
        	
        	if (extractInterval == 0) {
		
        		if (internalStorage == 0) {
        			tryExtract();	
        		}

				extractInterval = DEFAULT_EXTRACT_INTERVAL;
        	}
        	extractInterval--;
        }
    }

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);
		
		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			}
			else {
				itemstack = itemstack.splitStack(count);
				onInventoryChanged();
			}
		}
		
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, null);
		
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;
		
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
		onInventoryChanged();	
	}

	@Override
	public String getInvName() {
		return "InventoryArcaneExtractor";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return itemstack.itemID == Block.dirt.blockID ||
			   itemstack.itemID == Block.cobblestone.blockID;
	}   
	

	
	@Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);  
        
    	NBTTagList items = new NBTTagList();
		
		for (int i=0; i<getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);
			
			if(stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}
		
		compound.setTag("Items", items);
       
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList items = compound.getTagList("Items");
		
		for (int i=0; i<items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
			
			int slot = item.getByte("Slot");
			
			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
    }
    
    @Override
	public void onInventoryChanged() {
		super.onInventoryChanged();
	}
    
    private void tryExtract() {
    	
    	if (items[0] == null || items[0].stackSize < 1)
    		return;
    	
    	int amount = 0;
    	ItemStack itemStack = items[0];
    	
    	
    	if (itemStack.itemID == Block.cobblestone.blockID)
    		amount = 1;
    	else if (itemStack.itemID == Block.dirt.blockID)
    		amount = 2;
    		
    	if (amount > 0 && amount + internalStorage <= MAX_STORAGE) {
    		internalStorage += amount;
    		itemStack.stackSize--;   
    		
    		if (itemStack.stackSize == 0) {
    			items[0] = null;
    		}
    	}
    	
    	if (internalStorage > 0) {
    		System.out.println("Extractor collected: " + amount + ", total: " + internalStorage);
    	}
    }
    
    public int getTimer() {
    	return extractInterval;
    }
    
    public int getInternalStorage() {
    	return internalStorage;
    }
    
    public void setInternalStorage(int amount) {
    	internalStorage = amount;
    }
    
    public int requestEnergy(int amount) {
		
		if (amount > internalStorage) { 
			amount = internalStorage;
		}
		
		internalStorage -= amount;
		
		return amount;		
	}
    
}
