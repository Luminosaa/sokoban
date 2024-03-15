package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class GenerateurCoups {
    Niveau niveau;
    
    public GenerateurCoups(Niveau n) {
        //niveau = n.clone();
        niveau = n;
    }

    public void solver() {
        Situation s = niveau.toSituation();
        HashMap<Situation, Boolean> situationsMap = new HashMap<>();
        Queue<Situation> f = new LinkedList<>();
        f.add(s);
        while (!f.isEmpty()){
            s = f.remove();
            System.out.println(s);
            if (situationsMap.get(s) == null || situationsMap.get(s) == false){
                situationsMap.put(s, true);
                Situation[] nextS = s.futurSituations(niveau);
                for (int i = 0; i < nextS.length; i++){
                    if (nextS[i] != null){
                        f.add(nextS[i]);
                    }
                }
            }
        }
    }

    public ArrayList<Coup> pathFromTo(Situation depart, Situation arrivee) {
        
        Point dP;
        Point aP = null;
        ArrayList<Coup> listeCoups = new ArrayList<Coup>();

        Marque[][] marques = depart.getCloneMarques();
        HashSet<Point> positionsCaissesDepart = depart.getPositionCaisses();

        HashSet<Point>  positionsCaissesArrivee = arrivee.getPositionCaisses();

        // on cherche à déterminer la position du joueur à l'arrivée en comparant les deux tableaux de positions de caisses 
        for (Point p : positionsCaissesDepart) {
            if (!positionsCaissesArrivee.contains(p)) {
                aP = p;
            }
        }

        for (int i = marques.length - 1; i >= 0; i--) {
            for (int j = marques[i].length - 1; j >= 0; j--) {
                if (marques[i][j] != null) {
                    System.out.println("marque : " + marques[i][j]);
                }
            }
        }

        Marque courant = marques[aP.x][aP.y];
        System.out.println("aP : " + aP.toString());

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
