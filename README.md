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
