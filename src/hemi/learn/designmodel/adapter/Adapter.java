package hemi.learn.designmodel.adapter;

/**
 * @author yexq
 * @date 2017/7/30
 * @info
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
