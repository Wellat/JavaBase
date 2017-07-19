package hemi.subject.jobs.test.proxy;

/**
 * Created by yexq on 2017/7/16.
 */
public class CountProxy implements Count {
    private CountImpl countImpl;

    public CountProxy(CountImpl countImpl){
        this.countImpl = countImpl;
    }


    @Override
    public void queryCount() {
        System.out.println("before queryCount");
        countImpl.queryCount();
        System.out.println("after queryCount");
    }

    @Override
    public void updateCount() {
        System.out.println("before updateCount");
        countImpl.updateCount();
        System.out.println("after updateCount");
    }
}
