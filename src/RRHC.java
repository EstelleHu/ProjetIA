import java.util.ArrayList;


/**
 * The Class RRHC represents the Random-restart hill climbing algorithm version to solve the Traveling Salesman Problems.
 */
public class RRHC extends HillClimbing {
    
    /**
     * R rsearch.
     *
     * @param g the g
     * @return the array list
     */
    public ArrayList<Arc> RRsearch(Graph g) {
        ArrayList<Arc> lastChosen= new ArrayList<>();
        ArrayList<Arc> res = HCsearch(g);
        int e=0;
        for (Arc a : res){
            lastChosen.add(e,a);
            e++;
        }
        while (true) {
            g.setCurrent(g.chooseCurrentRandomly());
            ArrayList<Arc> resOfHC = HCsearch(g);
//            System.out.println("COMPARING DISTANCES : " + Graph.pathSize(lastChosen) + " with " + Graph.pathSize(resOfHC));
            if (Graph.pathSize(lastChosen) <= Graph.pathSize(resOfHC)) {
//                System.out.println("END of Random-restart hill climbing research  :");
                System.out.println("Resulting Path :\n" + lastChosen);
                System.out.println("Path length " + Graph.pathSize(lastChosen));
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
