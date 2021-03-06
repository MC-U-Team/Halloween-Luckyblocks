package info.u_team.halloween_luckyblock.entity.model;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import info.u_team.halloween_luckyblock.entity.EntityGhost;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class ModelGhost extends SegmentedModel<EntityGhost> {
	
	private final ModelRenderer body;
	private final ModelRenderer[] tentacles = new ModelRenderer[9];
	
	private final ImmutableList<ModelRenderer> parts;
	
	public ModelGhost() {
		
		final Builder<ModelRenderer> builder = ImmutableList.builder();
		
		final byte b0 = -16;
		this.body = new ModelRenderer(this, 0, 0);
		this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
		this.body.rotationPointY += 24 + b0;
		builder.add(body);
		final Random random = new Random(1660L);
		
		for (int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i] = new ModelRenderer(this, 0, 0);
			final float f = ((i % 3 - i / 3 % 2 * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
			final float f1 = (i / 3 / 2.0F * 2.0F - 1.0F) * 5.0F;
			final int j = random.nextInt(7) + 8;
			this.tentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, j, 2);
			this.tentacles[i].rotationPointX = f;
			this.tentacles[i].rotationPointZ = f1;
			this.tentacles[i].rotationPointY = 31 + b0;
			builder.add(this.tentacles[i]);
		}
		
		this.parts = builder.build();
	}
	
	@Override
	public void setRotationAngles(EntityGhost entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		for (int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i].rotateAngleX = 0.2F * MathHelper.sin(ageInTicks * 0.3F + i) + 0.4F;
		}
		
	}
	
	@Override
	public Iterable<ModelRenderer> getParts() {
		return parts;
	}
}