package com.zc.service;

import com.zc.dao.TopicTrendMapper;
import com.zc.model.TopicTrendModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TopicTrendServiceImpl implements TopicTrendService {

    @Autowired
    private TopicTrendMapper topicTrendMapper;

    @Override
    public List<TopicTrendModel> getTopicTrendHistory(@Param("id") Integer id){

        List<TopicTrendModel> trendModelList = new ArrayList<TopicTrendModel>();

        try{

            int interval = 3;
            List<TopicTrendModel>  trendList = topicTrendMapper.getTopicTrendHistory(id);

            if( trendList!=null && trendList.size() > 0 ){

                Calendar calendar = Calendar.getInstance();

                TopicTrendModel firstModel = trendList.get(0);

                String startDate = firstModel.getCreateDate();
                String startDay = startDate.substring(0,10);

                SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat simpleDateFormatHour = new SimpleDateFormat("yyyy-MM-dd HH");
                Date trendStartHour = simpleDateFormatHour.parse(startDate);
                calendar.setTime( trendStartHour );
                int hourIndex = calendar.get( Calendar.HOUR_OF_DAY );
                int remainder = hourIndex % interval ;
                if(remainder > 0){
                    hourIndex += (interval - remainder);
                }
                firstModel.setCreateDate(startDay + " " + hourIndex + ":00:00");
                trendModelList.add(firstModel);// the first element

                TopicTrendModel endModel = trendList.get(trendList.size() - 1);
                String endDate = endModel.getCreateDate();
                String endDay = endDate.substring(0, 10);
                calendar.setTime( simpleDateFormatHour.parse(endDate) );
                hourIndex = calendar.get( Calendar.HOUR_OF_DAY );
                remainder = hourIndex % interval ;
                if(remainder > 0){
                    hourIndex += (interval - remainder);
                }
                endModel.setCreateDate(endDay + " " + hourIndex + ":00:00");// the last element

                Date startTime = timeFormatter.parse( firstModel.getCreateDate() );
                Date endTime = timeFormatter.parse( endModel.getCreateDate() );
                long diff = endTime.getTime() - startTime.getTime();
                long diffHours = diff / (60 * 60 * 1000);
                calendar.setTime(startTime);    //set the start point
                for(int i=interval; i<diffHours; i+=interval){// this can be optimization
                    calendar.add( Calendar.HOUR_OF_DAY, interval );
                    String tmpTimeString = timeFormatter.format( calendar.getTime() );
                    TopicTrendModel tmpModel = new TopicTrendModel();
                    tmpModel.setCreateDate(tmpTimeString);

                    for(int j=0; j<trendList.size() - 1; j++){

                        TopicTrendModel preTrendModel = trendList.get(j);
                        TopicTrendModel nextTrendModel = trendList.get(j+1);

                        timeFormatter.parse(preTrendModel.getCreateDate());
                        if( timeFormatter.parse(nextTrendModel.getCreateDate()).after( calendar.getTime() ) &&
                                timeFormatter.parse(preTrendModel.getCreateDate()).before(calendar.getTime())
                                ){
                            tmpModel.setPrevailingTrend(preTrendModel.getPrevailingTrend());
                            trendModelList.add(tmpModel);
                            break;
                        }

                    }
                }

                calendar.setTime( simpleDateFormatHour.parse( endModel.getCreateDate() ) );
                trendModelList.add( endModel );
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return trendModelList;

    }


}
