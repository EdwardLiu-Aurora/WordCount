package service;

import bean.LineCount;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// 高级统计功能
public class ExtraStatistic {

    public LineCount getDetailLineCount(String filePath) {
        LineCount lineCount = new LineCount(0,0,0);
        // 正则表达式匹配任何非空字符
        Pattern pattern = Pattern.compile("\\S");
        // 统计空行数
        Path path = Paths.get(filePath);
        try(Stream<String> lines = Files.lines(path, Utils.charsetRecognize(filePath))){
            lineCount.setBlankLineCount(
                    (int) lines.filter(line -> {
                        if(line.length() == 0) return true;
                        int i = 0;
                        Matcher matcher = pattern.matcher(line);
                        while(matcher.find()){
                            i++;
                            // 如果有超过一个非空白字符，则不为空行
                            if(i > 1) return false;
                        }
                        // 其余为空行
                        return true;
                    }).count()
            );
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("文件不存在或无法访问");
            return null;
        }
        // 统计注释行和代码行
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath),
                        Utils.charsetRecognize(filePath)
                ))){
            int commentLineCount = 0;
            int codeLineCount = 0;
            // 按行读取文件
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine())
            {
                // 单行注释符号位置
                int oneLinePos = line.indexOf("//");
                // 多行注释符号位置
                int mulLinePos = line.indexOf("/*");
                // 如果该行有 //，且 // 在前，则将第一次匹配到的 // 后的内容删去，注释行 +1
                if(oneLinePos >= 0 && (mulLinePos < 0 || (mulLinePos >= 0 && oneLinePos < mulLinePos))){
                    line = line.substring(0,oneLinePos);
                    commentLineCount++;
                    // 如果有 >1 个非空字符，则同时也为代码行
                    Matcher matcher = pattern.matcher(line);
                    int i = 0;
                    while(matcher.find()) i++;
                    if(i > 1) codeLineCount++;
                }
                // 如果该行只有 /* ，则检查是否同时为代码行。注释行 +1，连续读取直到遇到 */ 行
                else if(mulLinePos >= 0){
                    line = line.substring(0, mulLinePos);
                    commentLineCount++;
                    // 如果有 >1 个非空字符，则同时也为代码行
                    Matcher matcher = pattern.matcher(line);
                    int i = 0;
                    while(matcher.find()) i++;
                    if(i > 1) codeLineCount++;
                    line = bufferedReader.readLine();
                    while(line.indexOf("*/") < 0) {
                        commentLineCount++;
                        line = bufferedReader.readLine();
                    }
                    commentLineCount++;
                    line = line.substring(line.indexOf("*/")+2);
                    // 如果有超过一个非空字符，则也为代码行
                    i = 0;
                    matcher = pattern.matcher(line);
                    while(matcher.find()) i++;
                    if(i > 1) codeLineCount++;
                }
                // 如果没有注释，则看是否能匹配到 >1 个非空字符
                else{
                    int i = 0;
                    Matcher matcher = pattern.matcher(line);
                    while(matcher.find()) i++;
                    if(i > 1) codeLineCount++;
                }
            }
            lineCount.setCodeLineCount(codeLineCount);
            lineCount.setCommentLineCount(commentLineCount);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("文件不存在或无法访问");
            return null;
        }
        return lineCount;
    }
}
