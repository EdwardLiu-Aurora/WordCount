package com.edwardliu_aurora;

import bean.CharCount;
import bean.LineCount;
import service.BasicStatistic;
import service.ExtraStatistic;
import service.Utils;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        boolean charCount = false;
        boolean wordCount = false;
        boolean lineCount = false;
        boolean directory = false;
        boolean detailLine = false;
        for(int i=0;i<args.length-1;i++){
            if(args[i].equals("-c")) charCount = true;
            else if(args[i].equals("-w")) wordCount = true;
            else if(args[i].equals("-l")) lineCount = true;
            else if(args[i].equals("-s")) directory = true;
            else if(args[i].equals("-a")) detailLine = true;
        }
        BasicStatistic basicStatistic = new BasicStatistic();
        ExtraStatistic extraStatistic = new ExtraStatistic();
        if(directory) {
            String filePattern = args[args.length-1];
            filePattern = filePattern.
                    replaceAll("\\?","[^/\\\\:*?<>|]").
                    replaceAll("\\*","[^/\\\\:*?<>|]*").
                    replaceAll("\\.","\\\\.");
            String folderPath = new File(".").getAbsolutePath();
            ArrayList<String> filePaths = Utils.getFilesPath(folderPath, filePattern);
            for(String filePath:filePaths) {
                System.out.println();
                System.out.println("文件: " + filePath);
                if(charCount) {
                    CharCount charCountRes = basicStatistic.getCharCount(filePath);
                    System.out.println("字符数: " + charCountRes.getAllCharCount() + "\t" + "中字数: " + charCountRes.getChnCharCount());
                }
                if(wordCount) System.out.println("单词数: " + basicStatistic.getWordCount(filePath));
                if(lineCount) System.out.println("总行数: " + basicStatistic.getLineCount(filePath));
                if(detailLine) {
                    LineCount lineCountRes = extraStatistic.getDetailLineCount(filePath);
                    System.out.println("空白行: " + lineCountRes.getBlankLineCount() + "\t" +
                            "代码行: " + lineCountRes.getCodeLineCount() + "\t" +
                            "注释行: " + lineCountRes.getCommentLineCount());
                }
            }
        }
        else {
            String fileName = args[args.length-1];
            File file = new File(fileName);
            if(!file.exists()){
                System.out.println("您要分析的文件不存在，请输入正确的文件名！");
                return;
            }
            System.out.println("文件名: " + fileName);
            if(charCount) {
                CharCount charCountRes = basicStatistic.getCharCount(file.getAbsolutePath());
                System.out.println("字符数: " + charCountRes.getAllCharCount());
                System.out.println("中字数: " + charCountRes.getChnCharCount());
            }
            if(wordCount) System.out.println("单词数: " + basicStatistic.getWordCount(file.getAbsolutePath()));
            if(lineCount) System.out.println("总行数: " + basicStatistic.getLineCount(file.getAbsolutePath()));
            if(detailLine) {
                LineCount lineCountRes = extraStatistic.getDetailLineCount(file.getAbsolutePath());
                System.out.println("空白行: " + lineCountRes.getBlankLineCount());
                System.out.println("代码行: " + lineCountRes.getCodeLineCount());
                System.out.println("注释行: " + lineCountRes.getCommentLineCount());
            }
        }
    }
}
