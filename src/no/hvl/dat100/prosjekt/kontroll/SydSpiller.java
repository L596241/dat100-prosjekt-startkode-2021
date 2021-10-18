package no.hvl.dat100.prosjekt.kontroll;

import java.util.Random;

import no.hvl.dat100.prosjekt.TODO;
import no.hvl.dat100.prosjekt.kontroll.dommer.Regler;
import no.hvl.dat100.prosjekt.kontroll.spill.Handling;
import no.hvl.dat100.prosjekt.kontroll.spill.HandlingsType;
import no.hvl.dat100.prosjekt.kontroll.spill.Spillere;
import no.hvl.dat100.prosjekt.modell.Kort;
import no.hvl.dat100.prosjekt.modell.KortSamling;

/**
 * Klasse som for å representere en vriåtter syd-spiller. Strategien er å lete
 * gjennom kortene man har på hand og spille det første som er lovlig.
 *
 */
public class SydSpiller extends Spiller {

	/**
	 * Konstruktør.
	 * 
	 * @param spiller
	 *            posisjon for spilleren (nord eller syd).
	 */
	public SydSpiller(Spillere spiller) {
		super(spiller);
	}

	/**
	 * Metode for å implementere strategi. Strategien er å spille det første
	 * kortet som er lovlig (også en åtter selv om man har andre kort som også
	 * kan spilles). Dersom man ikke har lovlige kort å spille, trekker man om
	 * man ikke allerede har trukket maks antall ganger. I så fall sier man
	 * forbi.
	 * 
	 * @param topp
	 *            kort som ligg øverst på til-bunken.
	 */
	@Override
	public Handling nesteHandling(Kort topp) {
		//Dette er nesten samme kode som nestehandling under spill
//		Oppretter arrayLister for de kortene vi har (og eventuelt kan spille) (Attere kan uansett spilles)
				Kort[] hand = getHand().getAllekort();
				KortSamling lovlige = new KortSamling();
				KortSamling attere = new KortSamling();

	// N�stet for-l�kke for � finne ut hvilke kort som kan spilles
	//Ser igjennom hva vi har p� handen (passer p� at handen og bord var opprettet riktig) -- Hvis vi hadde en feil, ville dette blitt null
				for (Kort kort : hand) {
					if (Regler.kanLeggeNed(kort, topp)) {
						if (Regler.atter(kort)) {
							attere.leggTil(kort);
						} else {
							lovlige.leggTil(kort);
						} //To forskjellige bunker lages, og lagrer kortene som kan spilles inklusiv attere (8)
					}
				}

				Kort spill = null;
				Kort[] spillFra = null;

				if (!lovlige.erTom()) {	//Hvis de to forskjellige bunkene ikke er tomme, samler vi det i en arrayliste med kort
					spillFra = lovlige.getAllekort();
				} else if (!attere.erTom())  {
					spillFra = attere.getAllekort();
				}

				Handling handling = null; //Lager en handlig (som kan v�re hva som helst)
				
				if (spillFra != null) { //Hvis jeg har noe som kan spilles: (SpillFra er ikke null)
					//vil et randomkort bli lagt ned
					
					Random r = new Random();
					int p = r.nextInt(spillFra.length);
					spill = spillFra[p];
					handling = new Handling(HandlingsType.LEGGNED, spill);
					// setAntallTrekk(0);
					
				} else if (getAntallTrekk() < Regler.maksTrekk()) {		 //hvis spiller ikke har noe som kan spilles: (spillfra er null)
					handling = new Handling(HandlingsType.TREKK, null);	//og det er plass til � trekke et kort, da trekker jeg.
				} else {
					handling = new Handling(HandlingsType.FORBI, null);	//ellers er eneste mulighet � passere (forbi)
					// setAntallTrekk(0);
				}

				return handling;
			}
	}
