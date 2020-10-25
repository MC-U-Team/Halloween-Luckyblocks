package info.u_team.halloween_luckyblock.entity.render;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.halloween_luckyblock.entity.EntityCreepyZombie;
import info.u_team.halloween_luckyblock.entity.model.ModelCreepyZombie;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class RenderCreepyZombie extends MobRenderer<EntityCreepyZombie, ModelCreepyZombie> {
	
	public RenderCreepyZombie(EntityRendererManager rendermanager) {
		super(rendermanager, new ModelCreepyZombie(), 0);
	}
	
	@Override
	public ResourceLocation getEntityTexture(EntityCreepyZombie entity) {
		return new ResourceLocation(HalloweenLuckyBlockMod.MODID + ":textures/entity/creepy_zombie.png");
	}
	
}
