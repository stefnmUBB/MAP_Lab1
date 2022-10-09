package Lab1_Complex.Expressions;

import Lab1_Complex.ComplexExpression;
import Lab1_Complex.ComplexNumber;
import Lab1_Complex.Operation;

public class Addition extends ComplexExpression {
    public Addition(ComplexNumber[] args)
    {
        super(Operation.ADDITION, args);
    }

    @Override
    protected ComplexNumber executeOneOperation(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.re+b.re, a.im+b.im);
    }
}
