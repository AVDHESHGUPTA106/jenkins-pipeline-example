package com.mycompany.app;

import static org.junit.Assert.assertTrue;

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
        String auth0Secret = System.getProperty("auth0Secret");
        System.out.println(System.getProperty("auth0Secret"));
        assertTrue( true );
    }
}
