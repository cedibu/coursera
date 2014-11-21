/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
abstract class Navire {
	private int x;
	private int y;
	private int drapeau;
	private boolean detruit;
	
	public Navire(int x, int y, int drapeau) {
		setX(x);
		setY(y);
		this.drapeau = drapeau;
		this.detruit = false;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		if (x > Piraterie.MAX_X) {
			this.x = Piraterie.MAX_X;
		} else if (x < 0) {
			this.x = 0;
		} else {
			this.x = x;
		}
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		if (y > Piraterie.MAX_Y) {
			this.y = Piraterie.MAX_Y;
		} else if (y < 0) {
			this.y = 0;
		} else {
			this.y = y;
		}
	}
	
	public int getDrapeau() {
		return drapeau;
	}
	
	public boolean estDetruit() {
		return detruit;
	}
	
	public double distance(Navire autreNavire) {
		return Math.sqrt(Math.pow(this.x-autreNavire.getX(), 2)+Math.pow(this.y-autreNavire.getY(), 2));
	}
	
	public void avance(int unitsX, int unitsY) {
		setX(this.x+unitsX);
		setY(this.y+unitsY);
	}
	
	public void coule() {
		this.detruit = true;
	}
	
	public String getNom() {
		return "Bateau";
	}
	
	public void rencontre(Navire autreNavire) {
		if (this.getDrapeau() != autreNavire.getDrapeau()) {
			if (distance(autreNavire) < Piraterie.RAYON_RENCONTRE) {
				combat(autreNavire);
			}
		}
	}
	
	public abstract boolean estPacifique();
	
	public abstract void combat(Navire autreNavire);
	
	public abstract void recoitBoulet(); 
}

class Pirate extends Navire {
	private boolean isEndommage;
	
	public Pirate(int x, int y, int drapeau, boolean isEndommage) {
		super(x, y, drapeau);
		this.isEndommage = isEndommage;
	}
	
	public boolean estEndommage() {
		return isEndommage;
	}
	
	@Override
	public String getNom() {
		return "Bateau pirate";
	}
	
	public String getEtat() {
		if (estDetruit()) {
			return "détruit";
		} else if (estEndommage()) {
			return "ayant subi des dommages";
		} else {
			return "intact";
		}
	}
	
	@Override
	public String toString() {
		return getNom()+" avec drapeau "+getDrapeau()+" en ("+getX()+","+getY()+") -> "+getEtat();
	}

	@Override
	public void combat(Navire autreNavire) {
		if (!autreNavire.estPacifique()) {
			recoitBoulet();
		}
		
		if (!estPacifique()) {
			autreNavire.recoitBoulet();
		}
	}

	@Override
	public void recoitBoulet() {
		if (estEndommage()) {
			super.coule();
		} else {
			isEndommage = true;
		}
	}
	
	@Override
	public boolean estPacifique() {
		return false;
	}
}

class Marchand extends Navire {
	public Marchand(int x, int y, int drapeau) {
		super(x, y, drapeau);
	}

	@Override
	public String getNom() {
		return "Bateau marchand";
	}
	
	public String getEtat() {
		if (estDetruit()) {
			return "détruit";
		} else {
			return "intact";
		}
	}
	
	@Override
	public boolean estPacifique() {
		return true;
	}
	
	@Override
	public String toString() {
		return getNom()+" avec drapeau "+getDrapeau()+" en ("+getX()+","+getY()+") -> "+getEtat();
	}

	@Override
	public void combat(Navire autreNavire) {
		if (!autreNavire.estPacifique()) {
			recoitBoulet();
		}
	}

	@Override
	public void recoitBoulet() {
		super.coule();
	}
}

/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
class Piraterie {

    static public final int MAX_X = 500;
    static public final int MAX_Y = 500;
    static public final double RAYON_RENCONTRE = 10;

    static public void main(String[] args) {
        // Test de la partie 1
        System.out.println("***Test de la partie 1***");
        System.out.println();
        // un bateau pirate 0,0 avec le drapeau 1 et avec dommages
        Navire ship1 = new Pirate(0, 0, 1, true);
        // un bateau marchand en 25,0 avec le drapeau 2
        Navire ship2 = new Marchand(25, 0, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println("Distance: " + ship1.distance(ship2));
        System.out.println("Quelques deplacements horizontaux et verticaux");
        // se deplace de 75 unites a droite et 100 en haut
        ship1.avance(75, 100);
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println("Un deplacement en bas:");
        ship1.avance(0, -5);
        System.out.println(ship1);
        ship1.coule();
        ship2.coule();
        System.out.println("Apres destruction:");
        System.out.println(ship1);
        System.out.println(ship2);

        // Test de la partie 2
        System.out.println();
        System.out.println("***Test de la partie 2***");
        System.out.println();

        // deux vaisseaux sont enemis s'ils ont des drapeaux differents

        System.out.println("Bateau pirate et marchand ennemis (trop loin):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(0, 25, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Apres la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Bateau pirate et marchand ennemis (proches):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(2, 0, 2);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Apres la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Bateau pirate et marchand amis (proches):");
        // bateau pirate intact
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Marchand(2, 0, 1);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Apres la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Deux bateaux pirates ennemis intacts (proches):");
        // bateaux pirates intacts
        ship1 = new Pirate(0, 0, 1, false);
        ship2 = new Pirate(2, 0, 2, false);
        System.out.println(ship1);
        System.out.println(ship2);
        ship1.rencontre(ship2);
        System.out.println("Apres la rencontre:");
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println();

        System.out.println("Un bateau pirate intact et un avec dommages, ennemis:");
        // bateau pirate intact
        Navire ship3 = new Pirate(0, 2, 3, false);
        System.out.println(ship1);
        System.out.println(ship3);
        ship3.rencontre(ship1);
        System.out.println("Apres la rencontre:");
        System.out.println(ship1);
        System.out.println(ship3);
        System.out.println();

        System.out.println("Deux bateaux pirates ennemis avec dommages:");
        System.out.println(ship2);
        System.out.println(ship3);
        ship3.rencontre(ship2);
        System.out.println("Apres la rencontre:");
        System.out.println(ship2);
        System.out.println(ship3);
        System.out.println();
    }
}
