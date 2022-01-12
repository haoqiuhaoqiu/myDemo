import asymmetric.Parameter;
import ecc.EccPoint;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class EccPointTest {

    @Test
    public void main() {
        //弄一个私钥这个看自己心情随便定义
        Parameter ep = new EccPoint(new BigInteger("0"), new BigInteger("1"));
        System.out.println("协议是EccPoint类定义,基于y² ≡ x³ + x + 1 (mod 98764321261) 上的 0,1点作为原点");

        //私钥自己随便选一个只有自己知道的大数字就行了
        BigInteger bi = new BigInteger("1000000000000000000000000000");
        System.out.println("私钥匙是:" + bi);

        //公钥直接就是用协议的原点乘以私钥就行了
        Parameter publicEp = ep.multiplication(bi);
        System.out.println("公钥是:" + publicEp);

        //用公钥加密生成报文 其实就是用公钥随便乘以一个数字然后加一个偏移量偏移量作为报文1, 然后再用协议原点乘以这个数字作为报文2 报文1和报文2合起来就是加密以后的报文
        BigInteger random = new BigInteger(new Random().nextLong() + "");
        Parameter postPar1 = publicEp.multiplication(random).deviation(new BigInteger("2022"));
        Parameter postPar2 = ep.multiplication(random);
        System.out.println("公钥加密以后的报文:" + postPar1 + "  " + postPar2);


        //私钥解密 直接用第二个参数乘以私钥和第一个参数对比差异就是用户发送的报文
        BigInteger data = postPar2.multiplication(bi).compare(postPar1);
        System.out.println("私钥解密以后的报文是:" + data);
    }
}
