import java.util.ArrayList;

public class HillClimbing {
	
	public int heuristic (ArrayList<Arc> current) {
		int somme = 0;
		for(Arc a : current) {
			somme = somme + a.getValeur();
		}
		return somme;
	}
	public ArrayList<Arc> search(Graphe g) { //First choice hill climbing
		int i=0;
		while (true){
			ArrayList<Arc> neighbor =  g.twoOpt();
			System.out.println( "Etape "+i+" : ");
			System.out.println("current heuristic "+heuristic(g.getCurrent()) +" VS neighbor heuristic "+heuristic(neighbor));
			if(heuristic(g.getCurrent())<= heuristic(neighbor) ){
				System.out.println("Chemin le plus court, resultant du FIRST CHOICE HILL CLIMBING SEARCH :\n"+g.getCurrent());
				System.out.println("Longueur du chemin minimal trouvé par First choice hill climbing = "+ heuristic(g.getCurrent()));
				return g.getCurrent();
			}
			i++;
			g.setCurrent( neighbor);
		}
	}
	
	public ArrayList<Arc> searchHC(Graphe g) {
		System.out.println( "CURRENT :"+g.getCurrent());
		int i=1;
		while (true){
			g.twoOptNeighbors();
			System.out.println( "Etape "+i+ ":");
			ArrayList<Integer> hList = h(g.getNeighborsOfCurrent());
			System.out.println("current heuristic "+heuristic(g.getCurrent()) +" VS neighbor heuristic "+hList);
			
			i++;
			int chosenNeighbor = posOfSmallerPathNeighbor(heuristic(g.getCurrent()),hList);
			if(chosenNeighbor==-1) {
				System.out.println("Chemin le plus court, resultant du HILL CLIMBING SEARCH :\n"+g.getCurrent());
				System.out.println("Longueur du chemin trouvé par Hill climbing= "+ heuristic(g.getCurrent()));
				return g.getCurrent();
			}
			System.out.println("current : "+g.getCurrent());
			g.setCurrent(g.getNeighborsOfCurrent().get(chosenNeighbor));
			System.out.println("Heuristic of neighbor chosen : "+ heuristic(g.getCurrent()));
			System.out.println("neighbor chosen : "+ g.getCurrent());
		}
	}
	public ArrayList<Integer> h (ArrayList<ArrayList<Arc>> neighbors){
		ArrayList<Integer> hList = new ArrayList<Integer>();
		for(int i = 0; i < neighbors.size(); i++) {
			hList.add(heuristic(neighbors.get(i)));
		}
		return hList;
	}
	public int posOfSmallerPathNeighbor(int hcurrent, ArrayList<Integer> hList) {
		int pos = -1;
		int minH = hcurrent;
		for(int i=0; i<hList.size();i++) {
			if(hList.get(i)<minH ) {
				minH = hList.get(i);
				pos = i;
			}
		}
		return pos;
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
	public ArrayList<Arc> searchRandomRestartHC(Graphe g) {
		ArrayList<Arc> lastChosen= new ArrayList<>();
		ArrayList<Arc> res = search(g);
		int e=0;
		for (Arc a : res){
			lastChosen.add(e,a);
			e++;
		}
		while (true) {
			g.setCurrent(g.chooseCurrentRandomly());
			ArrayList<Arc> resOfHC = search(g);
			System.out.println(" LAST CHOSEN "+lastChosen);
			System.out.println(" NEW RES OF HS "+resOfHC);
			System.out.println("COMPARING DISTANCES : " + heuristic(lastChosen) + " with " + heuristic(resOfHC));
			if (heuristic(lastChosen) <= heuristic(resOfHC)) {
				System.out.println("Fin de la recherche Random-restart hill climbing :");
				System.out.println("Chemin le plus court, resultant du Random-restart HILL CLIMBING SEARCH :\n" + lastChosen);
				System.out.println("Longueur du chemin = " + heuristic(lastChosen));
				return lastChosen;
			}
			int k=0;
			for (Arc a : resOfHC){
				lastChosen.set(k,a);
				k++;
			}
		}
	}
}
