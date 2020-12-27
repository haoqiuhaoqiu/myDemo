package ecc;

import java.math.BigInteger;

public class PrivateKey {
    public Point point;
    public BigInteger k;

    @Override
    public String toString() {
        return "PrivateKey{" +
                "point=" + point +
                ", k=" + k +
                '}';
    }

    public PrivateKey(Point point, BigInteger k) {
        this.point = point;
        this.k = k;
    }

    public PublicKey getPublicKey() {
        Point Point2 = EccUtil.NumberMultiplication(this.point, this.k);
        return new PublicKey(this.point, Point2);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public BigInteger getK() {
        return k;
    }

    public void setK(BigInteger k) {
        this.k = k;
    }
}
