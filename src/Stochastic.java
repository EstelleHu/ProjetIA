import java.util.ArrayList;

public class Stochastic extends HillClimbing{
	
	public ArrayList<Arc> search(Graphe g){
		System.out.println( "CURRENT :"+g.getCurrent());
		int i=0;
		while (true){
			g.twoOptNeighbors();
			System.out.println( "Etape "+i+"  neighbor : "+ g.getNeighborsOfCurrent());
			ArrayList<Integer> hList = h(g.getNeighborsOfCurrent());
			System.out.println("current heuristic "+ Graphe.heuristic(g.getCurrent()) +" VS neighbor heuristic "+hList);
			
			i++;
			int chosenNeighbor = posOfNeighbor(Graphe.heuristic(g.getCurrent()),hList);
			if(chosenNeighbor==-1) {
				System.out.println("Chemin le plus court, resultant du STOCHASTIC HILL CLIMBING SEARCH :\n"+g.getCurrent());
				System.out.println("Longueur du chemin = "+ Graphe.heuristic(g.getCurrent()));
				return g.getCurrent();
			}
			System.out.println("current : "+g.getCurrent());
			g.setCurrent(g.getNeighborsOfCurrent().get(chosenNeighbor));
			System.out.println("neighbor chosen : "+ g.getCurrent());
			
		}
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
