package net.funshinex.arcaneimpulsion.tileentity;

import net.funshinex.arcaneimpulsion.block.BlockInfo;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityArcaneCondenser extends TileEntity {

	private static final int DEFAULT_COLLECT_INTERVAL = 40;
	
	private int availableIMs;	
	private int collectInterval;
	
	public TileEntityArcaneCondenser()  {
		collectInterval = DEFAULT_COLLECT_INTERVAL;
	}
	
	@Override
    public void updateEntity() {
		if (!worldObj.isRemote) {
        	
        	if (collectInterval == 0){
		
        		updateNearbyStorage();	
				
				collectInterval = DEFAULT_COLLECT_INTERVAL;
        	}
        	collectInterval--;
        }
    }
	
	@Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);   
       
        compound.setInteger("CollectInterval", collectInterval); 
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        collectInterval = compound.getInteger("CollectInterval"); 
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
    
    public int getNearbyStorage() {
    	
    	return availableIMs;
    }
    
    public void setNearbyStorage(int amount) {
    	availableIMs = amount;
    }
}
