package net.funshinex.arcaneimpulsion.tileentity;

import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityArcaneCollector extends TileEntity {

	private static final int MAX_STORAGE = 32;
	private static final int DEFAULT_COLLECT_INTERVAL = 40;
	
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

        
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        
    }
    
    private void tryCollect(int amount) {
    	
    	for (int i=-1; i<=1; i++) {
    		for (int j=-1; j<=1; j++) {
    			if (worldObj.getBlockId(xCoord+i, yCoord, zCoord+j) == BlockInfo.IMPULSION_DRIVE_BASIC_ID) {
    				TileEntityImpulsionDriveBasic teImpulsionDriveBasic = (TileEntityImpulsionDriveBasic)worldObj.getBlockTileEntity(xCoord+i, yCoord, zCoord+j);
    			
    				if (amount + internalStorage > MAX_STORAGE)
    					amount = MAX_STORAGE - internalStorage;
    				
    				int actualAmount = teImpulsionDriveBasic.requestEnergy(amount);
    				
    				internalStorage += actualAmount;
    			}
    		}
    	}
    	
    	System.out.println("collected: " + internalStorage);
    }
}
