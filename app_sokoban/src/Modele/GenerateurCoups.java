package Modele;

import java.awt.Point;
import java.util.ArrayList;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Collections;
import java.util.Random;
=======
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
>>>>>>> e463c1c (improve situations)
=======
import java.util.Random;
>>>>>>> 0c08e7b (first try)

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
<<<<<<< HEAD
<<<<<<< HEAD

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
=======
>>>>>>> e463c1c (improve situations)
=======
        //if ()
>>>>>>> 0c08e7b (first try)

        return listeCoups;
    }
}
