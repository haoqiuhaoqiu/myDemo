import asymmetric.Parameter;
import demo.Ecc;
import demo.Simple;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class AsymmetricTest {
    //加密协议
    private static Parameter EncryptionProtocol = new Simple(new BigInteger("123"));
//    private static Parameter EncryptionProtocol = new Ecc(new BigInteger("0"), new BigInteger("1"));

    //私钥
    private static BigInteger privateKey = new BigInteger("1000000000000000000000000000");

    //公钥直接就是用协议的原点乘以私钥就行了
    public Parameter createPublicKey() {
        return EncryptionProtocol.multiplication(privateKey);
    }

    @Test
    public void main() {
        //假如要发送的信息是这个
        BigInteger bigInteger = new BigInteger("2022");

        //用公钥加密生成报文 其实就是用公钥随便乘以一个数字然后加一个偏移量偏移量作为报文1,
        // 然后再用协议原点乘以这个数字作为报文2
        // 报文1和报文2合起来就是加密以后的报文
        BigInteger random = new BigInteger(new Random().nextLong() + "");
        Parameter postPar1 = createPublicKey().multiplication(random).deviation(bigInteger);
        Parameter postPar2 = EncryptionProtocol.multiplication(random);
        System.out.println("公钥加密以后的报文:" + postPar1 + "  " + postPar2);


        //私钥解密 直接用第二个参数乘以私钥和第一个参数对比差异就是用户发送的报文
        BigInteger data = postPar2.multiplication(privateKey).compare(postPar1);
        System.out.println("私钥解密以后的报文是:" + data);
    }
}