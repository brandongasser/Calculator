package symbols;

import java.math.BigDecimal;

public class OperandSymbol implements MathSymbol {

    private final BigDecimal num;

    public OperandSymbol(String num) {
        this(new BigDecimal(num));
    }

    public OperandSymbol(double num) {
        this(new BigDecimal(num));
    }

    public OperandSymbol(BigDecimal num) {
        this.num = num;
    }
    
    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public BigDecimal getValue() {
        return num;
    }

    @Override
    public String toString() {
        return num.toString();
    }

    /**
     * do not use this method. it has to be here to allow for operators and operands to be in the same list
     */
    @Override
    public Operator getOperator() {
        return null;
    }

}
