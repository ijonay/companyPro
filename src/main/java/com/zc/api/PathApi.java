package com.zc.api;

import com.zc.enumeration.StatusCodeEnum;
import com.zc.model.path.NodeRelations;
import com.zc.model.path.PathModel;
import com.zc.service.PathService;
import com.zc.utility.EffectLog;
import com.zc.utility.ListHelper;
import com.zc.utility.ValidateHelper;
import com.zc.utility.response.ApiResultModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * Created by 张镇强 on 2016/8/23 14:39.
 */
@RestController
@RequestMapping("/api/paths/")
public class PathApi extends BaseApi {
    @Autowired
    private PathService pathService;

    @RequestMapping("{topicId}")
    public ApiResultModel getPaths(@PathVariable("topicId") Integer topicId,
                                   @Valid @ModelAttribute() PathParamWrapper pathParamWrapper,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ValidateHelper.handleFieldValidateErrors(bindingResult);
        }

        EffectLog log = new EffectLog("pathApi");

        List<PathModel> paths = pathService.getPaths(topicId, pathParamWrapper.query);

        log.add("1");

        log.writeToConsole();

        ApiResultModel resultModel = new ApiResultModel();
        if (ListHelper.isEmpty()) {
            resultModel.setStatusCode(StatusCodeEnum.NOCONTENT);
            return resultModel;
        }

        resultModel.setData(paths);

        return resultModel;
    }

    @RequestMapping("nodeRelations")
    public ApiResultModel getNodeRelations(@Valid @ModelAttribute() NodeRelationParamWrapper nodeRelationParamWrapper,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ValidateHelper.handleFieldValidateErrors(bindingResult);
        }
        NodeRelations relations = pathService.getRelations(nodeRelationParamWrapper.startNode,
                nodeRelationParamWrapper.endNode);

        ApiResultModel resultModel = new ApiResultModel(relations, StatusCodeEnum.SUCCESS);

        return resultModel;
    }

    @RequestMapping(value = "pathsearch", method = RequestMethod.GET)
    public ApiResultModel getPathSearch(@RequestParam("start") String start, @RequestParam("end") String end) {

        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        ApiResultModel result = new ApiResultModel();

        return result.data(pathService.getPathSearch(start, end));

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

    public static class NodeRelationParamWrapper {
        @NotEmpty
        private String startNode;
        @NotEmpty
        private String endNode;

        public String getStartNode() {
            return startNode;
        }

        public void setStartNode(String startNode) {
            this.startNode = startNode;
        }

        public String getEndNode() {
            return endNode;
        }

        public void setEndNode(String endNode) {
            this.endNode = endNode;
        }
    }
}
