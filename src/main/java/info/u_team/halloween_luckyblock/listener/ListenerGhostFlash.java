package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.util.MathUtil;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.event.EntityViewRenderEvent.CameraSetup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ListenerGhostFlash {
	
	public static volatile boolean flash = false;
	private static long time = 0;
	
	@SubscribeEvent
	public static void on(CameraSetup event) {
		try {
			if (flash) {
				if (time == 0) {
					time = System.currentTimeMillis();
				}
				GameRenderer render = event.getRenderer();
				if (System.currentTimeMillis() - time < 2000) {
					if (!render.isShaderActive()) {
						render.loadShader(GameRenderer.SHADERS_TEXTURES[MathUtil.getRandomNumberInRange(0, GameRenderer.SHADER_COUNT - 1)]);
					}
				} else {
					render.stopUseShader();
					time = 0;
					flash = false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
