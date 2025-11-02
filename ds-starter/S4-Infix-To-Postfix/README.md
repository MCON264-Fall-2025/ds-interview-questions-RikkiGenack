# Infix to Postfix Conversion and Evaluation

## Problem Statement
Given a mathematical expression written in **infix notation** (where operators are placed between operands, like `3 + 7 - 9 * 8`), convert it into **postfix notation** (also known as Reverse Polish Notation) and then evaluate the result.

Infix expressions are easier for humans to read, but postfix expressions are easier for computers to evaluate since they eliminate the need for parentheses and operator precedence handling during computation.

Your program should:
1. Convert a valid infix expression (using digits and basic operators) into postfix form.
2. Evaluate the postfix expression to produce the correct numerical result.

---

## Assumptions
- The expression contains **single-digit integers** and the operators `+`, `-`, `*`, `/`, and `^`.
- Parentheses may be used to change precedence.
- No invalid characters or malformed expressions will be given.
- Division is integer division.
- Exponentiation (`^`) is **right-associative** (e.g., `2^3^2 = 2^(3^2)`).
- Algorithm efficiency:
    - **Time Complexity:** O(n)
    - **Space Complexity:** O(n)
- There is no way to make this algorithm O(1) in time or space, because:
    - Every character must be processed at least once (so O(n) time is unavoidable).
    - A stack must be used to hold operators or operands (so O(n) space is unavoidable).

---

## Conceptual Solution
### Step 1: Convert Infix → Postfix
We use a **stack** to manage operators and parentheses while scanning the infix expression from left to right.

1. **Operands (numbers):** Add directly to the output.
2. **Left parenthesis `(`:** Push onto the stack.
3. **Right parenthesis `)`:** Pop from the stack to the output until a matching `(` is found.
4. **Operators (`+`, `-`, `*`, `/`, `^`):**
    - While the stack is not empty and the top of the stack has higher or equal precedence,
      pop operators from the stack and add them to the output.
    - Then push the current operator onto the stack.
5. After scanning the expression, pop any remaining operators to the output.

The result is a postfix expression with no parentheses and correct operator order.

---

### Step 2: Evaluate Postfix Expression
We again use a **stack**, this time for operands:

1. Read the postfix expression from left to right.
2. **If it’s a number**, push it onto the stack.
3. **If it’s an operator:**
    - Pop the top two numbers from the stack.
    - Apply the operator.
    - Push the result back onto the stack.
4. When finished, the stack will contain one number — the final answer.

---

## Example
### Input
Infix: 3 + 7 - 9 * 8

shell
Copy code

### Step 1: Convert to Postfix
Postfix: 3 7 + 9 8 * -

shell
Copy code

### Step 2: Evaluate Postfix
3 + 7 = 10
9 * 8 = 72
10 - 72 = -62

shell
Copy code

### ✅ Output
Result: -62

yaml
Copy code

---

## Complexity Analysis
| Operation | Time Complexity | Space Complexity | Explanation |
|------------|------------------|------------------|--------------|
| Infix → Postfix Conversion | O(n) | O(n) | Each token processed once; stack and output grow with input size. |
| Postfix Evaluation | O(n) | O(n) | Each number/operator processed once; uses a stack for operands. |

It’s impossible to achieve O(1) in either time or space since both algorithms must examine every symbol and temporarily store operators or operands.

---

## Summary
This project demonstrates how **stack-based algorithms** simplify expression evaluation by separating concerns:
- Conversion step: organize operators and precedence.
- Evaluation step: compute results sequentially.

This approach guarantees **linear time** and **linear space** performance while maintaining correctness and simplicity.