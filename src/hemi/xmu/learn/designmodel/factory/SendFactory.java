package hemi.xmu.learn.designmodel.factory;

/**
 * Created by Hemi on 2017/7/29.
 */
public class SendFactory {

    /**
     * 普通工厂模式
     */
    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型!");
            return null;
        }
    }


    /**
     * 多个工厂模式
     */
    public Sender mailSender(){
        return new MailSender();
    }

    public Sender smsSender(){
        return new SmsSender();
    }

    /**
     * 静态工厂模式
     */
    public static Sender produceMail(){
        return new MailSender();
    }

    public static Sender produceSms(){
        return new SmsSender();
    }


}
