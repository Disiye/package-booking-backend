package com.oocl.packagebooking.controller;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PackageBoyControllerTest {
    @Test
    public void should_get_hello_world_when_getHelloWorld() {
        // GIVEN
        PackageBoyController packageBoyController = new PackageBoyController();

        // WHEN
        String helloWorld = packageBoyController.getHelloWorld();

        // THEN
        Assertions.assertEquals("Hello world", helloWorld);
    }
}