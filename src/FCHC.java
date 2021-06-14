import java.util.ArrayList;


/**
 * The Class FCHC.
 */
public class FCHC extends HillClimbing {
    
    /**
     * F csearch.
     *
     * @param g the g
     * @return the array list
     */
    //First choice hill climbing
    public ArrayList<Arc> FCsearch(Graph g) {
        int i=1;
        while (true){
            ArrayList<Arc> neighbor =  g.twoOpt();
            System.out.println( "Step "+i+" : ");
            System.out.println("Current heuristic "+heuristic(g.getCurrent()) +" VS neighbor heuristic "+heuristic(neighbor));
            if(heuristic(g.getCurrent())<= heuristic(neighbor) ){
                System.out.println("Resulting path of FIRST CHOICE HILL CLIMBING SEARCH algorithm :\n"+g.getCurrent());
                System.out.println("Path's length = "+ heuristic(g.getCurrent()));
                return g.getCurrent();
            }
            i++;
            g.setCurrent( neighbor);
        }
    }
}
