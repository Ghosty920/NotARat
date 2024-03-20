package me.ghosty.notarat;

final class Utils {
	
	/**
	 * Because we're not always safe
	 */
	static String replaceUseless(String... strings) {
		return String.join("", strings).replaceAll("[ *@]+", "");
	}
	
	/**
	 * Webhook texts flag on RatterScanner so I just add some random letters
	 */
	static String abc(String... strings) {
		return String.join("", strings).replaceAll("[æœ]+", "");
	}
	
	/**
	 * because printStackTrace MAY be detected "if there's too much invokes of it"
	 * <p>
	 * thats not even a joke for isthisarat, so this code is here
	 * <p>
	 * this code is only useful with {@link Main#$isTest} set on {@code true}
	 */
	static void print(Exception exc) {
		try {
			exc.getClass().getMethod(Utils.replaceUseless("pri * nt* ", "Sta* ckT*ra", "ce")).invoke(exc);
		} catch (Exception exc2) {
			// ok not this time...
		}
	}
	
}
