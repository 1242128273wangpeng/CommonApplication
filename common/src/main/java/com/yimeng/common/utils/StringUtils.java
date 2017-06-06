package com.yimeng.common.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wang2014 on 2017/5/2 0002.
 */

public class StringUtils {
    public static String jsonFormat(String bodyString) {
        String message;
        try {
            if (bodyString.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(bodyString);
                message = jsonObject.toString(4);
            } else if (bodyString.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(bodyString);
                message = jsonArray.toString(4);
            } else {
                message = bodyString;
            }
        } catch (JSONException e) {
            message = bodyString;
        }
        return message;
    }
}
