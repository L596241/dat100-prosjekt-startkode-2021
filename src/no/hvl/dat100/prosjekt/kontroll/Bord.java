package no.hvl.dat100.prosjekt.kontroll;

import no.hvl.dat100.prosjekt.modell.KortSamling;
import no.hvl.dat100.prosjekt.modell.KortUtils;
import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.modell.Kort;

/**
 * Klasse som implementerer bordet som spilles på. 
 * 
 * Klassen har objektvariablene bunkeTil og bunkeFra som skal brukes til å representere 
 * kortene som er i de to bunker på border. 
 */
public class Bord {

	private KortSamling bunkeFra;
	private KortSamling bunkeTil;
	
	/**
	 * Metoden oppretter to bunker, til- og fra-bunken
	 * Alle kortene legges til fra-bunken. 
	 */
	public Bord() {
		
		bunkeFra = new KortSamling();
		bunkeFra.leggTilAlle();
		KortUtils.stokk(bunkeFra);
		bunkeTil = new KortSamling();

	}
	
	/**
	 * Gir peker/referanse til til-bunken.
	 * 
	 * @return referanse/peker til til-bunken.
	 */
	public KortSamling getBunkeTil() {
		
		return bunkeTil;
		
	}

	/**
	 * Gir peker/referanse til fra-bunken.
	 * 
	 * @return referanse/peker til fra-bunken.
	 */
	public KortSamling getBunkeFra() {
		
		return bunkeFra;
		
	}
	
	/**
	 * Sjekker om til-bunken er tom.
	 * 
	 * @return true om til-bunken er tom, false ellers.
	 */
	public boolean bunketilTom() {
		
		boolean tomBunke = bunkeTil.erTom();
		return tomBunke;
	}
	

	/**
	 * Sjekker om fra-bunken er tom.
	 * 
	 * @return true om fra-bunken er tom, false ellers.
	 */
	public boolean bunkefraTom() {
		
		boolean tomBunke = bunkeFra.erTom();
		return tomBunke;
		
	}
	
	/**
	 * Gir antall kort i fra-bunken.
	 * 
	 * @return antall kort i fra-bunken.
	 */
	public int antallBunkeFra() {
		
		int antallFraBunke = bunkeFra.getAntalKort();
		return antallFraBunke;
	}

	/**
	 * Gir antall kort i til-bunken.
	 * 
	 * @return antall kort i til-bunken.
	 */
	public int antallBunkeTil() {
		
		int antallTilBunke = bunkeTil.getAntalKort();
		return antallTilBunke;
	}
	
	/**
	 * Tar Overste kortet fra fra-bunken og legger dettte til til-bunken (med
	 * billedsiden opp, men det trenger ikke gruppen tenke på).
	 */
	public void vendOversteFraBunke() {
		
		Kort sisteKortBunkeFra = bunkeFra.taSiste();
		bunkeTil.leggTil(sisteKortBunkeFra);
		
	}

		
	/**
	 * Tar �verste kortet fra fra-bunken.
	 * 
	 * @return peker/referanse til det kort som blev tatt fra fra-bunken
	 */
	
	public Kort taOversteFraBunke() {
		
		Kort tafraoverst = bunkeFra.taSiste();
		return tafraoverst;
		
	}
	
	/**
	 * Metode som leser øverste kortet i til-bunken. Kortet vil fremdeles være
	 * øverst i til-bunken etter at metoden er utført.
	 * 
	 * @return peker/referanse til øverste kortet i til-bunken.
	 */
	public Kort seOversteBunkeTil() {
	
		Kort seOverste = bunkeTil.seSiste();
		return seOverste;
	}
	
	/**
	 * Når fra-bunken blir tom, tar man vare på kortet pÂ toppen av til-bunken.
	 * Deretter legges alle den andre kortene i til-bunken over i fra-bunken.
	 * Denne stokkes og kortet som man har tatt vare pÂ legges tilbake i
	 * til-bunken. Det vil nå være det eneste kortet i til-bunken.
	 */
	public void snuTilBunken() {
		
		if(!bunkeFra.erTom()) return;
		
		Kort sisteTilBunkeKort = bunkeTil.taSiste();
		Kort[] alleKortFraBunketilVenstre = bunkeTil.getAllekort();
		
		for(int i = 0; i < bunkeTil.getAntalKort(); i++) {
			bunkeFra.leggTil(alleKortFraBunketilVenstre[i]);
		}
		
		KortUtils.stokk(bunkeFra);
		
		bunkeTil.fjernAlle();
		bunkeTil.leggTil(sisteTilBunkeKort);
		
	}

		
	/**
	 * Metode som legger et kort i til-bunken. 
	 * 
	 * @param k
	 * 			kort som skal legges ned. 
	 * 	
	 */
	public void leggNedBunkeTil(Kort k) {
		
		bunkeTil.leggTil(k);
				
	}
}
