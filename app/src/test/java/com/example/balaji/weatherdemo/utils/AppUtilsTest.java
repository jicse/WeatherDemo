package com.example.balaji.weatherdemo.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by balaji on 20/12/17.
 */
public class AppUtilsTest {
    @Test
    public void convertTimeStampToDate() throws Exception {
        Assert.assertEquals("19-Dec-2017 14:30",
                AppUtils.convertTimeStampToDate(1513708200));
    }
}