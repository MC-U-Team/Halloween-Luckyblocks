package info.u_team.halloween_luckyblock.event;

import info.u_team.halloween_luckyblock.core.LuckyEvent;
import info.u_team.u_team_core.util.MathUtil;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class LuckyEventBoom extends LuckyEvent {
	
	public LuckyEventBoom() {
		super("Boom", 2);
	}
	
	@Override
	public void execute(ServerPlayerEntity player, ServerWorld world, BlockPos pos) {
		final int x = pos.getX();
		final int y = pos.getY();
		final int z = pos.getZ();
		
		final float offx = MathUtil.randomNumberInRange(0.3F, 1F);
		final float offy = MathUtil.randomNumberInRange(0.5F, 1.5F);
		final float offz = MathUtil.randomNumberInRange(0.3F, 1F);
		
		world.spawnParticle(ParticleTypes.CRIT, x, y, z, 200, offx, offy, offz, .000001);
		
		world.addEntity(new TNTEntity(world, x, y, z, player));
	}
}
