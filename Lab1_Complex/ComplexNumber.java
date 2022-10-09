package Lab1_Complex;

public class ComplexNumber {
    public float re;
    public float im;

    public ComplexNumber(float re, float im) {
        this.re=re;
        this.im=im;
    }

    public ComplexNumber(float re) { this(re,0); }

    public ComplexNumber() { this(0); }

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


    public static class InvalidOperatorSymbolException extends Throwable{
    }
}
