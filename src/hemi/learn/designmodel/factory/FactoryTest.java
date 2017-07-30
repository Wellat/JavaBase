package hemi.learn.designmodel.factory;

/**
 * 工厂模式适合：凡是出现了大量的产品需要创建，并且具有共同的接口时，
 * 可以通过工厂方法模式进行创建。在以上的三种模式中，第一种如果传入的字符串有误，
 * 不能正确创建对象，第三种相对于第二种，不需要实例化工厂类，
 * 所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 *
 * but:类的创建依赖工厂类，也就是说，如果想要拓展程序，必须对工厂类进行修改
 *
 * Created by Hemi on 2017/7/29.
 */
public class FactoryTest {


    public static void main(String[] args) {
        SendFactory factory = new SendFactory();
        // 普通工厂模式
        Sender sender = factory.produce("sms");

        // 多个工厂方法模式
        sender = factory.mailSender();

        // 静态工厂模式
        sender = SendFactory.produceMail();


        sender.send();
    }
}
