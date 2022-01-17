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
        Parameter 坐标点 = new Ecc(new BigInteger("74073240946"), new BigInteger("61727700787"));
        BigInteger 数字 = new BigInteger("200000000000");

        //根据初始点 和一个  数字 生成一个目标点
        Parameter 坐标点2 = 坐标点.乘(数字);
        System.out.println("生成的点:" + 坐标点2);//EccPoint{x=49640409832, y=84950561392}
    }
}
