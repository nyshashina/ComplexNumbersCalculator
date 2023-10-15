package Calculator.Classes;

import Calculator.Classes.Core.ComplexNumber;
import Calculator.Interfaces.ICalculatorController;
import Calculator.Interfaces.ICalculatorView;

import java.util.Scanner;

public class TextCalculatorView implements ICalculatorView {
    ICalculatorController controller;
    String result;
    String ss;


    @Override
    public void setController(ICalculatorController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        System.out.println("Калькулятор комлексных чисел принимает на вход выражение вида: ");
        System.out.println("1) N op M");
        System.out.println("2) op M");
        System.out.println("где op - арифметический оператор, а N и M - комлексные числа вида a+bi");
        Scanner scanner = new Scanner(System.in);
        result = "0";
        ss = controller.getSecondaryString();
        while (true) {
            System.out.println(ss);
            System.out.printf("%s : ", result);
            String s = scanner.nextLine();
            s = s.trim();

            String[] parts = s.split("[ \t]+");
            switch (parts.length) {
                case 1:
                    switch (parts[0]) {
                        case "=":
                        case "":
                            result = controller.result().toString();
                            ss = controller.getSecondaryString() + " =";
                            break;
                        case "c":
                        case "cancel":
                            controller.cancel();
                            result = "0";
                            ss = controller.getSecondaryString();
                            break;
                        case "exit":
                            controller.exit();
                            break;
                        default:
                            System.out.println("Ввод не распознан, повторите");
                    }
                    break;
                case 2:
                    if (isMathOp(parts[0]) && isComplexNumber(parts[1]))  {
                        try {
                            controller.handleOp(parts[0]);
                            controller.setRight(ComplexNumber.fromString(parts[1]));
                            result = controller.result().toString();
                            ss = controller.getSecondaryString() + " =";
                        } catch (ArithmeticException e) {
                            System.out.println("Ошибка");
                            controller.cancel();
                            ss = controller.getSecondaryString();
                            result = "0";
                        }
                    } else {
                        System.out.println("Ввод не распознан");
                    }
                    break;
                case 3:
                    if (isComplexNumber(parts[0]) && isMathOp(parts[1]) && isComplexNumber(parts[2])) {
                        controller.cancel();
                        try {
                            controller.setLeft(ComplexNumber.fromString(parts[0]));
                            controller.handleOp(parts[1]);
                            controller.setRight(ComplexNumber.fromString(parts[2]));
                            result = controller.result().toString();
                            ss = controller.getSecondaryString() + " =";
                        } catch (ArithmeticException e) {
                            System.out.println("Ошибка");
                            controller.cancel();
                            ss = controller.getSecondaryString();
                            result = "0";
                        }
                    }
                    break;
                default:
                    System.out.println("Ввод не распознан, повторите");
                    break;
            }
        }
    }

    boolean isMathOp(String s) {
        return s.matches("^[+*/-]$");
    }

    boolean isComplexNumber(String s) {
        try {
            ComplexNumber.fromString(s);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    boolean handleInputPart(String part) {
        switch (part) {
            case "=":
                result = controller.result().toString();
                ss = controller.getSecondaryString() + " =";
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                try {
                    result = controller.handleOp(part).toString();
                    ss = controller.getSecondaryString();
                    break;
                } catch (ArithmeticException e) {
                    return false;
                }
            case "c":
                controller.cancel();
                result = "0";
                ss = controller.getSecondaryString();
                break;
            case "exit":
                controller.exit();
                break;
            default:
                try {
                    ComplexNumber cn = ComplexNumber.fromString(part);
                    if (!controller.getOp().isEmpty()) {
                        controller.setRight(cn);
                        result = controller.result().toString();
                        ss = controller.getSecondaryString() + " =";
                    } else {
                        controller.setLeft(cn);
                        ss = controller.getSecondaryString();
                    }
                } catch (IllegalArgumentException e) {
                    return false;
                }
                break;
        }
        return true;
    }
}
