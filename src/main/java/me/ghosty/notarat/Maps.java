package me.ghosty.notarat;

/**
 * It's here in case I wanna add more later, or just for showcase
 */
enum Maps {
	
	v1_8(get(71449, "j"), get(74286, "b"), get(148257, "b"), get(148258, "c")),
	/** can still be useful for dev envs (aka faster tests, or just people using it in a IDE) */
	NOT_OBF("ses" + "sion", "usern" + "ame", "playe" + "rID", "tok" + "en");
	
	final String sess, name, id, tok;
	
	Maps(String sess, String name, String id, String tok) {
		this.sess = sess;
		this.name = name;
		this.id = id;
		this.tok = tok;
	}
	
	/**
	 * Patch to RatRater2 which checks for defined strings
	 */
	private static String get(int num, String let) {
		return "field_" + num + "_" + let;
	}
	
}
