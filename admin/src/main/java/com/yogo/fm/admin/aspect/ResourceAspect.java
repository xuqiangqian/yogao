package com.yogo.fm.admin.aspect;

import com.yogo.fm.common.exception.FmException;
import com.yogo.fm.service.system.IResourceService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 作草分茶
 * @Description web请求日志切面
 * @date 2018-04-28
 */
@Aspect
@Component
@Order(2)
public class ResourceAspect {
    private static Logger logger = LoggerFactory.getLogger(ResourceAspect.class);
    private final IResourceService resourceService;

    @Autowired
    public ResourceAspect(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Pointcut("@annotation(com.yogo.fm.admin.annotation.VerifyResource)")
    public void resource() {
    }

    /**
     * 处理请求前
     *
     * @param joinPoint
     */
    @Before("resource()")
    public void doBefore(JoinPoint joinPoint) throws FmException {
/*        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        VerifyResource verifyResource = method.getAnnotation(VerifyResource.class);
        String value = verifyResource.value();
        AccountEntity accountEntity = ThreadLocals.userLocal.get();
        if (accountEntity == null) {
            throw new FmException(FmResponseCode.ACCOUNT_UN_LOGIN);
        }
        List<ResourceEntity> resources = resourceService.findResourceListByRoleId(accountEntity.getRoleId());
        if (!resources.contains(value)) {
            throw new FmException(FmResponseCode.PERMISSION_DENIED);
        }*/
    }

    /**
     * 处理请求结束
     *
     * @param object
     * @throws Throwable
     */
    @AfterReturning(returning = "object", pointcut = "resource()")
    public void doAfterReturning(Object object) throws Throwable {

    }
}
