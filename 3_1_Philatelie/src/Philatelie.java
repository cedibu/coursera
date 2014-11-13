import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2014;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    protected String code;
    protected int annee;
    protected String pays;
    protected double valeurFaciale;
    
    public Timbre() {
    	this(CODE_DEFAUT, ANNEE_COURANTE,PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);	
	}
    
    public Timbre(String code) {
    	this(code, ANNEE_COURANTE,PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);	
    }
    
    public Timbre(String code, int annee) {
    	this(code, annee,PAYS_DEFAUT, VALEUR_TIMBRE_DEFAUT);
	}
    
    public Timbre(String code, int annee, String pays) {
    	this(code, annee,pays, VALEUR_TIMBRE_DEFAUT);
	}    
    public Timbre(String code, int annee, String pays, double valeurFaciale) {
    	this.valeurFaciale = valeurFaciale;
    	this.pays = pays;
    	this.annee = annee;
    	this.code = code;
	}
    
	public String getCode() {
		return code;
	}

	public int getAnnee() {
		return annee;
	}

	public double getValeurFaciale() {
		return valeurFaciale;
	}
	
	public String getPays() {
		return pays;
	}

	protected double vente() {
		double age = Double.valueOf(age());
		if (age<5) {
			return getValeurFaciale();
		} else {
			return getValeurFaciale() * age * 2.5;
		}
	}
	
	@Override
	public String toString() {
		return "Timbre de code "+getCode()+" datant de "+getAnnee()+" (provenance "+getPays()+") ayant pour valeur faciale "+getValeurFaciale()+" francs";
	}
	
	protected int age() {
		return ANNEE_COURANTE-this.annee;
	}
}

class Rare extends Timbre {
	private int exemplaires;
	
	public Rare(String code, int annee,String pays, double valeurFaciale, int exemplaires) {
		super(code, annee, pays, valeurFaciale);
		this.exemplaires = exemplaires;
	}
	
	public Rare(String code, int annee,String pays, int exemplaires) {
		super(code, annee, pays);
		this.exemplaires = exemplaires;
	}
	
	public Rare(String code, int annee, int exemplaires) {
		super(code, annee);
		this.exemplaires = exemplaires;
	}
	
	public Rare(String code, int exemplaires) {
		super(code);
		this.exemplaires = exemplaires;
	}
	
	public Rare(int exemplaires) {
		super();
		this.exemplaires = exemplaires;
	}
	
	public int getExemplaires() {
		return exemplaires;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nNombre d'exemplaires -> "+getExemplaires();
	}
	
	@Override
	protected double vente() {
		double prixBase = PRIX_BASE_3;
		double age = Double.valueOf(age());
		
		if (exemplaires < BASE_1_EXEMPLAIRES) {
			prixBase = PRIX_BASE_1;		
		} else if (exemplaires >= BASE_1_EXEMPLAIRES && exemplaires <=BASE_2_EXEMPLAIRES) {
			prixBase = PRIX_BASE_2;
		} 
		return prixBase * (age / 10);
	}
}

class Commemoratif extends Timbre {
	
	public Commemoratif(String code, int annee, String pays, double valeurFaciale) {
		super(code, annee, pays, valeurFaciale);
	}
	
	public Commemoratif(String code, int annee, String pays) {
		super(code, annee, pays);
	}
	
	public Commemoratif(String code, int annee) {
		super(code, annee);
	}
	
	public Commemoratif(String code) {
		super(code);
	}

	@Override
	public String toString() {
		return super.toString() + "\nTimbre celebrant un evenement";
	}
	
	@Override
	protected double vente() {
		return super.vente() * 2;
	}
	
}
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}

