package info.u_team.halloween_luckyblock.entity.model;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;

import info.u_team.halloween_luckyblock.entity.EntityGhost;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class ModelGhost extends EntityModel<EntityGhost> {
	
	private RendererModel body;
	private RendererModel[] tentacles = new RendererModel[9];
	
	public ModelGhost() {
		byte b0 = -16;
		this.body = new RendererModel(this, 0, 0);
		this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
		this.body.rotationPointY += 24 + b0;
		Random random = new Random(1660L);
		
		for (int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i] = new RendererModel(this, 0, 0);
			float f = ((i % 3 - i / 3 % 2 * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
			float f1 = (i / 3 / 2.0F * 2.0F - 1.0F) * 5.0F;
			int j = random.nextInt(7) + 8;
			this.tentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, j, 2);
			this.tentacles[i].rotationPointX = f;
			this.tentacles[i].rotationPointZ = f1;
			this.tentacles[i].rotationPointY = 31 + b0;
		}
	}
	
	@Override
	public void setRotationAngles(EntityGhost entity, float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_) {
		for (int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i].rotateAngleX = 0.2F * MathHelper.sin(p_78087_3_ * 0.3F + i) + 0.4F;
		}
	}
	
	@Override
	public void render(EntityGhost entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
		this.setRotationAngles(entity, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
		GlStateManager.pushMatrix();
		GlStateManager.translatef(0.0F, 0.6F, 0.0F);
		this.body.render(p_78088_7_);
		RendererModel[] aRendererModel = this.tentacles;
		int i = aRendererModel.length;
		
		for (int j = 0; j < i; ++j) {
			RendererModel RendererModel = aRendererModel[j];
			RendererModel.render(p_78088_7_);
		}
		GlStateManager.popMatrix();
	}
}