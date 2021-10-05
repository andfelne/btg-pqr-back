package com.bgt.pqr.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateUtilTest {

    @Test
    public void testGetCurrentDate() {
        System.out.println(DateUtil.getCurrentDate());
    }

}