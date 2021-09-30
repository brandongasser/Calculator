package symbols;

import java.math.BigDecimal;

public interface MathSymbol {

    public boolean isOperator();

    public BigDecimal getValue();
    public Operator getOperator();

}
