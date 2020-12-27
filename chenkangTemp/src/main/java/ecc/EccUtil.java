package ecc;

import java.math.BigInteger;

public class EccUtil {
    private static BigInteger x, y;

    private static BigInteger exgcd(BigInteger a, BigInteger b) {
        if (b.equals(new BigInteger("0"))) {
            x = new BigInteger("1");
            y = new BigInteger("0");
            return a;
        }
        BigInteger d = exgcd(b, a.mod(b));
        BigInteger t = x;
        x = y;
        y = t.subtract(a.divide(b).multiply(y));
        return d;
    }

    /***
     * 线性同于运算
     * @param a
     * @param b
     * @param m
     * @return
     */
    public static BigInteger linearMod(BigInteger a, BigInteger b, BigInteger m) {
        BigInteger d = exgcd(a, m);
        BigInteger ret = x.multiply(b.divide(d)).mod(m);
        if (ret.compareTo(new BigInteger("0")) > 0) {
            return ret;
        }
        return ret.add(m);
    }

    /***
     * 标量乘法
     * @param target
     * @param num
     * @return
     */
    public static Point NumberMultiplication(Point target, BigInteger num) {
        if (num.compareTo(new BigInteger("1")) < 1) {//如果num小于等于1直接返回target
            return target;
        }

        if (new BigInteger("0").compareTo(num.mod(new BigInteger("2"))) == 0) {//如果取模等于0就直接升一级
            return NumberMultiplication(target.addition(target), num.divide(new BigInteger("2")));
        }

        //如果取模不等于1则加上自己在加上升一级的
        return target.addition(NumberMultiplication(target.addition(target), num.divide(new BigInteger("2"))));
    }


}
