import MyEcc.DemoPoint;
import ecc.EccUtil;
import ecc.Point;
import ecc.PublicKey;

import java.math.BigInteger;
import java.util.Random;

public class 公钥加密 {
    public static void main(String[] arg) {
//        PublicKey{point1=DemoPoint{x=0, y=1}, point2=DemoPoint{x=72176107556, y=4368835808}}
        PublicKey publicKey = new PublicKey(new DemoPoint("0", "1"), new DemoPoint("72176107556", "4368835808"));//这是别人发给我们的公钥

//      假如我要告诉别人一个数字  2020
        Integer rand = new Random().nextInt();//先随便产生一个数字,写死也可以,不影响

        //用公钥的2个数字分别加入rand这个随机的数字
        Point point1 = EccUtil.NumberMultiplication(publicKey.point1, new BigInteger(rand + ""));
        Point point2 = EccUtil.NumberMultiplication(publicKey.point2, new BigInteger(rand + ""));
        point2 = point2.encode(new BigInteger("2020"));//2020是我们要发送的数据

        System.out.println(point1 + "  " + point2);//加密好以后把point1和point2 发送过去
    }
}
