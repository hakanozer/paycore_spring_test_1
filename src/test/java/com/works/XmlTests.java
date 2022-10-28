package com.works;

import com.works.props.Currency;
import com.works.services.XmlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XmlTests {

    XmlService service;
    @BeforeAll
    public void beforeAll() {
        service = new XmlService();
    }

    @Test
    public void tcmbXml() {
        List<Currency> currencies = service.xml();
        Assertions.assertNotEquals( currencies.size(), 0 );
        System.out.println( currencies.get(0).getBanknoteSelling() );
    }

    @Test
    public void testFnc() {
        int a = 10;
    }

}
