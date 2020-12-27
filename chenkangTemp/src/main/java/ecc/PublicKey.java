package ecc;

import java.math.BigInteger;

public class PublicKey {
    public Point point1;
    public Point point2;

    public PublicKey(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }


    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    @Override
    public String toString() {
        return "PublicKey{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }
}
