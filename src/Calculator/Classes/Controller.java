package Calculator.Classes;

import Calculator.Classes.Core.ComplexNumber;
import Calculator.Interfaces.ICalculationModel;
import Calculator.Interfaces.ICalculatorController;

public class Controller implements ICalculatorController {
    ICalculationModel cmodel;

    public Controller(ICalculationModel cmodel) {
        this.cmodel = cmodel;
        // TODO + логгер
    }
    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public ComplexNumber cancel() {
        return cmodel.cancel();
    }

    @Override
    public ComplexNumber result() {
        return cmodel.result();
    }

    @Override
    public void setLeft(ComplexNumber l) {
        cmodel.setLeft(l);
    }

    @Override
    public void setRight(ComplexNumber r) {
        cmodel.setRight(r);
    }

    @Override
    public ComplexNumber handleOp(String op) {
        return cmodel.handleOp(op);
    }

    @Override
    public String getSecondaryString() {
        return cmodel.toString();
    }

    @Override
    public String getOp() {
        return cmodel.getOp();
    }
}
