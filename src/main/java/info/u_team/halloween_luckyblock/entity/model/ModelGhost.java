package info.u_team.halloween_luckyblock.entity.model;

import java.util.Random;

import net.minecraft.client.model.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ModelGhost extends ModelBase {
	
	private ModelRenderer body;
	private ModelRenderer[] tentacles = new ModelRenderer[9];
	
	public ModelGhost() {
		byte b0 = -16;
		this.body = new ModelRenderer(this, 0, 0);
		this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
		this.body.rotationPointY += (float) (24 + b0);
		Random random = new Random(1660L);
		
		for (int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i] = new ModelRenderer(this, 0, 0);
			float f = (((float) (i % 3) - (float) (i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
			float f1 = ((float) (i / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
			int j = random.nextInt(7) + 8;
			this.tentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, j, 2);
			this.tentacles[i].rotationPointX = f;
			this.tentacles[i].rotationPointZ = f1;
			this.tentacles[i].rotationPointY = (float) (31 + b0);
		}
	}
	
	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entity) {
		for (int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i].rotateAngleX = 0.2F * MathHelper.sin(p_78087_3_ * 0.3F + (float) i) + 0.4F;
		}
	}
	
	@Override
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 0.6F, 0.0F);
		this.body.render(p_78088_7_);
		ModelRenderer[] amodelrenderer = this.tentacles;
		int i = amodelrenderer.length;
		
		for (int j = 0; j < i; ++j) {
			ModelRenderer modelrenderer = amodelrenderer[j];
			modelrenderer.render(p_78088_7_);
		}
		GlStateManager.popMatrix();
	}
}