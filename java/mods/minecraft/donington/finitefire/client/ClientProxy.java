package mods.minecraft.donington.finitefire.client;

import mods.minecraft.donington.finitefire.ModFiniteFire;
import mods.minecraft.donington.finitefire.common.CommonProxy;
import net.minecraft.client.Minecraft;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
//		ModFiniteFire.warning("on client;  ignoring");
		ModFiniteFire.warning("temporary allow on client!");
		super.preInit();
	}

}
