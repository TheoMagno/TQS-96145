package tqs_96145;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TqsStackTest {
    TqsStack<String> wordStack;
    @BeforeEach
    void init() {
        wordStack = new TqsStack<String>();
    }

    @DisplayName("A stack is empty on construction")
    @Test
    void isEmpty() {
        assertTrue(wordStack.isEmpty());
    }

    @DisplayName("A stack has size 0 on construcion")
    @Test
    void size() {
        assertEquals(0, wordStack.size());
    }

    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void push_n() {
        wordStack.push("Alpha");
        assertEquals(1, wordStack.size());

        wordStack.push("Bravo");
        wordStack.push("Charlie");
        assertEquals(3, wordStack.size());
    }

    @DisplayName("If one pushes x then pops, the value popped is x")
    @Test
    void push_then_pop() {
        wordStack.push("Alpha");
        assertEquals("Alpha", wordStack.pop());
    }

    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    void push_then_peek() {
        wordStack.push("Alpha");
        assertEquals(1, wordStack.size());
        Assertions.assertAll(
            () -> assertEquals("Alpha", wordStack.peek()),
            () -> assertEquals(1, wordStack.size())
        );
    }

    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void pop_n() {
        wordStack.push("Alpha");
        wordStack.push("Bravo");
        wordStack.push("Charlie");
        assertEquals(3, wordStack.size());
        for (int i=0; i<3; i++) {
            wordStack.pop();
        }
        Assertions.assertAll(
            () -> assertTrue(wordStack.isEmpty()),
            () -> assertEquals(0, wordStack.size())
        );
    }

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void pop_empty() {
        Assertions.assertThrows( NoSuchElementException.class, () -> wordStack.pop());
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void peek_empty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> wordStack.peek());
    }

    @DisplayName("For bounded stacks only:pushing onto a full stack does throw an IllegalStateException")
    @Test
    void bounded_stack() {
        wordStack.bound_stack(3);
        wordStack.push("Alpha");
        wordStack.push("Bravo");
        wordStack.push("Charlie");
        Assertions.assertThrows(IllegalStateException.class, () -> wordStack.push("Delta"));
    }
}