import java.util.ArrayList;

class Auteur {

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
	// Completer la classe Auteur ici
	private String nom;
	private boolean isPrime;
	
	public Auteur(String nom, boolean isPrime) {
		this.nom = nom;
		this.isPrime = isPrime;
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean getPrix() {
		return isPrime;
	}
}

class Oeuvre
{
 	// Completer la classe Oeuvre ici
	private String titre;
	private Auteur auteur;
	private String langue;
	
	public Oeuvre(String titre, Auteur auteur) {
		this(titre, auteur, "francais");
	}
	
	public Oeuvre(String titre, Auteur auteur, String langue) {
		this.titre = titre;
		this.auteur = auteur;
		this.langue = langue;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public Auteur getAuteur() {
		return auteur;
	}
	
	public String getLangue() {
		return langue;
	}
	
	public void afficher() {
		System.out.println(this);
	}
	
	@Override
    public String toString() {
		return getTitre()+", "+getAuteur().getNom()+", en "+ getLangue();
	}
}

// completer les autres classes ici
class Exemplaire
{
	private Oeuvre oeuvre;
	
	public Exemplaire(Oeuvre oeuvre) {
		this.oeuvre = oeuvre;
		System.out.println("Nouvel exemplaire -> "+getOeuvre());
	}
	
	public Exemplaire(Exemplaire exemplaire) {
		this.oeuvre = exemplaire.oeuvre;
		System.out.println("Copie d'un exemplaire de -> "+this.getOeuvre());
	}
	
	public Oeuvre getOeuvre() {
		return oeuvre;
	}
	
	public void afficher() {
		System.out.println("Un exemplaire de "+this.getOeuvre());
	}
}

class Bibliotheque {
	private String nom;
	private ArrayList<Exemplaire> exemplaires;
	
	public Bibliotheque(String nom) {
		this.nom = nom;
		exemplaires = new ArrayList<Exemplaire>();
		System.out.println("La bibliothèque "+getNom()+" est ouverte !");
	}
	
	public String getNom(){
		return nom;
	}
	
	public void stocker(Oeuvre oeuvre, int nombreExemplaire) {
		Exemplaire exemplaire = new Exemplaire(oeuvre);
		
		for (int i = 0; i < nombreExemplaire; i++) {
			this.exemplaires.add(exemplaire);	
		}
	}
	
	public void stocker(Oeuvre oeuvre) {
		stocker(oeuvre, 1);
	}
	
	public ArrayList<Exemplaire> listerExemplaires(String langue) {
		if ("".equals(langue)) {
			return this.exemplaires;
		}
		
		ArrayList<Exemplaire> exemplaireARetourner = new ArrayList<Exemplaire>();
		for (Exemplaire exemplaire : exemplaires) {
			
			if (exemplaire.getOeuvre().getLangue().equals(langue)) {
				exemplaireARetourner.add(exemplaire);
			}
		}
		return exemplaireARetourner;
	}
	
	public ArrayList<Exemplaire> listerExemplaires() {
		return listerExemplaires("");
	}
	
	public int compterExemplaires(Oeuvre oeuvre) {
		int nbExemplaires = 0;
		for (Exemplaire exemplaire : listerExemplaires()) {
			if (exemplaire.getOeuvre().getTitre().equals(oeuvre.getTitre())) {
				nbExemplaires++;
			}
		}
		
		return nbExemplaires;
	}

	public void afficherAuteur(boolean seulementPrimes) {
		for (Exemplaire exemplaire : listerExemplaires()) {
			if (seulementPrimes) {
				if (exemplaire.getOeuvre().getAuteur().getPrix()) {
					System.out.println(exemplaire.getOeuvre().getAuteur().getNom());
				}
			} else {
				if (!exemplaire.getOeuvre().getAuteur().getPrix()) {
					System.out.println(exemplaire.getOeuvre().getAuteur().getNom());
				}
			}
		}
	}
	
	public void afficherAuteur() {
		afficherAuteur(true);
	}
	
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
/*******************************************
 * Ce qui suit est propose' pour vous aider
 * dans vos tests, mais votre programme sera
 * teste' avec d'autres donnees.
 *******************************************/

public class Biblio {

    public static void afficherExemplaires(ArrayList<Exemplaire> exemplaires) {
        for (Exemplaire exemplaire : exemplaires) {
            System.out.print("\t");
            exemplaire.afficher();
        }
    }

    public static void main(String[] args) {
        // create and store all the exemplaries
        Auteur a1 = new Auteur("Victor Hugo", false);
        Auteur a2 = new Auteur("Alexandre Dumas", false);
        Auteur a3 = new Auteur("Raymond Queneau", true);

        Oeuvre o1 = new Oeuvre("Les Miserables", a1, "francais");
        Oeuvre o2 = new Oeuvre("L\'Homme qui rit", a1, "francais");
        Oeuvre o3 = new Oeuvre("Le Comte de Monte-Cristo", a2, "francais");
        Oeuvre o4 = new Oeuvre("Zazie dans le metro", a3, "francais");
        Oeuvre o5 = new Oeuvre("The count of Monte-Cristo", a2, "anglais");

        Bibliotheque biblio = new Bibliotheque("municipale");
        biblio.stocker(o1, 2);
        biblio.stocker(o2);
        biblio.stocker(o3, 3);
        biblio.stocker(o4);
        biblio.stocker(o5);

        // ...
        System.out.println("La bibliotheque " + biblio.getNom() + " offre ");
        afficherExemplaires(biblio.listerExemplaires());
        String langue = "anglais";
        System.out.println("Les exemplaires en " + langue + " sont  ");
        afficherExemplaires(biblio.listerExemplaires(langue));
        System.out.println("Les auteurs a succes sont  ");
        biblio.afficherAuteur();
        System.out.print("Il y a " + biblio.compterExemplaires(o3) + " exemplaires");
        System.out.println(" de  " + o3.getTitre());
    }
}

