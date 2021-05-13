import java.util.ArrayList;

public class HillClimbing {
	
	public int heuristic (ArrayList<Arc> current) {
		int somme = 0;
		for(Arc a : current) {
			somme = somme + a.getValeur();
		}
		return somme;
	}
	public ArrayList<Arc> search(Graphe g) {
		ArrayList<Arc> current = g.getCurrent();
		System.out.println( "CURRENT :"+current);
		int i=0;
		while (true){
			ArrayList<Arc> neighbor =  g.twoOpt();
			System.out.println( "Etape "+i+"  neighbor : "+neighbor);
			System.out.println("current heuristic "+heuristic(current) +" VS neighbor heuristic "+heuristic(neighbor));
			if(heuristic(current)<= heuristic(neighbor) ){
				System.out.println("Chemin le plus court, resultant du HILL CLIMBING SEARCH :\n"+current);
				System.out.println("Longueur du chemin = "+ heuristic(current));
				return current;
			}
			i++;
			g.setCurrent( neighbor);
		}
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
