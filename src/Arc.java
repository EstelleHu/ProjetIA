
public class Arc {
	public int sommet1;
	public int sommet2;
	public int valeur;
	
	
	public Arc(int sommet1, int sommet2) {
		this.sommet1=sommet1;
		this.sommet2 = sommet2;
	}


	public int getValeur() {
		return valeur;
	}


	public int getSommet1() {
		return sommet1;
	}

    public boolean equals(Arc c) {

        // Compare the data members and return accordingly 
        return (this.sommet1==c.sommet1 && this.sommet2==c.sommet2 && this.valeur==c.valeur)
        		|| (this.sommet1==c.sommet2 && this.sommet2==c.sommet1 && this.valeur==c.valeur);
    }
    public boolean distinctSommets(Arc c) {
    	if((this.sommet1!=c.sommet1 && this.sommet2!=c.sommet2) && (this.sommet1!=c.sommet2 && this.sommet2!=c.sommet1)){
    		return true;
    	}
    	return false;
        
    }
	
	@Override
	public String toString() {
		return "Arc [sommet1=" + sommet1 + ", sommet2=" + sommet2 + ", valeur=" + valeur + "]";
	}


	public void setSommet1(int sommet1) {
		this.sommet1 = sommet1;
	}


	public int getSommet2() {
		return sommet2;
	}


	public void setSommet2(int sommet2) {
		this.sommet2 = sommet2;
	}


	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
}
