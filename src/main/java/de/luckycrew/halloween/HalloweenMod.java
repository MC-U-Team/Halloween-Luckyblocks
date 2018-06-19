package de.luckycrew.halloween;

import static de.luckycrew.halloween.HalloweenConstants.*;

import de.luckycrew.halloween.proxy.CommonProxy;
import info.u_team.u_team_core.sub.USubMod;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = MODID, name = NAME, version = VERSION, acceptedMinecraftVersions = MCVERSION, dependencies = DEPENDENCIES, updateJSON = UPDATEURL)
public class HalloweenMod extends USubMod {
	
	@Instance
	public static HalloweenMod instance;
	
	@SidedProxy(serverSide = COMMONPROXY, clientSide = CLIENTPROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);
		proxy.preinit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
		proxy.init(event);
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		super.postinit(event);
		proxy.postinit(event);
	}
}
