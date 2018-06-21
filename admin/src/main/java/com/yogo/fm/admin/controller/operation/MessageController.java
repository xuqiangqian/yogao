package com.yogo.fm.admin.controller.operation;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.common.response.FmResponse;
import com.yogo.fm.common.utils.FmPage;
import com.yogo.fm.common.utils.ListTypeUtils;
import com.yogo.fm.model.operation.content.MessageEntity;
import com.yogo.fm.model.system.ResourceEntity;
import com.yogo.fm.service.operation.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qiqiang
 * @Description 消息
 * @date 2018-05-26
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    private final IMessageService messageService;

    @Autowired
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 通过查询条件和分页信息查询
     *
     * @param message
     * @param fmPage
     * @return
     * @throws FmException
     */
    @GetMapping("/list")
    public FmResponse<FmPage<MessageEntity>> list(MessageEntity message, FmPage<MessageEntity> fmPage) throws FmException {
        return FmResponse.ok("查找成功", messageService.findPage(message, fmPage));
    }

    /**
     * 根据id删除消息
     *
     * @param id
     * @return
     * @throws FmException
     */
    @DeleteMapping("/delete")
    public FmResponse<ResourceEntity> delete(@RequestParam("id") String id) throws FmException {
        List<String> ids = new ArrayList<>(Arrays.asList(id.split(",")));
        messageService.batchDelete(ListTypeUtils.listStringToLong(ids));
        return FmResponse.ok("删除成功");
    }

    /**
     * 通过id查找消息
     *
     * @param id
     * @return
     */
    @GetMapping("/find")
    public FmResponse<MessageEntity> find(@RequestParam("id") Long id) throws FmException {
        return FmResponse.ok("查找消息成功", messageService.find(id));
    }

    @PutMapping("/update")
    public FmResponse<MessageEntity> update(MessageEntity message) throws FmException {
        messageService.update(message);
        return FmResponse.ok("更新消息成功", null);
    }
}
