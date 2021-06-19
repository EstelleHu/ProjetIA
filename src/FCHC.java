import java.util.ArrayList;


/**
 * The Class FCHC represents the First Choice Hill Climbing algorithm version to solve the Traveling Salesman Problem.
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
//        int i=1;
        while (true){
            ArrayList<Arc> neighbor =  g.twoOpt();
//            System.out.println( "Step "+i+" : ");
//            System.out.println("Current path size "+Graph.pathSize(g.getCurrent()) +" VS neighbor path size "+Graph.pathSize(neighbor));
            if(Graph.pathSize(g.getCurrent())<= Graph.pathSize(neighbor) ){
                System.out.println("Resulting path of FIRST CHOICE HILL CLIMBING SEARCH algorithm :\n"+g.getCurrent());
                System.out.println("Path's length = "+ Graph.pathSize(g.getCurrent()));
                return g.getCurrent();
            }
//            i++;
            g.setCurrent( neighbor);
        }
    }
}
