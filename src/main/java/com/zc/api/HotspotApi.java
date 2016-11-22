package com.zc.api;

import com.zc.bean.HotTopicPrediction;
import com.zc.service.HotTopicPredictionService;
import com.zc.utility.CommonHelper;
import com.zc.utility.response.ApiResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * Created by xyzhuzhou on 2016/11/21 0021.
 */
@RestController
@RequestMapping("/api/hotspot/")
public class HotspotApi {

    @Autowired
    ApplicationContext app;
    @Autowired
    HttpServletRequest request;

    @Autowired
    HotTopicPredictionService hotTopicPredictionService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ApiResultModel addOrEdit(@RequestBody HotTopicPrediction model) {

        Objects.requireNonNull(model);

        ApiResultModel result = new ApiResultModel();

        model.setOperatorUserId(CommonHelper.getCurrentUserId());

        if (model.getId() != null && model.getId() > 0) {
            return result.data(hotTopicPredictionService.update(model));
        }
        model.setCreateDate(new Date());
        model.setIsActive(1);


        return result.data(hotTopicPredictionService.add(model));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ApiResultModel getUser(@PathVariable("id") Integer id) {

        ApiResultModel result = new ApiResultModel();

        return result.data(hotTopicPredictionService.get(id));

    }

    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public ApiResultModel getAll() {

        ApiResultModel result = new ApiResultModel();

        return result.data(hotTopicPredictionService.getAll());
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ApiResultModel del(@PathVariable("id") Integer id) {

        Objects.requireNonNull(id);

        return new ApiResultModel().data(hotTopicPredictionService.del(id));
    }

    @RequestMapping(value = "{id}/state", method = RequestMethod.PUT)
    public ApiResultModel updateUserState(@PathVariable("id") Integer id, @RequestParam("state") Integer state) {

        Objects.requireNonNull(id);

        HotTopicPrediction model = hotTopicPredictionService.get(id);
        model.setIsActive(state);

        return new ApiResultModel().data(hotTopicPredictionService.update(model));
    }

//    @RequestMapping(value = "{className}/{command}")
//    public ApiResultModel activeFunction(@PathVariable("className") String className, @PathVariable("command") String
//            command) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException,
//            ClassNotFoundException {
//
//        Objects.requireNonNull(className);
//        Objects.requireNonNull(command);
//
//        ApiResultModel result = new ApiResultModel();
//
//        List<String> items = Arrays.asList(app.getBeanDefinitionNames());
//
//        for (String item : items) {
//
//            if (className.toLowerCase().equals((item + "").toLowerCase())) {
//
//
//                BaseApi base = (BaseApi) app.getBean(item);
//
//                Map<String, String[]> parameterMap = request.getParameterMap();
//
//
//                List<Method> methods = Arrays.asList(base.getClass().getDeclaredMethods());
//
//                Method method = null;
//
////                Method method = base.getClass().getDeclaredMethod(command);
//
//                method = methods.stream().filter(p -> command.toLowerCase().equals(p.getName().toLowerCase()))
// .findFirst
//                        ().get();
//
//
//                Object objResult = null;
//
//                if (parameterMap.size() > 0) {
//
//
//                    List<Object> params = new ArrayList<>();
//
//                    Object[] objs = parameterMap.values().toArray();
//
//                    int i = 0;
//                    for (Class<?> p : method.getParameterTypes()) {
//
//                        Object obj = objs[i];
//
//                        Object cast = p.getClass().cast(obj);
//
//                        params.add(cast);
//
//                        i++;
//                    }
//
//                    //parameterMap.values().stream().map(p -> (Object) p).collect(Collectors.toList());
//
//                    objResult = method.invoke(base, params);
//
//                } else {
//
//                    objResult = method.invoke(base);
//
//                }
//
//                if (objResult != null) {
//
//                    if (objResult instanceof ApiResultModel) {
//                        return (ApiResultModel) objResult;
//                    } else {
//                        return result.data(objResult);
//                    }
//
//                }
//
//                break;
//            }
//        }
//
//
//        return result;
//    }

}
