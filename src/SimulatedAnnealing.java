import java.util.*;


/**
 * The Class SimulatedAnnealing represents the Simulated annealing algorithm.
 */
public class SimulatedAnnealing {
    // Simulated Annealing parameters

    /** The Constant Tmin. */
    // Temperature at which iteration terminates
    static final double Tmin = 0.0001;

    /** The Constant alpha. */
    // Decrease in temperature
    static final double alpha = 0.9;

    // Number of iterations of annealing
    /** The Constant numIterations. */
    // before decreasing temperature
    static final int numIterations = 50;


    /**
     * Simulated annealing search.
     *
     * @param g the g
     * @param startingTemperature the starting temperature
     * @return the array list
     */
    public ArrayList<Arc> simulatedAnnealingSearch(Graph g,double startingTemperature){
        ArrayList<Arc> current = g.getCurrent();
        ArrayList<Arc> next;
        double T=startingTemperature;

        for(int i=0;i<numIterations;i++) {
            while (T > Tmin) {
                g.setCurrent(current);
                next = g.twoOpt(); // random neighbor of current
//                System.out.println("Variables : \n T : " + T + "\n current's path size :" + (Graphe.pathSize(current) * 0.01) + "\n next path's path size : " + (Graphe.pathSize(next) * 0.01));

                if ((Graph.pathSize(current) * 0.01) > (Graph.pathSize(next) * 0.01))
                    current = next;
                else if (Math.pow(Math.E, ((Graph.pathSize(next) * 0.01) - (Graph.pathSize(current) * 0.01) / T)) < Math.random()) {
//                    System.out.println("prob :" + Math.pow(Math.E, ((Graphe.pathSize(next) * 0.01) - (Graphe.pathSize(current) * 0.01) / T)));
                    current = next;
                }
                T *= alpha;
            }
        }
        System.out.println("Result OF Simulated Annealing ALGO  :"+current);
        System.out.println("Path length " + Graph.pathSize(current));
        return current;
    }
}
