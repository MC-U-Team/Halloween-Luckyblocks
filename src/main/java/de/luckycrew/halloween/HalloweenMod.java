package de.luckycrew.halloween;

import de.luckycrew.halloween.proxy.CommonProxy;
import info.u_team.u_team_core.sub.USubMod;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = HalloweenConstants.MODID, name = HalloweenConstants.NAME, version = HalloweenConstants.VERSION, acceptedMinecraftVersions = HalloweenConstants.MCVERSION, dependencies = HalloweenConstants.DEPENDENCIES)
public class HalloweenMod extends USubMod {
	
	public HalloweenMod() {
		super(HalloweenConstants.MODID, HalloweenConstants.NAME, HalloweenConstants.VERSION, HalloweenConstants.UPDATEURL);
	}
	
	@Instance
	public static HalloweenMod instance;
	
	@SidedProxy(serverSide = HalloweenConstants.COMMONPROXY, clientSide = HalloweenConstants.CLIENTPROXY)
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
