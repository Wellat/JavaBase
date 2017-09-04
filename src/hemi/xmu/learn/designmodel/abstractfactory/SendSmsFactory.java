package hemi.xmu.learn.designmodel.abstractfactory;

/**
 * Created by Hemi on 2017/7/29.
 */
public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
