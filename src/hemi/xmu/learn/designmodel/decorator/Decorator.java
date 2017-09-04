package hemi.xmu.learn.designmodel.decorator;

/**
 * @author yexq
 * @date 2017/7/30
 * @info
 */
public class Decorator implements Sourceable {

    private Sourceable source;

    public Decorator(Sourceable source){
        super();
        this.source = source;
    }
    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }
}
