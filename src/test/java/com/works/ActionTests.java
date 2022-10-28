package com.works;

import com.works.utils.Action;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActionTests {

    int i = 0 ;
    Action action = null;

    // sınıf çalıştığında 1 kez çalışır.
    @BeforeAll
    public void beforeAll() {
        action = new Action();
        System.out.println("BeforeAll Call");
    }

    // her bir test methodu için bir kez daha çalışır.
    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach Call :" + i);
    }


    @Test
    public void sumTest() {
        i++;
        int sm = action.sum(40,60);
        Assertions.assertEquals(sm, 100);
    }

    @Test
    public void oneTest() {
        i++;
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach Call");
    }


    @AfterAll
    public void afterAll() {
        System.out.println("AfterAll Call");
        action = null;
    }


}
