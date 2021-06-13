import java.util.*;

public class SimulatedAnnealing {
    // Simulated Annealing parameters

    // Temperature at which iteration terminates
//    static final double Tmin = .0001;
    static final double Tmin = 0.0001;

    // Decrease in temperature
    static final double alpha = 0.9;

    // Number of iterations of annealing
    // before decreasing temperature
    static final int numIterations = 25;


    public ArrayList<Arc> simulatedAnnealingSearch(Graphe g,ArrayList<Arc> initial,double startingTemperature){
//        System.out.println("INITIAL :"+initial);
        ArrayList<Arc> current = initial;
//        System.out.println("CURRENT :"+current);
        ArrayList<Arc> next;
        double T=startingTemperature;

            for(int i=0;i<numIterations;i++){
                while(T>Tmin){
//                if(T==0){
//                    System.out.println("END OF ALGO WITH RESULT :"+current);
//                    return current;
//                }
                g.setCurrent(current);
                next = g.twoOpt(); // random neighbor of current
//            System.out.println("next :"+next);
                System.out.println("prob :"+Math.pow(Math.E,(heuristic(next)-heuristic(current)/T)));
                if(heuristic(current)>heuristic(next))
                    current=next;
                else{
                    double rand=Math.random();
                    System.out.println("rand :" +rand +" T : "+ T +" current h :"+heuristic(current)+ " next h : "+heuristic(next));
                    boolean b= Math.pow(Math.E,(heuristic(next)-heuristic(current)/T))< rand;
                    System.out.println( " result "+ b);
                    if(b)
                        current=next;
                }
                T *= alpha;
            }

        }
        System.out.println("2 END OF ALGO WITH RESULT :"+current);
        return current;
    }
    public static double heuristic(ArrayList<Arc> current) {
        int somme = 0;
        for(Arc a : current) {
            somme = somme + a.getValeur();
        }
        return somme*0.01;
    }
    
}
