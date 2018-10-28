package info.u_team.halloween_luckyblock;

import org.apache.logging.log4j.*;

public class HalloweenLuckyBlockConstants {
	
	public static final String MODID = "halloween_luckyblock";
	public static final String NAME = "Halloween LuckyBlock";
	public static final String VERSION = "${version}";
	public static final String MCVERSION = "${mcversion}";
	public static final String DEPENDENCIES = "required:forge@[14.23.4.2745,);required-after:uteamcore@[2.0.1.85,);";
	
	public static final String UPDATEURL = "https://api.u-team.info/update/halloween_luckyblock.json";
	
	public static final String COMMONPROXY = "info.u_team.halloween_luckyblock.proxy.CommonProxy";
	public static final String CLIENTPROXY = "info.u_team.halloween_luckyblock.proxy.ClientProxy";
	
	public static final Logger LOGGER = LogManager.getLogger(NAME);
	
	private HalloweenLuckyBlockConstants() {
	}
	
}
