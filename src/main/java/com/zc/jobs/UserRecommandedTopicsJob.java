package com.zc.jobs;

import com.zc.service.UserRecommendedTopicsService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by zhangcl on 2016/11/11.
 */
public class UserRecommandedTopicsJob extends QuartzJobBean{

    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        try{
            SchedulerContext schCtx = context.getScheduler().getContext();
            //获取Spring中的上下文
            ApplicationContext appCtx = (ApplicationContext)schCtx.get("applicationContext");
            UserRecommendedTopicsService userRecommendedTopicsService =
                    (UserRecommendedTopicsService)appCtx.getBean("userRecommendedTopicsService");
            userRecommendedTopicsService.updateUserRecommendedTopics();
            System.out.println("====UserRecommandedTopicsJob====" + System.currentTimeMillis() );
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
