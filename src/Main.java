public class Main {
	public static void main (String[] args) {
		int n= 2;
		Graph g = new Graph(n);
		HillClimbing hc= new HillClimbing();
		FCHC firstChoiceHC = new FCHC();
		RRHC randomRestartHC = new RRHC();
		GeneticAlgorithm genetic = new GeneticAlgorithm(5,g);
		SimulatedAnnealing sa=new SimulatedAnnealing();
		LBS localBeam = new LBS();


		System.out.println(" Number of cities = " +(n+4)+ "\n Graph matrix :");

		g.affiche();

		//Hill Climbing search
		System.out.println(" \n TEST HILL CLIMBING  \n");
		hc.HCsearch(g);


		//First Choice Hill Climbing search
		System.out.println(" \n TEST FIRST CHOICE  \n");
		firstChoiceHC.FCsearch(g);

		//Random-restart hill climbing
		g.randomizeCurrent();
		randomRestartHC.RRsearch(g);


		//Stochastic Hill Climbing search
		Stochastic s= new Stochastic();
		System.out.println(" \n TEST Stochastic \n");
		s.stocSearch(g);


		//Simulated Annealing Test
		System.out.println(" \n TEST Simulated Annealing \n");
		sa.simulatedAnnealingSearch(g,1);

//		//Local Beam Search Test
		System.out.println(" \n TEST Local Beam Search \n");
		System.out.println(localBeam.LBSAlgorithm(2,g));

		// Genetic Algorithm
		System.out.println(" \n TEST Genetic Algorithm \n");
		System.out.println("POPULATION : "); // NB a revoir
		System.out.println(genetic.getPopulation());
		System.out.println("SEARCH");
		genetic.geneticSearch(genetic.crossover(genetic.selection()),1);

	}
}
