package hemi.learn.designmodel.agent;

/**
 * @author yexq
 * @date 2017/7/17
 * @info
 */
public class BookFacadeImpl implements BookFacade {
    @Override
    public void addBook() {
        System.out.println("增加图书方法...");
    }

    @Override
    public void editBook() {
        System.out.println("编辑图书方法..");
    }
}
