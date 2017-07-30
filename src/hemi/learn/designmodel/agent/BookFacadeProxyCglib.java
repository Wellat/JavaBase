package hemi.learn.designmodel.agent;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yexq
 * @date 2017/7/17
 * @info
 */
public class BookFacadeProxyCglib implements MethodInterceptor{
    private Object target;
    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        //回调方法
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("事务开始...");
        methodProxy.invokeSuper(o,objects);
        System.out.println("事务结束...");
        return null;
    }
}
