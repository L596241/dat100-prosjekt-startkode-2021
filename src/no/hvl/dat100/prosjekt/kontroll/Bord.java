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
		KortSamling bunkeFraRandom = new KortSamling();
		bunkeFraRandom.leggTilAlle();
		Kort[] KortArrayRandomized = KortUtils.stokk(bunkeFraRandom);
		bunkeFra = new KortSamling();
		for(int i = 0; i < KortArrayRandomized.length; i++) {
			bunkeFra.leggTil(KortArrayRandomized[i]);
		}
		
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
	 * Tar øverste kortet fra fra-bunken og legger dettte til til-bunken (med
	 * billedsiden opp, men det trenger ikke gruppen tenke på).
	 */
	public void vendOversteFraBunke() {
		
		Kort[] taVersteSortert = KortUtils.sorter(bunkeFra);
		for (int i=0; i<taVersteSortert.length; i++) {
			
			if (taVersteSortert[i].getVerdi() > 1) {
				bunkeTil.leggTil(taVersteSortert[i]);
				bunkeFra.fjern(taVersteSortert[i]);
				break;
			}
			
		}
		
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
		
		Kort seoverste = bunkeFra.seSiste();
		return seoverste;
	}
	
	/**
	 * Når fra-bunken blir tom, tar man vare på kortet pÂ toppen av til-bunken.
	 * Deretter legges alle den andre kortene i til-bunken over i fra-bunken.
	 * Denne stokkes og kortet som man har tatt vare pÂ legges tilbake i
	 * til-bunken. Det vil nå være det eneste kortet i til-bunken.
	 */
	public void snuTilBunken() {
		if (bunkeFra.erTom()) {
		Kort sisteTilBunkeKort = bunkeTil.taSiste();
		Kort[] alleKortFraBunketilVenstreRandomisert = KortUtils.stokk(bunkeTil);
		int i = alleKortFraBunketilVenstreRandomisert.length-1;
		
		while (!bunkeTil.erTom()) {
			bunkeFra.leggTil(alleKortFraBunketilVenstreRandomisert[i]);
			i--;
		}
		bunkeTil.leggTil(sisteTilBunkeKort);
		}
		
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
