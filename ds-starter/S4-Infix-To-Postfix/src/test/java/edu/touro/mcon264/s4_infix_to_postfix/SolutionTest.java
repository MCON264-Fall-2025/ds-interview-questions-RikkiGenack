package edu.touro.mcon264.s4_infix_to_postfix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    @Test
    void testInfixToPostfix_SimpleAddition() {
        String infix = "3+4";
        String expected = "34+";
        String result = Solution.infixToPostfix(infix);
        assertEquals(expected, result, "3+4 should convert to 34+");
    }

    @Test
    void testInfixToPostfix_WithParentheses() {
        String infix = "(3+7)*2";
        String expected = "37+2*";
        String result = Solution.infixToPostfix(infix);
        assertEquals(expected, result, "(3+7)*2 should convert to 37+2*");
    }

    @Test
    void testEvaluatePostfix_SimpleAddition() {
        String postfix = "34+";
        int expected = 7;
        int result = Solution.evaluatePostfix(postfix);
        assertEquals(expected, result, "34+ should evaluate to 7");
    }



    @Test
    void testEvaluatePostfix_WithParentheses() {
        String infix = "(3+7)*2";
        String postfix = Solution.infixToPostfix(infix);
        int expected = 20;
        int result = Solution.evaluatePostfix(postfix);
        assertEquals(expected, result, "(3+7)*2 should evaluate to 20");
    }

    @Test
    void testEvaluatePostfix_Exponentiation() {
        String infix = "2^3^2";
        String postfix = Solution.infixToPostfix(infix);
        int expected = 512; // 2^(3^2)
        int result = Solution.evaluatePostfix(postfix);
        assertEquals(expected, result, "2^3^2 should evaluate to 512");
    }
}