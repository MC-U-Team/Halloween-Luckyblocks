package info.u_team.halloween_luckyblock.proxy;

import info.u_team.halloween_luckyblock.handler.HalloweenClientHandler;
import info.u_team.halloween_luckyblock.render.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);
		new HalloweenModelRegistry();
	}
	
	public void init(FMLInitializationEvent event) {
		super.init(event);
		new HalloweenRenderRegistry();
	}
	
	public void postinit(FMLPostInitializationEvent event) {
		super.postinit(event);
		new HalloweenClientHandler();
	}
	
}
