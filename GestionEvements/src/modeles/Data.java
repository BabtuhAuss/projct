package modeles;

public class Data {
	
	public static final String VERSION_STRING = "1.0.0";

	public static final String TITRE_FENETRE = "Gestionnaire des Ev�nements";
	public static final String PATH_ICONIMAGE = "picture_cuterly.png";
	
	// Accueil Panel all Strings
	
	public static final String LABEL_LISTE = "Liste des �v�nements:";
	
	public static final String BUTTON_ADDEVENT = "Ajouter un �v�nement";
	public static final String BUTTON_CHANGEEVENT = "Modifier un �v�nement";
	public static final String BUTTON_DELETEEVENT = "Supprimer un �v�nement";
	public static final String BUTTON_REFRESH = "Rafraichir";
	public static final String BUTTON_ABOUT = "A propos";
	
	public static final String COLUMN_NAME = "Nom";
	public static final String COLUMN_PERSONNUMBER = "Nombre de personnes";
	public static final String COLUMN_STARTDATE = "Horaire de d�but";
	public static final String COLUMN_ROOM = "Salon";
	
	// AjoutModifPanel all Strings
	
	public static final String TAB_GENERAL = "G�n�ral";
	public static final String TAB_MENU = "Menu";
	public static final String TAB_ARGUCOM = "Argumentation Commerciale";
	
	public static final String BUTTON_SAVEEVENT = "Enregistrer";
	public static final String BUTTON_SAVEANDQUITEVENT = "Enregistrer et quitter";
	public static final String BUTTON_CANCEL = "Annuler";

	public static final String GENERAL_COUVERT = "Dressage couvert ou Salle :";
	public static final String GENERAL_VAISSELLE = "Vaisselle :";
	
	public static final String MENU_ENTREE = "Entr�e :";
	public static final String MENU_DESSERT = "Dessert :";
	public static final String MENU_CANAPE = "Canap� :";
	public static final String MENU_PLAT = "Plat :";
	
	public static final String ARGUCOM_ENTREE = "Entr�e :";
	public static final String ARGUCOM_PLAT = "Plat :";
	public static final String ARGUCOM_DESSERT = "Dessert :";
	public static final String ARGUCOM_VIN_B = "Vin Blanc :";
	public static final String ARGUCOM_VIN_R = "Vin Rouge :";
	public static final String ARGUCOM_CHPG = "Champagne :";
	
	// All Alerts messages
	
	public static final String ALERT_CHOOSEEVENT = "Vous devez selectionner un �v�nement � modifier dans la liste !";
	
	public static final String ALERT_ASK_DELETE = "Voulez-vous vraiment supprimer cet �v�nement ?";
	
	public static final String ALERT_DELETE_OK = "L'�v�nement � bien �t� supprim�";
	public static final String ALERT_DELETE_KO = "Une erreur est survenue pendant la suppression";
	
	public static final String ALERT_REFRESH_KO = "Une erreur est survenue pendant la r�cup�ration des �v�nements !";
	public static final String ALERT_GETEVENT_KO = "Une erreur est survenue pendant la r�cup�ration de l'�v�nement !";
	public static final String ALERT_ADDEVENT_KO = "Une erreur est survenue pendant la cr�ation de l'�v�nement, veuillez r�essayez !";
	public static final String ALERT_MODIFEVENT_KO = "Une erreur est survenue pendant la modification de l'�v�nement, veuillez r�essayer !";
	
	public static final String ALERT_ABOUT = "\t\t\t" + TITRE_FENETRE + "\n\n"
			+ "\t\t\t\t\tVersion: " + VERSION_STRING + "\n\n"
			+ "\t\t\t\t\tD�veloppeurs:\n\n"
			+ "\t\t\t\t\tYohann MARTIN\n"
			+ "\t\t\t\t\tBaptiste Aussenac\n\n"
			+ "\t\t\t\t\tCr�dits:\n\n"
			+ "\t\t\t\tImages: https://icones8.fr/";
}
