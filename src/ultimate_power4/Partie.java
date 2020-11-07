package ultimate_power4;

import java.util.Scanner;

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
    Grille grille = new Grille();

    public Partie(Joueur joueur1, Joueur joueur2) {
        ListeJoueurs[0] = joueur1;
        ListeJoueurs[1] = joueur2;
        grille = new Grille();
    }

    public void attribuerCouleursAuxJoueurs() {
        ListeJoueurs[0].Couleur = "jaune";
        ListeJoueurs[1].Couleur = "rouge";
    }

    public void initialiserPartie() {

        grille.viderGrille();
        int nb_trouN = 5;  //nombre de trous noirs à placer
        int nb_Des = 5;  //nb de désintégrateur à placer
        boolean test_T, test_D; //tester si l'ajoue de trou noir/désintégrateur est possible
        while (nb_trouN != 0 || nb_Des != 0) {  //
            int i = (int) (Math.random() * 6 - 0.00001); //on génère des coordonnées aléatoires, comme random génère un nombre entre 0 et 1, on le multiplie par 6 ou 7
            int j = (int) (Math.random() * 7 - 0.00001);  //et on soustrait 0.00001 pour etre sur de ne pas tomber sur 6 ou 7 (random génère un string et le tronque en le convertissant en int)
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
                test_T = grille.Grille[i][j].trouNoir;
                test_D = grille.placerDesintegrateur(i, j);
                if (test_D == true && test_T == false) { //cas favorable à celà
                    grille.placerDesintegrateur(i, j);
                    nb_Des--;
                }
            }
        }
        jeton Jeton1 = new jeton("jaune");
        jeton Jeton2 = new jeton("rouge");
        for (int i = 0; i < 21; i++) {
            ListeJoueurs[0].ajouterJeton(Jeton1);
            ListeJoueurs[1].ajouterJeton(Jeton2);
        }
        if (Math.random() > 0.5) {                        //on genere un nombre aleatoire entre 0  et 1  , si ce nombre est supperieur à 0.5  le joueur 1  commence sinon l'autre joueur commence
            joueurCourant = ListeJoueurs[1];
        } else {
            joueurCourant = ListeJoueurs[0];
        }
    }

    public void debuterPartie() {
        //int o=0;
        //while(o<10){
        while (grille.etreGagnantePourJoueur(joueurCourant) == false && grille.etreRemplie() == false) {
            grille.afficherGrilleSurConsole();

            if (joueurCourant == ListeJoueurs[0]) {
                joueurCourant = ListeJoueurs[1];
            } else {
                joueurCourant = ListeJoueurs[0];
            }

            int Rep_Joueur;   //variable réponse du joueur
            if (joueurCourant.nombreJetonsRestants != 0) {
                Scanner sc = new Scanner(System.in);
                
                boolean Test_Jeton = grille.jetonPrésentDansGrille(joueurCourant.ListeJetons[0]);//pour tester si le joueur possède au moins 1 jeton sur la grille
                
                if (Test_Jeton==true) { 
                    System.out.println(joueurCourant.Nom +"("+joueurCourant.Couleur+")"+" Souhaitez vous jouer un jeton ou en récupérer un?\n 1/placer un jeton      2/Récupérere un jeton");
                    if (joueurCourant.nombreDesintegrateurs > 0) {
                        System.out.println("Ou bien encore en ANHIHILER en EXPLOSER en ATOMISER un???\n 3/Désintégrer un jeton");
                    }
                    Rep_Joueur = sc.nextInt();
                } else {
                    Rep_Joueur = 1; //on passe directement à l'ajout du jeton
                }
                if (Rep_Joueur == 2) {  //boucle récupérer jeton
                    System.out.println("Sélectionnez la ligne puis la colonne du jeton à récupérer");
                    System.out.println("ligne: ");
                    int lig = sc.nextInt() - 1;
                    System.out.println("colonne: ");
                    int col = sc.nextInt() - 1;
                    while (grille.Grille[lig][col].jetonCourant == null || grille.Grille[lig][col].jetonCourant.couleur != joueurCourant.Couleur || grille.Grille[lig][col] == null) {
                        System.out.println("Jeton inexistant ou à l'adversaire, sélectionnez la ligne puis la colonne du jeton à récupérer");
                        System.out.println("ligne: ");    //On demande à l'utilisateur de rentrer des coordonnées tant que  
                        lig = sc.nextInt() - 1;                    //la case sélectionnée ne contient pas un jeton du joueur courant
                        System.out.println("colonne: ");
                        col = sc.nextInt() - 1;
                    }
                    grille.recupererJeton(lig, col); //on récupère le jeton
                    joueurCourant.nombreJetonsRestants++;
                }
                if (Rep_Joueur == 1) {  //boucle jouer jeton
                    System.out.println(joueurCourant.Nom +"("+joueurCourant.Couleur+")"+ " choisissez une colonne où jouer votre jeton");
                    int colonne = sc.nextInt() - 1; // moins 1 pour atteindre la case 0 de la grille

                    while (colonne < 0 || colonne > 6) { //on vérifie que le nombre saisi est compris entre 0 et 6
                        System.out.println("colonne invalide");
                        System.out.println("choisissez une autre colonne où jouer");
                        colonne = sc.nextInt();
                    }

                    while (grille.colonneRemplie(colonne) == true) {
                        System.out.println("colonne pleine");
                        System.out.println("choisissez une autre colonne où jouer");
                        colonne = sc.nextInt();
                    }
                    int i = 5;
                    while (i > 0 && grille.Grille[i][colonne].jetonCourant != null) {
                        i--;
                    }
                    if (grille.Grille[i][colonne].desintegrateur == true) {
                        grille.Grille[i][colonne].recupererDesintegrateur();
                        joueurCourant.nombreDesintegrateurs++;
                    }

                    grille.ajouterJetonDansColonne(joueurCourant.ListeJetons[0], colonne);
                    joueurCourant.nombreJetonsRestants--;
                }

                if (Rep_Joueur == 3) {
                    if (joueurCourant.nombreDesintegrateurs > 0) {  //boucle du désintégrateur
                        System.out.println("Saisissez les coodonnées du jeton à DESINTEGRER");
                        System.out.println("ligne: ");
                        int lig = sc.nextInt() - 1;
                        System.out.println("colonne: ");
                        int col = sc.nextInt() - 1;
                        while (grille.Grille[lig][col].jetonCourant == null || grille.Grille[lig][col] == null) {
                            System.out.println("Jeton inexistant, sélectionnez la ligne puis la colonne du jeton à DESINTEGRER");
                            System.out.println("ligne: ");
                            lig = sc.nextInt() - 1;
                            System.out.println("colonne: ");
                            col = sc.nextInt() - 1;
                        }
                        grille.Grille[lig][col].supprimerJeton();
                        joueurCourant.utiliserDesintegrateur();
                        grille.tasserGrille(col);
                    } else { //si un joueur qui n'a pas de desintégrateur rentre 3 alors on passe au joueur 
                        //suivant dans le but qu'au prochain tour en réinversant les joueur ce joueur puisse rejouer
                        if (joueurCourant == ListeJoueurs[0]) {
                            joueurCourant = ListeJoueurs[1];
                        } else {
                            joueurCourant = ListeJoueurs[0];
                        }
                    }
                }
                if (Rep_Joueur != 1 && Rep_Joueur != 2 && Rep_Joueur != 3) {
                    if (joueurCourant == ListeJoueurs[0]) {
                        joueurCourant = ListeJoueurs[1];
                    } else {
                        joueurCourant = ListeJoueurs[0];
                    }
                }
            }
        }
        grille.afficherGrilleSurConsole();
        if (grille.etreGagnantePourJoueur(joueurCourant) == true) {
            System.out.println(joueurCourant.Nom + " a gagné");
        } else {
            System.out.println("EGALITE");
        }
    }
}
