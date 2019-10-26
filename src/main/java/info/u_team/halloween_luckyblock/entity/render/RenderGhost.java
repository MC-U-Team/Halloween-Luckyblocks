package info.u_team.halloween_luckyblock.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.entity.EntityGhost;
import info.u_team.halloween_luckyblock.entity.model.ModelGhost;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class RenderGhost extends MobRenderer<EntityGhost, ModelGhost> {
	
	public RenderGhost(EntityRendererManager rendermanager) {
		super(rendermanager, new ModelGhost(), 0.5F);
	}
	
	@Override
	protected void preRenderCallback(EntityGhost entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scalef(1.3F, 1.3F, 1.3F);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityGhost entity) {
		return new ResourceLocation(HalloweenLuckyBlockMod.MODID + ":textures/entity/ghost.png");
	}
}