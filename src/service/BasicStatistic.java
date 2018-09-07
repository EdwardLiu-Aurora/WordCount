package service;

import bean.CharCount;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/*
基础统计服务类
 */
public class BasicStatistic {

    // 返回文件字符数的函数
    public CharCount getCharCount(String filePath){
        // 全体字符数变量和中文字符数变量
        long allCharCount = 0, chnCharCount = 0;
        // 新建 nio 文件路径对象
        Path path = Paths.get(filePath);
        // 为了避免文本太大，这里采用惰性的 Stream<String> 对象
        // 注意文件的编码只能为 UTF_8 类型，不然会出现不可知的错误
        Stream<String> lines = null;
        try {
            // 按行读取文件并转化成流
            lines = Files.lines(path, charsetRecognize(filePath));
            // 使用 lambda 表达式和 nio 进行归并统计字符数
            allCharCount = lines.parallel().reduce(0,
                    (total, line) -> total + line.length(),
                    (total1, total2) -> total1 + total2);
            lines.close();
            // 按行读取文件并转化成流
            lines = Files.lines(path, charsetRecognize(filePath));
            // 使用 lambda 表达式和 nio 进行归并统计中文字数
            chnCharCount = lines.parallel().reduce(0,
                    (total, line) ->
                    {
                        int res=0;
                        for(int i=0;i<line.length();i++)
                            if(line.charAt(i) >= 19968 && line.charAt(i) <= 40869)
                                res++;
                        return res + total;
                    },
                    (total1, total2) -> total1 + total2
            );
            lines.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("文件不存在或无法访问");
            if(lines != null) lines.close();
            return null;
        }
        return new CharCount(allCharCount, chnCharCount);
    };

    // 返回文件的词汇数
    public long getWordCount(String filePath){
        long wordCount = 0;
        // 为了避免文本太大，这里采用惰性的 Stream<String> 对象
        // 注意文件的编码只能为 UTF_8 类型，不然会出现不可知的错误
        Stream<String> lines = null;
        // 新建 nio 文件路径对象
        Path path = Paths.get(filePath);
        try {
            // 按行读取文件并转化成流
            lines = Files.lines(path, charsetRecognize(filePath));
            Pattern pattern = Pattern.compile("\\w+");
            // 使用 lambda 表达式和 nio 进行归并统计词汇数
            wordCount = lines.parallel().reduce(0,
                    (total, line) ->
                    {
                        int count = 0;
                        Matcher matcher = pattern.matcher(line);
                        while(matcher.find()){
                            count++;
                        }
                        return total + count;
                    },
                    (total1, total2) -> total1 + total2
            );
            lines.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("文件不存在或无法访问");
            if(lines != null) lines.close();
            return 0;
        }
        return wordCount;
    }

    // 返回文件的行数
    public long getLineCount(String filePath){
        long lineCount = 0;
        // 为了避免文本太大，这里采用惰性的 Stream<String> 对象
        // 注意文件的编码只能为 UTF_8 类型，不然会出现不可知的错误
        Stream<String> lines = null;
        // 新建 nio 文件路径对象
        Path path = Paths.get(filePath);
        try {
            // 按行读取文件并转化成流
            lines = Files.lines(path, charsetRecognize(filePath));
            // 使用 lambda 表达式和 nio 进行归并统计行数
            lineCount = lines.parallel().reduce(0,
                    (total, line) -> total + 1,
                    (total1, total2) -> total1 + total2);
            lines.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("文件不存在或无法访问");
            if(lines != null) lines.close();
            return 0;
        }
        return lineCount;
    }

    // 文件编码类型简单识别
    private Charset charsetRecognize(String filePath){
        try{
            File file = new File(filePath);
            InputStream in = new java.io.FileInputStream(file);
            byte[] b = new byte[3];
            in.read(b);
            in.close();
            if (b[0] == -17 && b[1] == -69 && b[2] == -65)
                return Charset.forName("UTF-8");
            else
                return Charset.forName("GBK");
        }
        catch(Exception e){
            e.printStackTrace();
            return Charset.forName("UTF-8");
        }
    }
}
