package Lab1_Complex.Expressions;

import Lab1_Complex.ComplexExpression;
import Lab1_Complex.ComplexNumber;
import Lab1_Complex.Operation;

public class Multiplication extends ComplexExpression {
    public Multiplication(ComplexNumber[] args)
    {
        super(Operation.MULTIPLICATION, args);
    }
    @Override
    protected ComplexNumber executeOneOperation(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.re*b.re-a.im*b.im, a.re*b.im+a.im*b.re);
    }
}
