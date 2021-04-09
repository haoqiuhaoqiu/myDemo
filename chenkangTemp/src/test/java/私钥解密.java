import ecc.*;
import MyEcc.DemoPoint;

import java.math.BigInteger;

public class 私钥解密 {
    public static void main(String[] arg) {
        PrivateKey rivateKey = new PrivateKey(new DemoPoint("0", "1"), new BigInteger("9999999999999"));

        //      这是别人用公钥加密好的密文
        //DemoPoint{x=19075198770, y=12493183992}  DemoPoint{x=89852588610, y=29392771030}
        Point p1 = new DemoPoint("19075198770", "12493183992");
        Point p2 = new DemoPoint("89852588610", "29392771030");

        p1 = EccUtil.NumberMultiplication(p1, rivateKey.getK());
        System.out.println("私钥解密出来的是:" + p2.decode(p1));
    }
}
