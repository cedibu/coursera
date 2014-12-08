import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Position {
	private double x;
	private double y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Position() {
		this.x = 0;
		this.y = 0;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "("+getX()+","+getY()+")";
	}
}

class Neurone {
	private Position position;
	protected double signal;
	private double attenuation;
	private ArrayList<Neurone> connexions = new ArrayList<Neurone>();
	
	public Neurone(Position position, double attenuation) {
		this.position = position;
		this.attenuation = attenuation;
		this.signal = 0;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public int getNbConnexions() {
		return connexions.size();
	}
	
	public Neurone getConnexion(int index) {
		return connexions.get(index);
	}
	
	public double getAttenuation() {
		return attenuation;
	}
	
	public double getSignal() {
		return signal;
	}
	
	public void connexion(Neurone n) {
		connexions.add(n);
	}
	
	public void recoitStimulus(double stimulus) {
		this.signal  = calculSignal(stimulus);
		propagation();
	}
	
	protected double calculSignal(double stimulus) {
		return stimulus * this.attenuation;
	}
	
	protected void propagation() {
		for (Neurone neurone : connexions) {
			neurone.recoitStimulus(this.signal);
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		if (connexions.size() == 0) {
			str = "Le neurone en position " + getPosition() + " avec attenuation " + getAttenuation()
					+ " sans connexion\n";
		} else {
			str = "Le neurone en position " + getPosition() + " avec attenuation " + getAttenuation()
					+ " en connexion avec\n";
			for (Neurone neurone : connexions) {
				str += "  - un neurone en position " + neurone.getPosition()+"\n";
			}
		}
		return str;
	}
}

class NeuroneCumulatif extends Neurone {

	public NeuroneCumulatif(Position position, double attenuation) {
		super(position, attenuation);
	}
	
	@Override
	public void recoitStimulus(double stimulus) {
		this.signal = calculSignal(stimulus);
		propagation();
	}
	
	@Override
	protected double calculSignal(double stimulus) {
		return this.getSignal() + super.calculSignal(stimulus);
	}
}

class Cerveau {
	private ArrayList<Neurone> neurones = new ArrayList<Neurone>();
	
	public int getNbNeurones() {
		return neurones.size();
	}
	
	public Neurone getNeurone(int index) {
		try {
			return neurones.get(index);
		} catch (IndexOutOfBoundsException iobe) {
			return null;
		}
	}
	
	public void ajouterNeurone(Position position, double attenuation) {
		Neurone neurone = new Neurone(position, attenuation);
		neurones.add(neurone);
	}
	
	public void ajouterNeuroneCumulatif(Position position, double attenuation) {
		Neurone neurone = new NeuroneCumulatif(position, attenuation);
		neurones.add(neurone);
	}
	
	public void stimuler(int index, double stimulus) {
		Neurone neurone = getNeurone(index);
		neurone.recoitStimulus(stimulus);
	}
	
	public double sonder(int index) {
		return getNeurone(index).getSignal();
	}
	
	public void creerConnexions() {
		Neurone neuroneZero = getNeurone(0);
		
		if (getNeurone(1) != null) {
			neuroneZero.connexion(getNeurone(1));
		}
		
		if (getNeurone(2) != null) {
			neuroneZero.connexion(getNeurone(2));
		}
		
		for (int i = 0; i < getNbNeurones(); i++) {
			Neurone neurone = getNeurone(i);
			
			if (i % 2 != 0 && i < getNbNeurones()-2) {
				neurone.connexion(getNeurone(i+1));
				getNeurone(i+1).connexion(getNeurone(i+2));
			}
		}
		
	}
	
	@Override
	public String toString() {
		String str = "\n\n*----------*\n\n";
		str += "\nLe cerveau contient "+getNbNeurones()+" neurone(s)\n";
		
		for (Neurone neurone : neurones) {
			str += neurone+"\n";
		}
		str += "\n*----------*\n\n";
		return str;
	}
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));


        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());

        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0,0), 0.5);
        cerveau.ajouterNeurone(new Position(0,1), 0.2);
        cerveau.ajouterNeurone(new Position(1,0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1,1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3
    }
}
