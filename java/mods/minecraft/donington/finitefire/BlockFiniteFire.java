package mods.minecraft.donington.finitefire;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.block.BlockFire;
import net.minecraft.world.World;

public class BlockFiniteFire extends BlockFire {
	// mimic vanilla timing for fire extinguish
	private static final int metaBurnMin = 3;
	private static final int metaBurnMax = 15;


	public BlockFiniteFire() {
		super();
		this.setBlockName("finiteFire");
		//this.setBlockTextureName("minecraft:fire"); // glitches
		this.setBlockTextureName("fire"); // works
		this.setHardness(0.0F);
		this.setLightLevel(1.0F);
		this.setStepSound(soundTypeWood);
		this.disableStats();
	}


    public void updateTick(World world, int posX, int posY, int posZ, Random rng) {
    	// do vanilla fire behavior if doFireTick is true
        if ( world.getGameRules().getGameRuleBooleanValue("doFireTick") ) {
        	super.updateTick(world, posX, posY, posZ, rng);
        	return;
        }

        // check if the block below is a fire source (netherrack etc)
        if ( world.getBlock(posX, posY-1, posZ).isFireSource(world, posX, posY - 1, posZ, UP) )
        	return;  // just keep burning

        // update fire metadata
        int meta = world.getBlockMetadata(posX, posY, posZ);
        if ( meta < metaBurnMax ) {
        	//world.notifyBlocksOfNeighborChange(posX, posY, posZ, this);
            world.setBlockMetadataWithNotify(posX, posY, posZ, meta + rng.nextInt(3) / 2, 4);
        }
    	//System.out.printf("BlockFiniteFire meta := %d\n", meta);

        // schedule update in the future
        world.scheduleBlockUpdate(posX, posY, posZ, this, this.tickRate(world) + rng.nextInt(10));

        // extinguish fire if nothing nearby is considered flammable
        if ( !this.isNeighborFlammable(world, posX, posY, posZ) ) {
            if (!World.doesBlockHaveSolidTopSurface(world, posX, posY-1, posZ) || meta > metaBurnMin) {
                world.setBlockToAir(posX, posY, posZ);
                return;
            }
        }

        // extinguish fire if metadata has reached metaBurnMax and the rng gods align
        if ( meta == metaBurnMax && rng.nextInt(4) == 0) {
        	world.setBlockToAir(posX, posY, posZ);
        }
    }


    /** test surrounding blocks for flammability */
    private boolean isNeighborFlammable(World world, int posX, int posY, int posZ) {
        return
        		canCatchFire(world,  posX,    posY,    posZ+1,  NORTH) ||
                canCatchFire(world,  posX,    posY,    posZ-1,  SOUTH) ||
                canCatchFire(world,  posX-1,  posY,    posZ,    EAST ) ||
        		canCatchFire(world,  posX+1,  posY,    posZ,    WEST ) ||
        		canCatchFire(world,  posX,    posY-1,  posZ,    UP   ) ||
        		canCatchFire(world,  posX,    posY+1,  posZ,    DOWN );
    }

}
