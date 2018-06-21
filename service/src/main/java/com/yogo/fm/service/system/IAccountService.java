package com.yogo.fm.service.system;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.model.system.AccountEntity;
import com.yogo.fm.service.IBaseService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author qiqiang
 * @Description
 * @date 2018-05-18
 */
public interface IAccountService extends IBaseService<AccountEntity> {
    /**
     * 通过用户名查找账号
     *
     * @param username
     * @return
     */
    AccountEntity findByUsername(String username);

    /**
     * 通过token获取登录信息缓存
     *
     * @param token
     * @return
     */
    AccountEntity findByTokenFromCache(String token);

    /**
     * 通过token设置登录信息缓存
     * @param token
     * @param account
     * @return
     */
    AccountEntity setIntoCacheByToken(String token, AccountEntity account);

    /**
     * 根据token清空缓存
     * @param token
     */
    void clearCacheByToken(String token);

    /**
     * 通过手机号查找账号
     * @param mobile
     * @return
     */
    AccountEntity findByMobile(String mobile);

    /**
     * 查询所有
     * @return
     */
    List<AccountEntity> findAll();

    /**
     * 导入数据
     * @param list
     */
    void importExcel(List<AccountEntity> list) throws FmException;


}
