import Global.Configuration;
import Modele.GenerateurCoups;
import Modele.InterfaceTextuelle;
import Modele.Jeu;
import Modele.LecteurNiveau;
import Modele.Situation;
import Vue.InterfaceGraphique;

import java.io.File;
import java.io.InputStream;

class Sokoban {

	public static void main(String[] args) {
		try {
			String fichierNiveaux = Configuration.instance().lis("Niveaux");
			fichierNiveaux = fichierNiveaux.replace('/', File.separatorChar);
			InputStream in = Configuration.charge(fichierNiveaux);
			LecteurNiveau l = new LecteurNiveau(in);
			Jeu j = new Jeu(l);

			// DEBUT TEST DYLAN
			Situation s = j.niveau().toSituation();
			Situation s2 = s.futurSituations(j.niveau())[0];
			System.out.println("s :\n"+s);
			System.out.println("s2 :\n"+s2);

			GenerateurCoups g = new GenerateurCoups(j.niveau());

			System.out.println("ListeCoups :"+g.pathFromTo(s, s2));
			// FIN TEST DYLAN


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
