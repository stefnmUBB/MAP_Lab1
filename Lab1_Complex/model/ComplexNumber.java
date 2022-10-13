package Lab1_Complex.model;

public class ComplexNumber {
    public float re;
    public float im;

    // creates a ComplexNumber instance
    public ComplexNumber(float re, float im) {
        this.re=re;
        this.im=im;
    }

    // Creates a ComplexNumber instance from a real nunmber
    public ComplexNumber(float re) { this(re,0); }

    // Default constructor (z=0)
    public ComplexNumber() { this(0); }

    // Pretty-print
    // ComplexNumber z = new ComplexNumber(2,3);
    // System.out.println(z); // calls z.toString() => "2+3*i;
    public String toString() {
        if(im==0) return ""+re;
        if(re==0)
        {
            return im+"i";
        }
        if(im<0)
            return re+""+im+"*i";
        return re+"+"+im+"*i";
    }
}
