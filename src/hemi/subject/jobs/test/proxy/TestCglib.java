package hemi.subject.jobs.test.proxy;

/**
 * @author yexq
 * @date 2017/7/19
 * @info
 */
public class TestCglib {
    public static void main(String[] arg){
        BookFacadeProxyCglib cglib = new BookFacadeProxyCglib();
        BookFacadeImpl bookFacade = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
        bookFacade.addBook();
    }
}