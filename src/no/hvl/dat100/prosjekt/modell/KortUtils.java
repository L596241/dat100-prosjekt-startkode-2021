package no.hvl.dat100.prosjekt.modell;

import java.util.Random;

import no.hvl.dat100.prosjekt.TODO;

public class KortUtils {

	/**
	 * Sorterer en samling. Rekkefølgen er bestemt av compareTo() i Kort-klassen.
	 * 
	 * @see Kort
	 * 
	 * @param samling
	 * 			samling av kort som skal sorteres. 
	 */
	
	public static void sorter(KortSamling samling) {
		
		int antallKort = getAntalKort();
		Kort[] kortTab = getSamling();

		for (int i = 0; i < antallKort - 1; i++) {
			int minste = i;
			for (int j = i + 1; j < antallKort; j++) {
				if (kortTab[j].compareTo(kortTab[minste]) < 0) {
					minste = j;
				}
			}
			Kort k = kortTab[i];
			kortTab[i] = kortTab[minste];
			kortTab[minste] = k;
		}
	}
	/**
	 * Stokkar en kortsamling. 
	 * 
	 * @param samling
	 * 			samling av kort som skal stokkes. 
	 */
	public static void stokk(KortSamling samling) {
		
		Random rand = new Random();
		for (int i = 0; i < antallKort; i++) {
			Kort gammel = samling[i];
			int index = rand.nextInt(antall - i) + i;
			samling[i] = samling[index];
			samling[index] = gammel;
		}
		
		throw new UnsupportedOperationException(TODO.method());
		// TODO - END
	}
	
}
