# WordCount

### ��������

wc.exe [�������] ([�����������...]) [�����ļ������ļ�ͨ���]

�������

wc.exe -c file.java	// ���غ� wc.exe ��ͬĿ¼�� file.java �ļ����ַ���
wc.exe -w file.java	// ���غ� wc.exe ��ͬĿ¼�� file.java �ļ��ĵ�����
wc.exe -l file.java	// ���غ� wc.exe ��ͬĿ¼�� file.java �ļ���������
wc.exe -a file.java	// ���غ� wc.exe ��ͬĿ¼�� file.java �ļ�����ϸ����
wc.exe -a -s *.java	// ���� wc.exe ����Ŀ¼������Ŀ¼�µ����� java �ļ�����ϸ����

#### ע������

1. �ļ�ͨ�������� -s ����һ��ʹ��
2. -s ����������������һ��ʹ��
3. -c -w -l -a ����ͬʱʹ��
4. ���ʹ�� -s ����� wc.exe Ҫ��Ѱ���ļ��д���ͬĿ¼�µģ���ΪҪ����win10�Դ�����ͨ����Գ����Ӱ�죬��Ҫ��ͨ����Լ���""
	���ӣ�	wc.exe λ�� xx Ŀ¼�£�xxĿ¼��ͬʱ�� xx/wc.exe��xx/a.java��xx/b.java ... ���������Ҫ��ͨ�����������ӦΪ wc.exe -a -s "*.java" 