package no.hvl.dat100.prosjekt.modell;
import java.util.Random;
import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.modell.KortSamling;
public class KortUtils {

	/**
	 * Sorterer en samling. Rekkefølgen er bestemt av compareTo() i Kort-klassen.
	 * 
	 * @see Kort
	 * 
	 * @param samling
	 * 			samling av kort som skal sorteres. 
	 */
	
	public static Kort[] sorter(KortSamling samling) {
		
		int antallKort = samling.getAntalKort();
		Kort[] kortTab = samling.getSamling();

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
		
		return kortTab;
	}
	/**
	 * Stokkar en kortsamling. 
	 * 
	 * @param samling
	 * 			samling av kort som skal stokkes. 
	 */
	public static Kort[] stokk(KortSamling samling) {
		int antallKort = samling.getAntalKort();
		Kort [] midlertidigSamling = samling.getSamling();
		
		
		Random rand = new Random();
		for (int i = 0; i < antallKort; i++) {
			Kort midlertidigKort = midlertidigSamling[i];
			int index = rand.nextInt(antallKort - i) + i;
			midlertidigSamling[i] = midlertidigSamling[index];
			midlertidigSamling[index] = midlertidigKort;
	}	
		return midlertidigSamling;
		
}
}