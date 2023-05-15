package org.example;

public class Main {
    public static void main(String[] args) throws valueError {
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer();
        syntaxAnalyzer.readExpression();
        syntaxAnalyzer.syntaxAnalysis();
    }
}
