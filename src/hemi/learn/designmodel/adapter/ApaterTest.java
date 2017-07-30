package hemi.learn.designmodel.adapter;

/**
 * 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，
 * 目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式
 *
 * @author yexq
 * @date 2017/7/30
 * @info
 */
public class ApaterTest {

    public static void main(String[] args) {
        /*
         * 类的适配器模式
         *
         * 核心思想就是：有一个Source类，拥有一个方法，待适配，
         * 目标接口是Targetable，通过Adapter类，将Source的功能扩展到Targetable里
         */
        Targetable target = new Adapter();
        target.method1();
        target.method2();


        /*
         * 对象的适配器模式
         *
         * 基本思路和类的适配器模式相同，只是将Adapter类作修改，
         * 这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题
         */
        Source source = new Source();
        target = new Wrapper(source);
        target.method1();
        target.method2();

        /*
        接口的适配器模式

        有时我们写的一个接口中有多个抽象方法，当我们写该接口的实现类时，
        必须实现该接口的所有方法，这明显有时比较浪费，
        因为并不是所有的方法都是我们需要的，有时只需要某一些，
        此处为了解决这个问题，我们引入了接口的适配器模式，
        借助于一个抽象类，该抽象类实现了该接口，实现了所有的方法，
        而我们不和原始的接口打交道，只和该抽象类取得联系，
        所以我们写一个类，继承该抽象类，重写我们需要的方法就行
         */


    }
}
