import java.util.ArrayList;

public class HillClimbing {
	
	public int heuristic (ArrayList<Arc> current) {
		int somme = 0;
		for(Arc a : current) {
			somme = somme + a.getValeur();
		}
		return somme;
	}
	//HILL CLIMBING SEARCH ALGO
	public ArrayList<Arc> HCsearch(Graph g) {
//		System.out.println( "CURRENT :"+g.getCurrent());
		int i=1;
		while (true){
			g.twoOptNeighbors();
			System.out.println( "Step "+i+ ":");
			ArrayList<Integer> hList = heuristicsOfNeighbors(g.getNeighborsOfCurrent());
			System.out.println("Current heuristic "+heuristic(g.getCurrent()) +" VS neighbor heuristic "+hList);

			i++;
			int chosenNeighbor = posOfSmallerPathNeighbor(heuristic(g.getCurrent()),hList);
			if(chosenNeighbor==-1) {
				System.out.println("Resulting path of HILL CLIMBING SEARCH algorithm :\n"+g.getCurrent());
				System.out.println("Path's length = "+ heuristic(g.getCurrent()));
				return g.getCurrent();
			}
			g.setCurrent(g.getNeighborsOfCurrent().get(chosenNeighbor));
//			System.out.println("Heuristic of neighbor chosen : "+ heuristic(g.getCurrent()));
//			System.out.println("neighbor chosen : "+ g.getCurrent());
		}
	}

	// Tools
	public ArrayList<Integer> heuristicsOfNeighbors (ArrayList<ArrayList<Arc>> neighbors){
		ArrayList<Integer> hList = new ArrayList<>();
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
	public int posOfNeighbor(int hcurrent, ArrayList<Integer> hList) {
		ArrayList<Integer> pos = new ArrayList<Integer>();
		for(int i=0; i<hList.size();i++) {
			if(hList.get(i)<hcurrent) {
				pos.add(i);
			}
		}
		if(pos.size()==0) {
			return -1;
		}
		return pos.get((int)(Math.random()*pos.size()));
	}
}
