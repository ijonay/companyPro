package com.zc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zc.model.HotTopicPredictionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.bean.HotTopicPrediction;
import com.zc.dao.HotTopicPredictionMapper;

@Service
public class HotTopicPredictionServiceImpl implements HotTopicPredictionService {

    @Autowired
    private HotTopicPredictionMapper hotTopicPredictionMapper;
    
    @Override
    public boolean add(HotTopicPrediction record) {
        return hotTopicPredictionMapper.add(record) > 0;
    }
    
    @Override
    public boolean del(Integer id) {
        return hotTopicPredictionMapper.del(id) > 0;
    }
    
    @Override
    public boolean update(HotTopicPrediction record) {
       return hotTopicPredictionMapper.update(record) > 0;
    }

    @Override
    public HotTopicPrediction get(Integer id) {
        return hotTopicPredictionMapper.get(id);
    }

    @Override
    public List<HotTopicPredictionModel> getHotTopicEventList(String dateStr) {
        List<HotTopicPredictionModel> hotTopicEventList = hotTopicPredictionMapper.getHotTopicEventList(dateStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date currentDate = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date lastDayOfMonth = calendar.getTime();

            calendar.setTime(currentDate);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            Date firstDayOfMonth = calendar.getTime();

            for(int i=0;i < hotTopicEventList.size(); i++){
                HotTopicPredictionModel event = hotTopicEventList.get(i);
                String startDateStr =event.getStartDate();
                Date eventStartDate = sdf.parse(startDateStr);
                String endDateStr = event.getEndDate();
                Date eventEndDate = sdf.parse(endDateStr);
                if( eventStartDate.before(firstDayOfMonth) &&
                        eventEndDate.after( lastDayOfMonth )
                        ){
                    event.setStartDate( sdf.format(firstDayOfMonth) );
                    event.setEndDate( sdf.format(lastDayOfMonth) );
                }else if( eventStartDate.before( firstDayOfMonth ) && eventEndDate.before( lastDayOfMonth ) ){
                    event.setStartDate( sdf.format(firstDayOfMonth) );
                }else if( eventStartDate.before(lastDayOfMonth) && eventEndDate.after( lastDayOfMonth )){
                    event.setEndDate( sdf.format(lastDayOfMonth) );
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hotTopicEventList;
    }
}
