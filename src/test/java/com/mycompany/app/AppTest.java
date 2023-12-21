package com.mycompany.app;

import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */

    private static final String AWS_REGION = System.getProperty("awsRegion","XYZ");
    @Test
    public void shouldAnswerWithTrue() throws JsonProcessingException {
        // ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(System.getProperty("auth0Secret"));
        System.out.println(AWS_REGION);
//        TypeReference<TestModel> listType = new TypeReference<TestModel>() {};
//        TestModel auth0Secret1 = objectMapper.readValue(System.getProperty("auth0Secret"), listType);
//        System.out.println(auth0Secret1);
        assertTrue( true );
    }
}
