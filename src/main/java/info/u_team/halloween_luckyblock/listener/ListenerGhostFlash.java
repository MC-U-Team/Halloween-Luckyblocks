package info.u_team.halloween_luckyblock.listener;

import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class ListenerGhostFlash {
	
	public static volatile boolean flash = false;
	private static long time = 0;
	
	private static void onClientTick(ClientTickEvent event) {
		try {
			if (flash) {
				if (time == 0) {
					time = System.currentTimeMillis();
				}
				final GameRenderer render = Minecraft.getInstance().gameRenderer;
				if (System.currentTimeMillis() - time < 2000) {
					if (render.getShaderGroup() == null) {
						render.loadShader(GameRenderer.SHADERS_TEXTURES[MathUtil.randomNumberInRange(0, GameRenderer.SHADER_COUNT - 1)]);
					}
				} else {
					render.stopUseShader();
					time = 0;
					flash = false;
				}
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(ListenerGhostFlash::onClientTick);
	}
}
