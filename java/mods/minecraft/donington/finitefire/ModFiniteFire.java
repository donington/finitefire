package mods.minecraft.donington.finitefire;

import mods.minecraft.donington.finitefire.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.Type;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ModFiniteFire.MOD_ID, name = ModFiniteFire.MOD_NAME, version = ModFiniteFire.MOD_VERSION)
public class ModFiniteFire {
	public static final String MOD_ID = "finitefire";
	public static final String MOD_NAME = "Finite Fire";
	public static final String MOD_VERSION = "1.1";

	@SidedProxy(clientSide = "mods.minecraft.donington.finitefire.client.ClientProxy", serverSide = "mods.minecraft.donington.finitefire.common.CommonProxy")
	private static CommonProxy proxy;

	public static final Block blockFiniteFire = new BlockFiniteFire();
	public static final Item itemFiniteFire = new ItemBlock(blockFiniteFire);


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}


	public static void info(String fmt, Object... data) {
		FMLLog.info("[%s]: "+fmt, MOD_NAME, data);
	}


	public static void warning(String fmt, Object... data) {
		FMLLog.warning("[%s]: "+fmt, MOD_NAME, data);
	}


	public static boolean isServer() {
		return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
	}

}
