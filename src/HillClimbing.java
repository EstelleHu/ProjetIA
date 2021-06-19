import java.util.ArrayList;


/**
 * The Class HillClimbing represents the Hill Climbing Algorithm version to solve the Traveling Salesman Problem.
 */
public class HillClimbing {

	
	/**
	 * H csearch.
	 *
	 * @param g the g
	 * @return the array list
	 */
	//HILL CLIMBING SEARCH ALGO
	public ArrayList<Arc> HCsearch(Graph g) {
//		System.out.println( "CURRENT :"+g.getCurrent());
		int i=1;
		while (true){
			g.twoOptNeighbors();
//			System.out.println( "Step "+i+ ":");
			ArrayList<Integer> hList = pathSizesOfNeighbors(g.getNeighborsOfCurrent());
//			System.out.println("Current path sizes "+Graph.pathSize(g.getCurrent()) +" VS neighbor path size "+hList);

			i++;
			int chosenNeighbor = posOfSmallerPathNeighbor(Graph.pathSize(g.getCurrent()),hList);
			if(chosenNeighbor==-1) {
				System.out.println("Resulting path of HILL CLIMBING SEARCH algorithm :\n"+g.getCurrent());
				System.out.println("Path's length = "+ Graph.pathSize(g.getCurrent()));
				return g.getCurrent();
			}
			g.setCurrent(g.getNeighborsOfCurrent().get(chosenNeighbor));
//			System.out.println("Path size of neighbor chosen : "+ Graph.pathSize(g.getCurrent()));
//			System.out.println("neighbor chosen : "+ g.getCurrent());
		}
	}

	/**
	 * Path sizes of neighbors.
	 *
	 * @param neighbors the neighbors
	 * @return the array list
	 */
	// Tools
	public ArrayList<Integer> pathSizesOfNeighbors (ArrayList<ArrayList<Arc>> neighbors){
		ArrayList<Integer> hList = new ArrayList<>();
		for(int i = 0; i < neighbors.size(); i++) {
			hList.add(Graph.pathSize(neighbors.get(i)));
		}
		return hList;
	}
	
	/**
	 * Pos of smaller path neighbor.
	 *
	 * @param hcurrent the hcurrent
	 * @param hList the h list
	 * @return the int
	 */
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
	
	/**
	 * Pos of neighbor.
	 *
	 * @param hcurrent the hcurrent
	 * @param hList the h list
	 * @return the int
	 */
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
