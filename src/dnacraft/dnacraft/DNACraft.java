package dnacraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import dnacraft.common.CommonProxy;
import dnacraft.common.item.ItemGeneric;
import dnacraft.common.item.ItemUnstackable;
import net.minecraftforge.common.config.Configuration;

@Mod( modid = "DNACraft", name = "DNACraft", version = "a0.0.1")
public class DNACraft {
	
	@Instance( value = "DNACraft" )
	public static DNACraft instance;

	@SidedProxy( clientSide = "dnacraft.dnacraft.client.ClientProxy", serverSide = "dnacraft.dnacraft.common.CommonProxy" )
	public static CommonProxy proxy;
	
	public static class Items
	{
		public static ItemUnstackable itemUnstackable;
		public static ItemGeneric itemGeneric;
	}

	@Mod.EventHandler
	public void preInit( FMLPreInitializationEvent evt )
	{
		Configuration configFile = new Configuration(evt.getSuggestedConfigurationFile());
		
		// get config here
		
		configFile.save();
		
	}
	
	@Mod.EventHandler
	public void init( FMLInitializationEvent evt )
	{
		proxy.init();
		proxy.registerRenderInformation();
	}
}