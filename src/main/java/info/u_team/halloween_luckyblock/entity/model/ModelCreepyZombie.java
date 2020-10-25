package info.u_team.halloween_luckyblock.entity.model;

import com.google.common.collect.ImmutableList;

import info.u_team.halloween_luckyblock.entity.EntityCreepyZombie;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class ModelCreepyZombie extends SegmentedModel<EntityCreepyZombie> {
	
	private ModelRenderer headright;
	private ModelRenderer headleft;
	private ModelRenderer brain;
	private ModelRenderer body;
	private ModelRenderer rightarm;
	private ModelRenderer leftarm;
	private ModelRenderer rightleg;
	private ModelRenderer leftleg;
	
	public ModelCreepyZombie() {
		textureWidth = 64;
		textureHeight = 32;
		
		headright = new ModelRenderer(this, 24, 0);
		headright.addBox(0F, -8F, -4F, 4, 8, 8);
		headright.setRotationPoint(0F, 0F, 0F);
		headright.setTextureSize(64, 32);
		headright.mirror = true;
		setRotation(headright, 0F, 0F, 0.3141593F);
		headleft = new ModelRenderer(this, 0, 0);
		headleft.addBox(-4F, -8F, -4F, 4, 8, 8);
		headleft.setRotationPoint(0F, 0F, 0F);
		headleft.setTextureSize(64, 32);
		headleft.mirror = true;
		setRotation(headleft, 0F, 0F, -0.1745329F);
		brain = new ModelRenderer(this, 48, 0);
		brain.addBox(-2F, -6F, -2F, 4, 6, 4);
		brain.setRotationPoint(0F, 0F, 0F);
		brain.setTextureSize(64, 32);
		brain.mirror = true;
		setRotation(brain, 0F, 0F, 0F);
		
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(headright, headleft, brain, body, rightarm, leftarm, rightleg, leftleg);
	}
	
	@Override
	public void setRotationAngles(EntityCreepyZombie entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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
		this.rightarm.rotateAngleZ += MathHelper.cos(limbSwingAmount * 0.09F) * 0.05F + 0.05F;
		this.leftarm.rotateAngleZ -= MathHelper.cos(limbSwingAmount * 0.09F) * 0.05F + 0.05F;
		this.rightarm.rotateAngleX += MathHelper.sin(limbSwingAmount * 0.067F) * 0.05F;
		this.leftarm.rotateAngleX -= MathHelper.sin(limbSwingAmount * 0.067F) * 0.05F;
		
		this.rightleg.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 1.4F * limbSwing;
		this.leftleg.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F + (float) Math.PI) * 1.4F * limbSwing;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;
	}
}
