package hemi.learn.designmodel.agent;

/**
 * @author yexq
 * @date 2017/7/17
 * @info
 */
public class TestDynProxy {
    public static void main(String[] args){
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookFacade = (BookFacade)proxy.bind(new BookFacadeImpl());

        bookFacade.addBook();
        bookFacade.editBook();

    }
}
