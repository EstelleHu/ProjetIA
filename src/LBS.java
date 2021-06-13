import java.util.ArrayList;

public class LBS { //faut lui donner un goal ?
    public ArrayList<Arc> LBSAlgorithm(int k, Graphe g) {
        //select randomly k states
        int e = 0;
        ArrayList<ArrayList<Arc>> currentStates = new ArrayList<ArrayList<Arc>>();
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
//            System.out.println("WHILE LBS  " + currentStates.size());
            for (int l = 0; l < k; l++) {
                ArrayList<ArrayList<Arc>> neighboursOfOne = g.twoOptNeighborsOf(currentStates.get(l));
                for (ArrayList<Arc> neighbour : neighboursOfOne) {
                    if (neighbours.size() == 0 || !neighbours.contains(neighbour)) {
                        neighbours.add(neighbour);
                    }
                }
            }
            //selectionner les k meilleurs voisins et comparer leurs heuristics avec les k actuels
            ArrayList<ArrayList<Arc>> bestNeighbours = compareAndChoose_k_Best(k, neighbours);
            //si aucun des k voisins n'est meilleur que les actuels, on retourne le best des actuels
            if (compare(bestNeighbours, currentStates)) {
                return bestOfAll(currentStates);
            }
            //sinon on loop avec les k voisins selectionnés
            currentStates = bestNeighbours;
        }
    }

    private ArrayList<Arc> bestOfAll(ArrayList<ArrayList<Arc>> currentStates) {
        System.out.println("BEST OF ALLLLLLLL");
        ArrayList<Arc> min = currentStates.get(0);
        int i = 1;
        while (i < currentStates.size()) {
            if (Graphe.heuristic(currentStates.get(i)) < Graphe.heuristic(min)) {
                min = currentStates.get(i);
            }
            i++;
        }
        System.out.println("BEST OF ALLLLLLLL MIN " + Graphe.heuristic(min));
        return min;
    }

    private boolean compare(ArrayList<ArrayList<Arc>> bestNeighbours, ArrayList<ArrayList<Arc>> currentStates) {
        System.out.println("COMPARE ");
        for (ArrayList<Arc> neighbour : bestNeighbours) {
            for (ArrayList<Arc> currentState : currentStates) {
                if (Graphe.heuristic(neighbour) < Graphe.heuristic(currentState)) {
                    return false;
                }
            }
        }
        //none of the neighbours is better then the current ones
        return true;
    }

    private ArrayList<ArrayList<Arc>> compareAndChoose_k_Best(int k, ArrayList<ArrayList<Arc>> neighbours) {
        ArrayList<ArrayList<Arc>> res = new ArrayList<ArrayList<Arc>>(k);
//        System.out.println("SIZZZZZZZZZZZZZE" + neighbours.size());
        while (res.size() < k) {
            //on choisit le max ne doit pas etre dans la liste res
            //prob in here return list of size 1
            ArrayList<Arc> min = neighbours.get(0);
            int i = 1;
            System.out.println("LOOKING FOR " + (res.size() + 1) + " BEST ELEMENT");
            while (i < neighbours.size()) {
                System.out.println("comparing min " + Graphe.heuristic(min) + " to " + Graphe.heuristic(neighbours.get(i)));
                boolean h = listContains(res, neighbours.get(i));
                if (res.size() > 0 && h) {
                    System.out.println("YES IT EXISTS " + h);
                    i++;
                } else if (Graphe.heuristic(neighbours.get(i)) < Graphe.heuristic(min)) {
                    min = neighbours.get(i);
                }
                i++;
            }
//            if (res.size() > 0 && !(listContains(res, min)))
                res.add(min);
        }
            System.out.println("RES of K kings");
            for (int u = 0; u < res.size(); u++) {
                System.out.println(" " + u + "  : " + res.get(u) + " : " + Graphe.heuristic(res.get(u)));
            }
            return res;
    }
    private boolean listContains(ArrayList<ArrayList<Arc>> res, ArrayList<Arc> arc) {
        for(ArrayList<Arc> arcOfRes : res){
//            System.out.println(arcOfRes +" && "+ arc);
            if(arcOfRes.equals(arc))
                return true;
        }
        return false;
    }
}



