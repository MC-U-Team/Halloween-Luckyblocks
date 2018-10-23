package info.u_team.halloween_luckyblock;

import org.apache.logging.log4j.*;

public class HalloweenLuckyBlockConstants {
	
	public static final String MODID = "halloween_luckyblock";
	public static final String NAME = "Halloween LuckyBlock";
	public static final String VERSION = "${version}";
	public static final String MCVERSION = "${mcversion}";
	public static final String DEPENDENCIES = "required:forge@[14.23.4.2705,);required-after:uteamcore@[2.0.1.83,);";
	
	public static final String UPDATEURL = "https://api.u-team.info/update/halloween_luckyblock.json";
	
	public static final String COMMONPROXY = "de.luckycrew.halloween.proxy.CommonProxy";
	public static final String CLIENTPROXY = "de.luckycrew.halloween.proxy.ClientProxy";
	
	public static final Logger LOGGER = LogManager.getLogger(NAME);
	
	private HalloweenLuckyBlockConstants() {
	}
	
}
