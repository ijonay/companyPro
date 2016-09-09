package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.ApiResultModel;
import com.zc.model.path.PathNode;
import com.zc.service.PathService;
import com.zc.utility.ValidateHelper;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 张镇强 on 2016/8/23 14:39.
 */
@RestController
@RequestMapping("/api/paths")
public class PathApi {
    @Autowired
    private PathService pathService;

    @RequestMapping("{topicId}")
    public ApiResultModel getPaths(@PathVariable("topicId") Integer topicId,
                                   @Valid @ModelAttribute() PathParamWrapper pathParamWrapper,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ValidateHelper.handleFieldValidateErrors(bindingResult);
        }

        List<LinkedList<PathNode>> paths = pathService.getPaths(topicId, pathParamWrapper.query);
        ApiResultModel resultModel = new ApiResultModel();
        if (paths.size() < 1) {
            resultModel.setStatusCode(StatusCodeEnum.NOCONTENT);
            return resultModel;
        }

        resultModel.setData(paths);

        return resultModel;
    }

    public static class PathParamWrapper {
        @NotEmpty
        private String query;


        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }
    }
}
