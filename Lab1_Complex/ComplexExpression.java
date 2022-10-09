package Lab1_Complex;

public abstract class ComplexExpression {
    private Operation operation;
    private ComplexNumber[] args;

    public ComplexNumber[] getArgs(){return args;}
    public Operation getOperation() {return operation;}

    // create ComplexExpression instance featuring an operation
    // and arguments
    public ComplexExpression(Operation op, ComplexNumber[] args)
    {
        this.operation=op;
        this.args=args;
    }


    // abstract method for custom operations
    protected abstract ComplexNumber executeOneOperation(ComplexNumber a, ComplexNumber b);

    // core expression solver, performs n1 op n2 op ... op nk
    public final ComplexNumber execute()
    {
        ComplexNumber result=null;
        for(ComplexNumber z: this.getArgs())
        {
            if(result==null)
                result=new ComplexNumber(z.re,z.im);
            else
                result = executeOneOperation(result, z);
        }
        return result;
    }
}
