import java.util.ArrayList;
import java.util.Collections;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Vehicule implements Comparable<Vehicule> {
	private String nom;
	private double vitesseMax;
	private int poids;
	private int carburant;
	
	public Vehicule(String nom, double vitesseMax, int poids, int carburant) {
		this.nom = nom;
		this.vitesseMax = vitesseMax;
		this.poids = poids;
		this.carburant = carburant;
	}
	
	public Vehicule() {
		this("Anonyme", 130.00, 1000, 0);
	}
	
	@Override
	public String toString() {
		return getNom()+" -> vitesse max = "+getVitesseMax()+" km/h, poids = "+getPoids()+" kg";
	}
	
	public double performance() {
		return getVitesseMax() / getPoids();
	}
	
	public boolean meilleur(Vehicule autreVehicule) {
		return this.performance() > autreVehicule.performance();
	}
	
	public String getNom() {
		return nom;
	}
	public double getVitesseMax() {
		return vitesseMax;
	}
	public int getPoids() {
		return poids;
	}
	public int getCarburant() {
		return carburant;
	}
	
	public boolean estDeuxRoues() {
		return false;
	}

	@Override
	public int compareTo(Vehicule o) {
		if (this.meilleur(o)) {
			return -1;
		} else if (o.meilleur(this)) {
			return 1;
		} else {
			return 0;
		}
	}
	
}

class Voiture extends Vehicule{
	private String categorie;
	
	public Voiture(String nom, double vitesseMax, int poids, int carburant, String categorie) {
		super(nom, vitesseMax, poids, carburant);
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return super.toString()+", Voiture de "+getCategorie();
	}

	public String getCategorie() {
		return categorie;
	}

	@Override
	public boolean estDeuxRoues() {
		return super.estDeuxRoues();
	}
}
class Moto extends Vehicule {
	private boolean hasSideCar;
	
	public Moto(String nom, double vitesseMax, int poids, int carburant, boolean hasSideCar) {
		super(nom, vitesseMax, poids, carburant);
		this.hasSideCar = hasSideCar;
	}
	
	
	public boolean isHasSideCar() {
		return hasSideCar;
	}

	public Moto(String nom, double vitesseMax, int poids, int carburant) {
		this(nom, vitesseMax, poids, carburant, false);
	}

	@Override
	public String toString() {
		String str = ", Moto";
		if (isHasSideCar()) {
			str +=", avec sidecar";
		}
		return super.toString()+str;
	}

	@Override
	public boolean estDeuxRoues() {
		return !isHasSideCar();
	}
	
}

class GrandPrix extends Rallye {
	private ArrayList<Vehicule> participants = new ArrayList<Vehicule>();

	public void ajouter(Vehicule vehicule) {
		participants.add(vehicule);
	}
	
	@Override
	public boolean check() {
		boolean isDeuxRoues = false;
		boolean isQuatreRoues = false;
		for (Vehicule vehicule : participants) {
			if (vehicule.estDeuxRoues()) {
				isDeuxRoues = true;
			} else {
				isQuatreRoues = true;
			}
		}
		return !(isDeuxRoues && isQuatreRoues);
	}

	private int deduireCarburant(Vehicule vhc, int tours) {
		return vhc.getCarburant()-tours;
	}
	
	public void run(int tours) {
		if (!check()) {
			System.out.println("Pas de Grand Prix");
		} else {
			ArrayList<Vehicule> vhcFranchisLigne = new ArrayList<Vehicule>();
			for (Vehicule vehicule : participants) {
				if (deduireCarburant(vehicule, tours)>0) {
					vhcFranchisLigne.add(vehicule);
				}
			}
			
			if (vhcFranchisLigne.size() == 0) {
				System.out.println("Elimination de tous les vehicules");
			} else {
				Collections.sort(vhcFranchisLigne);
				System.out.println("Le gagnant du grand prix est ->\n"+vhcFranchisLigne.get(0));
			}
		}
	}
}

abstract class Rallye {
	public abstract boolean check();
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
 
    }
}
