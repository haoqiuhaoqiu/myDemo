package asymmetric;

import java.math.BigInteger;

/**
 * @author 陈康
 */
public abstract class Parameter {
    /**
     * 2个对象合并成一个新对象
     */
    public abstract Parameter 加(Parameter p);

    /***
     * 对原来对象进行一个偏移让他有差别
     */
    public abstract Parameter deviation(BigInteger bi);

    /***
     * 比较对象返回差别,和deviation是互逆的
     */
    public abstract BigInteger compare(Parameter p);

    /***
     * 标量乘法
     * @return
     */
    public Parameter 乘(BigInteger num) {
        if (num.compareTo(new BigInteger("1")) < 1) {//如果等于1
            return this;
        }
        if (new BigInteger("0").compareTo(num.mod(new BigInteger("2"))) == 0) {//如果取模等于0就直接升一级
            return this.加(this).乘(num.divide(new BigInteger("2")));
        }
        return this.加(this).乘(num.divide(new BigInteger("2"))).加(this);
    }

    /***
     * 标量乘法
     * @return
     */
    public Parameter 乘(String num) {
        return 乘(new BigInteger(num));
    }
}
