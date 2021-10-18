package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;

/**
 * Struktur for Ã¥ lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * en konstant i klassen Regler som angir antall kort i hver av de 4 fargene.
 * NÃ¥r programmet er ferdig settes denne til 13, men under utvikling / testing
 * kan det vÃ¦re praktisk Ã¥ ha denne mindre.
 * 
 */
public class KortSamling {

	private final int MAKS_KORT = 4 * Regler.MAKS_KORT_FARGE;

	private Kort[] samling;
	private int antall;

	/**
	 * Oppretter en tom Kortsamling med plass til MAKS_KORT (hele kortstokken).
	 */
	public KortSamling() {

		samling = new Kort[MAKS_KORT]; //dette er en default konstruktor for kortsamlingen
		antall = 0;

	}

	/**
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke vÃ¦re
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan fÃ¥ tilgang
	 * til antallet ved Ã¥ bruke metoden getAntallKort(). Metoden er fÃ¸rst og
	 * fremst ment Ã¥ brukes i testklasser. Om man trenger kortene utenfor,
	 * anbefales metoden getAlleKort().
	 * 
	 * @return tabell av kort.
	 */
	public Kort[] getSamling() { 

		return samling;

	}

	/**
	 * Antall kort i samlingen.
	 * 
	 * @return antall kort i samlinga.
	 */
	public int getAntalKort() {

		return antall;
	}

	/**
	 * Sjekker om samlinga er tom.
	 * 
	 * @return true om samlinga er tom, false ellers.
	 */
	public boolean erTom() {

		return (antall == 0);
	}

	/**
	 * Legg et kort til samlinga.
	 * 
	 * @param kort er kortet som skal leggast til.
	 */
	public void leggTil(Kort kort) {

		samling[antall] = kort;		// antall er posisjonen og begynner på 0
		antall = antall + 1;		//antall økes

	}

	/**
	 * Legger alle korta (hele kortstokken) til samlinga. Korta vil være sortert
	 * slik at de normalt må stokkes før bruk.
	 */
	public void leggTilAlle() {
//		 Husk: bruk Regler.MAKS_KORT_FARGE for å få antall kort per farge
			for (Kortfarge fargeValues : Kortfarge.values()) {
				for (int i = 1; i <= Regler.MAKS_KORT_FARGE; i++) {
					Kort newKort = new Kort(fargeValues, i);	//her får hvert fargekort alle mulige verdier. f.eks hjerte 1, hjerte 2, hjerte 3 osv
					leggTil(newKort);
				}	//loop som går igjennom alle mulige kortfarger og lager alle 
					//mulige antall kort til hver farge, deretter leggess dette til samling.
			}
		
	}
//		
//		}


	/**
	 * Fjerner alle korta fra samlinga slik at den blir tom.
	 */
	public void fjernAlle() {

		for (int i = antall - 1; i >= 0; i--) 
		{	//går fra siste til forste kort
		samling[i] = null;						//legger til null i verdi/farge på hver plass (dvs fjerner kort fra slots/plass)
		antall--;
		}
		
	}


//	}

	/**
	 * Ser pÃ¥ siste kortet i samlinga.
	 * 
	 * @return siste kortet i samlinga, men det blir ikke fjernet. Dersom samalinga
	 *         er tom, returneres null.
	 */
	public Kort seSiste() {
	
		if(antall == 0) return null; // Dersom kortsamling er tom
		Kort siste = samling[antall - 1];
		return siste;
		
	}

	/**
	 * Tek ut siste kort fra samlinga.
	 * 
	 * @return siste kortet i samlinga. Dersom samalinga er tom, returneres null.
	 */
	public Kort taSiste() {
		if(antall == 0) return null;
		Kort sisteKort = samling [antall - 1];
		samling [antall - 1] = null;
		antall--;				
		return sisteKort;
	}
//		
	/**
	 * UndersÃ¸ker om et kort finst i samlinga.
	 * 
	 * @param kort.
	 * 
	 * @return true om kortet finst i samlinga, false ellers.
	 */
	public boolean har(Kort kort) {
		boolean har = false;
		if(kort == null) 
			return false;
		for(int i = 0; i < samling.length; i++) {
			if(samling[i] != null) {
				if(kort.lik(samling[i])) 
					har = true;
			}
		}
		return har;
	}


	/**
	 * Fjernar et kort frÃ¥ samlinga. Dersom kortet ikke finnest i samlinga, skjer
	 * ingenting med samilingen
	 * 
	 * @param kort kortet som skal fjernast. Dersom kortet ikke finnes, skjer
	 *             ingenting.
	 * @return true om kortet blev fjernet fra samlinga, false ellers.
	 */

	public boolean fjern(Kort kort) {
		
		boolean fjernet = false;
		if(kort == null) return fjernet;
		if(antall < 1) return fjernet;
		for(int i = 0; i < MAKS_KORT; i++) {
			if(samling[i] != null) {
				if(kort.lik(samling[i])) {
					antall--;
					samling [i] = null;
					fjernet = true;
				}
			}
		}
		return fjernet;
	}


	/**
	 * Gir kortene som en tabell av samme lengde som antall kort i samlingen
	 * 
	 * @return tabell av kort som er i samlingen, der kort skal ha samme rekkefÃ¸lge
	 *         som i kortsamlinga.
	 */
	public Kort[] getAllekort() {

		Kort[] alleKort = new Kort[(antall)];
		int a = 0;
		for (int i=0; i<samling.length; i++) {
			if(samling[i] != null) {
				alleKort[a] = samling[i]; // denne metoden returnerer en kopi av samling med samme verdier, men forskjellig referanse	
				a++;
			}
		}

		return alleKort;
}
}
