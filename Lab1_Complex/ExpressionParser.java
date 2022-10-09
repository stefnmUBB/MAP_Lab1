package Lab1_Complex;

import Lab1_Complex.Expressions.Addition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ExpressionParser {
    public String[] elements;

    public ExpressionParser(String[] elems)
    {
        this.elements = elems;
    }

    private boolean isPlusSign(String token) {
        return Objects.equals(token, "+");
    }

    private boolean isMinusSign(String token) {
        return Objects.equals(token, "-");
    }

    private boolean isSign(String token) {
        return isPlusSign(token) || isMinusSign(token);
    }

    private int getSign(String token) {
        return isPlusSign(token) ? +1 : -1;
    }

    private boolean isNumber(String token)
    {
        for(int i=0;i<token.length();i++){
            if(!Character.isDigit(token.charAt(i)))
                return false;
        }
        return true;
    }

    private boolean isI(String token) {
        return Objects.equals(token, "i");
    }

    private boolean isMul(String token) {
        return Objects.equals(token, "*");
    }

    private ComplexNumber parseComplex(String str) throws InvalidTokenException {
        int re=0;
        int im=0;

        // pad operators with spaces
        // 1+3*i => 1 + 3*i
        str = str.replace("+"," + ");
        str = str.replace("-"," - ");
        str = str.replace("*"," * ");

        String[] tokens = str.split(" ");

        // remove empty strings resulted from split
        int len = tokens.length;
        for(int i=0;i<tokens.length;i++){
            if(tokens[i].length()==0){
                tokens[i]=null;
                len--;
            }
        }
        String[] tmp_tokens = new String[len];
        int k=0;
        for (String token : tokens) {
            if (token != null) {
                tmp_tokens[k++] = token;
            }
        }
        tokens=tmp_tokens;

        //System.out.println(String.join(", ",tokens));

        //System.out.println(tokens.length);

        // Try to detect a complex number pattern
        if(tokens.length==1) {
            if(isNumber(tokens[0])) {
                // 5
                re = Integer.parseInt(tokens[0]);
            }
            else if(isI(tokens[0]))
            {
                // i
                im = 1;
            }
            else throw new InvalidTokenException();
        }
        else if(tokens.length==2){
            if(isSign(tokens[0]) && isNumber(tokens[1])) {
                // - 3
                re = getSign(tokens[0])*Integer.parseInt(tokens[1]);
            }
            else if(isSign(tokens[0]) && isI(tokens[1]))
            {
                // - i
                im = getSign(tokens[0]);
            }
            else throw new InvalidTokenException();
        }
        else if(tokens.length==3){
            if(isNumber(tokens[0]) && isSign(tokens[1]) && isI(tokens[2])) {
                // 2 + i
                re = Integer.parseInt(tokens[0]);
                im = getSign(tokens[1]);
            }
            if(isNumber(tokens[0]) && isMul(tokens[1]) && isI(tokens[2])) {
                // 6 * i
                re = 0;
                im = Integer.parseInt(tokens[0]);
            }
            else throw new InvalidTokenException();
        }
        else if(tokens.length==4) {
            /*System.out.println("Sign = "+isSign(tokens[0]));
            System.out.println("Number = "+isNumber(tokens[1]));
            System.out.println("Sign = "+isSign(tokens[2]));
            System.out.println("I = "+isI(tokens[3]));*/
            if(isSign(tokens[0]) && isNumber(tokens[1]) && isMul(tokens[2]) && isI(tokens[3])) {
                // + 7 * i
                re = 0;
                im = getSign(tokens[0]) * Integer.parseInt(tokens[1]);
            }
            else if(isSign(tokens[0]) && isNumber(tokens[1]) && isSign(tokens[2]) && isI(tokens[3])) {
                // - 5 - i
                re = getSign(tokens[0]) * Integer.parseInt(tokens[1]);
                im = getSign(tokens[2]);
            }
            else throw new InvalidTokenException();
        }
        else if(tokens.length==5) {
            if(isNumber(tokens[0]) && isSign(tokens[1]) && isNumber(tokens[2]) &&
                isMul(tokens[3]) && isI(tokens[4])) {
                // 10 + 4 * i
                re = Integer.parseInt(tokens[0]);
                im = getSign(tokens[1]) * Integer.parseInt(tokens[2]);
            }
            else throw new InvalidTokenException();
        }
        else if(tokens.length==6) {
            if(isSign(tokens[0]) && isNumber(tokens[1]) && isSign(tokens[2]) && isNumber(tokens[3]) &&
                    isMul(tokens[4]) && isI(tokens[5])) {
                // - 10 + 4 * i
                re = getSign(tokens[0]) * Integer.parseInt(tokens[1]);
                im = getSign(tokens[2]) * Integer.parseInt(tokens[3]);
            }
            else throw new InvalidTokenException();
        }

        return new ComplexNumber(re, im);
    }

    private Operation parseOperation() throws OperatorException, InvalidOperatorSymbolException {
        String op = null;
        for(int i=1;i<elements.length;i+=2) {
            if(op==null) {
                op=elements[i];
                continue;
            }
            if(!Objects.equals(elements[i], op)) {
                throw new OperatorException();
            }
        }

        if(op.length()!=1) throw new InvalidOperatorSymbolException();
        switch(op.charAt(0))
        {
            case '+': return Operation.ADDITION;
            case '-': return Operation.SUBTRACTION;
            case '*': return Operation.MULTIPLICATION;
            case '/': return Operation.DIVISION;
            default: throw new InvalidOperatorSymbolException();
        }
    }


    public ComplexExpression parse() throws InvalidTokenException, InvalidTokenCountException, OperatorException, InvalidOperatorSymbolException {
        if(elements.length==0) {
            ComplexNumber[] dummy = new ComplexNumber[1];
            dummy[0] = new ComplexNumber(0);
            return new Addition(dummy);
        }
        if(elements.length==1) {
            ComplexNumber[] dummy = new ComplexNumber[1];
            dummy[0] = parseComplex(elements[0]);
            return new Addition(dummy);
        }

        if(elements.length%2==0){
            throw new InvalidTokenCountException();
        }
        var operation = parseOperation();

        ComplexNumber[] list = new ComplexNumber[elements.length/2+1];
        for(int i=0;i<elements.length;i+=2)
        {
            //System.out.println(elements[i]);
            list[i/2] = parseComplex(elements[i]);
            //System.out.println(list[i/2]);
        }
        return ExpressionFactory.getInstance().createExpression(operation, list);
    }
}
