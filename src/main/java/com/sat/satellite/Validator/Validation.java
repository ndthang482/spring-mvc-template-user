package com.sat.satellite.Validator;
//Author: Doan Duc Tin

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Validation {

    public static void validateString(String a) throws Exception {
        validateEmpty(a);
        if (a.contains("<") || a.contains(">") || a.contains("\"") || a.contains("'") || a.contains("`") || a.contains("|")) {
            throw new Exception("Bạn không được nhập các ký tự [\",\"]|[',']|[<,>]|[|]");
        }
    }

    public static void validateEmpty(String a) throws Exception {
        if (a == null || a.equals("")) {
            throw new Exception("Không được để trống các trường");
        }
    }

}
