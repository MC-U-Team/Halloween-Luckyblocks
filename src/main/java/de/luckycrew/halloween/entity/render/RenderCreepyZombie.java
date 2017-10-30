package de.luckycrew.halloween.entity.render;

import de.luckycrew.halloween.HalloweenConstants;
import de.luckycrew.halloween.entity.model.ModelCreepyZombie;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderCreepyZombie extends RenderLiving {
	
	public RenderCreepyZombie(RenderManager rendermanager) {
		super(rendermanager, new ModelCreepyZombie(), 0);
	}
	
	@Override
	public ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(HalloweenConstants.MODID + ":textures/entity/creepy_zombie.png");
	}
	
}
