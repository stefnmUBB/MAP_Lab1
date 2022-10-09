package Lab1_Complex.Expressions;

import Lab1_Complex.ComplexExpression;
import Lab1_Complex.ComplexNumber;
import Lab1_Complex.Operation;

public class Division extends ComplexExpression {
    public Division(ComplexNumber[] args)
    {
        super(Operation.DIVISION, args);
    }
    @Override
    protected ComplexNumber executeOneOperation(ComplexNumber a, ComplexNumber b) {
        // (a*b^)/|b|^2
        float l = b.re*b.re+b.im*b.im;
        float re = (a.re*b.re+a.im*b.im)/l;
        float im = (a.im*b.re-a.re*b.im)/l;
        return new ComplexNumber(re,im);
    }
}
