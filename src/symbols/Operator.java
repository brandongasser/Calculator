package symbols;

public enum Operator {
    
    ADD(0),
    SUBTRACT(0),
    MULTIPLY(1),
    DIVIDE(1),
    MODULUS(1),
    EXPONENT(2),
    OPEN_PAREN(-1),
    CLOSE_PAREN(-1);

    private int precedence;

    private Operator(int precedence) {
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return precedence;
    }

}
