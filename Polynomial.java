//package cscb07lab1;

public class Polynomial {
	double[] coeffcients;
	public Polynomial(){
		coeffcients=new double[0];
	}
	public Polynomial(double[] coeffcients){
		this.coeffcients=coeffcients;
	}
	public double evaluate(double x) {
		double result =0.0;
		for(int i=0; i<this.coeffcients.length;i++)
			result +=this.coeffcients[i]*Math.pow(x, i);
		return result;
	}
	public Polynomial add(Polynomial num){
		double[] coef;
		int len1 = this.coeffcients.length;
		int len2 = num.coeffcients.length;
		if(len1>len2)
			coef = new double[len1];
		else
			coef = new double[len2];
		
		for(int i=0; i<len1;i++)
			coef[i]=this.coeffcients[i];
		
		for(int i=0;i<len2;i++)
			coef[i]+=num.coeffcients[i];
		return new Polynomial(coef);
	}
	public boolean hasRoot(int i) {
		return evaluate(i) ==0;
	}
}
