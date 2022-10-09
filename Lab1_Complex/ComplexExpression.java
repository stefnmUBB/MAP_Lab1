package Lab1_Complex;

public abstract class ComplexExpression {
    private Operation operation;
    private ComplexNumber[] args;

    public ComplexNumber[] getArgs(){return args;}
    public Operation getOperation() {return operation;}

    public ComplexExpression(Operation op, ComplexNumber[] args)
    {
        this.operation=op;
        this.args=args;
    }


    protected abstract ComplexNumber executeOneOperation(ComplexNumber a, ComplexNumber b);
    public ComplexNumber execute()
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
