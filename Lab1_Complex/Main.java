package Lab1_Complex;

import Lab1_Complex.model.ComplexExpression;
import Lab1_Complex.model.ComplexNumber;
import Lab1_Complex.parser.ExpressionParser;

public class Main {
    public static void main(String[] args)
    {
        ComplexNumber a = new ComplexNumber(1,2);
        ComplexNumber b = new ComplexNumber(1,2);

        for(String arg : args){
            System.out.println(arg);
        }
        System.out.println(String.join(" ",args));
        ExpressionParser parser = new ExpressionParser(args);
        try {
            ComplexExpression expression = parser.parse();

            ComplexNumber result = expression.execute();
            System.out.println(result);
        }
        catch (Exception e){
            throw new RuntimeException();
            //System.out.println("General exception");
        }
        catch (Lab1_Complex.InvalidTokenException e) {
            System.out.println("Could not parse token");
        } catch (Lab1_Complex.InvalidTokenCountException e) {
            System.out.println("Invalid tokens count");
        } catch (Lab1_Complex.OperatorException e) {
            System.out.println("Multiple operators detected");
        } catch (Lab1_Complex.InvalidOperatorSymbolException e) {
            System.out.println("Invalid operator symbol");
        }
    }
}
