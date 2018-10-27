package info.u_team.halloween_luckyblock.proxy;

import info.u_team.halloween_luckyblock.listener.ListenerGhostFlash;
import info.u_team.halloween_luckyblock.render.HalloweenRenderRegistry;
import info.u_team.u_team_core.registry.CommonRegistry;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);
		HalloweenRenderRegistry.preinit();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		CommonRegistry.registerEventHandler(ListenerGhostFlash.class);
	}
	
	@Override
	public void postinit(FMLPostInitializationEvent event) {
		super.postinit(event);
	}
	
}
