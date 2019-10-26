package info.u_team.halloween_luckyblock.entity.model;

import info.u_team.halloween_luckyblock.entity.EntityCreepyZombie;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class ModelCreepyZombie extends EntityModel<EntityCreepyZombie> {
	
	private RendererModel headright;
	private RendererModel headleft;
	private RendererModel brain;
	private RendererModel body;
	private RendererModel rightarm;
	private RendererModel leftarm;
	private RendererModel rightleg;
	private RendererModel leftleg;
	
	public ModelCreepyZombie() {
		textureWidth = 64;
		textureHeight = 32;
		
		headright = new RendererModel(this, 24, 0);
		headright.addBox(0F, -8F, -4F, 4, 8, 8);
		headright.setRotationPoint(0F, 0F, 0F);
		headright.setTextureSize(64, 32);
		headright.mirror = true;
		setRotation(headright, 0F, 0F, 0.3141593F);
		headleft = new RendererModel(this, 0, 0);
		headleft.addBox(-4F, -8F, -4F, 4, 8, 8);
		headleft.setRotationPoint(0F, 0F, 0F);
		headleft.setTextureSize(64, 32);
		headleft.mirror = true;
		setRotation(headleft, 0F, 0F, -0.1745329F);
		brain = new RendererModel(this, 48, 0);
		brain.addBox(-2F, -6F, -2F, 4, 6, 4);
		brain.setRotationPoint(0F, 0F, 0F);
		brain.setTextureSize(64, 32);
		brain.mirror = true;
		setRotation(brain, 0F, 0F, 0F);
		
		body = new RendererModel(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new RendererModel(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new RendererModel(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new RendererModel(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new RendererModel(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
	}
	
	private void setRotation(RendererModel model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void render(EntityCreepyZombie entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		headright.render(f5);
		headleft.render(f5);
		brain.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
	}
	
	@Override
	public void setRotationAngles(EntityCreepyZombie entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		
		float f6 = MathHelper.sin(this.swingProgress * (float) Math.PI);
		float f7 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float) Math.PI);
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightarm.rotateAngleY = -(0.1F - f6 * 0.6F);
		this.leftarm.rotateAngleY = 0.1F - f6 * 0.6F;
		this.rightarm.rotateAngleX = -((float) Math.PI / 2F);
		this.leftarm.rotateAngleX = -((float) Math.PI / 2F);
		this.rightarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
		this.leftarm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
		this.rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
		this.rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
		this.leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
		
		this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;
	}
	
}
