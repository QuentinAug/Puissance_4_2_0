package ultimate_power4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cinga
 */
public class Joueur {

    String Nom, Couleur;
    int nombreDesintegrateurs, nombreJetonsRestants;
    jeton[] ListeJetons = new jeton[21];

    public Joueur(String NomJ) {
        Nom = NomJ;
        nombreJetonsRestants=21;
    }

    public void affecterCouleur(String NomCouleur) {
        Couleur = NomCouleur;
    }

    public void ajouterJeton(jeton JetonJ) {
        for (int i = 0; i < 21; i++) {
            if (ListeJetons[i] == null) {
                ListeJetons[i] = JetonJ;
            }
            break;
        }
    }
    
    public void obtenirDesintegrateur(){
        nombreDesintegrateurs++;
    }
    
    public boolean utiliserDesintegrateur(){
        if (nombreDesintegrateurs>0){
            nombreDesintegrateurs--;
            return true;
        }
        else{
            return false;
        }
        
        
    }

}
