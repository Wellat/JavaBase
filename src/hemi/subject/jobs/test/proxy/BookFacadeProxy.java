package hemi.subject.jobs.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yexq
 * @date 2017/7/17
 * @info
 */
public class BookFacadeProxy implements InvocationHandler {
    private Object target;

    public Object bind(Object object){
        this.target = object;
        //取得代理对象
        //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("事务开始...");
        result = method.invoke(target,args);
        System.out.println("事务结束...");
        return result;
    }
}
