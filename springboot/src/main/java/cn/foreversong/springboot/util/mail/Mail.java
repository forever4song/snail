package cn.foreversong.springboot.util.mail;

import lombok.Data;


/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-27
 * Time: 上午10:03
 * Description: 邮件实体
 */
@Data
public class Mail {
    private String[] receiver;  // 收件人列表

    private String subject; //  邮件标题

    private String text;    // 邮件内容

    private String[] carbonCopy;    // 抄送人列表

}
