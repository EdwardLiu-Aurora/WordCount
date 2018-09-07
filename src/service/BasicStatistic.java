package service;

import bean.CharCount;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Stream<String> lines;
        try {
            // 按行读取文件并转化成流
            lines = Files.lines(path, StandardCharsets.UTF_8);
            // 使用 lambda 表达式和 nio 进行归并
            allCharCount = lines.parallel().reduce(0,
                    (total, line) -> total + line.length(),
                    (total1, total2) -> total1 + total2);
            lines.close();
            // 按行读取文件并转化成流
            lines = Files.lines(path, StandardCharsets.UTF_8);
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
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("文件不存在或无法访问");
            return null;
        }
        return new CharCount(allCharCount, chnCharCount);
    };
}
