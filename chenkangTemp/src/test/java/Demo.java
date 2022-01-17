import asymmetric.Parameter;
import demo.Ecc;
import org.junit.Test;

import java.math.BigInteger;

public class Demo {

    /***
     * 一个正向很容易逆向很难的方法,并且这个方法是线性的
     */
    @Test
    public void groupMultiplication() {
        Parameter 坐标点 = new Ecc(new BigInteger("0"), new BigInteger("1"));
        BigInteger 数字 = new BigInteger("200000000000");
        System.out.println(坐标点.乘(数字) + " = " + 坐标点 + " * " + 数字);
    }

    /***
     * 这个方法必须是线性的
     */
    @Test
    public void linear() {
        Parameter 坐标点 = new Ecc(new BigInteger("0"), new BigInteger("1"));

        System.out.println(坐标点.乘("999999999").乘("1000000000"));
        System.out.println(坐标点.乘("1000000000").乘("999999999"));
        System.out.println(坐标点.乘("999999999000000000"));
    }
}
