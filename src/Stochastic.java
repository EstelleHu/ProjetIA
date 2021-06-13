import java.util.ArrayList;

public class Stochastic extends HillClimbing{
	
	public ArrayList<Arc> stocSearch(Graph g){
//		System.out.println( "CURRENT :"+g.getCurrent());
		int i=1;
		while (true){
			g.twoOptNeighbors();
			System.out.println( "Step "+i);
			ArrayList<Integer> hList = heuristicsOfNeighbors(g.getNeighborsOfCurrent());
			System.out.println("Current heuristic "+ Graph.heuristic(g.getCurrent()) +" VS neighbor heuristic "+hList);
			
			i++;
			int chosenNeighbor = posOfNeighbor(Graph.heuristic(g.getCurrent()),hList);
			if(chosenNeighbor==-1) {
				System.out.println("Resulting path of STOCHASTIC HILL CLIMBING SEARCH algorithm :\n"+g.getCurrent());
				System.out.println("Path's length = "+ Graph.heuristic(g.getCurrent()));
				return g.getCurrent();
			}
			g.setCurrent(g.getNeighborsOfCurrent().get(chosenNeighbor));
			
		}
	}
		
}
