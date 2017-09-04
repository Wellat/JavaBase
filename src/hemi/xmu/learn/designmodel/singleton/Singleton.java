package hemi.xmu.learn.designmodel.singleton;

/**
 * 这个类可以满足基本要求，但是，像这样毫无线程安全保护的类，
 * 如果我们把它放入多线程的环境下，肯定就会出现问题了
 *
 * Created by Hemi on 2017/7/29.
 */
public class Singleton {

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static Singleton instance = null;

    /* 私有构造方法，防止被实例化 */
    private Singleton() {
    }

    /*
    懒汉模式---DCL双检查锁机制 保证多线程下安全
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}