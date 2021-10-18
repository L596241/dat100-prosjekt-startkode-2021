package no.hvl.dat100.prosjekt.kontroll;


import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortUtils;

/**
 * Klassen har objektvariaber som er referanser til de spillerne, nord og syd
 * (type ISpiller). Den har ogs√Ç en bunke man deler/trekker fra og en bunke man
 * spiller til.
 * 
 */
public class Spill {

	private ISpiller nord;
	private ISpiller syd;
	
	private Bord bord;
	
	// antall kort som skal deles ut til hver spiller ved start
	public final static int ANTALL_KORT_START = Regler.ANTALL_KORT_START;
	
	public Spill() {

		 nord = new NordSpiller(Spillere.NORD);
		 syd = new SydSpiller(Spillere.SYD);
		 bord = new Bord();
	}
	
	/**
	 * Gir referanse/peker til bord.
	 * 
	 * @return referanse/peker bord objekt.
	 */
	public Bord getBord() {
		return bord;
		
	}
	
	/**
	 * Gir referanse/peker til syd spilleren.
	 * 
	 * @return referanse/peker til syd spiller.
	 */
	public ISpiller getSyd() {
		
		return syd;
		
	}

	/**
	 * Gir referanse/peker til nord.
	 * 
	 * @return referanse/peker til nord.
	 */
	public ISpiller getNord() {
		
		return nord;
	}

	/**
	 * Metoden oppretter to spillere, nord og syd. Det opprettes to bunker, fra
	 * og til. Alle kortene legges til fra. Bunken fra stokkes. Deretter deles
	 * det ut kort fra fra-bunken til nord og syd i henhold til regler. Til
	 * slutt tas √∏verste kortet fra fra-bunken og legges til til-bunken.
	 * 
	 * Nord har type RandomSpiller (som er forh√•ndefinert). Syd vil v√¶re spiller
	 * av en klasse laget av gruppen (implementeres i oppgave 3).
	 */
	public void start() {
		
		delutKort();
		bord.vendOversteFraBunke();
//		}
		// Tar det siste kortet fra fra-bunke og legges pÂ til-bunke
	}

	/**
	 * Deler ut kort til nord og syd.
	 * 
	 */
	private void delutKort() {
		for (int i = 0; i < Regler.ANTALL_KORT_START; i++) {
			nord.leggTilKort(bord.taOversteFraBunke());
			syd.leggTilKort(bord.taOversteFraBunke());
	}
		}

