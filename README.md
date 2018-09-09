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
4. 如果使用 -s 命令，且 wc.exe 要搜寻的文件有处于同目录下的，因为要避免win10自带命令通配符对程序的影响，需要在通配符旁加上""
	例子：	wc.exe 位于 xx 目录下，xx目录下同时有 xx/wc.exe、xx/a.java、xx/b.java ... ，则如果需要用通配符，则命令应为 wc.exe -a -s "*.java" 