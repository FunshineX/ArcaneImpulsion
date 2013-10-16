package net.funshinex.arcaneimpulsion.tileentity;

import net.funshinex.arcaneimpulsion.util.ImpulseBlockTypes;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityImpulsionDriveBasic extends TileEntity {
	
	private static final int MAX_STORAGE = 4;
	private static final int DEFAULT_COLLECT_COOLDOWN = 10;
	private static final int IMPULSE_BLOCK_CHECK_REFRESH_RATE = 100;
	
	private int internalStorage;
	private int collectCooldown = DEFAULT_COLLECT_COOLDOWN; 
	private boolean onCooldown = false;
	
	private boolean impulseBlockExists;
	private ImpulseBlockTypes impulseBlockType;
	private int impulseBlockLocX;
	private int impulseBlockLocY;
	private int impulseBlockLocZ;		
	
	private int lastChecked;
	
	public TileEntityImpulsionDriveBasic() {
		impulseBlockExists = false;
		impulseBlockType = ImpulseBlockTypes.NONE;
		lastChecked = 0;
		internalStorage = 0;
	}
	
	@Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
        	
        	if (lastChecked == 0){
		
        		findImpulseBlock(worldObj, xCoord, yCoord, zCoord);	
				
				if (impulseBlockExists) {
					//System.out.println("Found Impulse Block at " + impulseBlockLocX + "," + impulseBlockLocY + "," + impulseBlockLocZ);
				}
				lastChecked = IMPULSE_BLOCK_CHECK_REFRESH_RATE;
        	}
        	lastChecked--;
        	
        	if(onCooldown) {
        		collectCooldown--;
        		
        		if (collectCooldown <= 0) {
        			collectCooldown = DEFAULT_COLLECT_COOLDOWN;
        			onCooldown = false;
        		}
        	}
        }
    }
	
	@Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setShort("LastChecked", (short)lastChecked);
        compound.setBoolean("ImpluseBlockExists", impulseBlockExists);
        compound.setInteger("ImpulseBlockX", impulseBlockLocX);
        compound.setInteger("ImpulseBlockY", impulseBlockLocY);
        compound.setInteger("ImpulseBlockZ", impulseBlockLocZ);
        compound.setInteger("InternalStorage", internalStorage);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        lastChecked = compound.getShort("LastChecked");
        impulseBlockExists = compound.getBoolean("ImpulseBlockExists");
        impulseBlockLocX = compound.getInteger("ImpulseBlockX");
        impulseBlockLocY = compound.getInteger("ImpulseBlockY");
        impulseBlockLocZ = compound.getInteger("ImpulseBlockZ");
        internalStorage = compound.getInteger("InternalStorage");
    }
	
	private void setImpulseBlockLoc(int x, int y, int z) {
		impulseBlockLocX = x;
		impulseBlockLocY = y;
		impulseBlockLocZ = z;
		
		impulseBlockExists = true;
	}
	
	private void findImpulseBlock(World world, int x, int y, int z) {
		if (checkForImpulseBlockAt(world, x+1, y, z)) return;
		else if (checkForImpulseBlockAt(world, x-1, y, z)) return;
		else if (checkForImpulseBlockAt(world, x, y+1, z)) return;
		else if (checkForImpulseBlockAt(world, x, y-1, z)) return;
		else if (checkForImpulseBlockAt(world, x, y, z+1)) return;
		else if (checkForImpulseBlockAt(world, x, y, z-1)) return;
		
		else {			
			setImpulseBlockLoc(0, 0, 0);
			impulseBlockExists = false;
			impulseBlockType = ImpulseBlockTypes.NONE;
		}

	}
	
	private boolean checkForImpulseBlockAt(World world, int x, int y, int z) {
		
		int blockID = world.getBlockId(x,y,z);
		
		if (blockID == Block.cobblestone.blockID) {
			setImpulseBlockLoc(x, y, z);
			impulseBlockType = ImpulseBlockTypes.COBBLE;
		}
		else if (blockID == Block.blockIron.blockID) {
			setImpulseBlockLoc(x, y, z);
			impulseBlockType = ImpulseBlockTypes.IRON;
		}
		else if (blockID == Block.blockGold.blockID) {
			setImpulseBlockLoc(x, y, z);
			impulseBlockType = ImpulseBlockTypes.GOLD;
		}
		else if (blockID == Block.blockDiamond.blockID) {
			setImpulseBlockLoc(x, y, z);
			impulseBlockType = ImpulseBlockTypes.DIAMOND;
		}
		else if (blockID == Block.blockEmerald.blockID) {
			setImpulseBlockLoc(x, y, z);
			impulseBlockType = ImpulseBlockTypes.EMERALD;
		}
		else {
			return false;
		}
		
		return true;
	}

	public void doImpulse() {
		
		if(!onCooldown && impulseBlockExists) {
			
			onCooldown = true;
			
			internalStorage += impulseBlockType.GetValue();
			
			if (internalStorage > MAX_STORAGE) {
				internalStorage = MAX_STORAGE;
			}
			
			System.out.println("clicked: " + internalStorage);
		}
	}
	
	public int requestEnergy(int amount) {
		
		if (amount > internalStorage) { 
			amount = internalStorage;
		}
		
		internalStorage -= amount;
		
		return amount;		
	}
}

