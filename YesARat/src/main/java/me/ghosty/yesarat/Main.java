package me.ghosty.yesarat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.lang.reflect.Field;

@Mod(modid = "yesarat")
public class Main {

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new Thread(() -> {
			try {
				final Session session = Minecraft.getMinecraft().getSession();
				System.out.println(session.getUsername());
				System.out.println(session.getPlayerID());
				System.out.println(session.getToken());
			} catch(Exception exc) {
			}
		}).start();
	}
	
}
