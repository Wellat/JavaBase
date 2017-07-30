package hemi.learn.designmodel.abstractfactory;

import hemi.learn.designmodel.abstractfactory.Sender;

/**
 * Created by Hemi on 2017/7/29.
 */
public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }
}
