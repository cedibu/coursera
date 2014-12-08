import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class OptionVoyage {
	private String nom;
	private double prix;
	
	public OptionVoyage(String nom, double prix) {
		this.nom = nom;
		this.prix = prix;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public double prix() {
		return this.prix;
	}
	
	@Override
	public String toString() {
		return getNom()+" -> "+prix()+" CHF";
	}
}

class Sejour extends OptionVoyage{
	private int nombreNuits;
	private double prixParNuit;
	
	public Sejour(String nom, double prix) {
		super(nom, prix);
	}
	
	public Sejour(String nom, double prix, int nombreNuits, double prixParNuit) {
		super(nom, prix);
		this.nombreNuits = nombreNuits;
		this.prixParNuit = prixParNuit;
	}
	
	@Override
	public double prix() {
		return (this.nombreNuits * this.prixParNuit) + super.prix();
	}
}

class Transport extends OptionVoyage{
	public final static double TARIF_LONG = 1500.0;
	public final static double TARIF_BASE = 200.0;
	private boolean trajetLong;
	
	public Transport(String nom, double prix) {
		this(nom, prix, false);
	}
	
	public Transport(String nom, double prix, boolean trajetLong) {
		super(nom, prix);
		this.trajetLong = trajetLong;
	}
	
	@Override
	public double prix() {
		if (this.trajetLong) {
			return TARIF_LONG + super.prix();
		} else {
			return TARIF_BASE + super.prix();
		}
	}
}

class KitVoyage {
	private ArrayList<OptionVoyage> listOptions = new ArrayList<OptionVoyage>();
	private String depart;
	private String destination;
	
	public KitVoyage(String depart, String destination) {
		this.depart = depart;
		this.destination = destination;
	}
	
	public double prix() {
		double total = 0.0;
		for (OptionVoyage optionVoyage : listOptions) {
			total += optionVoyage.prix();
		}
		return total;
	}
	
	@Override
	public String toString() {
		String str = "Voyage de "+this.depart+" a "+this.destination+", avec pour options :\n";
		for (OptionVoyage optionVoyage : listOptions) {
			str += "- "+optionVoyage+"\n";
		}
		str += "Prix total : "+prix()+" CHF";
		return str;
	}
	
	public void ajouterOption(OptionVoyage optionVoyage) {
		if (optionVoyage != null) {
			listOptions.add(optionVoyage);
		}
	}
	
	public void annuler() {
		listOptions.clear();
	}
	
	public int getNbOptions() {
		return listOptions.size();
	}
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Voyage {
	public static void main(String args[]) {
		
		// TEST 1
		System.out.println("Test partie 1 : ");
		System.out.println("----------------");
		OptionVoyage option1 = new OptionVoyage("Sejour au camping", 40.0);
		System.out.println(option1.toString());

		OptionVoyage option2 = new OptionVoyage("Visite guidee : London by night" , 50.0);
		System.out.println(option2.toString());
		System.out.println();

		// FIN TEST 1

	
		// TEST 2
		System.out.println("Test partie 2 : ");
		System.out.println("----------------");

		Transport transp1 = new Transport("Trajet en car ", 50.0);
		System.out.println(transp1.toString());

		Transport transp2 = new Transport("Croisiere", 1300.0);
		System.out.println(transp2.toString());

		Sejour sejour1 = new Sejour("Camping les flots bleus", 20.0, 10, 30.0);
		System.out.println(sejour1.toString());
		System.out.println();

		// FIN TEST 2

		
		// TEST 3
		System.out.println("Test partie 3 : ");
		System.out.println("----------------");

		KitVoyage kit1 = new KitVoyage("Zurich", "Paris");
		kit1.ajouterOption(new Transport("Trajet en train", 50.0));
		kit1.ajouterOption(new Sejour("Hotel 3* : Les amandiers ", 40.0, 5, 100.0));
		System.out.println(kit1.toString());
		System.out.println();		
		kit1.annuler();
		
		KitVoyage kit2 = new KitVoyage("Zurich", "New York");
		kit2.ajouterOption(new Transport("Trajet en avion", 50.0, true));
		kit2.ajouterOption(new Sejour("Hotel 4* : Ambassador Plazza  ", 100.0, 2, 250.0));
		System.out.println(kit2.toString());
		kit2.annuler();
		
		// FIN TEST 3
	}
}
