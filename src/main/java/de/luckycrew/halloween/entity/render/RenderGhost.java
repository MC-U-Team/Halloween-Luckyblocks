package de.luckycrew.halloween.entity.render;

import de.luckycrew.halloween.HalloweenConstants;
import de.luckycrew.halloween.entity.EntityGhost;
import de.luckycrew.halloween.entity.model.ModelGhost;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderGhost extends RenderLiving<EntityGhost> {
	
	public RenderGhost(RenderManager rendermanager) {
		super(rendermanager, new ModelGhost(), 0.5F);
	}
	
	public void preRenderCallback(EntityGhost entitylivingbase, float particalTickTime) {
		GlStateManager.scale(1.3F, 1.3F, 1.3F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(EntityGhost entity) {
		return new ResourceLocation(HalloweenConstants.MODID + ":textures/entity/ghost.png");
	}
}