//import asymmetric.Parameter;
//import demo.Ecc;
//import org.junit.Test;
//
//import java.math.BigInteger;
//
//public class Demo {
//
//    /***
//     * 一个正向很容易逆向很难的方法,并且这个方法是线性的
//     */
//    @Test
//    public void groupMultiplication() {
//        Parameter 点 = new Ecc("0", "1");
//        System.out.println(点.乘("100") + " = " + 点 + " * 100");
//    }
//
//    /***
//     * 这个方法必须是线性的
//     */
//    @Test
//    public void linear() {
//        Parameter 点 = new Ecc(new BigInteger("0"), new BigInteger("1"));
//
//        System.out.println(点.乘("999999999").乘("1000000000") + " = " + 点 + "*999999999*1000000000");
//        System.out.println(点.乘("1000000000").乘("999999999") + " = " + 点 + "*1000000000*999999999");
//        System.out.println(点.乘("10000").乘("100000").乘("333333333").乘("3") + " = " + 点 + "*10000*100000*333333333*3");
//        System.out.println(点.乘("999999999000000000") + " = " + 点 + "*999999999000000000");
//
//    }
//
//
//    /***
//     * "111111111111111111111111111111111111111111111111111111111111"
//     *  (0,1) *  ? = (35635118541,12346768964)
//     *  求?是多少
//     */
//    @Test
//    public void test() {
//        Parameter 点1 = new Ecc("0", "1");
//        Parameter 点2 = new Ecc("26612436477606353293325288948829719703333064886167161648385097211", "9265656935811150930228023468200998605727297356231480407275506475");
//
//        //下面的代码需要运行几百万年
//        BigInteger i = new BigInteger("0");
//        while (true) {
//            if (点2.equals(点1.乘(i))) {
//                System.out.println("结果是" + i);
//                break;
//            }
//            i = i.add(new BigInteger("1"));//i =  i + 1
//        }
//    }
//}
