package com.mnubo.java.sdk.client.models;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mnubo.java.sdk.client.models.result.Result;
import com.mnubo.java.sdk.client.models.result.Result.ResultStates;

public class ResultTest {

    @Test
    public void whenCreatingResult_assertValueOK() {
        Result sut = new Result("idTest", ResultStates.success, "message Test", false);
        assertTrue(sut.getId().equals("idTest"));
        assertTrue(sut.getResult().equals(ResultStates.success));
        assertTrue(sut.getMessage().equals("message Test"));
    }
}
