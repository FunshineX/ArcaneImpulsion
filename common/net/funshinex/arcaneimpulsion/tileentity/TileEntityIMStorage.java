package net.funshinex.arcaneimpulsion.tileentity;

import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityIMStorage extends TileEntity {

	private static final int MAX_STORAGE = 128;
	private static final int DEFAULT_COLLECT_INTERVAL = 40;
	
	private int internalStorage;	
	private int collectInterval;
	
	public TileEntityIMStorage() {
		collectInterval = DEFAULT_COLLECT_INTERVAL;
	}
	
	@Override
    public void updateEntity() {
		if (!worldObj.isRemote) {
        	
        	if (collectInterval == 0){
		
				tryCollect(4);	
				
				collectInterval = DEFAULT_COLLECT_INTERVAL;
        	}
        	collectInterval--;
        }
    }
	
	@Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);   

        compound.setInteger("CollectInterval", collectInterval); 
        compound.setInteger("InternalStorage", internalStorage);        
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        collectInterval = compound.getInteger("CollectInterval"); 
        internalStorage = compound.getInteger("InternalStorage");
    }
    
    private void tryCollect(int amount) {
    	
    	int actualAmount = 0;
    	for (int i=-1; i<=1; i++) {
    		for (int j=-1; j<=1; j++) {
    			if (worldObj.getBlockId(xCoord+i, yCoord, zCoord+j) == BlockInfo.ARCANE_COLLECTOR_ID) {
    				TileEntityArcaneCollector teArcaneCollector = (TileEntityArcaneCollector)worldObj.getBlockTileEntity(xCoord+i, yCoord, zCoord+j);
    			
    				if (amount + internalStorage > MAX_STORAGE)
    					amount = MAX_STORAGE - internalStorage;
    				
    				actualAmount = teArcaneCollector.requestEnergy(amount);
    				
    				internalStorage += actualAmount;
    			}
    			
    		}
    	}
    	
    	if (internalStorage > 0) {
    		//System.out.println("Storage collected: " + actualAmount + ", total: " + internalStorage);
    	}
    }
    
    public int getTimer() {
    	return collectInterval;
    }
    
    public int getInternalStorage() {
    	return internalStorage;
    }
    
    public void setInternalStorage(int amount) {
    	internalStorage = amount;
    }
}
