package com.service.batch.constant;

import java.util.HashMap;
import java.util.Map;

public class BatchMapping {

    public static Map<String, Map<String, String>> mapping = new HashMap<>();


    static {
        mapping.put("header1",
                Map.of("name", "name",
                        "address", "address",
                        "number", "number",
                        "email", "email"));
    }

}
