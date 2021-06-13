import java.util.ArrayList;

public class Main {
	public static void main (String[] args) {
		int n= 2;
		Graphe g = new Graphe(n);
		HillClimbing hc= new HillClimbing();
		System.out.println("Le graphe est de taille= " +(n+4));
		g.affiche();
		
		
		//First Choice Hill Climbing search

//		System.out.println(a.size());
		/*System.out.println("TEST 1 \n");
		hc.search(g);
		System.out.println("TEST 2 \n");
		hc.search(g);
		System.out.println("TEST 3 \n");
		hc.search(g);*/

		//Stochastic Hill Climbing search
//		Stochastic s= new Stochastic();
//		System.out.println("TEST 1 \n");
//		s.search(g);

		//TESTING Random-restart hill climbing
		//g.randomizeCurrent();
		//hc.searchRandomRestartHC(g);
		
		// Genetic Algorithm
		/*GeneticAlgorithm genetic = new GeneticAlgorithm(5,g);
		System.out.println("POPULATION");
		System.out.println(genetic.getPopulation());
		System.out.println("SEARCH");
		genetic.search(genetic.crossover(genetic.selection()));*/
		
		//Hill Climbing search
		hc.searchHC(g);
		
	}
}
