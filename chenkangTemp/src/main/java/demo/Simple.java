package demo;

import asymmetric.Parameter;

import java.math.BigInteger;

/**
 *  @author  陈康
 * 这是一个反例 说明一下这个虽然实现了parameter接口但是他的加是有逆运算的,所以能直接推出私钥,
 * 就是普通的加法
 * 不靠谱的反例
 * 通过公钥可以直接推出私钥虽然能加密解密但是直接就能破解
 */
public class Simple extends Parameter {
    private BigInteger x;

    public Simple(BigInteger x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Simple{" +
                "x=" + x +
                '}';
    }

    public Parameter 加(Parameter p) {
        Simple temp = (Simple) p;
        return new Simple(this.x.add(temp.x));
    }

    public Parameter 加密(BigInteger bi) {
        return new Simple(this.x.add(bi));
    }

    public BigInteger 解密(Parameter p) {
        Simple temp = (Simple) p;
        return temp.x.subtract(this.x);
    }
}
