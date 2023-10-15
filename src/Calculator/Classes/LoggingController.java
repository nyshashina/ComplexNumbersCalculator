package Calculator.Classes;

import Calculator.Classes.Core.ComplexNumber;
import Calculator.Interfaces.ICalculationModel;

import java.util.logging.Logger;

public class LoggingController extends Controller  {
    Logger log;

    public LoggingController(ICalculationModel cmodel, Logger log) {
        super(cmodel);
        this.log = log;
    }

    @Override
    public void exit() {
        log.info("Завершение работы по команде exit");
        super.exit();
    }

    @Override
    public ComplexNumber cancel() {
        try {
            return super.cancel();
        } finally {
            logInfo("Выполнен сброс");
        }
    }

    @Override
    public ComplexNumber result() {
        try {
            return super.result();
        } finally {
            logInfo("Выполнен result");
        }
    }

    @Override
    public void setLeft(ComplexNumber l) {
        try {
            super.setLeft(l);
        } finally {
            logInfo("Установлено left");
        }
        
    }

    @Override
    public void setRight(ComplexNumber r) {
        try {
            super.setRight(r);
        } finally {
            logInfo("Установлено right");
        }
    }

    @Override
    public ComplexNumber handleOp(String op) {
        try {
            return super.handleOp(op);
        } finally {
            logInfo("Установлен оператор");
        }
    }

    void logInfo(String msg) {
        log.info(String.format("%s; %s", msg, cmodel.represent()));
    }
}
