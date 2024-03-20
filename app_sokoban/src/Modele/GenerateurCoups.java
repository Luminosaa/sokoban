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

        // on marque les cases acessibles à partir de la position du pousseur
        niveau.marqueAccessibles(pousseur.x, pousseur.y);
        
        // on récupère les positions des caisses à la fin et au début
        HashMap<Point, Integer> caissesDep = sitDebut.positionCaisses;
        HashMap<Point, Integer> caissesArr = sitFin.positionCaisses;

        // On cherche la caisse qui a bougé pour trouver la position du joueur à la fin
        for (Point p: caissesDep.keySet()) {
            if (caissesArr.get(p) == null)
                aP = p;
        }

        for (Point p: caissesArr.keySet()) {
            if (caissesDep.get(p) == null)
                dP = p;
        }
        
        // On cherche la position du joueur à la fin
        int diffX = aP.x - dP.x;
        int diffY = aP.y - dP.y;
        Marque courant = new Marque(aP, new Point(aP.x + diffX, aP.y + diffY));
        niveau.marques()[aP.x][aP.y] = courant;

        // on effectue un itération de la boucle while avant celle-ci
        // pour ajouter le dernier coup (qui effectue un déplacement de caisse)
        dP = courant.casePrecedente;
        listeCoups.add(new Coup(dP, aP, new Point(aP.x - diffX, aP.y - diffY)));

        courant = niveau.marques()[dP.x][dP.y];
        dP = courant.casePrecedente;
        aP = courant.caseCourante;
        
        // on itère sur les cases précédentes pour ajouter les coups jusqu'à l'emplacement du pousseur
        while (courant.casePrecedente != null) {
            listeCoups.add(new Coup(dP, aP));
            courant = niveau.marques()[dP.x][dP.y];
            dP = courant.casePrecedente;
            aP = courant.caseCourante;
        }

        Collections.reverse(listeCoups);

        return listeCoups;
    }
}
