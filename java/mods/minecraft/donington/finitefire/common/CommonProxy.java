package mods.minecraft.donington.finitefire.common;

import mods.minecraft.donington.finitefire.ModFiniteFire;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.Type;

public class CommonProxy {

	public void preInit() {
		// override BlockFire with BlockFiniteFire

		// temporary allow on client for faster debugging
		//if ( ModFiniteFire.isServer() ) try {
		
		try {
			GameRegistry.addSubstitutionAlias("minecraft:fire", Type.BLOCK, ModFiniteFire.blockFiniteFire);

			// this item can no longer be substituted, rendering the block substitution ineffective
			//GameRegistry.addSubstitutionAlias("minecraft:fire", Type.ITEM, ModFiniteFire.itemFiniteFire);
			
		} catch (ExistingSubstitutionException e) {
			ModFiniteFire.warning("Failed to inject BlockFiniteFire!");
		} finally {
			ModFiniteFire.info("injected BlockFiniteFire");
		}
	}

}
