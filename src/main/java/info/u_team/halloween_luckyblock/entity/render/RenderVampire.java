package info.u_team.halloween_luckyblock.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.entity.EntityVampire;
import info.u_team.halloween_luckyblock.entity.model.ModelVampire;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class RenderVampire extends MobRenderer<EntityVampire, ModelVampire> {
	
	public RenderVampire(EntityRendererManager rendermanager) {
		super(rendermanager, new ModelVampire(), 0.25F);
	}
	
	@Override
	protected void preRenderCallback(EntityVampire entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scalef(1.35F, 1.35F, 1.35F);
	}
	
	@Override
	protected void applyRotations(EntityVampire entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		if (!entityLiving.getIsBatHanging()) {
			GlStateManager.translatef(0.0F, MathHelper.cos(p_77043_2_ * 0.3F) * 0.1F, 0.0F);
		} else {
			GlStateManager.translatef(0.0F, -0.1F, 0.0F);
		}
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityVampire entity) {
		return new ResourceLocation(HalloweenLuckyBlockMod.MODID + ":textures/entity/vampire.png");
	}
}