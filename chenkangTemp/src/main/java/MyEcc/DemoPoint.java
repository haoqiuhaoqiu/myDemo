package MyEcc;

import ecc.EccUtil;
import ecc.Point;

import java.math.BigInteger;

/***
 * y² ≡ x³ + x + 1 (mod 98764321261)
 */
public class DemoPoint implements Point {
    private BigInteger x;
    private BigInteger y;
    private BigInteger p = new BigInteger("98764321261");


    public DemoPoint(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
        BigInteger y2 = y.multiply(y).mod(this.p);
        BigInteger x3 = x.multiply(x).multiply(x).add(x).add(new BigInteger("1")).mod(this.p);
        if (!y2.equals(x3)) {
            throw new RuntimeException("不符合条件");
        }
    }

    public DemoPoint(String x, String y) {
        this.x = new BigInteger(x);
        this.y = new BigInteger(y);
    }

    /***
     * Xr = (λ² - Xp - Xq) mod p　　Yr = (λ(Xp - Xr) - Yp) mod p　　
     *
     * λ = (Yq - Yp)/(Xq - Xp) mod p（P≠Q）,
     * λ = (3Xp² + a)/2Yp mod p（P=Q）
     * @param pointq
     * @return
     */
    public Point addition(Point pointq) {
        DemoPoint Q = (DemoPoint) pointq;
        BigInteger λ;
        BigInteger a, b;
        if (!this.equals(pointq)) {
            a = Q.x.subtract(this.x);
            b = Q.y.subtract(this.y);
        } else {
            a = this.y.multiply(new BigInteger("2"));
            b = (this.x.multiply(this.x).multiply(new BigInteger("3"))).add(new BigInteger("1"));
        }
        λ = EccUtil.linearMod(a, b, this.p);
        BigInteger x = (λ.multiply(λ)).subtract(this.x).subtract(Q.x);
        x = x.mod(p);

        BigInteger y = λ.multiply(this.x.subtract(x)).subtract(this.y).mod(p);

        return new DemoPoint(x, y);
    }

    public Point encode(BigInteger en) {
        DemoPoint dp = new DemoPoint(this.x, this.y);
        dp.x = dp.x.add(en);
        return dp;
    }

    public BigInteger decode(Point point) {
        DemoPoint dp = (DemoPoint) point;
        return this.x.subtract(dp.x);
    }

    @Override
    public String toString() {
        return "DemoPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        DemoPoint dp = (DemoPoint) obj;
        return this.x.equals(dp.x) && this.y.equals(dp.y);
    }
}
