import java.util.*;

public class SimulatedAnnealing {
    // Simulated Annealing parameters

    // Temperature at which iteration terminates
    static final double Tmin = 0.0001;

    // Decrease in temperature
    static final double alpha = 0.9;

    // Number of iterations of annealing
    // before decreasing temperature
    static final int numIterations = 25;


    public ArrayList<Arc> simulatedAnnealingSearch(Graph g,double startingTemperature){
        ArrayList<Arc> current = g.getCurrent();
        ArrayList<Arc> next;
        double T=startingTemperature;

        for(int i=0;i<numIterations;i++) {
            while (T > Tmin) {
                g.setCurrent(current);
                next = g.twoOpt(); // random neighbor of current
//                System.out.println("Variables : \n T : " + T + "\n current's heuristic :" + (Graphe.heuristic(current) * 0.01) + "\n next path's heurostic : " + (Graphe.heuristic(next) * 0.01));

                if ((Graph.heuristic(current) * 0.01) > (Graph.heuristic(next) * 0.01))
                    current = next;
                else if (Math.pow(Math.E, ((Graph.heuristic(next) * 0.01) - (Graph.heuristic(current) * 0.01) / T)) < Math.random()) {
//                    System.out.println("prob :" + Math.pow(Math.E, ((Graphe.heuristic(next) * 0.01) - (Graphe.heuristic(current) * 0.01) / T)));
                    current = next;
                }
                T *= alpha;
            }
        }
        System.out.println("Result OF Simulated Annealing ALGO  :"+current);
        System.out.println("Path length " + Graph.heuristic(current));
        return current;
    }
}
