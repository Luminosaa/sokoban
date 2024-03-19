package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GenerateurCoups {
    Niveau niveau;
    Point pousseur;

    public GenerateurCoups(Niveau n) {
        // niveau = n.clone();
        niveau = n.clone();
        pousseur = new Point(niveau.pousseurL(), niveau.pousseurC());
    }

    public boolean solver() {
        Situation s = niveau.toSituation();
        HashMap<Situation, Boolean> situationsMap = new HashMap<>();
        Queue<Situation> f = new LinkedList<>();
        f.add(s);
        int n_sit = 0;
        while (!f.isEmpty()) {
            s = f.remove();
            //System.out.println(s);
            if (situationsMap.get(s) == null || situationsMap.get(s) == false) {
                //System.out.println(situationsMap);
                situationsMap.put(s, true);
                if (s.gagnante(niveau)) {
                    System.out.println("GG: " + s);
                    return true;
                }
                System.out.println("Nouvelle situation : " + n_sit++);
                Situation[] nextS = s.futurSituations(niveau);
                for (int i = 0; i < nextS.length; i++) {
                    if (nextS[i] != null) {
                        f.add(nextS[i]);
                    }
                }
            }
        }
        System.out.println("pas GG");
        return false;
    }

    public ArrayList<Coup> pathFromTo(Situation sitDebut, Situation sitFin) {
        
        Point dP = null;
        Point aP = null;
        ArrayList<Coup> listeCoups = new ArrayList<Coup>();

        niveau.marqueAccessibles(pousseur.x, pousseur.y);
        
        HashMap<Point, Integer> caissesDep = sitDebut.positionCaisses;
        HashMap<Point, Integer> caissesArr = sitFin.positionCaisses;

        // On cherche la caisse qui a bougé pour trouver la position du joueur à la fin
        for (Point p: caissesDep.keySet()) {
            if (caissesArr.get(p) == null) {
                aP = p;
            }
        }

        for (Point p: caissesArr.keySet()) {
            if (caissesDep.get(p) == null) {
                dP = p;
            }
        }
        
        Marque[][] marques = niveau.marques();

        
        // On cherche la position du joueur à la fin
        
        int diffX = aP.x - dP.x;
        int diffY = aP.y - dP.y;
        Marque courant = new Marque(aP, new Point(aP.x - diffX, aP.y - diffY));
        marques[aP.x][aP.y] = courant;
        // affichage de la matrice des marques
        for (int i = 0; i < niveau.lignes(); i++) {
            for (int j = 0; j < niveau.colonnes(); j++) {
                if (marques[i][j] != null) {
                    if (marques[i][j].caseCourante != null) {
                        System.out.print("("+marques[i][j].caseCourante.x + "," + marques[i][j].caseCourante.y + ")");
                    } else {
                        System.out.print("  o  ");
                    }
                } else {
                    System.out.print("  n  ");
                }
            }
            System.out.println();
        }
        
        System.out.println();

        dP = courant.casePrecedente;

        while (courant.casePrecedente != null) {
            listeCoups.add(new Coup(dP, aP));
            dP = courant.casePrecedente;
            aP = courant.caseCourante;
            courant = niveau.marques()[aP.x][aP.y];
        }

        Collections.reverse(listeCoups);

        return listeCoups;
    }
}
