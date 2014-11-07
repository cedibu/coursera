class Souris {

    public static final int ESPERANCE_VIE_DEFAUT = 36;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    
    private int poids;
    private int age;
    private String couleur;
    private int esperanceVie;
    private boolean clonee;

    public Souris() {
    	System.out.println("Une nouvelle souris !");
		this.age = 0;
		this.clonee = false;
		this.esperanceVie = ESPERANCE_VIE_DEFAUT;
	}
    
    public Souris(int poids, String couleur) {
    	this(poids, couleur, 0, ESPERANCE_VIE_DEFAUT);
    }
    
    public Souris(int poids, String couleur, int age) {
    	this(poids, couleur, age, ESPERANCE_VIE_DEFAUT);
    }
    
    public Souris(int poids, String couleur, int age, int esperanceVie) {
    	this();
    	this.poids = poids;
    	this.couleur = couleur;
    	this.age = age;
    	this.esperanceVie = esperanceVie;
    }
    
    public Souris(Souris s) {    	
    	this.poids = s.poids;
    	this.couleur = s.couleur;
    	this.age = s.age;
    	this.esperanceVie = (s.esperanceVie / 5) * 4;
    	this.clonee = true;
    	System.out.println("Clonage d'une souris !");
    }
    
    @Override
    public String toString() {
    	if (this.clonee) {
    		return "Une souris "+this.couleur+", clonee, de "+this.age+" mois et pesant "+this.poids+" grammes";
    	} else {
    		return "Une souris "+this.couleur+" de "+this.age+" mois et pesant "+this.poids+" grammes";
    	}
    }
    
    public void vieillir() {
    	this.age += 1;
    }
    
    public void evolue() {
    	this.age = this.esperanceVie;
    	
    	
    	int moitieEsperanceDeVie = this.esperanceVie / 2;
    	
    	if (this.clonee && age > moitieEsperanceDeVie) {
    		this.couleur = "verte";
    	}
    }
}

/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

public class Labo {

    public static void main(String[] args) {
        Souris s1 = new Souris(50, "blanche", 2);
        Souris s2 = new Souris(45, "grise");
        Souris s3 = new Souris(s2);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        s1.evolue();
        s2.evolue();
        s3.evolue();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
