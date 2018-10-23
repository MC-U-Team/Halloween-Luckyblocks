package info.u_team.halloween_luckyblock.listener;

import info.u_team.halloween_luckyblock.init.HalloweenLuckyBlockItems;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.*;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ListenerKillerKniveAttack {
	
	@SubscribeEvent
	public void on(LivingAttackEvent event) {
		Entity entity = event.source.getEntity();
		if (entity == null || !(entity instanceof EntityPlayerMP)) {
			return;
		}
		EntityPlayerMP player = (EntityPlayerMP) entity;
		ItemStack stack = player.inventory.getCurrentItem();
		if (stack == null || stack.getItem() == null) {
			return;
		}
		Item item = stack.getItem();
		if (item == HalloweenLuckyBlockItems.killerknive) {
			EntityLivingBase base = event.entityLiving;
			
			Vec3 lookplayer = player.getLookVec();
			Vec3 lookbase = base.getLookVec();
			
			double differx = Math.abs(lookbase.xCoord - lookplayer.xCoord);
			double differz = Math.abs(lookbase.zCoord - lookplayer.zCoord);
			
			if (differx < 0.4 && differz < 0.4) {
				base.onKillCommand();
				stack.damageItem(40, player);
				if (stack.getItemDamage() >= stack.getMaxDamage() || stack.stackSize == 0) {
					player.destroyCurrentEquippedItem();
				}
			}
		}
	}
}
