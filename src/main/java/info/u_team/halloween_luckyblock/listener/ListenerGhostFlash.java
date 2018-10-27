package info.u_team.halloween_luckyblock.listener;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ListenerGhostFlash {
	
	public static boolean flash = false;
	private static long time = 0;
	
	@SubscribeEvent
	public static void on(CameraSetup event) {
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
