package Calculator.Interfaces;

import Calculator.Classes.Core.ComplexNumber;

public interface ICalculatorController {
    void exit();
    ComplexNumber cancel();
    ComplexNumber result();
    void setLeft(ComplexNumber l);
    void setRight(ComplexNumber r);
    ComplexNumber handleOp(String op);
    String getSecondaryString();
    String getOp();



}
