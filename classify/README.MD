https://github.com/EndlessCheng

https://oi-wiki.org/

## 复杂度评估
数组长度10^3 限制O(n^2)

数组长度10^5 限制O(nlogn)

数组长度10^7 限制O(n)

数组长度10^9 限制O(logn)

## 数学
### 取整
向下取整: a/b

向上取整: (a+b-1)/b

### 真除
除法真实值: a/(b*1.0) 或者 (double)a/b, 注意不可double(a/b), 这样会提前丢失数位

### 数列
等差数列`a[0] + a[1] + a[2] + ... + a[n-1]`, 求和公式`Sn = n(a[0]+na[n-1])/2`，假设公差为`d`, 也可以表示为`Sn = na[0]+n(n-1)d/2`

### 求根
一元二次方程`ax^2+bx+c = 0`, 求根公式`( -b - sqrt(b^2 - 4ac) / 2a < x < ( -b + sqrt(b^2 - 4ac) / 2a`

特别的当一元二次方程由等差数列而来, 假设数列长度为x, d = a[0], 和为m
```
d + 2d + 3d + ... + xd = m

xd(1+x)/2 = m

x(1+x)/2 = m/d

x^2 +x -2m/d = 0
```
此时根`x=( - + sqrt(1 + 8m/d) / 2`

### 取模
1. `+-*`法取模, 根据带余除法定理, 任意整数 `a` 都可以表示为 `a=qm+r (m!=0)`，其中整数 `q` 为 `a` 除以 `m` 的商（quotient），整数 `r` 为 `a` 除以 `m` 的余数（remainder），即 `r = a mod m`
```text
//根据带余除法定理, 设整数 a = q1*m + r1, b = q2*m + r2 。可证明
(a+b) % m = ((a % m) + (b % m)) % m
(a-b) % m = ((a % m) - (b % m)) % m
(a*b) % m = ((a % m) * (b % m)) % m
```
当对负整数求模, 如-1 % 4 结果是-1, 这是错误的, 正确结果应该是3, 那么如何让上述公式兼容负数的情况呢?

根据同余定理, 两个整数 `x` 和 `y`，如果 `(x−y) mod m = 0`（也就是 `x−y` 是 `m` 的倍数），则称 `x` 与 `y` 关于模 `m` 同余，记作`x 三 y (mod m)`

符合同余定理的两个数对目标取模余数相等, 因此只要将负数映射到它的同余镜像正数即可

以`-1`和`3`为例, 根据同余公式`-1 三 3 (mod 4)`, 我们可以通过`-1 % 4 + 4` 可得 镜面`3`

一般的, 镜像`y = x % m + m`, 因此在代码实现时，如果被模数存在负数的可能, 需要先将被模数`x`转化为 `x % m + m` 再对 m 求模


2. `/`除法取模, 如果 p 是一个质数，a 是 b 的倍数, 且 b 和 p 互质（b 不是 p 的倍数），那么有
```text
//推导过程需要用到二项式定义与费马小定理: 见: https://leetcode.cn/discuss/post/3584387/fen-xiang-gun-mo-yun-suan-de-shi-jie-dan-7xgu/
(a/b) % p = (a * b^(p-2)) % p
```
由于`1_000_000_007`是一个质数，所以上式可用于要求对`1_000_000_007`取模的题目, 如果推导出了包含除法的式子，可以用上式转换成乘法，

### 快速幂
如何计算`x`的`n`次幂`pow(x,n)`, 快速幂推导如下

将n用二进制表示, 例如求`pow(x, 13)`, `13`表示为二进制是`1101`

计算过程为, 从低到高枚举二进制的1位, 并以该位代表的实际二进制值作为幂, 累乘即x * x^4 * x^8
```java
    public double myPow(double x, int N) {
        double ans = 1;
        long n = N;
        //x^(-n) = (1/x)^n
        if(n<0){
            x = 1/x;
            n = -n;
        }

        //快速幂, 将n用二进制表示, 例如求pow(x, 13), 13表示为二进制是1101
        //计算过程为, 从低到高枚举二进制的1位, 并以该位代表的实际二进制值作为幂, 累乘即x * x^4 * x^8
        while(n!=0){
            if((n & 1) == 1){
                ans *= x; 
            }
            //以任意位代表的实际二进制值作为幂, 如1, 2, 4, 8
            x *=x;
            n >>= 1;
        }
        return ans;
    }
```

### 排列组合公式
求排列总数
```text
A(a,b) = !a/!(a-b) 
= a* !(a-1)/!((a-1) - (b-1)) 
= a*A(a-1, b-1)
```
根据该递推式, 可以用递归实现
```java
    private static Integer permutation(Integer a, Integer b) {
        //递归出口 A(a, 0) = 1
        if (b == 0) {
            return 1;
        }
        return a * permutation(a - 1, b-1);
    }
```

求组合总数 
```text
C(a,b) = !a/(!b!(a-b)) 
= a* !(a-1)/(!b!(a-b))
= (a-b)* !(a-1)/(!b!(a-b)) + b* !(a-1)/(!b!(a-b))
= !(a-1)/(!b!(a-b-1)) + !(a-1)/(!(b-1)!(a-b))
= C(a-1,b) + C(a-1, b-1)
```
根据该递推式, 可以用递归实现
```java
    private static Integer combination(Integer a, Integer b) {
        //递归出口 C(a, 0) 和 C(a, a)都直接等于1
        if (b == 0 || a.equals(b)) {
            return 1;
        }
        b = Math.min(b, a - b);//利用组合的对称性C(a, b) == C(a, a-b), 降低递归深度
        return combination(a - 1, b) + combination(a - 1, b -1 );
    }
```


## 位运算