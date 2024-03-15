import Global.Configuration;
import Modele.InterfaceTextuelle;
import Modele.Jeu;
import Modele.LecteurNiveau;
import Modele.Situation;
import Vue.InterfaceGraphique;
import Modele.Coup;
import Modele.GenerateurCoups;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

class Sokoban {

	public static void main(String[] args) {
		try {
			String fichierNiveaux = Configuration.instance().lis("Niveaux");
			fichierNiveaux = fichierNiveaux.replace('/', File.separatorChar);
			InputStream in = Configuration.charge(fichierNiveaux);
			LecteurNiveau l = new LecteurNiveau(in);
			Jeu j = new Jeu(l);

			GenerateurCoups gc = new GenerateurCoups(j.niveau());

			Situation depart = j.niveau().toSituation();
			Situation arrivee = depart.futurSituations(j.niveau())[0];

			ArrayList<Coup> listeCoups = gc.pathFromTo(depart, arrivee);

			for (Coup c : listeCoups) {
				System.out.println(c);
			}

			if (args.length > 0) {
				int nb = Integer.parseInt(args[0]);
				for (int i = 1; i < nb; i++)
					j.prochainNiveau();
			}
			switch (Configuration.instance().lis("Interface")) {
				case "Textuelle":
					InterfaceTextuelle.demarrer(j);
					break;
				case "Graphique":
					InterfaceGraphique.demarrer(j);
					break;
				default:
					Configuration.instance().logger().severe("Interface invalide");
					System.exit(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
