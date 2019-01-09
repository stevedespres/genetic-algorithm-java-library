package Individual;

public abstract class Individual {

	/* Fonction de création d'un individu */
	public abstract Individual create();
	/* Fonction pour recupérer le gene de l'individu */
	public abstract Object getGene();
	public abstract byte getGene(int index);
	public abstract int getSkill();
	public abstract void setSkill(int skill);
	public abstract int size();
	

}
