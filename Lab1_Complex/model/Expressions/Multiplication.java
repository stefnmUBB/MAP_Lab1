package Lab1_Complex.model.Expressions;

import Lab1_Complex.model.ComplexExpression;
import Lab1_Complex.model.ComplexNumber;
import Lab1_Complex.model.Operation;

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
