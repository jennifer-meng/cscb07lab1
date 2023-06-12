import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//package cscb07lab1;

public class Polynomial {
	double[] coeffcients;
	int [] exponents;
	
	public Polynomial(){
		coeffcients=new double[0];
		exponents= new int[0];
	}
	public Polynomial(File file) throws IOException {
		BufferedReader read = new BufferedReader(new FileReader(file));
		String line = read.readLine();
		read.close();
		line = line.replace("-", "+-");
        String[] items = line.split("\\+");


        this.coeffcients = new double[items.length];
        this.exponents = new int[items.length];

        String [] new_items;
        if (items[0].equals("")) {
            new_items = new String[items.length - 1];
            for (int i = 1; i < items.length; i++)
                new_items[i-1] = items[i];
        }
        else {
            new_items = new String[items.length - 1];
            for (int i = 0; i < items.length; i++)
                new_items[i] = items[i];
        }


        for (int i = 0; i < new_items.length; i++) {
            String[] item = new_items[i].split("x");

            this.coeffcients[i] = Double.parseDouble(item[0]);
            if(item.length==1) {
            	this.exponents[i] = 0;
        }
            else {
            	this.exponents[i]=Integer.parseInt(item[1]);
            }
        }
	}
	public Polynomial(double[] coeffcients, int[] exponents) {
        int n = exponents.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (exponents[j] > exponents[j + 1]) {
                    // Swap exponents[j] and exponents[j+1]
                    int temp_exponent = exponents[j];
                    exponents[j] = exponents[j + 1];
                    exponents[j + 1] = temp_exponent;

                    // Swap coefficients[j] and coefficients[j+1]
                    double temp_coefficient = coeffcients[j];
                    coeffcients[j] = coeffcients[j + 1];
                    coeffcients[j + 1] = temp_coefficient;
                }
            }
        }

        this.coeffcients = coeffcients;
        this.exponents = exponents;
    }

	public double evaluate(double x) {
		double result =0.0;
		for(int i=0; i<this.coeffcients.length;i++)
			result +=this.coeffcients[i]*Math.pow(x, this.exponents[i]);
		return result;
	}
	
	public Polynomial add(Polynomial num){
		if (this.exponents.length == 0)
            return new Polynomial(num.coeffcients, num.exponents);
		
        if (num.exponents.length == 0)
            return new Polynomial(this.coeffcients, this.exponents);
        int max_e = Math.max(this.exponents[this.exponents.length - 1],
                               num.exponents[num.exponents.length - 1]);
        double[] exp_c = new double[max_e + 1];
        
        for (int i = 0; i < this.coeffcients.length; i++)
            exp_c[this.exponents[i]] += this.coeffcients[i];

        for (int i = 0; i < num.coeffcients.length; i++)
            exp_c[num.exponents[i]] += num.coeffcients[i];

        int len = 0;
        for (int i = 0; i < exp_c.length; i++)
            if (exp_c[i] != 0)
                len++;

        double[] new_coe = new double[len];
        int[] new_exp = new int[len];

        int j = 0;
        for (int i = 0; i < exp_c.length; i++) {
            if (exp_c[i] != 0) {
                new_coe[j] = exp_c[i];
                new_exp[j] = i;
                j++;
            }
        }
        return new Polynomial(new_coe, new_exp);
//		double[] coef;
//		int len1 = this.coeffcients.length;
//		int len2 = num.coeffcients.length;
//		if(len1>len2)
//			coef = new double[len1];
//		else
//			coef = new double[len2];
//		
//		for(int i=0; i<len1;i++)
//			coef[i]=this.coeffcients[i];
//		
//		for(int i=0;i<len2;i++)
//			coef[i]+=num.coeffcients[i];
//		return new Polynomial(coef,);
	}
	public Polynomial multiply(Polynomial num) {
		double[] coe = new double[this.coeffcients.length * num.coeffcients.length];
        int[] exp = new int[this.coeffcients.length * num.coeffcients.length];
        int item = 0;

        for (int i = 0; i < this.coeffcients.length; i++) {
            for (int j = 0; j < num.coeffcients.length; j++) {
                coe[item] = this.coeffcients[i] * num.coeffcients[j];
                exp[item] = this.exponents[i] + num.exponents[j];
                item++;
            }
        }
        double[] new_coefs = new double[item];
        int[] new_exps = new int[item];
        for (int i=0;i<item;i++)
        {
            new_coefs[i]=coe[i];
            new_exps[i] = exp[i];
        }


        return new Polynomial(new_coefs, new_exps).simplify();
		
	}
	public Polynomial simplify() {
        double[] coefs = new double[this.coeffcients.length ];
        int[] exps = new int[this.coeffcients.length ];


        for (int i = 0; i < this.coeffcients.length; i++) {
            double coef = 0.0;
            int exp = this.exponents[i];

            for (int j = i; j < this.coeffcients.length; j++) {
                if (this.exponents[j] == exp) {
                    coef += this.coeffcients[j];
                    i = j;
                }
            }
            coefs[i] = coef;
            exps[i] = exp;
        }
        
        int valid_len=0;
        for (int i = 0; i < this.coeffcients.length; i++) {
            if (coefs[i] !=0.0)
                valid_len ++;
        }
        double[] coe = new double[valid_len];
        int[] exp = new int[valid_len];
        int k=0;
        for (int i = 0; i < this.coeffcients.length; i++) {
            if (coefs[i] !=0.0) {
                coe[k] = coefs[i];
                exp[k] = exps[i];
                k++;
            }

        }

        return new Polynomial(coe, exp);
	}
	
	public void saveToFile(String filename) throws IOException {
        FileWriter write = new FileWriter(filename);

        String line = "";

        for (int i = 0; i < this.coeffcients.length; i++) {

            line += this.coeffcients[i] + "x" + this.exponents[i] + "+";
        }
        line = line
                .replace("+-", "-")     //
                .replace("x0", "")      //
                .replace(".0", "")      //
                .replace("+end", "");  // to remove the last '+' sign


//        line += "end";
//        line = line.replace("+-", "-"); 
//        line = line.replace("x0", "");         
//        line = line.replace(".0", "");
//        line = line.replace("+end", "");
        write.write(line);
        write.close();
    }
	public boolean hasRoot(int i) {
		return evaluate(i) ==0;
	}
	public String toString() {
        String result = "";
        for (int i = 0; i < this.coeffcients.length; i++) {
            result += this.coeffcients[i] + "x" + this.exponents[i] + "+";
        }
        result += "end";

        return result
                .replace("+-", "-")     
                .replace("x0", "")   
                .replace(".0", "")     
                .replace("+end", "");  
    }
}
