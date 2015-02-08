package mods.minecraft.donington.finitefire;

import static net.minecraft.util.EnumFacing.NORTH;
import static net.minecraft.util.EnumFacing.SOUTH;
import static net.minecraft.util.EnumFacing.EAST;
import static net.minecraft.util.EnumFacing.WEST;
import static net.minecraft.util.EnumFacing.UP;
import static net.minecraft.util.EnumFacing.DOWN;

import java.util.Random;

import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockFiniteFire extends BlockFire {
	// mimic vanilla timing for fire extinguish
	private static final int fireAgeMin = 3;
	private static final int fireAgeMax = 15;
    private static final EnumFacing[] direction = EnumFacing.values();



	public BlockFiniteFire() {
		super();
		this.setUnlocalizedName("finiteFire");
		//this.setBlockTextureName("minecraft:fire"); // glitches
		//this.setBlockTextureName("fire"); // works
		this.setHardness(0.0F);
		this.setLightLevel(1.0F);
		this.setStepSound(soundTypeWood);
		this.disableStats();
	}


    public void updateTick(World world, BlockPos pos, IBlockState state, Random rng) {
    	System.out.printf("BlockFiniteFire::updateTick()");

    	// do vanilla fire behavior if doFireTick is true
        if ( world.getGameRules().getGameRuleBooleanValue("doFireTick") ) {
        	super.updateTick(world, pos, state, rng);
        	return;
        }

        // check if the block below is a fire source (netherrack etc)
        if ( world.getBlockState(pos.down()).getBlock().isFireSource(world, pos, UP) )
        	return;  // just keep burning

        // douse fire in rain
        if ( world.isRaining() && this.canDie(world, pos) ) {
        	world.setBlockToAir(pos);
        	return;
    	}
        
        // update fire metadata
        int age = ((Integer)state.getValue(AGE)).intValue();
        if ( age < fireAgeMax ) {
            state = state.withProperty(AGE, Integer.valueOf(age + rng.nextInt(3) / 2));
            world.setBlockState(pos, state, 4);
        }
    	System.out.printf("BlockFiniteFire age := %d\n", age);

        // schedule update in the future
        world.scheduleUpdate(pos, this, this.tickRate(world) + rng.nextInt(10));

        // extinguish fire if nothing nearby is considered flammable
        if ( !this.isNeighborFlammable(world, pos) ) {
            if (!World.doesBlockHaveSolidTopSurface(world, pos.down()) || age > fireAgeMin) {
                world.setBlockToAir(pos);
                return;
            }
        }

        // try to extinguish fire if age has reached fireAgeMax
        if ( age == fireAgeMax && rng.nextInt(4) == 0) {
        	world.setBlockToAir(pos);
        }
    }


    /** test surrounding blocks for flammability */
    private boolean isNeighborFlammable(World world, BlockPos pos) {
        for (int i = 0; i < direction.length; ++i) {
            EnumFacing facing = direction[i];

            if (this.canCatchFire(world, pos.offset(facing), facing.getOpposite()))
                return true;
        }

        return false;
    }

}
