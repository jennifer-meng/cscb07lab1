import java.io.File;
import java.io.IOException;

//package cscb07lab1;

public class Driver {
	public static void main(String [] args) {
	        double[] c1 = {5, 7, -3,-5};
	        int[]    e1 = {2, 1, 0,8};
	        Polynomial p1 = new Polynomial(c1, e1);

	        double[] c2 = {7, 6,-2};
	        int[]    e2 = {0, 4, 1};
	        Polynomial p2 = new Polynomial(c2, e2);

	        System.out.println("testing add");
	        Polynomial r1 = p1.add(p2);

	        for (int i = 0; i < r1.coeffcients.length; i++)
	            System.out.print(r1.coeffcients[i] + " ");
	        System.out.println();

	        for (int i = 0; i < r1.exponents.length; i++)
	            System.out.print(r1.exponents[i] + " ");
	        System.out.println();


	        double[] c4 = {6, 5,-2,-10};
	        int[]    e4 = {0, 3, 1,8};
	        Polynomial p4 = new Polynomial(c4, e4);

	        double[] c5 = {5, 6};
	        int[]    e5 = {0, 4};
	        Polynomial p5 = new Polynomial(c5, e5);

	        System.out.println("testing add");
	        Polynomial r3 = p4.add(p5);
	        for (int i = 0; i < r3.coeffcients.length; i++)
	            System.out.print(r3.coeffcients[i] + " ");
	        System.out.println();

	        for (int i = 0; i < r3.exponents.length; i++)
	            System.out.print(r3.exponents[i] + " ");
	        System.out.println();

	        System.out.println("testing multiply");
	        Polynomial r2 = p2.multiply(p5);
	        for (int i = 0; i < r2.coeffcients.length; i++)
	            System.out.print(r2.coeffcients[i] + " ");
	        System.out.println();
	        for (int i = 0; i < r2.exponents.length; i++)
	            System.out.print(r2.exponents[i] + " ");
	        System.out.println();
	        System.out.println("testing multiply");
	        double[] c6 = {6, 5,-2,-10};
	        int[]    e6 = {0, 3, 1,8};
	        Polynomial p6 = new Polynomial(c6, e6);

	        double[] c7 = {5, -0.5,1,2};
	        int[]    e7 = {0, 4,2,1};
	        Polynomial p7 = new Polynomial(c7, e7);
	        Polynomial r4 = p6.multiply(p7);
	        for (int i = 0; i < r4.coeffcients.length; i++)
	            System.out.print(r4.coeffcients[i] + " ");
	        System.out.println();
	        for (int i = 0; i < r4.exponents.length; i++)
	            System.out.print(r4.exponents[i] + " ");
	        System.out.println();

//	        r4 = p6.multiply(p7);
//	        for (int i = 0; i < r4.coefficients.length; i++)
//	            System.out.print(r4.coefficients[i] + " ");
//	        System.out.println();
//	        for (int i = 0; i < r4.exponents.length; i++)
//	            System.out.print(r4.exponents[i] + " ");
//	        System.out.println();

	        try {
	            p1.saveToFile("p1.txt");

	            File file = new File("p1.txt");
	            Polynomial p3 = new Polynomial(file);

	            System.out.println(p3);
	            r1.saveToFile("r1.txt");;
	            r2.saveToFile("r2.txt");;
	            r3.saveToFile("r3.txt");;
	            r4.saveToFile("r4.txt");;
	        } catch (IOException ex1) {
	            // TODO:
	        }



	    }
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Polynomial p = new Polynomial();
//		System.out.println(p.evaluate(3));
//		double [] c1 = {6,0,0,5};
//		Polynomial p1 = new Polynomial(c1);
//		double [] c2 = {0,-2,0,0,-9};
//		Polynomial p2 = new Polynomial(c2);
//		Polynomial s = p1.add(p2);
//		System.out.println("s(0.1) = " + s.evaluate(0.1));
//		if(s.hasRoot(1))
//		System.out.println("1 is a root of s");
//		else
//		System.out.println("1 is not a root of s");
//	}
		
