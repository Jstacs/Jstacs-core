package de.jstacs.algorithms.optimization;

/**
 * This class allows to do a numerical optimization on a subset of the parameters.
 * The parameters to be optimizer are given in the constructor via a <code>boolean</code> array.
 * 
 * @author Jens Keilwagen
 */
public class MaskedDifferentiableFunction extends DifferentiableFunction {

	private DifferentiableFunction fun;
	private boolean[] masked;
	private int n;
	private double[] par, working;
	
	/**
	 * the constructor of the masked <code>function</code> which wraps a function using a <code>mask</code> for the parameters.
	 * 
	 * @param function the original function
	 * @param masked the mask, if <code>mask[i]=false</code> parameter <code>i</code> is optimizer, otherwise it is not
	 * @param par the (start) parameters
	 */
	public MaskedDifferentiableFunction( DifferentiableFunction function, boolean[] masked, double[] par ) {
		this.fun = function;
		
		n = 0;
		this.masked = masked.clone();
		for( int i = 0; i < masked.length; i++ ) {
			if( !masked[i] ) n++;
		}
		
		this.par = par.clone();
		this.working = par.clone();
	}
	
	/**
	 * Creates a complete parameter vector with masked and unmasked parameters.
	 * 
	 * @param x the unmasked parameters
	 * 
	 * @see #evaluateFunction(double[])
	 * @see #evaluateGradientOfFunction(double[]) 
	 */
	private void fillWorking( double[] x ) {
		for( int i = 0, j = 0; i < working.length; i++ ) {
			working[i] = masked[i] ? par[i] : x[j++];
		}
		/*
		System.out.println("p "+Arrays.toString(par));
		System.out.println("w "+Arrays.toString(working));
		System.out.println();
		*/
	}
	
	/**
	 * A convenience method for creating a parameter vector for the optimization.
	 * 
	 * @return a parameter vector
	 */
	public double[] getStartParameters() {
		double[] startPar = new double[n];
		for( int i = 0, j = 0; i < masked.length; i++ ) {
			if( !masked[i] ) {
				startPar[j] = par[i];
				j++;
			}
		}
		return startPar;
	}
	
	@Override
	public double evaluateFunction(double[] x) throws DimensionException, EvaluationException {
		fillWorking(x);
		return fun.evaluateFunction(working);
	}

	@Override
	public int getDimensionOfScope() {
		return n;
	}

	@Override
	public double[] evaluateGradientOfFunction(double[] x) throws DimensionException, EvaluationException {
		fillWorking(x);
		double[] grad = fun.evaluateGradientOfFunction(working);
		double[] g = new double[n];
		for( int i = 0, j = 0; i < masked.length; i++ ) {
			if( !masked[i] ) {
				g[j] = grad[i];
				j++;
			}
		}
		//System.out.println( "g\t" + Arrays.toString(x) + "\t" + Arrays.toString(g) );
		return g;
	}
}