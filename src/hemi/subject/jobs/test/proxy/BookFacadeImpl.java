package hemi.subject.jobs.test.proxy;

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
}
