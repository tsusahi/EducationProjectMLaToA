package org.example;

import java.util.Scanner;

public class SyntaxAnalyzer {
    private String initialExpression;
    private int currentIndex;
    private final String wrongExpression = "Неправильная запись";

    public void readExpression() {
        System.out.print("""
                Правильная скобочная запись арифметических выражений с двумя видами скобок.
                Скобки одного вида не могут стоять рядом.
                Не должно быть “лишних” скобок, и одна буква не может браться в скобки.
                                
                Пример:
                Правильная запись: [((a+b)*[d-d])+d]/((c-d)/[a-(b+c-d)])*[(a-b)/[[c+d]*(a+d)]-a]
                Неправильная запись: [(a+b)*([a]/([c+a*b]/((a-b)))]
                
                Пожалуйста, введите выражение:
                """);
        Scanner scanner = new Scanner(System.in);
        initialExpression = scanner.nextLine();
    }
    public void syntaxAnalysis() throws valueError {
        if (language()) {
            System.out.print("Правильная запись");
        } else throw new valueError(wrongExpression);
    }
    private boolean language() throws valueError {
        if (Character.isLetter(initialExpression.charAt(currentIndex)) ||
                initialExpression.charAt(currentIndex) == '[' ||
                initialExpression.charAt(currentIndex) == '(') {
            read();
            expression();
            return true;
        } else return false;
    }
    private void expression() throws valueError {
        if (Character.isLetter(initialExpression.charAt(currentIndex))) {
            letter();
            sign();
            setOfOperands3();
        } else if (initialExpression.charAt(currentIndex) == '[') {
            theSquareBrackets();
        } else if (initialExpression.charAt(currentIndex) == '(') {
            theParentheses();
        } else throw new valueError("Пустое выражение");
    }
    private void afterTheParentheses() throws valueError {
        if (isSign(initialExpression.charAt(currentIndex))) {
            read();
            setOfOperands1();
        }
    }
    private void afterTheSquareBrackets() throws valueError {
        if (isSign(initialExpression.charAt(currentIndex))) {
            read();
            setOfOperands2();
        }
    }
    private void setOfOperands1() throws valueError {
        if (initialExpression.charAt(currentIndex) == '[') {
            theSquareBrackets();
        } else if (Character.isLetter(initialExpression.charAt(currentIndex))) {
            read();
            setOfOperands4();
        } else throw new valueError(wrongExpression);
    }
    private void setOfOperands2() throws valueError {
        if (initialExpression.charAt(currentIndex) == '(') {
            theParentheses();
        } else if (Character.isLetter(initialExpression.charAt(currentIndex))) {
            read();
            setOfOperands4();
        } else throw new valueError(wrongExpression);
    }
    private void setOfOperands3() throws valueError {
        if (initialExpression.charAt(currentIndex) == '(') {
            theParentheses();
        } else if (initialExpression.charAt(currentIndex) == '[') {
            theSquareBrackets();
        } else if (Character.isLetter(initialExpression.charAt(currentIndex))) {
            read();
            setOfOperands4();
        } else throw new valueError(wrongExpression);
    }
    private void setOfOperands4() throws valueError {
        if (isSign(initialExpression.charAt(currentIndex))) {
            setOfOperands3();
        }
    }
    private void letter() throws valueError {
        if (Character.isLetter(initialExpression.charAt(currentIndex))) {
            read();
        } else throw new valueError("letter");
    }
    private void sign() throws valueError {
        if (isSign(initialExpression.charAt(currentIndex))) {
            read();
        } else throw new valueError("sign");
    }
    private void theSquareBrackets() throws valueError {
        read();
        expression();
        if (initialExpression.charAt(currentIndex) == ']') {
            read();
            afterTheSquareBrackets();
        }
    }
    private void theParentheses() throws valueError {
        read();
        expression();
        if (initialExpression.charAt(currentIndex) == ')') {
            read();
            afterTheParentheses();
        }
    }
    private boolean isSign(char symbol) {
        return symbol == '*' || symbol == '/' || symbol == '+' || symbol == '-';
    }
    private void read() {
        currentIndex ++;
    }
}

class valueError extends Exception {
    public valueError(String message) {
        super(message);
    }
}