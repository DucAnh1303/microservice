package com.service.batch.constant;

import java.util.*;

public class BatchConstant {

    private static Map<String, List<String>> partnerColumnsMap = new HashMap<>();

    static {
        partnerColumnsMap.put("header1", List.of("name", "email", "address", "number"));

    }

    public static Map<String, List<String>> getList() {
        return partnerColumnsMap;
    }
}
