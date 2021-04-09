import MyEcc.DemoPoint;
import ecc.EccUtil;
import ecc.Point;
import ecc.PublicKey;

import java.math.BigInteger;
import java.util.Random;

public class 公钥加密 {
    public static void main(String[] arg) {
//      这就是上面那个类生成的公钥
//      PublicKey{point1=DemoPoint{x=0, y=1}, point2=DemoPoint{x=61745727438, y=19524854285}}
        PublicKey publicKey = new PublicKey(new DemoPoint("0", "1"), new DemoPoint("61745727438", "19524854285"));//这是别人发给我们的公钥

//      假如我要告诉别人一个数字 77777777777

        Long rand = new Random().nextLong();//先随便产生一个数字越大越好,这个数字越大越不好破解
        //用参数的数字和公钥的2个点运算
        Point point1 = EccUtil.NumberMultiplication(publicKey.point1, new BigInteger(rand + ""));
        Point point2 = EccUtil.NumberMultiplication(publicKey.point2, new BigInteger(rand + ""));
        //然后使用你偏移量来作为我们要发送的数字,这里假如我们要发送的数字是2020
        point2 = point2.encode(new BigInteger("2020"));

        System.out.println(point1 + "  " + point2);//加密好以后把point1和point2 发送过去
        //DemoPoint{x=19075198770, y=12493183992}  DemoPoint{x=89852588610, y=29392771030}
    }
}
