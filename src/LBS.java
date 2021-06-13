import java.util.ArrayList;

public class LBS { // NB faut lui donner un goal ?
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
            //selects k best neighbors and copares their heuristics to those of the k current paths
            ArrayList<ArrayList<Arc>> bestNeighbours = compareAndChoose_k_Best(k, neighbours);
            // if none of the K best selected is better than all of the current paths, we return the best of currents
            if (compare(bestNeighbours, currentStates)) {
                return bestOfAll(currentStates);
            }
            currentStates = bestNeighbours;
        }
    }

    private ArrayList<Arc> bestOfAll(ArrayList<ArrayList<Arc>> currentStates) {
        ArrayList<Arc> min = currentStates.get(0);
        int i = 1;
        while (i < currentStates.size()) {
            if (Graph.heuristic(currentStates.get(i)) < Graph.heuristic(min)) {
                min = currentStates.get(i);
            }
            i++;
        }
        System.out.println("BEST OF ALL MIN " + Graph.heuristic(min));
        return min;
    }

    private boolean compare(ArrayList<ArrayList<Arc>> bestNeighbours, ArrayList<ArrayList<Arc>> currentStates) {
        for (ArrayList<Arc> neighbour : bestNeighbours) {
            for (ArrayList<Arc> currentState : currentStates) {
                if (Graph.heuristic(neighbour) < Graph.heuristic(currentState)) {
                    return false;
                }
            }
        }
        //none of the neighbours is better then the current ones
        return true;
    }

    private ArrayList<ArrayList<Arc>> compareAndChoose_k_Best(int k, ArrayList<ArrayList<Arc>> neighbours) {
        ArrayList<ArrayList<Arc>> res = new ArrayList<>(k);
        while (res.size() < k) {
            ArrayList<Arc> min = neighbours.get(0);
            int i = 1;
            System.out.println("LOOKING FOR " + (res.size() + 1) + " BEST ELEMENT");
            while (i < neighbours.size()) {
//                System.out.println("Comparing min " + Graphe.heuristic(min) + " to " + Graphe.heuristic(neighbours.get(i)));
                if (res.size() > 0 && listContains(res, neighbours.get(i))) {
                    i++;
                } else if (Graph.heuristic(neighbours.get(i)) < Graph.heuristic(min)) {
                    min = neighbours.get(i);
                }
                i++;
            }
            res.add(min);
        }
            System.out.println("K best neighbors in the list : ");
            for (int u = 0; u < res.size(); u++) {
                System.out.println(" " + u + "  : " + res.get(u) + " : " + Graph.heuristic(res.get(u)));
            }
            return res;
    }
    private boolean listContains(ArrayList<ArrayList<Arc>> res, ArrayList<Arc> arc) {
        for(ArrayList<Arc> arcOfRes : res){
            if(arcOfRes.equals(arc))
                return true;
        }
        return false;
    }
}



