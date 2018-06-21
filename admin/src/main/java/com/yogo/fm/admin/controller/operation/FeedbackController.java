package com.yogo.fm.admin.controller.operation;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.ListTypeUtils;
import com.yogo.fm.model.operation.content.FeedbackEntity;
import com.yogo.fm.service.operation.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-30
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final IFeedbackService feedbackService;

    @Autowired
    public FeedbackController(IFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    @GetMapping("/find")
    public FmResponse<FeedbackEntity> find(@RequestParam("id") Long id) throws FmException {
        FeedbackEntity feedbackEntity = feedbackService.find(id);
        feedbackEntity.setLookOver(true);
        feedbackService.update(feedbackEntity);
        return FmResponse.ok("查找账号成功", feedbackEntity);
    }
    @DeleteMapping("/delete")
    public FmResponse delete(@RequestParam("id") String id) throws FmException {
        List<String> ids = new ArrayList<>(Arrays.asList(id.split(",")));
        feedbackService.batchDelete(ListTypeUtils.listStringToLong(ids));
        return FmResponse.ok("删除成功");
    }

    @GetMapping("/list")
    public FmResponse<FmPage<FeedbackEntity>> findPage(FeedbackEntity feedbackEntity, FmPage<FeedbackEntity> fmPage) throws FmException {
        return FmResponse.ok("查找成功", feedbackService.findPage(feedbackEntity, fmPage));
    }

    @PutMapping("/lookOver")
    public FmResponse enable(FeedbackEntity entity) throws FmException {
        feedbackService.update(entity);
        return FmResponse.ok("success");
    }
}
