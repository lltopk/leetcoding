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