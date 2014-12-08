import java.util.ArrayList;


/*******************************************
 * Completez le programme a partir d'ici.

 * Tableau à utiliser pour identifier les cases rouges :
 {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
 *******************************************/
abstract class Mise {
	private int mise;

	public Mise(int mise) {
		this.mise = mise;
	}
	
	public int getMise() {
		return mise;
	}
	
	public abstract int gain(int numero);
}

class Pleine extends Mise {
	public final static int FACTEUR_GAIN = 35;
	private int numero;
	
	public Pleine(int mise, int numero) {
		super(mise);
		this.numero = numero;
	}

	@Override
	public int gain(int numero) {
		if (this.numero == numero) {
			return super.getMise() * FACTEUR_GAIN;
		} else {
			return 0;
		}
	}
}

class Rouges extends Mise {
	public final static Integer[] ROUGES = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
	
	public Rouges(int mise) {
		super(mise);
	}

	@Override
	public int gain(int numero) {
		for (Integer numeroRouge : ROUGES) {
			if (numeroRouge == numero) {
				return super.getMise();
			}
		}
		return 0;
	}
}

class Joueur {
	private String nom;
	private Mise mise;
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setStrategie(Mise mise) {
		this.mise = mise;
	}
	
	public int getMise() {
		if (this.mise == null) {
			return 0;
		} else {
			return mise.getMise();
		}
	}
	
	public int gain(int n) {
		if (this.mise == null) {
			return 0;
		} else {
			return mise.gain(n);
		}
	}
}

abstract class Roulette {
	private ArrayList<Joueur> participants = new ArrayList<Joueur>();
	private int gainMaison;
	private int tirage = 0;
	
	public Roulette() {
		gainMaison = 0;
	}
	
	public void participe(Joueur joueur) {
		participants.add(joueur);
	}
	
	public int getParticipants() {
		return participants.size();
	}
	
	public int getGainMaison() {
		return gainMaison;
	}
	
	public int getTirage() {
		return tirage;
	}
	
	public void rienNeVaPlus(int tirage) {
		this.tirage = tirage;
		calculerGainMaison();
	}

	public abstract int perteMise(int miseDuJoueur);
	
	
	public void calculerGainMaison() {
		this.gainMaison = 0;
		for (Joueur joueur : participants) {
			int gain = joueur.gain(getTirage()); 
			
			if (gain == 0) {
				this.gainMaison += perteMise(joueur.getMise());
			} else {
				this.gainMaison -= gain;
			}
		}
		  
	}
	
	@Override
	public String toString() {
		String str = "Croupier : le numero du tirage est le "+getTirage()+"\n";
	
		for (Joueur joueur : participants) {
			int gain = joueur.gain(getTirage()); 
			if (gain == 0) {
				str += "Le joueur "+joueur.getNom()+" mise "+joueur.getMise()+" et perd\n";
			} else {
				str += "Le joueur "+joueur.getNom()+" mise "+joueur.getMise()+" et gagne "+gain+"\n";
			}
		}
		
		str += "Gain/perte du casino : "+this.getGainMaison();
		return str;
	}
}

class RouletteFrancaise extends Roulette {

	@Override
	public int perteMise(int miseDuJoueur) {
		return miseDuJoueur;
	}
}

class RouletteAnglaise extends Roulette implements ControleJoueurs {
	
	@Override
	public void participe(Joueur joueur) {
		if (check()) {
			super.participe(joueur);
		}
	}
	
	@Override
	public boolean check() {
		return getParticipants() < 10;
	}

	@Override
	public int perteMise(int miseDuJoueur) {
		return miseDuJoueur / 2;
	}
	
}

interface ControleJoueurs {
	public boolean check();
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Casino {
	
	private static void simulerJeu(Roulette jeu) {
		for (int tirage : new int [] { 12, 1, 31 }) {
			jeu.rienNeVaPlus(tirage);
			System.out.println(jeu);
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
 
		Joueur joueur1 = new Joueur("Dupond");
		joueur1.setStrategie(new Pleine(100,1)); // miser 100 jetons sur le 1
	
		Joueur joueur2 = new Joueur("Dupont");
		joueur2.setStrategie(new Rouges(30)); // miser 30 jetons sur les rouges
	
		Roulette jeu1 = new RouletteAnglaise();
		jeu1.participe(joueur1);
		jeu1.participe(joueur2);

		Roulette jeu2 = new RouletteFrancaise();
		jeu2.participe(joueur1);
		jeu2.participe(joueur2);
	
		System.out.println("Roulette anglaise :");
		simulerJeu(jeu1);
		System.out.println("Roulette francaise :");
		simulerJeu(jeu2);
	}
}

