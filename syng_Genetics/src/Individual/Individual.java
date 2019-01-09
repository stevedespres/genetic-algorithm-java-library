package Individual;

public interface Individual {

	/* Fonction de création d'un individu */
	public abstract Individual create();
	/* Fonction pour recupérer le gene de l'individu */
	public abstract Object getGene();
	/* Recupérer la competence de l'individu */
	public abstract Integer getSkill();
	

}
