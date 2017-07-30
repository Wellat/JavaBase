package hemi.learn.designmodel.agent;

/**
 * Created by yexq on 2017/7/16.
 */
public class CountImpl implements Count {
    @Override
    public void queryCount() {
        System.out.println("----------queryCount");
    }

    @Override
    public void updateCount() {
        System.out.println("==========updateCount");
    }
}
