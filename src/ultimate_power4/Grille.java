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



public class Grille {
    Cellule[][] Grille = new Cellule[6][7];
    
    public Grille(){
        for (int i=0;i<5;i++){
            for (int j=0;j<6;j++){
                Grille[i][j]=new Cellule();
            }
        }
    }
    
    public boolean ajouterJetonDansColonne(jeton Jeton,int colonne){
        int i=5;
        while (i>=0){
            if (Grille[i][colonne].jetonCourant==null){
                Grille[i][colonne].jetonCourant=Jeton;
                return true;
            }
            
            i--;   
        }
        
        return false;
    }
    
    public boolean etreRemplie(){
        for (int i=0;i<5;i++){
            for (int j=0;j<6;j++){
                if(Grille[i][j].jetonCourant==null){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void viderGrille(){
        for (int i=0;i<5;i++){
            for (int j=0;j<6;j++){
                Grille[i][j]=new Cellule();
                
            }
        }
    }
    public void afficherGrilleSurConsole(){
        for (int i=0;i<5;i++){
            for (int j=0;j<6;j++){
                System.out.print(Grille[i][j].jetonCourant.couleur);
                if(Grille[i][j].trouNoir==true){
                    System.out.print("_Tn_");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
    
    public boolean celluleOccupee(int i, int j){
        if(Grille[i][j].supprimerJeton()==true){
            return true;
        }
        return false;
    }
    
    public String lireCouleurDuJeton(int i, int j){
        return Grille[i][j].jetonCourant.couleur;
    }
    
    public boolean etreGagnantePourJoueur (Joueur joueur){
        for (int i=0;i<5;i++){           
            for (int j=0;j<3;j++){
                if(Grille[i][j].jetonCourant!=null && Grille[i][j+1].jetonCourant!=null && Grille[i][j+2].jetonCourant!=null && Grille[i][j+3].jetonCourant!=null){
                    if (joueur.Couleur==Grille[i][j].jetonCourant.couleur && joueur.Couleur==Grille[i][j+1].jetonCourant.couleur &&joueur.Couleur==Grille[i][j+2].jetonCourant.couleur&&joueur.Couleur==Grille[i][j+3].jetonCourant.couleur){
                        return true;
                    }
                }        
            }
        }
        for (int i=0;i<2;i++){           
            for (int j=0;j<6;j++){
                if(Grille[i][j].jetonCourant!=null && Grille[i+1][j].jetonCourant!=null && Grille[i+2][j].jetonCourant!=null && Grille[i+3][j].jetonCourant!=null){
                    if (joueur.Couleur==Grille[i][j].jetonCourant.couleur && joueur.Couleur==Grille[i+1][j].jetonCourant.couleur &&joueur.Couleur==Grille[i+2][j].jetonCourant.couleur&&joueur.Couleur==Grille[i+3][j].jetonCourant.couleur){
                        return true;
                    }   
                }
            }
        }
        for (int i=0;i<2;i++){           
            for (int j=0;j<3;j++){
                if(Grille[i][j].jetonCourant!=null && Grille[i+1][j+1].jetonCourant!=null && Grille[i+2][j+2].jetonCourant!=null && Grille[i+3][j+3].jetonCourant!=null){
                    if (joueur.Couleur==Grille[i][j].jetonCourant.couleur && joueur.Couleur==Grille[i+1][j+1].jetonCourant.couleur &&joueur.Couleur==Grille[i+2][j+2].jetonCourant.couleur&&joueur.Couleur==Grille[i+3][j+3].jetonCourant.couleur){
                        return true;
                    } 
                }
            }
        }
        for (int i=0;i<3;i++){           
            for (int j=6;j>3;j--){
                if(Grille[i][j].jetonCourant!=null && Grille[i+1][j-1].jetonCourant!=null && Grille[i+2][j-2].jetonCourant!=null && Grille[i+3][j-3].jetonCourant!=null){
                    if (joueur.Couleur==Grille[i][j].jetonCourant.couleur && joueur.Couleur==Grille[i+1][j-1].jetonCourant.couleur &&joueur.Couleur==Grille[i+2][j-2].jetonCourant.couleur&&joueur.Couleur==Grille[i+3][j-3].jetonCourant.couleur){
                        return true;
                    }     
                }
            }
        }
        return false;
       
    }
    
    public void tasserGrille (int ligne,int colonne){
        for (int i=ligne;i<1;i--){
            Grille[i][colonne]=Grille[i-1][colonne];
        }
        Grille[0][colonne].supprimerJeton();
    }
    
    public boolean colonneRemplie(int colonne){
        return Grille[0][colonne].jetonCourant!=null;
    }
    
    public boolean placerTrouNoir(int a,int b){
        if(Grille[a][b].trouNoir==false){
            Grille[a][b].placerTrouNoir();
            return true;
        }
        return false;
    }
    
    public boolean placerDesintegrateur(int a,int b){
        if(Grille[a][b].desintegrateur==false){
            Grille[a][b].placerDesintegrateur();
            return true;
        }
        return false;
    }
    
    public boolean supprimerJeton(int a,int b){
        if(Grille[a][b].jetonCourant!=null){
            Grille[a][b].supprimerJeton();
            return true;
        }
        return false;
    }
    
    public jeton recupererJeton(int a, int b){
        jeton retour_J=Grille[a][b].jetonCourant;
        supprimerJeton(a,b);
        return retour_J;
    }
}