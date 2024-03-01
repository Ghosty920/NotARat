package me.ghosty.notarat;

final class Utils {
	
	static String replaceUseless(String... strings) {
		return String.join("", strings).replace("_", "").replace(" ", "");
	}
	
}
