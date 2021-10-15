package no.hvl.dat100.prosjekt.modell;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;

/**
 * Struktur for å lagre ei samling kort. Kan lagre hele kortstokken. Det finnes
 * en konstant i klassen Regler som angir antall kort i hver av de 4 fargene.
 * Når programmet er ferdig settes denne til 13, men under utvikling / testing
 * kan det være praktisk å ha denne mindre.
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
	 * Returnerer en tabell med kortene i samlinga. Tabellen trenger ikke være
	 * full. Kortene ligger sammenhengende fra starten av tabellen. Kan få tilgang
	 * til antallet ved å bruke metoden getAntallKort(). Metoden er først og
	 * fremst ment å brukes i testklasser. Om man trenger kortene utenfor,
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

		samling[antall] = kort;		// antall er posisjonen og begynner p� 0
		antall = antall + 1;		//antall �kes

	}

	/**
	 * Legger alle korta (hele kortstokken) til samlinga. Korta vil v�re sortert
	 * slik at de normalt m� stokkes f�r bruk.
	 */
	public void leggTilAlle() {

		
//		 Husk: bruk Regler.MAKS_KORT_FARGE for � f� antall kort per farge
		for (Kortfarge fargeValues : Kortfarge.values()) {
			for (int i = 1; i <= Regler.MAKS_KORT_FARGE; i++) {
				Kort newKort = new Kort(fargeValues, i);	//her f�r hvert fargekort alle mulige verdier. f.eks hjerte 1, hjerte 2, hjerte 3 osv
				leggTil(newKort);
			}	//loop som g�r igjennom alle mulige kortfarger og lager alle 
				//mulige antall kort til hver farge, deretter leggess dette til samling.
		}
		}


	/**
	 * Fjerner alle korta fra samlinga slik at den blir tom.
	 */
	public void fjernAlle() {

		for (int i = antall - 1; i >= 0; i--) {	//g�r fra siste til forste kort
			samling[i] = null;					//legger til null i verdi/farge p� hver plass (dvs fjerner kort fra slots/plass)
			antall--;
		}
	}

	/**
	 * Ser på siste kortet i samlinga.
	 * 
	 * @return siste kortet i samlinga, men det blir ikke fjernet. Dersom samalinga
	 *         er tom, returneres null.
	 */
	public Kort seSiste() {

		if (antall == 0)
			return null;
		else
			return samling[antall - 1];		//siste kortet i samlingen

	}

	/**
	 * Tek ut siste kort fra samlinga.
	 * 
	 * @return siste kortet i samlinga. Dersom samalinga er tom, returneres null.
	 */
	public Kort taSiste() {
		if (antall == 0) return null;
		else {
			for (int i = MAKS_KORT-1; i >= 0; i--) {
				if (samling[i] == null) ;
				else {
		Kort siste = null;
		if (antall != 0) {
			siste = samling[antall - 1];
			samling[antall - 1] = null;
			antall--;
			return siste;
		}
		return siste;
	}
			}
			return null;
		}
	}
	/**
	 * Undersøker om et kort finst i samlinga.
	 * 
	 * @param kort.
	 * 
	 * @return true om kortet finst i samlinga, false ellers.
	 */
	public boolean har(Kort kort) {
		if (kort == null) return false;
		for (int i = 0; i < antall; i++) {
			if (samling[i] == kort) {
				return true;
			}
			else;
		}
		return false;
	}

	/**
	 * Fjernar et kort frå samlinga. Dersom kortet ikke finnest i samlinga, skjer
	 * ingenting med samilingen
	 * 
	 * @param kort kortet som skal fjernast. Dersom kortet ikke finnes, skjer
	 *             ingenting.
	 * @return true om kortet blev fjernet fra samlinga, false ellers.
	 */

	public boolean fjern(Kort kort) {
		
		
		if (kort == null) return false;
		
			for (int i = 0; i <= MAKS_KORT-1; i++) {
				if (kort == samling[i]) {
					samling[i] = null;
					antall--;
					return true;
				}
			}
		return false;
	}

	/**
	 * Gir kortene som en tabell av samme lengde som antall kort i samlingen
	 * 
	 * @return tabell av kort som er i samlingen, der kort skal ha samme rekkefølge
	 *         som i kortsamlinga.
	 */
	public Kort[] getAllekort() {
		Kort[] allekort = new Kort[samling.length];
		for (int i=0; i<samling.length; i++) {
			allekort[i] = samling[i];
		}	// denne metoden returnerer en kopi av samling med samme verdier, men forskjellig referanse
		
		return allekort;

	}

}
