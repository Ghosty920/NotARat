package me.ghosty.notarat;

/**
 * It's here in case I wanna add more later, or just for showcase
 */
public enum Maps {
	
	v1_8("field_71449_j", "field_74286_b", "field_148257_b", "field_148258_c"),
	/** can still be useful for dev envs (aka faster tests, or just people using it in a IDE) */
	NOT_OBF("session", "username", "playerID", "token");
	
	final String sess, name, id, tok;
	
	Maps(String sess, String name, String id, String tok) {
		this.sess = sess;
		this.name = name;
		this.id = id;
		this.tok = tok;
	}
	
}
