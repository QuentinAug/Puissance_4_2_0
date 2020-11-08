/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate_power4;

import java.util.Scanner;

/**
 *
 * @author cinga
 */
public class Ultimate_power4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Nom joueur 1"); //rentrer noms des joueurs
        String Nom_J1 = sc.nextLine();
        System.out.println("Nom joueur 2");
        String Nom_J2 = sc.nextLine();

        Joueur joueur1 = new Joueur(Nom_J1);
        Joueur joueur2 = new Joueur(Nom_J2); //on créé 2 joueurs a partir de leurs noms

        Partie partie1 = new Partie(joueur1, joueur2); //on créé une nouvelle partie
        partie1.attribuerCouleursAuxJoueurs();
        partie1.initialiserPartie();
        partie1.debuterPartie();
    }

}
