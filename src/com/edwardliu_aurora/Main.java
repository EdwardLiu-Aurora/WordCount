package com.edwardliu_aurora;

import bean.LineCount;
import service.BasicStatistic;
import service.ExtraStatistic;
import service.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        BasicStatistic bs = new BasicStatistic();
        //bs.getCharCount("D:\\作业\\软件工程-18-09-05\\WordCount\\设计文档.md");
        ExtraStatistic es = new ExtraStatistic();
        LineCount lc = es.getDetailLineCount("D:\\作业\\软件工程-18-09-05\\WordCount\\code.txt");
        System.out.println("空白行：" + lc.getBlankLineCount());
        System.out.println("代码行：" + lc.getCodeLineCount());
        System.out.println("注释行：" + lc.getCommentLineCount());
        String filePattern = "Char*.?ava";
        filePattern = filePattern.
                replaceAll("\\?","[^/\\\\:*?<>|]").
                replaceAll("\\*","[^/\\\\:*?<>|]*").
                replaceAll("\\.","\\\\.");
        for(String path: Utils.getFilesPath("D:\\作业\\软件工程-18-09-05\\",filePattern)){
            System.out.println(path);
        }
    }
}
