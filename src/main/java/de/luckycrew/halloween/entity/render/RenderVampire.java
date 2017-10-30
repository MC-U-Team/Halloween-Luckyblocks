package de.luckycrew.halloween.entity.render;

import de.luckycrew.halloween.HalloweenConstants;
import de.luckycrew.halloween.entity.EntityVampire;
import de.luckycrew.halloween.entity.model.ModelVampire;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderVampire extends RenderLiving {
	
	public RenderVampire(RenderManager rendermanager) {
		super(rendermanager, new ModelVampire(), 0.25F);
	}
	
	@Override
	public void preRenderCallback(EntityLivingBase entitylivingbase, float particalTickTime) {
		GlStateManager.scale(1.35F, 1.35F, 1.35F);
	}
	
	@Override
	public void rotateCorpse(EntityLivingBase entitylivingbase, float f1, float f2, float particalTickTime) {
		if (!((EntityVampire) entitylivingbase).getIsBatHanging()) {
			GlStateManager.translate(0.0F, MathHelper.cos(f1 * 0.3F) * 0.1F, 0.0F);
		} else {
			GlStateManager.translate(0.0F, -0.1F, 0.0F);
		}
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(HalloweenConstants.MODID + ":textures/entity/vampire.png");
	}
}