package hemi.learn.designmodel.decorator;

/**
 * @author yexq
 * @date 2017/7/30
 * @info
 */
public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
