package info.u_team.halloween_luckyblock.entity.model;

import info.u_team.halloween_luckyblock.entity.EntityVampire;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class ModelVampire extends EntityModel<EntityVampire> {
	
	private RendererModel batHead;
	private RendererModel batBody;
	private RendererModel batRightWing;
	private RendererModel batLeftWing;
	private RendererModel batOuterRightWing;
	private RendererModel batOuterLeftWing;
	
	public ModelVampire() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.batHead = new RendererModel(this, 0, 0);
		this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
		RendererModel RendererModel = new RendererModel(this, 24, 0);
		RendererModel.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
		this.batHead.addChild(RendererModel);
		RendererModel RendererModel1 = new RendererModel(this, 24, 0);
		RendererModel1.mirror = true;
		RendererModel1.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
		this.batHead.addChild(RendererModel1);
		this.batBody = new RendererModel(this, 0, 16);
		this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
		this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
		this.batRightWing = new RendererModel(this, 42, 0);
		this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
		this.batOuterRightWing = new RendererModel(this, 24, 16);
		this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
		this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
		this.batLeftWing = new RendererModel(this, 42, 0);
		this.batLeftWing.mirror = true;
		this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
		this.batOuterLeftWing = new RendererModel(this, 24, 16);
		this.batOuterLeftWing.mirror = true;
		this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
		this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
		this.batBody.addChild(this.batRightWing);
		this.batBody.addChild(this.batLeftWing);
		this.batRightWing.addChild(this.batOuterRightWing);
		this.batLeftWing.addChild(this.batOuterLeftWing);
	}
	
	@Override
	public void render(EntityVampire p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
		this.setRotationAngles(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
		this.batHead.render(p_78088_7_);
		this.batBody.render(p_78088_7_);
	}
	
	@Override
	public void setRotationAngles(EntityVampire p_78087_7_, float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_) {
		
		if (((EntityVampire) p_78087_7_).getIsBatHanging()) {
			this.batHead.rotateAngleX = p_78087_5_ / (180F / (float) Math.PI);
			this.batHead.rotateAngleY = (float) Math.PI - p_78087_4_ / (180F / (float) Math.PI);
			this.batHead.rotateAngleZ = (float) Math.PI;
			this.batHead.setRotationPoint(0.0F, -2.0F, 0.0F);
			this.batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
			this.batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
			this.batBody.rotateAngleX = (float) Math.PI;
			this.batRightWing.rotateAngleX = -0.15707964F;
			this.batRightWing.rotateAngleY = -((float) Math.PI * 2F / 5F);
			this.batOuterRightWing.rotateAngleY = -1.7278761F;
			this.batLeftWing.rotateAngleX = this.batRightWing.rotateAngleX;
			this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
			this.batOuterLeftWing.rotateAngleY = -this.batOuterRightWing.rotateAngleY;
		} else {
			this.batHead.rotateAngleX = p_78087_5_ / (180F / (float) Math.PI);
			this.batHead.rotateAngleY = p_78087_4_ / (180F / (float) Math.PI);
			this.batHead.rotateAngleZ = 0.0F;
			this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.batBody.rotateAngleX = ((float) Math.PI / 4F) + MathHelper.cos(p_78087_3_ * 0.1F) * 0.15F;
			this.batBody.rotateAngleY = 0.0F;
			this.batRightWing.rotateAngleY = MathHelper.cos(p_78087_3_ * 1.3F) * (float) Math.PI * 0.25F;
			this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
			this.batOuterRightWing.rotateAngleY = this.batRightWing.rotateAngleY * 0.5F;
			this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;
		}
	}
}