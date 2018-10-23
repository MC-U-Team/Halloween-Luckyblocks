package info.u_team.halloween_luckyblock.entity.render;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.entity.EntityVampire;
import info.u_team.halloween_luckyblock.entity.model.ModelVampire;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
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
		return new ResourceLocation(HalloweenLuckyBlockConstants.MODID + ":textures/entity/vampire.png");
	}
}