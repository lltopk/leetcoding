## VSCode轻量化刷题
本仓库基于Java17开发

安装oracle java插件(基于netbeans), 支持并自带maven工具
```
C:\Users\hasee\.vscode\extensions\oracle.oracle-java-25.0.0\nbcode\java\maven
```

创建.vscode/settings.json配置Jdk路径(可选Java8)
```
{
    // "jdk.project.jdkhome": "C:\\Users\\hasee\\.jdks\\corretto-1.8.0_472",
    "jdk.project.jdkhome": "C:\\Users\\hasee\\.jdks\\ms-17.0.17",
    "jdk.jdkhome": "C:\\Users\\hasee\\.jdks\\ms-17.0.17",
    "jdk.telemetry.enabled": true,
}
```

Ctrl Shift P初始化maven项目: 
```
Java: New Project
```
F5启动调试

## 复杂度评估
数组长度10^3 限制O(n^2)

数组长度10^5 限制O(nlogn)

数组长度10^9 限制O(logn)

## Coding之外的初级数学问题

向下取整: a/b

向上取整: (a+b-1)/b

除法真实值: a/(b*1.0) 或者 (double)a/b, 注意不可double(a/b), 这样会提前丢失数位

等差数列`a[0] + a[1] + a[2] + ... + a[n-1]`, 求和公式`Sn = n(a[0]+na[n-1])/2`，假设公差为`d`, 也可以表示为`Sn = na[0]+n(n-1)d/2`

一元二次方程`ax^2+bx+c = 0`, 求根公式`( -b - sqrt(b^2 - 4ac) / 2a < x < ( -b + sqrt(b^2 - 4ac) / 2a`

特别的当一元二次方程由等差数列而来, 假设数列长度为x, d = a[0], 和为m
```
d + 2d + 3d + ... + xd = m

xd(1+x)/2 = m

x(1+x)/2 = m/d

x^2 +x -2m/d = 0
```
此时根`x=( - + sqrt(1 + 8m/d) / 2`