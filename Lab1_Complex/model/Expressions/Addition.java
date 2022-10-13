package Lab1_Complex.model.Expressions;

import Lab1_Complex.model.ComplexExpression;
import Lab1_Complex.model.ComplexNumber;
import Lab1_Complex.model.Operation;

public class Addition extends ComplexExpression {
    public Addition(ComplexNumber[] args)
    {
        super(Operation.ADDITION, args);
    }

    @Override
    protected ComplexNumber executeOneOperation(ComplexNumber a, ComplexNumber b) {
        return a.add(b);
    }
}
