package net.funshinex.arcaneimpulsion.tileentity;

import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.funshinex.arcaneimpulsion.item.ItemInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityArcaneCondenser extends TileEntity implements IInventory {

	private static final int DEFAULT_UPDATE_INTERVAL = 40;
	private static final int DEFAULT_CONDENSE_INTERVAL = 5;
	
	private int availableIMs;	
	private int updateInterval;	
	private int condenseInterval;
	
	private ItemStack[] items;
	
	public TileEntityArcaneCondenser()  {
		updateInterval = DEFAULT_UPDATE_INTERVAL;
		condenseInterval = DEFAULT_CONDENSE_INTERVAL;
		
		items = new ItemStack[2];
	}
	
	@Override
    public void updateEntity() {
		if (!worldObj.isRemote) {
        	
			if (updateInterval == 0) {
        		
				updateNearbyStorage();

				updateInterval = DEFAULT_UPDATE_INTERVAL;
			}        	
			updateInterval--;
        	
        	if (condenseInterval == 0) {
        		
        		if(tryCondense())        		
        			condenseInterval = DEFAULT_CONDENSE_INTERVAL;
        	}
        	
        	condenseInterval--;
        	if (condenseInterval < 0) condenseInterval = 0;
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
		return "InventoryArcaneCondenser";
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
		
		switch(i) {
			case 0:
				return itemstack.itemID == ItemInfo.ARCANE_TEMPLATE_ID;
			default:
				return false;
		}	
	}
	
	@Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);   
       
        compound.setInteger("CondenseInterval", condenseInterval); 
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        condenseInterval = compound.getInteger("CondenseInterval"); 
    }   
    
    private void updateNearbyStorage() {
    	
		int amount = 0;
		for (int i=-1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {
				if (worldObj.getBlockId(xCoord+i, yCoord, zCoord+j) == BlockInfo.ARCANE_COLLECTOR_ID) {
					TileEntityArcaneCollector teArcaneCollector = (TileEntityArcaneCollector)worldObj.getBlockTileEntity(xCoord+i, yCoord, zCoord+j);
				
					amount += teArcaneCollector.getInternalStorage();
				}
				else if (worldObj.getBlockId(xCoord+i, yCoord, zCoord+j) == BlockInfo.IM_STORAGE_ID) {
					TileEntityIMStorage teIMStorage = (TileEntityIMStorage)worldObj.getBlockTileEntity(xCoord+i, yCoord, zCoord+j);
				
					amount += teIMStorage.getInternalStorage();
				}
				
			}
		}
		
		availableIMs = amount;
		
		if (amount > 0) {
			//System.out.println("IMs available to condenser " + amount);
		}
		
    }
    
    private boolean tryCondense() {
    	
    	if (items[0] == null || items[0].stackSize < 1 || getNearbyStorage() == 0)
    		return false;
    	
    	switch (items[0].getItemDamage()) {
    		case 1:
    			if (availableIMs > 1)
    				if (items[1] == null)
    					items[1] = new ItemStack(Block.cobblestone,1);
    				else if (items[1].isItemEqual(new ItemStack(Block.cobblestone,1)) && items[1].stackSize < 64)
    					items[1].stackSize++;
    			
    			break;
    		
    		case 2:
    			if (availableIMs > 2)
    				if (items[1] == null)
    					items[1] = new ItemStack(Block.dirt,1);
    				else if (items[1].isItemEqual(new ItemStack(Block.dirt,1)) && items[1].stackSize < 64)
    					items[1].stackSize++;
    			break;
    	}
    	
    	return true;
    }
    
    public int getNearbyStorage() {
    	
    	return availableIMs;
    }
    
    public void setNearbyStorage(int amount) {
    	availableIMs = amount;
    }	
}
