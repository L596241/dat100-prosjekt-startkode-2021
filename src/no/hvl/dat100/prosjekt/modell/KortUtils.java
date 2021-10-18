package no.hvl.dat100.prosjekt.modell;
import java.util.Random;
import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.modell.KortSamling;
public class KortUtils {

	/**
	 * Sorterer en samling. RekkefÃ¸lgen er bestemt av compareTo() i Kort-klassen.
	 * 
	 * @see Kort
	 * 
	 * @param samling
	 * 			samling av kort som skal sorteres. 
	 */

		public static void sorter(KortSamling samling) {
		
			//Kopierer først kortene fra samling
		Kort[] kortSamlingSomSkalSorteres = samling.getAllekort();
		
		// Sorteringen skjer på kopien vi gjorde av samling
		for(int i = 1; i < kortSamlingSomSkalSorteres.length; i++) {
			int j = i;
			while(j > 0 && (kortSamlingSomSkalSorteres[j-1].compareTo(kortSamlingSomSkalSorteres[j]) > 0)) {
				Kort k = kortSamlingSomSkalSorteres[j];
				kortSamlingSomSkalSorteres[j] = kortSamlingSomSkalSorteres[j-1];
				kortSamlingSomSkalSorteres[j-1] = k;
				j = j-1;
			}
		}
		
		//Nå har vi en sortert kopi laget fra original samling.
		//Nå skal vi slette den gamle samling (ikke sorterte) og leggetil den nye (allerede sorterte)
		samling.fjernAlle();
		for(int i = 0; i < kortSamlingSomSkalSorteres.length; i++) {
			samling.leggTil(kortSamlingSomSkalSorteres[i]);
		}
		
	}

	/**
	 * Stokkar en kortsamling. 
	 * 
	 * @param samling
	 * 			samling av kort som skal stokkes. 
	 */
		
		
		public static void stokk(KortSamling samling) {
			// First a copy of the cards contained on the Samling is created
			Kort[] kortSamlingSomSkalStokkes = samling.getAllekort();
			//Antall kort inneholdt er viktig for å lage et random nummer som er mindre eller lik
			int antall = samling.getAntalKort();
			Random r = new Random();
			//Similert til sortering, men randomiseringen er utført
			for(int i = 0; i < antall; i++) {
				int nyRandomPosisjon = r.nextInt(antall);
				Kort kopiAvKort = kortSamlingSomSkalStokkes[nyRandomPosisjon];
				kortSamlingSomSkalStokkes[nyRandomPosisjon] = kortSamlingSomSkalStokkes[i];
				kortSamlingSomSkalStokkes[i] = kopiAvKort;
			}
			//Nå må vi fjerne den gamle kortsamlingen fra samling(ikke random) og sette inn en ny en (allerede randomisert)
			samling.fjernAlle();
			for(int i = 0; i < antall; i++) {
				samling.leggTil(kortSamlingSomSkalStokkes[i]);
			}

		}
		
	}
