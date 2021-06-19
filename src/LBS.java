import java.security.AllPermission;
import java.util.ArrayList;


/**
 * The Class LBS represents the Local beam search Algorithm version to solve the Traveling Salesman Problem.
 */
public class LBS { /**
  * LBS algorithm.
  *
  * @param k the k
  * @param g the g
  * @return the array list
  */
 // NB faut lui donner un goal ?
    public ArrayList<Arc> LBSAlgorithm(int k, Graph g) {
        //selects randomly k states
        int e = 0;
        ArrayList<ArrayList<Arc>> currentStates = new ArrayList<>();
        while (e < k) {
            ArrayList<Arc> randState = g.chooseCurrentRandomly();
            if (currentStates.size() == 0 || !currentStates.contains(randState)) {
                currentStates.add(randState);
                e++;
            }
        }
        while (true) {
            ArrayList<ArrayList<Arc>> neighbours = new ArrayList<ArrayList<Arc>>();
            //generate all neighbors
            for (int l = 0; l < k; l++) {
                ArrayList<ArrayList<Arc>> neighboursOfOne = g.twoOptNeighborsOf(currentStates.get(l));
                for (ArrayList<Arc> neighbour : neighboursOfOne) {
                    if (neighbours.size() == 0 || !neighbours.contains(neighbour)) {
                        neighbours.add(neighbour);
                    }
                }
            }
            //selects k best neighbours and compares their path sizes to those of the k current paths


            ArrayList<ArrayList<Arc>> bestNeighbours  = compareAndChoose_k_Best(g.getTaille(),k, neighbours);

            // if none of the K best selected is better than all of the current paths, we return the best of currents
            if (compare(bestNeighbours, currentStates)) {
                return bestOfAll(currentStates);
            }
            System.out.println("Passing best neighbours selected to be current states");
            currentStates = bestNeighbours;
        }
    }

    /**
     * Best of all.
     *
     * @param currentStates the current states
     * @return the array list
     */
    private ArrayList<Arc> bestOfAll(ArrayList<ArrayList<Arc>> currentStates) {
        ArrayList<Arc> min = currentStates.get(0);
        int i = 1;
        while (i < currentStates.size()) {
            if (Graph.pathSize(currentStates.get(i)) < Graph.pathSize(min)) {
                min = currentStates.get(i);
            }
            i++;
        }
        System.out.println("BEST OF ALL MIN " + Graph.pathSize(min));
        return min;
    }

    /**
     * Compare.
     *
     * @param bestNeighbours the best neighbours
     * @param currentStates the current states
     * @return true, if successful
     */
    private boolean compare(ArrayList<ArrayList<Arc>> bestNeighbours, ArrayList<ArrayList<Arc>> currentStates) {
        if (allNeighbourSizesEqualToCurrentOnes(currentStates,bestNeighbours)) {
            return true;
        }
        for (ArrayList<Arc> neighbour : bestNeighbours) {
            for (ArrayList<Arc> currentState : currentStates) {
                if (Graph.pathSize(neighbour) < Graph.pathSize(currentState)) {
                    return false;
                }
            }
        }
        //none of the neighbours is better then the current ones
        return true;
    }
    /**
     * Looks if none of the neighbours has a better size than the currentOnes.
     *
     * @param bestNeighbours the best neighbours
     * @param currentStates the current states
     * @return true, if successful
     */
    private boolean allNeighbourSizesEqualToCurrentOnes(ArrayList<ArrayList<Arc>> currentStates, ArrayList<ArrayList<Arc>> bestNeighbours) {
        ArrayList<Integer> currentStatesSizes = new ArrayList<Integer>();
        for (ArrayList<Arc> currentState : currentStates) {
            currentStatesSizes.add(Graph.pathSize(currentState));
        }
        for (ArrayList<Arc> neighbour : bestNeighbours) {
            if (!currentStatesSizes.contains(Graph.pathSize(neighbour))) {
                return false;
            }
        }
        //the two lists are equal
        return true;
    }

    /**
     * Compare and choose k best.
     *
     * @param k the k
     * @param neighbours the neighbours
     * @return the array list
     */
    private ArrayList<ArrayList<Arc>> compareAndChoose_k_Best(int n,int k, ArrayList<ArrayList<Arc>> neighbours) {
        ArrayList<ArrayList<Arc>> res = new ArrayList<>(k);
        while (res.size() != k ) {
//            System.out.println("List size : "+res.size()+" on "+k);
            ArrayList<Arc> min = neighbours.get(0);
            int i = 1;
//            System.out.println("LOOKING FOR " + (res.size() + 1) + " BEST ELEMENT");

            while (i < neighbours.size()) {
//                System.out.println("Comparing min " + Graph.pathSize(min) + " to " + Graph.pathSize(neighbours.get(i)));
                if (res.size() > 0 && listContains(res, neighbours.get(i))) {
                    i++;
                } else if (Graph.pathSize(neighbours.get(i)) < Graph.pathSize(min)) {
                    min = neighbours.get(i);
                }
                i++;
            }
            res.add(min);
        }
            if(n<25){
                System.out.println("K best neighbors in the list : ");
                for (int u = 0; u < res.size(); u++) {
                    System.out.println(" " + u + "  : " + res.get(u) + " : " + Graph.pathSize(res.get(u)));
                }
            }
            return res;
    }
    
    /**
     * List contains.
     *
     * @param res the res
     * @param arc the arc
     * @return true, if successful
     */
    private boolean listContains(ArrayList<ArrayList<Arc>> res, ArrayList<Arc> arc) {
        for(ArrayList<Arc> arcOfRes : res){
            if(arcOfRes.equals(arc))
                return true;
        }
        return false;
    }
}



