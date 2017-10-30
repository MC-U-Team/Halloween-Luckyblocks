package de.luckycrew.halloween.listener;

import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.*;

public class ListenerGhostFlash {
	
	@SideOnly(Side.CLIENT)
	public static boolean flash = false;
	@SideOnly(Side.CLIENT)
	private long time = 0;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void on(CameraSetup event) {
		if (flash) {
			if (time == 0) {
				time = System.currentTimeMillis();
			}
			if (System.currentTimeMillis() - time < 1000) {
				event.renderer.activateNextShader();
			} else {
				event.renderer.switchUseShader();
				time = 0;
				flash = false;
			}
		}
	}
}
