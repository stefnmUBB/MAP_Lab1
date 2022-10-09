package Lab1_Complex;

import Lab1_Complex.Expressions.Addition;
import Lab1_Complex.Expressions.Division;
import Lab1_Complex.Expressions.Multiplication;
import Lab1_Complex.Expressions.Subtraction;

public class ExpressionFactory {
    private static ExpressionFactory instance = new ExpressionFactory();

    private ExpressionFactory() {}

    public static ExpressionFactory getInstance() { return instance; }

    public ComplexExpression createExpression(Operation op, ComplexNumber[] args)
    {
        switch(op)
        {
            case ADDITION: return new Addition(args);
            case SUBTRACTION: return new Subtraction(args);
            case MULTIPLICATION: return new Multiplication(args);
            case DIVISION: return new Division(args);
            default: return null;
        }
    }
}
