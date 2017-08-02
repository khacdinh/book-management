/**
 * 
 */
package com.bookmanagement.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Dino
 */
public enum Example {
	 MALAYSIA("MALAYSIA", "MY");

    private String countryName;
    private String value;

    private Example(String countryName, String value) {
        this.countryName = countryName;
        this.value = value;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Example fromStatus(String status) {
        try {
            return Example.valueOf(status);
        } catch (Exception e) {
            return null;
        }
    }

    private static final Map<String, Example> directoryValue;

    static {
        directoryValue = new LinkedHashMap<String, Example>();
        for (Example em : Example.values()) {
            directoryValue.put(em.getValue(), em);
        }
    }

    public static CountriesEnum fromCountryValue(String value) {
        return directoryValue.get(value);
    }
}
