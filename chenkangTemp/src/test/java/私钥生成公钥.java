import ecc.*;
import MyEcc.DemoPoint;

import java.math.BigInteger;

public class 私钥生成公钥 {
    public static void main(String[] arg) {
        PrivateKey rivateKey = new PrivateKey(new DemoPoint("0", "1"), new BigInteger("9876543210"));//自己做一个私钥,随便选一个点,随便选一个
        PublicKey publicKey = rivateKey.getPublicKey();//根据私钥计算公钥
        System.out.print(publicKey);
    }
}
