package info.u_team.halloween_luckyblock.entity.render;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.halloween_luckyblock.entity.model.ModelCreepyZombie;
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
		return new ResourceLocation(HalloweenLuckyBlockConstants.MODID + ":textures/entity/creepy_zombie.png");
	}
	
}
