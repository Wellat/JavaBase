package hemi.learn.designmodel.singleton;

/**
 * 线程安全
 * Created by Hemi on 2017/7/29.
 */
public class SingletonWithInnerClass {

    /* 私有构造方法，防止被实例化 */
    private SingletonWithInnerClass() {
    }

    /* 此处使用一个内部类来维护单例 */
    private static class SingletonFactory {
        private static SingletonWithInnerClass instance = new SingletonWithInnerClass();
    }

    /* 获取实例 */
    public static SingletonWithInnerClass getInstance() {
        return SingletonFactory.instance;
    }
}
