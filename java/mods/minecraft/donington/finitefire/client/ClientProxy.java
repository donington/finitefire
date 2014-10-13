package mods.minecraft.donington.finitefire.client;

import mods.minecraft.donington.finitefire.ModFiniteFire;
import mods.minecraft.donington.finitefire.common.CommonProxy;
import net.minecraft.client.Minecraft;

public class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		super.init();

		// registerBlockIcons() is not called properly, invoking manually
		ModFiniteFire.info("registering BlockFiniteFire icons");
		ModFiniteFire.blockFiniteFire.registerBlockIcons(Minecraft.getMinecraft().getTextureMapBlocks());
	}

}
