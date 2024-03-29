package Controleur;
/*
 * Sokoban - Encore une nouvelle version (à but pédagogique) du célèbre jeu
 * Copyright (C) 2018 Guillaume Huard
 *
 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).
 *
 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.
 *
 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.
 *
 * Contact:
 *          Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Vue.AireDeDessin;

public class EcouteurDeSouris implements MouseListener {
	AireDeDessin aire;

	EcouteurDeSouris(AireDeDessin a) {
		aire = a;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Le bouton de la souris a été pressé en (" + e.getX() + ", " + e.getY() + ")");
		aire.deplace(e.getX(), e.getY());
	}

	// Toutes les méthodes qui suivent font partie de l'interface. Si nous ne
	// nous en servons pas, il suffit de les déclarer vides.
	// Une autre manière de faire, plus simple, est d'hériter de MouseAdapter,
	// qui est une classe qui hérite de MouseListener avec une implémentation
	// vide de toutes ses méthodes.
	@Override
	public void mouseClicked(MouseEvent e) { }
	@Override
	public void mouseReleased(MouseEvent e) { }
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) { }
}
