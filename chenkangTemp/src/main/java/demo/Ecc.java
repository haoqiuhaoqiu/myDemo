package demo;

import asymmetric.Parameter;

import java.math.BigInteger;

/***
 *  * @author  陈康
 *
 * 通过一个椭圆曲线来加密,靠谱
 * y² ≡ x³ + x + 1 (mod p)
 * p = 111111111111111111111111111111111111111111111111111111111111111257
 */
public class Ecc extends Parameter {
    private BigInteger x;
    private BigInteger y;
    private final BigInteger p = new BigInteger("111111111111111111111111111111111111111111111111111111111111111257");

    public Ecc(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
        BigInteger y2 = y.multiply(y).mod(this.p);
        BigInteger x3 = x.multiply(x).multiply(x).add(x).add(new BigInteger("1")).mod(this.p);
        if (!y2.equals(x3)) {
            throw new RuntimeException("不符合条件");
        }
    }

    public Ecc(String strx, String stry) {
        BigInteger x = new BigInteger(strx);
        BigInteger y = new BigInteger(stry);
        this.x = x;
        this.y = y;
        BigInteger y2 = y.multiply(y).mod(this.p);
        BigInteger x3 = x.multiply(x).multiply(x).add(x).add(new BigInteger("1")).mod(this.p);
        if (!y2.equals(x3)) {
            throw new RuntimeException("不符合条件");
        }
    }

    class ExgcdTempP {
        private BigInteger p1;
        private BigInteger p2;

        public ExgcdTempP(BigInteger p1, BigInteger p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    private static BigInteger exgcd(BigInteger a, BigInteger b, ExgcdTempP p) {
        if (b.equals(new BigInteger("0"))) {
            p.p1 = new BigInteger("1");
            p.p2 = new BigInteger("0");
            return a;
        }
        BigInteger d = exgcd(b, a.mod(b), p);
        BigInteger t = p.p1;
        p.p1 = p.p2;
        p.p2 = t.subtract(a.divide(b).multiply(p.p2));
        return d;
    }

    /***
     * 线性同于运算
     * @param a
     * @param b
     * @param m
     * @return
     */
    private BigInteger linearMod(BigInteger a, BigInteger b, BigInteger m) {
        ExgcdTempP p = new ExgcdTempP(null, null);
        BigInteger d = exgcd(a, m, p);
        BigInteger ret = p.p1.multiply(b.divide(d)).mod(m);
        if (ret.compareTo(new BigInteger("0")) > 0) {
            return ret;
        }
        return ret.add(m);
    }

    @Override
    public boolean equals(Object obj) {
        Ecc p = (Ecc) obj;
        return this.x.equals(p.x) && this.y.equals(p.y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }

    public Parameter 加(Parameter pointq) {
        Ecc Q = (Ecc) pointq;
        BigInteger λ;
        BigInteger a, b;
        if (!this.equals(pointq)) {
            a = Q.x.subtract(this.x);
            b = Q.y.subtract(this.y);
        } else {
            a = this.y.multiply(new BigInteger("2"));
            b = (this.x.multiply(this.x).multiply(new BigInteger("3"))).add(new BigInteger("1"));
        }
        λ = linearMod(a, b, this.p);
        BigInteger x = (λ.multiply(λ)).subtract(this.x).subtract(Q.x);
        x = x.mod(p);

        BigInteger y = λ.multiply(this.x.subtract(x)).subtract(this.y).mod(p);

        return new Ecc(x, y);
    }


    public Parameter 加密(BigInteger bi) {
        Ecc dp = new Ecc(this.x, this.y);
        dp.x = dp.x.add(bi);
        dp.y = dp.y.add(bi);
        return dp;
    }

    public BigInteger 解密(Parameter p) {
        Ecc dp = (Ecc) p;
        return dp.x.subtract(this.x);
    }
}
