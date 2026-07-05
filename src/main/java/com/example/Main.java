package com.example;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Searcher searcher = new Searcher();
        searcher.searchWord(null, null);
    }
}