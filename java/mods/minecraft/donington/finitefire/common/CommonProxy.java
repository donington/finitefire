package mods.minecraft.donington.finitefire.common;

import mods.minecraft.donington.finitefire.ModFiniteFire;
import cpw.mods.fml.common.registry.ExistingSubstitutionException;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.Type;

public class CommonProxy {

	public void preInit() {
		// override BlockFire with BlockFiniteFire
		try {
			GameRegistry.addSubstitutionAlias("minecraft:fire", Type.BLOCK, ModFiniteFire.blockFiniteFire);
			GameRegistry.addSubstitutionAlias("minecraft:fire", Type.ITEM, ModFiniteFire.itemFiniteFire);
		} catch (ExistingSubstitutionException e) {
			ModFiniteFire.warning("Failed to inject BlockFiniteFire!");
		} finally {
			ModFiniteFire.info("injected BlockFiniteFire");
		}
	}

	public void init() {}

}
