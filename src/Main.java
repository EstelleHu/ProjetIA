import java.util.Scanner;

/**
 * The Class Main tests all the algorithms.
 */
public class Main {
	public static void testAllWithSize(int n,Graph g){
		System.out.println("***************** TESTING WITH GRAPH SIZE = " + n + " *******************");
		System.out.println("   THE GRAPH'S MATRIX :\n");
		g.affiche();

		//ALGORITHMS INITIATIONS
		HillClimbing hc = new HillClimbing();
		FCHC firstChoiceHC = new FCHC();
		RRHC randomRestartHC = new RRHC();
		int geneticK = n; // WE CAN ALSO CHANGE THE NUMBER OF PARENTS IF WE WANT
		GeneticAlgorithm genetic = new GeneticAlgorithm(geneticK, g);
		SimulatedAnnealing sa = new SimulatedAnnealing();
		LBS localBeam = new LBS();
		Stochastic s = new Stochastic();

		//TIME COMPLEXITY VARIABLES
		long startTime = System.currentTimeMillis();
		long endTime;
		float seconds;

		//Hill Climbing search
		System.out.println("\n \n    ************ TESTING HILL CLIMBING SEARCH ***********        \n");
		hc.HCsearch(g);
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("\n \n    *****//******END OF TEST [HILL CLIMBING SEARCH] after " + Float.toString(seconds) + " seconds. ****//*****        \n");

		startTime = endTime;

		//First Choice Hill Climbing search
		System.out.println("\n \n    ************ TESTING FIRST CHOICE HILL CLIMBING SEARCH ***********        \n");
		firstChoiceHC.FCsearch(g);
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("\n \n    *****//******END OF TEST [FIRST CHOICE SEARCH] after " + Float.toString(seconds) + " seconds. ****//*****        \n");

		startTime = endTime;

		//Random-restart hill climbing
		System.out.println("\n \n    ************ TESTING Random-restart HILL CLIMBING SEARCH ***********        \n");
		g.randomizeCurrent();
		randomRestartHC.RRsearch(g);
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("\n \n    *****//******END OF TEST [Random-restart SEARCH] after " + Float.toString(seconds) + " seconds. ****//*****        \n");

		startTime = endTime;


		//Stochastic Hill Climbing search
		System.out.println("\n \n    ************ TESTING Stochastic HILL CLIMBING SEARCH ***********        \n");
		s.stocSearch(g);
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("\n \n    *****//******END OF TEST [Stochastic SEARCH] after " + Float.toString(seconds) + " seconds. ****//*****        \n");

		startTime = endTime;

		//Simulated Annealing Test
		System.out.println("\n \n    ************ TESTING Simulated Annealing SEARCH ***********        \n");
		int startingTemprature = 1;
		System.out.println(" Starting with temprature equal to " + startingTemprature);
		sa.simulatedAnnealingSearch(g, startingTemprature);
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("\n \n    *****//******END OF TEST [Simulated Annealing SEARCH] after " + Float.toString(seconds) + " seconds. ****//*****        \n");

		startTime = endTime;



		// Genetic Algorithm
		System.out.println("\n \n    ************ TESTING Genetic Algorithm ***********        \n");
		System.out.println(" We select " + geneticK + " cities");
		int nbIterations = 100;
		System.out.println("SEARCH WITH " + nbIterations + " iterations");
		genetic.geneticSearch(genetic.crossover(nbIterations));
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("\n \n    *****//******END OF TEST [Genetic Algorithm] after " + Float.toString(seconds) + " seconds. ****//*****        \n");
		startTime = endTime;

		//Local Beam Search Test
		System.out.println("\n \n    ************TESTING Local Beam SEARCH ***********        \n");
		int k = 2;
		System.out.println(" We select " + k + " cities");
		System.out.println(localBeam.LBSAlgorithm(k, g));
		endTime = System.currentTimeMillis();
		seconds = (endTime - startTime) / 1000F;
		System.out.println("\n \n    *****//******END OF TEST [Local Beam SEARCH] after " + Float.toString(seconds) + " seconds. ****//*****        \n");


	}
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */

