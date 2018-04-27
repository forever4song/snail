package cn.foreversong.springboot.util.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * ===========================
 * User: 长歌
 * Date: 18-4-27
 * Time: 上午11:45
 * Description: ${DESCRIPTION}
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailUtilTest {

    @Autowired
    private MailUtil mailUtil;

    @Test
    public void sendSampleMail() {

        System.out.println(mailUtil);
        Mail mail = new Mail();
        mail.setSubject("测试邮件标题");
        mail.setText("测试邮件内容");
        String[] receiver = new String[]{};
        receiver[0] = "baichen@sunline.cn";
        mail.setReceiver(receiver);
        mailUtil.sendSampleMail(mail);
    }

    @Test
    public void sendHtmlMail(){
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "<img id=\"bigImg\" src=\"https://desk-fd.zol-img.com.cn/t_s960x600c5/g5/M00/0E/09/ChMkJlrdl9CIXXkZAAMwWioPodwAAn0zwEdOVkAAzBy277.jpg\" width=\"960\" height=\"600\">"+
                "</body>\n" +
                "</html>";
        Mail mail = new Mail();
        mail.setSubject("HTML邮件");
        mail.setText(content);
        String[] receiver = new String[]{"baichen@sunline.cn"};
        String[] copyTo = new String[]{"99924632@qq.com","lishida@sunline.cn"};
        mail.setReceiver(receiver);
        mail.setCarbonCopy(copyTo);
        mailUtil.sendHtmlMail(mail);
    }

    @Test
    public void sendTmplMail(){
        Mail mail = new Mail();
        mail.setSubject("模板邮件");
        mail.setReceiver(new String[]{"99924632@qq.com"});
        mail.setCarbonCopy(new String[]{"forever4song@163.com"});
        Map<String,Object> params = new HashMap<>();
        params.put("id","001");
        mailUtil.sendTmplMail(mail, "emailTemplate",params);
    }
}