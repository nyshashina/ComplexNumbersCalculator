package Calculator;

import Calculator.Classes.Controller;
import Calculator.Classes.CalculationModel;
import Calculator.Classes.LoggingController;
import Calculator.Classes.TextCalculatorView;
import Calculator.Interfaces.ICalculationModel;
import Calculator.Interfaces.ICalculatorController;
import Calculator.Interfaces.ICalculatorView;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        ICalculationModel calc = new CalculationModel();
        ICalculatorView tcv = new TextCalculatorView();
        Logger logger = makeLogger("CalculatorLog.txt", true);
        ICalculatorController controller = new LoggingController(calc, logger);
        tcv.setController(controller);
        logger.info("Калькулятор запущен");
        tcv.run();

    }

    static Logger makeLogger(String logPath, boolean append) {
        Logger logger = Logger.getLogger("Calculator");
        try {
            FileHandler fh = new FileHandler(logPath, append);
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            return logger;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