	public static void main (String[] args) {
		//BLOC 1 : test with n =5
		System.out.println("TEST 1 WITH 5 cities");
		 // HERE WE GENERATE THE GRAPH ON WHICH WE WILL MAKE TESTS
		Graph g = new Graph(1); // size of graph equal to 1+4
		testAllWithSize(1,g);
		System.out.println("END OF TEST 1");
		//END OF BLOC 1

		//BLOC 2 : changing the number n of cities on the graph
		/*System.out.println("TEST 2 WITH different number of cities");
		int[] sizes = new int[3];
		sizes[0]=-1;
		sizes[1]=-1;
		sizes[2]=-1;
		// WE VARIATE N
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.print("Veuillez saisir un premier entier n : ");
				sizes[0] = scanner.nextInt();
			}while(sizes[0]<4);

			do {
			System.out.print("Veuillez saisir un second entier n : ");
			sizes[1] = scanner.nextInt();
			}while(sizes[1]<4);

			do {
			System.out.print("Veuillez saisir un troisième entier n : ");
			sizes[2] = scanner.nextInt();
			}while(sizes[2]<4);
		}

		for (int i = 0; i < 3;i++) {
			Graph g = new Graph(sizes[i]-4);
			testAllWithSize(sizes[i],g);
		}
		System.out.println("END OF TEST 2");*/
		//END OF BLOC 2

		//FOR THE REMAINING TESTS, PLEASE UNCOMMENT THIS BLOC :
		/*int n= 10;
		Graph g = new Graph(n-4);
		LBS localBeam = new LBS();
		System.out.println("***************** TESTING WITH GRAPH SIZE = " + n + " *******************");
		System.out.println("   THE GRAPH'S MATRIX :\n");
		g.affiche();*/

		//END OF THE BLOC

		//BLOC 3: testing Local Beam Search with different number k of selected cities
		/*System.out.println("\n \n    ************TESTING Local Beam SEARCH with different k ***********        \n");
		System.out.println("TEST 3 WITH different number of cities");
		int[] LBSk= new int[3];
		LBSk[0] =-1;
		LBSk[1]=-1;
		LBSk[2] =-1;
		 //WE VARIATE  K
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.print("Veuillez saisir un premier entier k superieur à 1 :");
				LBSk[0] = scanner.nextInt();
			}while(LBSk[0]==0);// nb combien de voisins peut avoir un chemin de taille n ?

			do {
				System.out.print("Veuillez saisir un second entier k superieur à 1 :");
				LBSk[1] = scanner.nextInt();
			}while(LBSk[1]==0);

			do {
				System.out.print("Veuillez saisir un troisième entier k superieur à 1 :");
				LBSk[2] = scanner.nextInt();
			}while(LBSk[2]==0);
		}
		//TIME COMPLEXITY VARIABLES
		long startTime = System.currentTimeMillis();
		long endTime;
		float seconds;
		for(int o=0;o<3;o++) {
			System.out.println(" We select " + LBSk[o] + " cities");
			System.out.println(localBeam.LBSAlgorithm(LBSk[o], g));
			endTime = System.currentTimeMillis();
			seconds = (endTime - startTime) / 1000F;
			System.out.println("\n \n    ***********END OF TEST [Local Beam SEARCH] with k="+LBSk[o]+" after " + Float.toString(seconds) + " seconds. *********        \n");
			startTime = endTime;
		}
		System.out.println("END OF TEST 3");*/
		//END OF TEST 3

		//BLOC 4: testing Genetic search algorithm with different size k of population
		/*System.out.println("\n \n    ************ TESTING Genetic Algorithm with different k ***********        \n");
		int[] geneticK1= new int[3];
		geneticK1[0] =-1;
		geneticK1[1]=-1;
		geneticK1[2] =-1;
		// WE VARIATE the size K of population
		try (Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.print("Veuillez saisir un premier nombre de crossover : ");
				geneticK1[0] = scanner.nextInt();
			}while(geneticK1[0]==0 );//
			do {
				System.out.print("Veuillez saisir un second nombre de crossover : ");
				geneticK1[1] = scanner.nextInt();
			}while(geneticK1[1]==0 );

			do {
				System.out.print("Veuillez saisir un troisième nombre de crossover : ");
				geneticK1[2] = scanner.nextInt();
			}while(geneticK1[2]==0 );
		}
		//TIME COMPLEXITY VARIABLES
		long startTime = System.currentTimeMillis();
		long endTime;
		float seconds;
		for(int e=0;e<3;e++){
			System.out.println(" We select " + n + " cities");
			GeneticAlgorithm geneticX = new GeneticAlgorithm(n, g);
			System.out.println("SEARCH WITH " + geneticK1[e] + " iterations of crossover");
			geneticX.geneticSearch(geneticX.crossover(geneticK1[e]));
			endTime = System.currentTimeMillis();
			seconds = (endTime - startTime) / 1000F;
			System.out.println("\n \n    ***********END OF TEST [Genetic Algorithm] with "+(geneticK1[e])+" after " + Float.toString(seconds) + " seconds. *********        \n");
			startTime = endTime;
		}
		System.out.println("END OF TEST 4");*/
		//END OF BLOC 4

	}
}
