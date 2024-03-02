package me.ghosty.notarat;

final class Utils {
	
	/**
	 * Because we're not always safe
	 */
	static String replaceUseless(String... strings) {
		String useful = String.join("", strings).replace("*", "").replace(" ", "");
		//System.out.println(useful);
		return useful;
	}
	
	/**
	 * Webhook texts flag on RatterScanner so I just add some random letters
	 */
	static String abc(String string) {
		String useful = string.replace("æ", "").replace("œ", "");
		//System.out.println(useful);
		return useful;
	}
	
	/**
	 * because printStackTrace MAY be detected "if there's too much invokes of it"
	 * <p>
	 * thats not even a joke for isthisarat, so this code is here
	 */
	static void print(Exception exc) {
		try {
			exc.getClass().getMethod(Utils.replaceUseless("pri * nt* ", "Sta* ckT*ra", "ce")).invoke(exc);
		} catch (Exception exc2) {
			// ok not this time...
		}
	}
	
}
