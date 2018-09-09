/*
*    没能实现的功能:wc.exe -s递归处理目录下符合条件的文件
*                   wc.exe -x 显示图形界面
*
*
*    实现的功能: wc.exe -c显示文件的字符数、
*                wc.exe -l行数、
*                wc.exe -w单词、
*                wc.exe -a空行数、代码行数、注释行数的统计测试
*`
*
*/

#include"iostream"
using namespace std;
void CharCount(char path[]);  //字符统计函数
void WordCount(char path[]);  //单词统计函数
void LineCount(char path[]);  //行数统计函数
void Muiltiple(char path[]);  //综合统计函数，包括代码行，空行，注释行
int main()
{
    char input[100],path[50];
    gets(input);
    int count=strlen(input);
    strncpy(path, input + 10,  count - 10);
    path[count - 9] = '\0';
    //cout << path << endl;
    int check = 1;
    while (check)
    {
        int i = 7;
        if ((input[i] == '-') && (input[i + 1] == 'c'))
        {
            CharCount(path);
            break;
        }
        if ((input[i] == '-') && (input[i + 1] == 'w'))
        {
            WordCount(path);
            break;
        }
        if ((input[i] == '-') && (input[i + 1] == 'l'))
        {
            LineCount(path);
            break;
        }
        if ((input[i] == '-') && (input[i + 1] == 'a'))
        {
            Muiltiple(path);
            break;
        }//获取文件名
    }
    system("pause");
    return 0;
}
void CharCount(char path[]) //字符统计函数
{
    FILE *fp=NULL;
    int c = 0;
    char ch;
    cout << path<<endl;
    char *path3 = path;
    int k = strlen(path);
    *(path3 + k-1) = '\0';
    //cout << path3<<endl;
    if ((fp = fopen(path3 , "r")) == NULL)
    {
        printf("file read failure.");
        exit(0);
    }
    ch = fgetc(fp);
    while (ch != EOF)
    {
        c++;
        ch = fgetc(fp);
    }
    cout << "char count is ：" << c << endl;
    c--;
    fclose(fp);
}

void WordCount(char path[]) //单词统计函数
{
    FILE *fp;
    int w = 0;
    char ch;
    char *path3 = path;
    int k = strlen(path);
    *(path3 + k - 1) = '\0';
    if ((fp = fopen(path3, "r")) == NULL)
    {
        printf("file read failure.");
        exit(0);
    }
    //fgetc()会返回读取到的字符，若返回EOF则表示到了文件尾，或出现了错误。
    ch = fgetc(fp);
    while (ch != EOF)
    {
        if ((ch >= 'a'&&ch <= 'z') || (ch >= 'A'&&ch <= 'Z') || (ch >= '0'&&ch <= '9'))
        {
            while ((ch >= 'a'&&ch <= 'z') || (ch >= 'A'&&ch <= 'Z') || (ch >= '0'&&ch <= '9') || ch == '_')
            {
                ch = fgetc(fp);
            }
            w++;
            ch = fgetc(fp);
        }
        else
        {
            ch = fgetc(fp);
        }
    }
    printf("word count is ：%d.\n", w);
    fclose(fp);

}

void LineCount(char path[]) //行数统计函数
{
    FILE *fp;
    int l = 1;
    char ch;
    char *path3 = path;
    int k = strlen(path);
    *(path3 + k - 1) = '\0';
    if ((fp = fopen(path3, "r")) == NULL)
    {
        printf("file read failure.");
        exit(0);
    }
    //fgetc()会返回读取到的字符，若返回EOF则表示到了文件尾，或出现了错误。
    ch = fgetc(fp);
    while (ch != EOF)
    {
        if (ch == '\n')
        {
            l++;
            ch = fgetc(fp);
        }
        else
        {
            ch = fgetc(fp);
        }
    }
    l--;
    printf("line count is ：%d.\n", l);
    fclose(fp);
}

void Muiltiple(char path[])  //综合统计函数，包括代码行，空行，注释行
{
    FILE *fp;
    char ch;
    char *path3 = path;
    int k = strlen(path);
    *(path3 + k - 1) = '\0';
    int c = 0, e = 0, n = 0;
    if ((fp = fopen(path3, "r")) == NULL)
    {
        printf("file read failure.");
        exit(0);
    }
    //fgetc()会返回读取到的字符，若返回EOF则表示到了文件尾，或出现了错误。
    ch = fgetc(fp);
    while (ch != EOF)
    {
        if (ch == '{' || ch == '}')
        {
            e++;
            ch = fgetc(fp);
        }
        else if (ch == '\n')
        {
            ch = fgetc(fp);
            while (ch == '\n')
            {
                e++;
                ch = fgetc(fp);
            }
        }
        else if (ch == '/')
        {
            ch = fgetc(fp);
            if (ch == '/')
            while (ch != '\n')
            {
                ch = fgetc(fp);
            }
            n++;
            ch = fgetc(fp);
        }
        else
        {
            c++;
            while (ch != '{'&&ch != '}'&&ch != '\n'&&ch != '/'&&ch != EOF)
            {
                ch = fgetc(fp);
            }
        }

    }
    printf("code line count is ：%d.\n", c);
    printf("empt line count is ：%d.\n", e);
    printf("note line count is ：%d.\n", n);
    fclose(fp);
}