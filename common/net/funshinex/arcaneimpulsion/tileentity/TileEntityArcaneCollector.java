package net.funshinex.arcaneimpulsion.tileentity;

import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityArcaneCollector extends TileEntity {

	private static final int MAX_STORAGE = 32;
	private static final int DEFAULT_COLLECT_INTERVAL = 100;
	
	private int internalStorage;	
	private int collectInterval;
	
	public TileEntityArcaneCollector() {
		collectInterval = DEFAULT_COLLECT_INTERVAL;
	}
	
	@Override
    public void updateEntity() {
		if (!worldObj.isRemote) {
        	
        	if (collectInterval == 0){
		
				tryCollect(1);	
				
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
    			if (worldObj.getBlockId(xCoord+i, yCoord, zCoord+j) == BlockInfo.IMPULSION_DRIVE_BASIC_ID) {
    				TileEntityImpulsionDriveBasic teImpulsionDriveBasic = (TileEntityImpulsionDriveBasic)worldObj.getBlockTileEntity(xCoord+i, yCoord, zCoord+j);
    			
    				if (amount + internalStorage > MAX_STORAGE)
    					amount = MAX_STORAGE - internalStorage;
    				
    				actualAmount = teImpulsionDriveBasic.requestEnergy(amount, true);
    				
    				internalStorage += actualAmount;
    			}
    			if (worldObj.getBlockId(xCoord+i, yCoord, zCoord+j) == BlockInfo.ARCANE_EXTRACTOR_ID) {
    				TileEntityArcaneExtractor teArcaneExtractor = (TileEntityArcaneExtractor)worldObj.getBlockTileEntity(xCoord+i, yCoord, zCoord+j);
    			
    				if (amount + internalStorage > MAX_STORAGE)
    					amount = MAX_STORAGE - internalStorage;
    				
    				actualAmount = teArcaneExtractor.requestEnergy(amount, true);
    				
    				internalStorage += actualAmount;
    			}
    		}
    	}
    	
    	if (internalStorage > 0) {
    		//System.out.println("Collector collected: " + actualAmount + ", total: " + internalStorage);
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
    
    public int requestEnergy(int amount, boolean allowPartial) {
		
		if (amount > internalStorage) { 
			
			if (allowPartial)
				amount = internalStorage;
			else
				return 0;
		}
		
		internalStorage -= amount;
		
		return amount;		
	}
}
