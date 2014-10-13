package mods.minecraft.donington.finitefire;

import mods.minecraft.donington.finitefire.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.ExistingSubstitutionException;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.Type;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = ModFiniteFire.MOD_ID, name = ModFiniteFire.MOD_NAME, version = ModFiniteFire.MOD_VERSION)
public class ModFiniteFire {
	public static final String MOD_ID = "finitefire";
	public static final String MOD_NAME = "Finite Fire";
	public static final String MOD_VERSION = "1.0";

	@SidedProxy(clientSide = "mods.minecraft.donington.finitefire.client.ClientProxy", serverSide = "mods.minecraft.donington.finitefire.common.CommonProxy")
	private static CommonProxy proxy;

	public static final Block blockFiniteFire = new BlockFiniteFire();
	public static final Item itemFiniteFire = new ItemBlock(blockFiniteFire);


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}


	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}


	public static void info(String fmt, Object... data) {
		FMLLog.info("%s: "+fmt, MOD_NAME, data);
	}


	public static void warning(String fmt, Object... data) {
		FMLLog.warning("%s: "+fmt, MOD_NAME, data);
	}

}
