package Individual;

public interface Individual {

	/* Fonction de cr�ation d'un individu */
	public abstract Individual create();
	/* Fonction pour recup�rer le gene de l'individu */
	public abstract Object getGene();
	/* Recup�rer la competence de l'individu */
	public abstract Integer getSkill();
	

}
