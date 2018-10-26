package info.u_team.halloween_luckyblock.entity.render;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.entity.EntityGhost;
import info.u_team.halloween_luckyblock.entity.model.ModelGhost;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderGhost extends RenderLiving<EntityGhost> {
	
	public RenderGhost(RenderManager rendermanager) {
		super(rendermanager, new ModelGhost(), 0.5F);
	}
	
	@Override
	protected void preRenderCallback(EntityGhost entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(1.3F, 1.3F, 1.3F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityGhost entity) {
		return new ResourceLocation(HalloweenLuckyBlockConstants.MODID + ":textures/entity/ghost.png");
	}
}