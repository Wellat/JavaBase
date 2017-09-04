package hemi.xmu.learn.designmodel.abstractfactory;

/**
 * Created by Hemi on 2017/7/29.
 */
public class SendMailFactory implements Provider{
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
