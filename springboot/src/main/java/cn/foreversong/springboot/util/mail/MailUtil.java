package cn.foreversong.springboot.util.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-27
 * Time: 上午10:35
 * Description: 邮件工具类
 */
@Slf4j
@Service("mailUtil")
public class MailUtil {


    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单邮件
     * @param mail 邮件实体
     */
    public void sendSampleMail(Mail mail){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        message.setTo(mail.getReceiver());
        message.setCc(mail.getCarbonCopy());
        try{
            mailSender.send(message);
            log.info("简单邮件,已经发送");
        }catch (Exception e){
            log.error("简单邮件发送异常!",e);
        }
    }

    /**
     * 发送HTML邮件
     * @param mail 邮件实体
     */
    public void sendHtmlMail(Mail mail) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true 表示需要创建一个 multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getReceiver());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText(), true);
            helper.setCc(mail.getCarbonCopy());
            mailSender.send(message);
            log.info("html邮件发送成功");
        } catch (MessagingException e) {
            log.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * 发送带附件的邮件
     * @param mail 邮件
     * @param filePathList 附件路径列表
     */
    public void sendAttachmentsMail(Mail mail, List<String> filePathList){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(mail.getReceiver());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getText(), true);
            helper.setCc(mail.getCarbonCopy());
            if(filePathList.size() > 0){
                for (String filePath :filePathList) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = file.getFilename();
                    helper.addAttachment(fileName, file);
                }
            }
            mailSender.send(message);
            log.info("带附件的邮件已经发送");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件发生异常!",e);
        }
    }

    /**
     * 发送模板邮件
     * @param mail  邮件
     * @param tmplName  模板名称
     * @param params    参数
     */
    public void sendTmplMail(Mail mail,String tmplName,Map<String,Object> params){
        Context context = new Context();
        params.forEach((K,V) -> {
            context.setVariable(K,V);
        });
        String emailContent = templateEngine.process(tmplName, context);
        mail.setText(emailContent);
        sendHtmlMail(mail);
    }
}
