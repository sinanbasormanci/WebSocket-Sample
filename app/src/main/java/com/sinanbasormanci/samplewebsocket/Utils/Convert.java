package com.sinanbasormanci.samplewebsocket.Utils;

import com.sinanbasormanci.samplewebsocket.Model.MItem;

import java.text.DecimalFormat;

/**
 * Created by sinanbasormanci.com on 2019-09-21.
 */
public class Convert {

    /** String **/
    public static String toString(int value){
        return String.valueOf(value);
    }

    public static String toString(double value){
        return String.valueOf(value);
    }

    public static String toString(float value){
        return String.valueOf(value);
    }


    /** Integer **/
    public static int toInteger(String value){
        return Integer.parseInt(value);
    }

    public static int toInteger(double value){
        return (int) value;
    }

    public static int toInteger(float value){
        return (int) value;
    }


    /** Double **/
    public static double toDouble(String value){
        if (value.contains(",")){
            return Double.parseDouble(value.replace(",","."));
        }
        return Double.parseDouble(value);
    }

    public static double toDouble(int value){
        return (double) value;
    }

    public static double toDouble(float value){
        return (double) value;
    }


    /** Float **/
    public static float toFloat(String value){
        return Float.parseFloat(value);
    }

    public static float toFloat(int value){
        return (float) value;
    }

    public static float toFloat(double value){
        return (float) value;
    }

    /** Money **/
    public static String toMoney(Double money, String type){
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(money) + " " + type;
    }


    /** MODELS **/
    public static MItem toMItem(String value){
        MItem item = new MItem();
        if (value.contains("-") && value.split("-").length == 2){
            String[] separated =  value.split("-");
            try {
                if (separated[0] != null) item.setId(Convert.toInteger(separated[0])); else return null;
                if (separated[1] != null) item.setName(separated[1]); else return null;
                return item;
            }catch (Exception e){
                return null;
            }
        }else {
            return null;
        }
    }

}
