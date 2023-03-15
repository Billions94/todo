package com.api.todo.Util;

public class Util {
    protected String concatStrings(String inputString, String variable) {
        return inputString.replace("id", variable);
    }
}
