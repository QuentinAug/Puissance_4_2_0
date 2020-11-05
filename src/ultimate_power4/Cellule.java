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
public class Cellule {
    jeton jetonCourant;
    boolean trouNoir,desintegrateur;
    
    public Cellule(){
        jetonCourant=null;
        trouNoir=false;
        desintegrateur=false;
    }
    
    public boolean affecterJeton(jeton Jeton){
        if (jetonCourant==null){
            jetonCourant=Jeton;
            return true;
        }
        else{
            return false;
        }
    }
    
    public jeton recupererJeton(){
        return(jetonCourant);
    }
    
    public boolean supprimerJeton(){
        if (jetonCourant!=null){
            jetonCourant=null;
            return true;
        }
        else{
            return false;
        }    
    }
    
    public boolean placerTrouNoir(){
        if (trouNoir==false){
            trouNoir=true;
            return true;
        }
        else{
            return false;
        }  
    }
    public boolean placerDesintegrateur(){
        if (desintegrateur==false){
            desintegrateur=true;
            return true;
        }
        else{
            return false;
        }  
    }
    public boolean presenceTrouNoir(){
        return trouNoir;
    }
    public boolean presenceDesintegrateur(){
        return desintegrateur;
    }
    public String lireCouleurDuJeton(){
        return(jetonCourant.lireCouleur());
    }
    public boolean recupererDesintegrateur(){
        if (desintegrateur==true){
            desintegrateur=false;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean activerTrouNoir(){
        if (trouNoir==true){
            jetonCourant=null;
            trouNoir=false;
            return true;
        }
        else{
            return false;
        }
        
        
    }
}
