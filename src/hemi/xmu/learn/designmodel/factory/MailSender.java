package hemi.xmu.learn.designmodel.factory;

/**
 * Created by Hemi on 2017/7/29.
 */
public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is mailsender!");
    }
}
