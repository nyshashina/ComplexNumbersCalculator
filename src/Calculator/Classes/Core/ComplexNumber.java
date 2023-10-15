package Calculator.Classes.Core;

import static java.lang.Double.parseDouble;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumber {
    private double real = 0;
    private double imaginary = 0;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public String toString() {
        String res = "";
        String sr;
        String si;
        NumberFormat nf = DecimalFormat.getInstance();
        nf.setMaximumFractionDigits(100);
        nf.setGroupingUsed(false);
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        ((DecimalFormat) nf).setDecimalFormatSymbols(dfs);
        if (this.getReal() != 0) {
            if (this.getImaginary() < 0) {
                sr = nf.format(this.getReal());
                si = nf.format(this.getImaginary());
                res = String.format("%s%si", sr, si);
            } else if (this.getImaginary() > 0) {
                sr = nf.format(this.getReal());
                si = nf.format(this.getImaginary());
                res = String.format("%s+%si", sr, si);
            } else {
                sr = nf.format(this.getReal());
                res = String.format("%s", sr);
            }
        } else if (this.getReal() == 0) {
            if (this.getImaginary() != 0) {
                si = nf.format(this.getImaginary());
                res = String.format("%si", si);
            } else {
                res = "0";
            }
        }
        return res;
    }

    public static ComplexNumber fromString(String s) {
        String sr = "";
        String si = "";
        double real = 0;
        double imaginary = 0;
        s = s.replace("[\t\n ]", "");
        s = s.replace(",", ".");
        if (s.isEmpty()) {
            throw new IllegalArgumentException("Комплексное число введено неверно");
        }
        if (s.contains("i")) {
            if (s.indexOf("i") != s.lastIndexOf("i")) {
                throw new IllegalArgumentException(String.format("Комплексное число введено неверно: %s", s));
            }
        }
        // Если строка содержит что-то кроме +, -, ., 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, i - исключение
        Pattern r = Pattern.compile(
                "^(([+-]?i)|(([+-]?(0|[1-9][0-9]*)(\\.[0-9]+)?)([+-]((0|[1-9][0-9]*)(\\.[0-9]+)?)?)?(i)?))$");
        int singleIGroup = 2;
        int firstNumberGroup = 4;
        int secondNumberGroup = 7;
        int trailingIGroup = 11;
        Matcher m = r.matcher(s);

        if (!m.find() || (m.group(secondNumberGroup) != null && m.group(trailingIGroup) == null)) {
            throw new IllegalArgumentException(String.format("Комплексное число введено неверно: %s", s));
        }

        if (m.group(singleIGroup) != null) {
            if (m.group(singleIGroup).equals("i") || m.group(singleIGroup).equals("+i")) {
                imaginary = 1;
            } else {
                imaginary = -1;
            }
        } else if (m.group(firstNumberGroup) != null && m.group(secondNumberGroup) == null) {
            if (m.group(trailingIGroup) == null) {
                real = parseDouble(m.group(firstNumberGroup));
            } else {
                imaginary = parseDouble(m.group(firstNumberGroup));
            }
        } else {
            if (m.group(secondNumberGroup).equals("-")) {
                imaginary = -1;
            } else if (m.group(secondNumberGroup).equals("+")) {
                imaginary = 1;
            } else {
                imaginary = parseDouble(m.group(secondNumberGroup));
            }
            real = parseDouble(m.group(firstNumberGroup));
        }

        return new ComplexNumber(real, imaginary);
    }

    public ComplexNumber add(ComplexNumber l) {
        return new ComplexNumber(this.getReal() + l.getReal(), this.getImaginary() + l.getImaginary());
    }

    public ComplexNumber sub(ComplexNumber l) {
        return new ComplexNumber(this.getReal() - l.getReal(), this.getImaginary() - l.getImaginary());
    }

    public ComplexNumber mult(ComplexNumber l) {
        double a = this.getReal();
        double b = this.getImaginary();
        double c = l.getReal();
        double d = l.getImaginary();
        double real = a * c - b * d;
        double imaginary = b * c + a * d;
        return new ComplexNumber(real, imaginary);
    }

    public ComplexNumber div(ComplexNumber l) {
        double a = this.getReal();
        double b = this.getImaginary();
        double c = l.getReal();
        double d = l.getImaginary();
        if (c == 0 && d == 0) {
            throw new ArithmeticException("На ноль делить нельзя!");
        }
        double real = (a * c + b * d) / (c * c + d * d);
        double imaginary = (b * c - a * d) / (c * c + d * d);
        return new ComplexNumber(real, imaginary);
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

}
