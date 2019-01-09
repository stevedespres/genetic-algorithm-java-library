package Individual;

public abstract class Individual {

	/* Fonction de cr�ation d'un individu */
	public abstract Individual create();
	/* Fonction pour recup�rer le gene de l'individu */
	public abstract Object getGene();
	public abstract byte getGene(int index);
	public abstract int getSkill();
	public abstract void setSkill(int skill);
	public abstract int size();
	

}
