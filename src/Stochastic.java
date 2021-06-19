import java.util.ArrayList;


/**
 * The Class Stochastic represents Stochastic hill climbing algorithm version to solve the Traveling Salesman Problem.
 */
public class Stochastic extends HillClimbing{
	
	/**
	 * Stoc search.
	 *
	 * @param g the g
	 * @return the array list
	 */
	public ArrayList<Arc> stocSearch(Graph g){
//		System.out.println( "CURRENT :"+g.getCurrent());
		int i=1;
		while (true){
			g.twoOptNeighbors();
//			System.out.println( "Step "+i);
			ArrayList<Integer> hList = pathSizesOfNeighbors(g.getNeighborsOfCurrent());
//			System.out.println("Current path Size "+ Graph.pathSize(g.getCurrent()) +" VS neighbor path Size "+hList);
			
			i++;
			int chosenNeighbor = posOfNeighbor(Graph.pathSize(g.getCurrent()),hList);
			if(chosenNeighbor==-1) {
				System.out.println("Resulting path of STOCHASTIC HILL CLIMBING SEARCH algorithm :\n"+g.getCurrent());
				System.out.println("Path's length = "+ Graph.pathSize(g.getCurrent()));
				return g.getCurrent();
			}
			g.setCurrent(g.getNeighborsOfCurrent().get(chosenNeighbor));
			
		}
	}
		
}