	/**
	 * Trekker et kort fra fra-bunken til spilleren gitt som parameter. Om
	 * fra-bunken er tom, m√• man "snu" til-bunken. Se info om metoden
	 * snuTilBunken().
	 * 
	 * @param spiller
	 *            spilleren som trekker.
	 * 
	 * @return kortet som trekkes.
	 */
	public Kort trekkFraBunke(ISpiller spiller) {

		Kort sisteKort = bord.taOversteFraBunke();
		if(sisteKort == null) {
			bord.snuTilBunken();
			sisteKort = bord.taOversteFraBunke();
		}
		spiller.trekker(sisteKort);
		return sisteKort;
		
	}
	/**
	 * Gir neste handling for en spiller (spilt et kort, trekker et kort, forbi)
	 * 
	 * @param spiller
	 *            spiller som handle.
	 * 
	 * @return handlingen som blir utf√∏rt.
	 */
	public Handling nesteHandling(ISpiller spiller) {
	
		//	Oppretter arrayLister for de kortene vi har (og eventuelt kan spille) (Attere kan uansett spilles)
		Kort[] hand = spiller.getHand().getAllekort();
		KortSamling lovlige = new KortSamling();
		KortSamling attere = new KortSamling();
		
		// N¯stet for-l¯kke for Â finne ut hvilke kort som kan spilles
		//Ser igjennom hva vi har pÂ handen (passer pÂ at handen og bord var opprettet riktig) -- Hvis vi hadde en feil, ville dette blitt null
		for (Kort kort : hand) {
			if(kort != null && bord.seOversteBunkeTil() != null) {
				if (Regler.kanLeggeNed(kort, bord.seOversteBunkeTil())) {
					if (Regler.atter(kort)) {
						attere.leggTil(kort);
					} else {
						lovlige.leggTil(kort);
					}
				}	//To forskjellige bunker lages, og lagrer kortene som kan spilles inklusiv attere (8)
			}		
		}
		
		Kort spill = null;
		Kort[] spillFra = null;

		if (!lovlige.erTom()) { 	//Hvis de to forskjellige bunkene ikke er tomme, samler vi det i en arrayliste med kort
			spillFra = lovlige.getAllekort();
		} else if (!attere.erTom())  {
			spillFra = attere.getAllekort();
		}

		Handling handling = null;	//Lager en handlig (som kan vÊre hva som helst)
		
		if (spillFra != null) {	//Hvis jeg har noe som kan spilles: (SpillFra er ikke null)
								//vil et randomkort bli lagt ned
			Random random = new Random();
			int p = random.nextInt(spillFra.length);
			spill = spillFra[p];
			handling = new Handling(HandlingsType.LEGGNED, spill);
			
		} else if (spiller.getAntallTrekk() < Regler.maksTrekk()) {	//hvis jeg ikke har noe som kan spilles: (spillfra er null)
			handling = new Handling(HandlingsType.TREKK, null);		//og jeg har plass til Â trekke et kort, da trekker jeg.
		} else {						
			handling = new Handling(HandlingsType.FORBI, null);		//ellers er eneste mulighet Â passere (forbi)
		}

		return handling;
		
	}
		

	/**
	 * Metoden spiller et kort. Den sjekker at spiller har kortet. Dersom det er
	 * tilfelle, fjernes kortet fra spilleren og legges til til-bunken. Metoden
	 * nulltiller ogs√• antall ganger spilleren har trukket kort.
	 * 
	 * @param spiller
	 *            den som spiller.
	 * @param kort
	 *            kort som spilles.
	 * 
	 * @return true dersom spilleren har kortet, false ellers.
	 */
	public boolean leggnedKort(ISpiller spiller, Kort kort) {
			
			// TODO - START
			if(spiller.getHand().har(kort)) {
				bord.leggNedBunkeTil(kort);
				spiller.fjernKort(kort);
				spiller.setAntallTrekk(0);
				return true;
			} else return false;
		
	}
		

	/**
	 * Metode for √• si forbi. M√• nullstille antall ganger spilleren har trukket
	 * kort.
	 * 
	 * @param spiller
	 *            spilleren som er i tur.
	 */
	public void forbiSpiller(ISpiller spiller) {
		
		spiller.setAntallTrekk(0);
	}

	/**
	 * Metode for √• utf√∏re en handling (trekke, spille, forbi). Dersom handling
	 * er kort, blir kortet ogs√• spilt.
	 * 
	 * @param spiller
	 *            spiller som utf√∏rer handlingen.
	 * @param handling
	 *            handling som utf√∏res.
	 * 
	 * @return kort som trekkes, kort som spilles eller null ved forbi.
	 */
	public Kort utforHandling(ISpiller spiller, Handling handling) {
		
		//Her kaller vi funksjonene ut ifra handling vi selv gj¯r i spillet
		Kort kort = null;
		if(handling.getType() == HandlingsType.FORBI) {
			forbiSpiller(spiller);
			kort = null;
		} else if(handling.getType() == HandlingsType.LEGGNED) {
			leggnedKort(spiller,handling.getKort());
			kort = handling.getKort();
		} else if(handling.getType() == HandlingsType.TREKK) {
			kort = trekkFraBunke(spiller);
		}
		
		return kort;

		// Hint: del opp i de tre mulige handlinger og vurder 
		// om noen andre private metoder i klassen kan brukes
		// til √• implementere denne metoden
		
	}

}