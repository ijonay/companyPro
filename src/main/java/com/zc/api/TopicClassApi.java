package com.zc.api;

import com.zc.bean.TopicClass;
import com.zc.enumeration.SearchTypeEnum;
import com.zc.model.searchmodel.SearchClassItemModel;
import com.zc.service.TopicClassService;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xyzhuzhou on 2016/11/4 0004 14:38:02.
 */

@RestController
@RequestMapping("/api/topicclass")
public class TopicClassApi extends BaseApi {

    @Autowired
    private TopicClassService topicClassService;

    /**
     * @return
     */
    @RequestMapping(value = "getsearchitem", method = RequestMethod.GET)
    public ApiResultModel getSearchItem() {

        ApiResultModel result = new ApiResultModel();

        HashMap<String, List<SearchClassItemModel>> maps = new HashMap<>();

        maps.put(SearchTypeEnum.EventClass.name(), getEventClass());
        maps.put(SearchTypeEnum.UserClass.name(), getSearchClassByType(SearchTypeEnum.UserClass));
        maps.put(SearchTypeEnum.Gender.name(), getSearchClassByType(SearchTypeEnum.Gender));
        maps.put(SearchTypeEnum.Area.name(), getSearchClassByType(SearchTypeEnum.Area));
        maps.put(SearchTypeEnum.Education.name(), getSearchClassByType(SearchTypeEnum.Education));

        return result.data(maps);

    }

    private List<SearchClassItemModel> getEventClass() {

        List<TopicClass> list = topicClassService.getByType(SearchTypeEnum.EventClass.getValue());

        List<TopicClass> parentList = list.stream().filter(p -> StringUtils.isEmpty(p.getParentId())).collect(Collectors
                .toList());

        List<SearchClassItemModel> result = new ArrayList<>();

        parentList.forEach(p -> {

            SearchClassItemModel item = new SearchClassItemModel(p);

            List<SearchClassItemModel> childs = new ArrayList<>();

            list.stream().filter(q -> p.getParentId().equals(q.getId())).forEach(q -> childs
                    .add(new SearchClassItemModel(q)));

            result.add(item);

        });


        return result;
    }

    private List<SearchClassItemModel> getSearchClassByType(SearchTypeEnum searchType) {

        List<TopicClass> list = topicClassService.getByType(searchType.getValue());

        List<SearchClassItemModel> result = new ArrayList<>();

        list.forEach(p -> result.add(new SearchClassItemModel(p)));

        return result;

    }

}
