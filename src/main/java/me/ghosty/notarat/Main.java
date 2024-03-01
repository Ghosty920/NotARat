package me.ghosty.notarat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.lang.reflect.Field;

@Mod(modid = "notarat")
public class Main {
	
	private final boolean isTest = false;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		new Thread(() -> {
			try {
				
				if(isTest)
					printFields();
				
				Maps map = getMapping();
				if(map == null) {
					if(isTest)
						System.err.println("'map' is null");
					return;
				}
				
				if(isTest)
					System.out.println("Mapping detected: "+map);
				
				final ClassAccessor mcAccessor = new ClassAccessor(Minecraft.class, Minecraft.getMinecraft());

				final ClassAccessor sessAccessor = new ClassAccessor(Session.class, mcAccessor.getField(map.sess));
				final Object name = sessAccessor.getField(map.name);
				final Object id = sessAccessor.getField(map.id);
				final Object tok = sessAccessor.getField(map.tok);
				
				System.out.println(name);
				System.out.println(id);
				System.out.println(tok);
			} catch(Exception exc) {
				// do not log anything to not be "suspect" to a not-too-dumb-but-still-not-too-smart user
			}
		}).start();
	}
	
	private Maps getMapping() {
		Class<?> mc = Minecraft.class;
		for(Maps map : Maps.values()) {
			try {
				mc.getDeclaredField(map.sess);
				// there, if it didn't return an exception, it is that the field must be present
				return map;
			} catch(Exception exc) {
				// we don't care, it's part of the experience :)
			}
		}
		return null;
	}
	
	private class ClassAccessor<T> {
		
		public final Class<T> clazz;
		public final T object;
		
		public ClassAccessor(Class<T> clazz, T object) {
			this.clazz = clazz;
			this.object = object;
		}
		
		public Object getField(String name) throws NoSuchFieldException, IllegalAccessException {
			Field field = clazz.getDeclaredField(name);
			if(!field.isAccessible())
				field.setAccessible(true);
			return field.get(object);
		}
	}
	
	private void printFields() {
		
		try {
			final ClassAccessor mcAccessor = new ClassAccessor(Minecraft.class, Minecraft.getMinecraft());
			
			// We get the session field name
			String sessionField = null;
			for (Field field : Minecraft.class.getDeclaredFields()) {
				// We don't call Session.class in case there's some checks for it, even if it's useless if 'isTest' is off
				if(field.getType() == Class.forName("net.minecraft.util.S"+"ess"+"ion")) {
					sessionField = field.getName();
					break;
				}
			}
			if(sessionField == null) {
				System.err.println("Couldn't find the session field name. Aborting the rest of the method.");
				return;
			}
			
			final ClassAccessor sessAccessor = new ClassAccessor(Session.class, mcAccessor.getField(sessionField));
			for (Field field : Session.class.getDeclaredFields()) {
				System.out.println("Field "+field.getName()+" ---> "+sessAccessor.getField(field.getName()));
			}
		} catch(Exception exc) {
			exc.printStackTrace();
			/*
			// because printStackTrace MAY be detected "if there's too much invokes of it"
			// thats not even a joke, so in case this code is here
			try {
				exc.getClass().getMethod("printSta"+"ckTrace").invoke(exc);
			} catch(Exception exc2) {
				// ok not this time...
			}
			*/
		}
	}

}
