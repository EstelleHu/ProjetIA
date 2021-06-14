

/**
 * The Class Arc.
 */
public class Arc {
		
		/** The sommet 1. */
		public int sommet1;
		
		/** The sommet 2. */
		public int sommet2;
		
		/** The valeur. */
		public int valeur;
		
		
		/**
		 * Instantiates a new arc.
		 *
		 * @param sommet1 the sommet 1
		 * @param sommet2 the sommet 2
		 */
		public Arc(int sommet1, int sommet2) {
			this.sommet1=sommet1;
			this.sommet2 = sommet2;
		}


		/**
		 * Gets the valeur.
		 *
		 * @return the valeur
		 */
		public int getValeur() {
			return valeur;
		}


		/**
		 * Gets the sommet 1.
		 *
		 * @return the sommet 1
		 */
		public int getSommet1() {
			return sommet1;
		}
		
		/**
		 * Gets the sommet 2.
		 *
		 * @return the sommet 2
		 */
		public int getSommet2() {
			return sommet2;
		}

	    
	    /**
    	 * Distinct sommets.
    	 *
    	 * @param c the c
    	 * @return true, if successful
    	 */
    	public boolean distinctSommets(Arc c) {
	    	if((this.sommet1!=c.sommet1 && this.sommet2!=c.sommet2) && (this.sommet1!=c.sommet2 && this.sommet2!=c.sommet1)){
	    		return true;
	    	}
	    	return false;
	        
	    }
		
		/**
		 * To string.
		 *
		 * @return the string
		 */
		@Override
		public String toString() {
			return "Arc [sommet1=" + sommet1 + ", sommet2=" + sommet2 + ", valeur=" + valeur + "]\n";
		}

		/**
		 * Sets the valeur.
		 *
		 * @param valeur the new valeur
		 */
		public void setValeur(int valeur) {
			this.valeur = valeur;
		}
	}

