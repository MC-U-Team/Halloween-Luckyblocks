package info.u_team.halloween_luckyblock.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

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
	protected void preRenderCallback(EntityGhost entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		matrixStackIn.scale(1.3F, 1.3F, 1.3F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(EntityGhost entity) {
		return new ResourceLocation(HalloweenLuckyBlockMod.MODID + ":textures/entity/ghost.png");
	}
}