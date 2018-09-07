package com.edwardliu_aurora;

import service.BasicStatistic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        BasicStatistic bs = new BasicStatistic();
        //bs.getCharCount("D:\\作业\\软件工程-18-09-05\\WordCount\\设计文档.md");
        System.out.println(bs.getLineCount("D:\\作业\\软件工程-18-09-05\\WordCount\\src\\bean\\CharCount.java"));
    }
}
