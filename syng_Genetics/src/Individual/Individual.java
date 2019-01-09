package Individual;

public interface Individual {

	/* Fonction de création d'un individu */
	public abstract Individual create();
	/* Fonction pour recupérer le gene de l'individu */
	public abstract Object getGene();
	public byte getGene(int index);
	public int size();
	

}
