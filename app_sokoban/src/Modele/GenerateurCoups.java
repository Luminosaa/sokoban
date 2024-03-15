package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GenerateurCoups {
    Niveau niveau;
    
    public GenerateurCoups(Niveau n) {
        Situation s = n.toSituation();
        niveau = n.clone();
        System.out.println(s);
    }

    public ArrayList<Coup> generationCoup(int n) {
        ArrayList<Coup> coups = new ArrayList<>();
        Random r = new Random();
        int i = 0;
        while (i < n) {
            int x, y;
            switch (r.nextInt() % 4) {
                case 0:
                    x = -1;
                    y = 0;
                    break;
                case 1:
                    x = 1;
                    y = 0;
                    break;
                case 2:
                    x = 0;
                    y = -1;
                    break;
                default:
                    x = 0;
                    y = 1;
            }
            if (niveau.deplace(x, y)) {
                i++;
                coups.add(niveau.dernierCoup());
            }
        }
        return coups;
    }

    public ArrayList<Coup> pathFromTo(Situation depart, Situation arrivee) {
        
        Point dP;
        Point aP = null;
        ArrayList<Coup> listeCoups = new ArrayList<Coup>();

        Marque[][] marques = depart.getCloneMarques();
        Point[] positionsCaissesDepart = depart.getPositionCaisses();
        System.out.println("positionsCaissesDepart: " + positionsCaissesDepart);
        Point[] positionsCaissesArrivee = arrivee.getPositionCaisses();
        System.out.println("positionsCaissesArrivee: " + positionsCaissesArrivee);

        // on cherche à déterminer la position du joueur à l'arrivée en comparant les deux tableaux de positions de caisses 
        for (int i = 0; i < positionsCaissesDepart.length; i++) {
            if (positionsCaissesDepart[i].x != positionsCaissesArrivee[i].x || positionsCaissesDepart[i].y != positionsCaissesArrivee[i].y)
                aP = positionsCaissesDepart[i];
        }

        Marque courant = marques[aP.x][aP.y];

        listeCoups.add(new Coup(courant.casePrecedente, courant.caseCourante, aP, true));
        dP = courant.casePrecedente;
        aP = courant.caseCourante;

        while (courant.casePrecedente != null) {
            dP = courant.casePrecedente;
            aP = courant.caseCourante; 
            listeCoups.add(new Coup(dP, aP));   
            courant = marques[dP.x][dP.y];
        }

        Collections.reverse(listeCoups);

        return listeCoups;
    }
}
