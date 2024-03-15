package Modele;

import java.awt.Point;
import java.util.HashSet;

public class Situation {
<<<<<<< HEAD

    Point[] positionCaisses;
=======
    HashSet<Point> positionCaisses;
>>>>>>> e463c1c (improve situations)
    int[] positionFuturCaisses;
    Marque[][] clone_marques;

<<<<<<< HEAD
    Situation(Point[] pC, int[] pFC, Marque[][] m) {
        positionCaisses = pC;
=======
    Situation(HashSet<Point> pC, int[] pFC) {
        positionCaisses = new HashSet<>();
        for (Point p : pC){
            positionCaisses.add(p);
        }
>>>>>>> e463c1c (improve situations)
        positionFuturCaisses = pFC;
        clone_marques = m;
    }

    public Marque[][] getCloneMarques() {
        return clone_marques;
    }

    public Point[] getPositionCaisses() {
        return positionCaisses;
    }

    public Situation[] futurSituations(Niveau n) {
        int[][] directions = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
        Situation[] fS = new Situation[positionFuturCaisses.length * 4];
        int idx = 0;
        int i = 0;
        for (Point caisse : positionCaisses) {
            for (int j = 0; j < 4; j++) {
                System.out.println(positionFuturCaisses[i]);
                System.out.println((positionFuturCaisses[i] & (int) Math.pow(2, j)));

                if ((positionFuturCaisses[i] & (int) Math.pow(2, j)) > 0) {
<<<<<<< HEAD
                    Point arriveCaisse = new Point(positionCaisses[i].x + directions[j][0],
                    positionCaisses[i].y + directions[j][1]);
                    Point departJoueur = new Point(positionCaisses[i].x - directions[j][0],
                    positionCaisses[i].y - directions[j][1]);
                    Point arriveJoueur = positionCaisses[i];
=======
                    HashSet<Point> tmp = n.caisses;
                    n.caisses = positionCaisses;
                    Point arriveCaisse = new Point(caisse.x + directions[j][0],
                            caisse.y + directions[j][1]);
                    Point departJoueur = new Point(caisse.x - directions[j][0],
                            caisse.y - directions[j][1]);
                    Point arriveJoueur = caisse;
>>>>>>> e463c1c (improve situations)
                    Coup c = new Coup(departJoueur, arriveJoueur, arriveCaisse);
                    n.jouerCoup(c, false);
                    fS[idx] = n.toSituation();
                    System.out.println("position_caisse dans la future situation n°" + idx + fS[idx].positionCaisses);
                    idx++;
                    n.annulerDernierCoup();
                    n.caisses = tmp;
                }
            }
            i++;
        }
        return fS;
    }

    public String toString() {
        String s = "";
        int i = 0;
        for (Point caisse : positionCaisses) {
            s += caisse + " => " + positionFuturCaisses[i] + "\n";
            i++;
        }
        return s;
    }
}
