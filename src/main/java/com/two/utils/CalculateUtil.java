package com.two.utils;

import java.math.BigDecimal;

public class CalculateUtil {

    public static BigDecimal add(Double d1,Double d2){

        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));

        return bigDecimal.add(bigDecimal1);
    }
    public static BigDecimal add(BigDecimal d1,BigDecimal d2){

        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));

        return bigDecimal.add(bigDecimal1);
    }

    public static BigDecimal subtract(Double d1,Double d2){

        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));

        return bigDecimal.subtract(bigDecimal1);
    }

    public static BigDecimal multiply(Double d1,Double d2){

        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));

        return bigDecimal.multiply(bigDecimal1);
    }
    public static BigDecimal multiply(BigDecimal d1,Integer d2){

        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));

        return bigDecimal.multiply(bigDecimal1);
    }

    public static BigDecimal divide(Double d1,Double d2){

        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d1));
        BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(d2));

        return bigDecimal.divide(bigDecimal1,2,BigDecimal.ROUND_HALF_UP);
    }

}
