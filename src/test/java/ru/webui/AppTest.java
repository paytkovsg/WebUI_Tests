package ru.webui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void testSum(){
        int a = 6;
        int b = 4;
        int expected = 10;
        int result = App.sum(a, b);

        assertEquals(expected, result);
    }
    @Test
    public void testDiv(){
        int a = 10;
        int b = 2;
        int expected = 5;
        int result = App.div(a, b);
        assertEquals(expected, result);
    }
    @Test
    public void testDivZero(){
        int a = 10;
        int b = 0;
        int expected = -1;
        int result = App.div(a, b);
        assertEquals(expected, result);
    }


}
