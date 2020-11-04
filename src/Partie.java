/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quentin
 */
public class Partie {

    Joueur[] ListeJoueurs = new Joueur[2];
    Joueur joueurCourant;

    public Partie(Joueur joueur1, Joueur joueur2) {
        ListeJoueurs[1] = joueur1;
        ListeJoueurs[2] = joueur2;
    }

    public void attribuerCouleursAuxJoueurs() {
        ListeJoueurs[1].Couleur = "jaune";
        ListeJoueurs[2].Couleur = "rouge";
    }

    public void initialiserPartie() {
        /*boolean exist=true;
        try{
            Grille grille=new Grille();
        }
        catch (ArithmeticException ex) {            TEST 
            exist=false;
        }
        if(exist==true){
            Grille grille=new Grille();
        }
        else{
            grille
        }*/
        Grille grille = new Grille();
        grille.viderGrille();
        int nb_trouN = 5;  //nombre de trous noirs à placer
        int nb_Des = 5;  //nb de désintégrateur à placer
        boolean test_T, test_D; //tester si l'ajoue de trou noir/désintégrateur est possible
        while (nb_trouN != 0 && nb_Des != 0) {  //
            int i = (int) (Math.random() * 5);
            int j = (int) (Math.random() * 6);  //on génère des coordonnées aléatoires
            if (nb_trouN != 0) {
                test_T = grille.placerTrouNoir(i, j);  //on fait le test
                if (test_T == true && nb_Des > 3) {  //cas où on peut placer trou noir et désintégrateur sur la meme case 
                    grille.placerTrouNoir(i, j);
                    nb_trouN--; //on diminue le nb de trou noir et dés. à placer
                    nb_Des--;
                    test_T = false;
                    grille.placerDesintegrateur(i, j);
                }
                if (test_T == true) { //cas où on a placé 2 désintégrateur et on place les autres trous noirs
                    grille.placerTrouNoir(i, j);
                    nb_trouN--;
                }
            } else { //cas où on a placé tous les trous noirs et où l'on place les désintégrateurs restants sur d'autres cases
                test_T = grille.placerTrouNoir(i, j);
                test_D = grille.placerDesintegrateur(i, j);
                if (test_D == true && test_T == false) { //cas favorable à celà
                    grille.placerDesintegrateur(i, j);
                    nb_Des--;
                }
            }
        }
        jeton Jeton1 = new jeton("jaune");
        jeton Jeton2 = new jeton("rouge");
        for(int i=0;i<21;i++){
            ListeJoueurs[1].ajouterJeton(Jeton1);
            ListeJoueurs[2].ajouterJeton(Jeton2);
        }
    }
    
    public void debuterPartie(){
        
    }
}
