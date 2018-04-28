package cn.foreversong.snail.springboot.util.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-4-27
 * Time: 下午10:50
 * Description:
 */
public class SampleJob extends QuartzJobBean {

    private String name;

    // Invoked if a Job data map entry with that name
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println(String.format("Hello %s!", this.name));
    }
}
