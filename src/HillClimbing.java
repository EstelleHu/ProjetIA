import java.util.ArrayList;

public class HillClimbing {
	
	public int heuristic (ArrayList<Arc> current) {
		int somme = 0;
		for(Arc a : current) {
			somme = somme + a.getValeur();
		}
		return somme;
	}
	public void search(Graphe g) {
		int compteur = 0;
		
		do {
			
		}while(compteur<g.getTaille()-1);
	}
	
	public int h(int[]ligne, int[]resultat) {
		int min = ligne[0];
		for(int i=1;i<ligne.length-1;i++) {
			if(ligne[i]<min) {
				min=ligne[i];
			}
		}
		return min;
	}
}
