package eetac.cities.noattack;

import eetac.cities.noattack.analyzers.AnalyzerFactory;
import eetac.cities.noattack.args.Args;
import eetac.cities.noattack.exception.AnalyzerInitializationException;
import eetac.cities.noattack.exception.ArgsException;
import eetac.cities.noattack.model.Flow;

/**
 * Main class for NoAttack program
 *
 */
public class Main implements Runnable
{
	public Main(String[] args) {
		super();
		
		// Load arguments
		try {
			Args.setArgs(args);
		} catch (ArgsException e) {
			System.out.println("The arguments are not correct.");
			System.exit(-1);
		}
		
//		if(args.length == 0) {
//			System.out.println("No flow detected.");
//			System.exit(0);
//		} else {
//			
//			try {
//				InputStream file = new FileInputStream(args[INPUT_FILE_ARG]);
//				flow = FlowTCP.fromBoundle(new PropertyResourceBundle(file));
//				System.out.println("Flow loader:\n " + flow);
//				
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				System.exit(-1);
//			}
//		}
	}

	public void run() {
		try {
			startNoAttack();
		} catch(AnalyzerInitializationException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		} catch (ArgsException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}

	private void startNoAttack() {
		for (Flow flow : Args.getInputFlows()) {
			AnalyzerFactory.analyze(flow);
		}
	}

	public static void main(String[] args) {
		new Main(args).run();
	}
}
