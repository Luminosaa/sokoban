package Modele;

import java.awt.Point;
import java.util.HashSet;

public class Situation {
<<<<<<< HEAD
<<<<<<< HEAD

    Point[] positionCaisses;
=======
    HashSet<Point> positionCaisses;
>>>>>>> e463c1c (improve situations)
=======
    HashSet<Point> positionCaisses;
=======

    Point[] positionCaisses;
>>>>>>> 96cabd5 (first try)
>>>>>>> 0c08e7b (first try)
    int[] positionFuturCaisses;
    Marque[][] clone_marques;

<<<<<<< HEAD
<<<<<<< HEAD
    Situation(Point[] pC, int[] pFC, Marque[][] m) {
        positionCaisses = pC;
=======
=======
>>>>>>> 0c08e7b (first try)
    Situation(HashSet<Point> pC, int[] pFC) {
        positionCaisses = new HashSet<>();
        for (Point p : pC){
            positionCaisses.add(p);
        }
<<<<<<< HEAD
>>>>>>> e463c1c (improve situations)
=======
=======
    Situation(Point[] pC, int[] pFC, Marque[][] m) {
        positionCaisses = pC;
>>>>>>> 96cabd5 (first try)
>>>>>>> 0c08e7b (first try)
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
<<<<<<< HEAD
                    Point arriveCaisse = new Point(positionCaisses[i].x + directions[j][0],
                    positionCaisses[i].y + directions[j][1]);
                    Point departJoueur = new Point(positionCaisses[i].x - directions[j][0],
                    positionCaisses[i].y - directions[j][1]);
                    Point arriveJoueur = positionCaisses[i];
=======
=======
>>>>>>> c38c789 (a)
                    HashSet<Point> tmp = n.caisses;
                    n.caisses = positionCaisses;
                    Point arriveCaisse = new Point(caisse.x + directions[j][0],
                            caisse.y + directions[j][1]);
                    Point departJoueur = new Point(caisse.x - directions[j][0],
                            caisse.y - directions[j][1]);
                    Point arriveJoueur = caisse;
<<<<<<< HEAD
>>>>>>> e463c1c (improve situations)
=======
=======
                    Point arriveCaisse = new Point(positionCaisses[i].x + directions[j][0],
                    positionCaisses[i].y + directions[j][1]);
                    Point departJoueur = new Point(positionCaisses[i].x - directions[j][0],
                    positionCaisses[i].y - directions[j][1]);
                    Point arriveJoueur = positionCaisses[i];
>>>>>>> 8b3aac1 (a)
>>>>>>> c38c789 (a)
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
