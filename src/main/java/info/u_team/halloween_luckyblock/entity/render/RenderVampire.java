package info.u_team.halloween_luckyblock.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

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
	protected void preRenderCallback(EntityVampire entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		matrixStackIn.scale(1.35F, 1.35F, 1.35F);
	}
	
	@Override
	protected void applyRotations(EntityVampire entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		if (!entityLiving.getIsBatHanging()) {
			matrixStackIn.translate(0.0F, MathHelper.cos(ageInTicks * 0.3F) * 0.1F, 0.0F);
		} else {
			matrixStackIn.translate(0.0F, -0.1F, 0.0F);
		}
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
	}
	
	@Override
	public ResourceLocation getEntityTexture(EntityVampire entity) {
		return new ResourceLocation(HalloweenLuckyBlockMod.MODID + ":textures/entity/vampire.png");
	}
}