package Lab1_Complex;

public class Main {
    public static void main(String[] args)
    {
        ComplexNumber a = new ComplexNumber(1,2);
        ComplexNumber b = new ComplexNumber(1,2);

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
        catch (InvalidTokenException e) {
            System.out.println("Could not parse token");
        } catch (InvalidTokenCountException e) {
            System.out.println("Invalid tokens count");
        } catch (OperatorException e) {
            System.out.println("Multiple operators detected");
        } catch (InvalidOperatorSymbolException e) {
            System.out.println("Invalid operator symbol");
        }
    }
}
