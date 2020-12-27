import ecc.*;
import MyEcc.DemoPoint;

import java.math.BigInteger;

public class 私钥解密 {
    public static void main(String[] arg) {
        PrivateKey rivateKey = new PrivateKey(new DemoPoint("0", "1"), new BigInteger("9876543210"));//我们自己的私钥
//      这是别人用公钥加密好的密文
//      DemoPoint{x=49967128010, y=55727664622}  DemoPoint{x=50328711320, y=71399355806}
        Point p1 = new DemoPoint("49967128010", "55727664622");
        Point p2 = new DemoPoint("50328711320", "71399355806");

        p1 = EccUtil.NumberMultiplication(p1,new BigInteger("9876543210"));
        System.out.println(p2.decode(p1));

    }
}
