package Calculator.Interfaces;

import Calculator.Classes.Core.ComplexNumber;

public interface ICalculationModel {
    void setLeft(ComplexNumber left);
    ComplexNumber getLeft();
    void setRight(ComplexNumber right);
    ComplexNumber getRight();
    ComplexNumber handleOp(String op);
    String toString();
    String represent();
    String getOp();
    ComplexNumber result();
    ComplexNumber cancel();
}
