package ecc;

import java.math.BigInteger;

/***
 * 还必须实现equals方法
 */
public interface Point {
    /***
     * 定义加法
     * @param point
     * @return
     */
    public Point addition(Point point);

    /**
     * 定义编码
     *
     * @param en
     * @return
     */
    public Point encode(BigInteger en);

    /**
     * 定义解码
     *
     * @return
     */
    public BigInteger decode(Point point);
}
