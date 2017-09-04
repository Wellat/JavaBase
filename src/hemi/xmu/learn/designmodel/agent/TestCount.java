package hemi.xmu.learn.designmodel.agent;

/**
 * 静态代理测试
 * Created by yexq on 2017/7/16.
 */
public class TestCount {
    public static void main(String[] args){
        Count proxy = new CountProxy(new CountImpl());
        proxy.queryCount();
        proxy.updateCount();
    }
}
