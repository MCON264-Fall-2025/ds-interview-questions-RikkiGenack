package edu.touro.mcon264.s4_infix_to_postfix;

import java.util.Stack;

public class Solution {

    // Return precedence of operators
    static int prec(char c) {
        if (c == '^')
            return 3;
        else if (c == '/' || c == '*')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

    // Check if operator is right-associative
    static boolean isRightAssociative(char c) {
        return c == '^';
    }

    // Convert infix to postfix (numbers only)
    public static String infixToPostfix(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If it's a digit, add directly to output
            if (Character.isDigit(c)) {
                res.append(c);
            }
            // If '(' push to stack
            else if (c == '(') {
                st.push(c);
            }
            // If ')' pop until '('
            else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(')
                    res.append(st.pop());
                st.pop();
            }
            // If operator
            else {
                //when there's elements in the stack, that's not a parenthesis*, check if the new operator has precedence
                // if not, and it's not an exponent, otherwise pop the operator on the stack
                //(we deal with parenthesis differently- push it ont stack and then when you reach a closing one you pop the operand and opening parenthesis)
                while (!st.isEmpty() && st.peek() != '(' &&
                        (prec(st.peek()) > prec(c) ||
                                (prec(st.peek()) == prec(c) && !isRightAssociative(c)))) {
                    res.append(st.pop());
                }
                st.push(c);
            }
        }

        // Pop remaining operators
        while (!st.isEmpty())
            res.append(st.pop());

        return res.toString();
    }

    // Evaluate postfix expression- this method reads the expression from left to right.
    //numbers are pushed onto the stack.
    //When you see an operator (+, -, *, /), pop two numbers from the stack & apply operator to them
    //push result onto stack
    //at the end of expression, the top of the stack is the final answer.
    public static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c)) {
                stack.push(c - '0'); // convert char to int
            } else {
                int val2 = stack.pop();
                int val1 = stack.pop();

                switch (c) {
                    case '+': stack.push(val1 + val2); break;
                    case '-': stack.push(val1 - val2); break;
                    case '*': stack.push(val1 * val2); break;
                    case '/': stack.push(val1 / val2); break;
                    case '^': stack.push((int) Math.pow(val1, val2)); break;
                }
            }
        }
//return the result:
        return stack.pop();
    }

    public static void main(String[] args) {
        String exp1 = "3+7-9*8";
        String postfix1 = infixToPostfix(exp1);
        System.out.println("Infix: " + exp1);
        System.out.println("Operators are between operands");
        System.out.println("Postfix: " + postfix1);
        System.out.println("Operators go between last two operators");
        System.out.println("Evaluation Result: " + evaluatePostfix(postfix1));
        System.out.println("\n9*8=72\n3+7=10\n10-72=-62");
        //DO another example with the class
        //String exp2=;
        //String postfix2=infixToPostfix(exp2);
        //System.out.println("Postfix: " + postfix2);
        //System.out.println("Evaluation Result: " + evaluatePostfix(postfix2));
    }
}
//O(n) time & space because only need one stack and then the output string,
//doesn't matter how big the string is