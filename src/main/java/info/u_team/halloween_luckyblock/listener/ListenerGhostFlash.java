package info.u_team.halloween_luckyblock.listener;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.client.renderer.EntityRenderer;
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
			EntityRenderer render = event.getRenderer();
			if (System.currentTimeMillis() - time < 1000) {
				render.loadShader(EntityRenderer.SHADERS_TEXTURES[MathUtil.getRandomNumberInRange(0, EntityRenderer.SHADER_COUNT - 1)]);
			} else {
				render.stopUseShader();
				time = 0;
				flash = false;
			}
		}
	}
}
