package ecc;

import java.math.BigInteger;

public class EccUtil {
    private static BigInteger p1, p2;

    private static BigInteger exgcd(BigInteger a, BigInteger b) {
        if (b.equals(new BigInteger("0"))) {
            p1 = new BigInteger("1");
            p2 = new BigInteger("0");
            return a;
        }
        BigInteger d = exgcd(b, a.mod(b));
        BigInteger t = p1;
        p1 = p2;
        p2 = t.subtract(a.divide(b).multiply(p2));
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
        BigInteger ret = p1.multiply(b.divide(d)).mod(m);
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
