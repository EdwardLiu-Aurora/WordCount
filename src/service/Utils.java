package service;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Utils {
    // 文件编码类型简单识别
    public static Charset charsetRecognize(String filePath){
        try{
            File file = new File(filePath);
            InputStream in = new java.io.FileInputStream(file);
            byte[] b = new byte[3];
            in.read(b);
            in.close();
            if (b[0] == -17 && b[1] == -69 && b[2] == -65)
                return Charset.forName("UTF-8");
            else{
                try (Stream<String> lines = Files.lines(Paths.get(filePath), Charset.forName("UTF-8"))){
                    lines.count();
                    return Charset.forName("UTF-8");
                }
                catch(Exception e){
                    return Charset.forName("GBK");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return Charset.forName("UTF-8");
        }
    }
    // 获取一个目录下及其子目录下的所有的文件路径
    public static ArrayList<String> getFilesPath(String folderPath,String filePattern){
        Pattern pattern = Pattern.compile(filePattern);
        ArrayList<String> filePaths = new ArrayList<>();
        // 获取路径
        File file = new File(folderPath);
        // 如果这个路径是文件夹
        if(file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果路径下的还是文件夹，则递归获取里面的文件和文件夹
                if(files[i].isDirectory()) {
                    filePaths.addAll(getFilesPath(files[i].getPath(),filePattern));
                } else {
                    Matcher matcher = pattern.matcher(files[i].getName());
                    if(matcher.find()) filePaths.add(files[i].getPath());
                }
            }
        } else {
            Matcher matcher = pattern.matcher(file.getPath());
            if(matcher.find()) filePaths.add(file.getPath());
        }
        return filePaths;
    }
}
