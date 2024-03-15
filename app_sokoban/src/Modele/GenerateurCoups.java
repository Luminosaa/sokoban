package Modele;

import java.awt.Point;
import java.util.ArrayList;
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

    public ArrayList<Coup> pathFromTo(Point dP) {
        ArrayList<Coup> listeCoups = new ArrayList<Coup>();
        //if ()

        return listeCoups;
    }
}
