# WordCount

### 基础功能

wc.exe [命令参数] ([更多命令参数...]) [具体文件名或文件通配符]

命令参数

wc.exe -c file.java	// 返回和 wc.exe 相同目录下 file.java 文件的字符数
wc.exe -w file.java	// 返回和 wc.exe 相同目录下 file.java 文件的单词数
wc.exe -l file.java	// 返回和 wc.exe 相同目录下 file.java 文件的总行数
wc.exe -a file.java	// 返回和 wc.exe 相同目录下 file.java 文件的详细行数
wc.exe -a -s *.java	// 返回 wc.exe 所在目录及其子目录下的所有 java 文件的详细行数

#### 注意事项

1. 文件通配符必须和 -s 命令一起使用
2. -s 命令必须和其他命令一起使用
3. -c -w -l -a 可以同时使用
4. 如果使用 -s 命令，则 wc.exe 必须位于要搜寻的目录同级目录下，且该目录没有能匹配的文件。