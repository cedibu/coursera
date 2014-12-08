/*******************************************
 * Completez le programme a partir d'ici.

 * Tableau à utiliser pour identifier les cases rouges :
 {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
 *******************************************/

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

