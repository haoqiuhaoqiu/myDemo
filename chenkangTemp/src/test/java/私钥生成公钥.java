import ecc.*;
import MyEcc.DemoPoint;

import java.math.BigInteger;

public class 私钥生成公钥 {
    public static void main(String[] arg) {
        //自己做一个私钥,随便选一个点,随便选一个大数越大越好 这里做例子就选点(0,1) 和 9999999999999 作为私钥
        PrivateKey rivateKey = new PrivateKey(new DemoPoint("0", "1"), new BigInteger("9999999999999"));
        PublicKey publicKey = rivateKey.getPublicKey();//根据私钥计算公钥
        System.out.print(publicKey);
    }
}
