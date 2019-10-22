package com.konka.DataDictionaryCheckTool.utils;

import com.konka.DataDictionaryCheckTool.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务消息
 *
 * @author framework-generator
 * @version 1.0.0
 * @date 2018-07-18
 */
@Slf4j
public class ParamValidationUtil {

    /**
     * 根据JSR303规则判断合法性
     *
     * @throws BusinessException
     */
    public static void check(Object param, int exceptionCode, String exceptionMsg) throws BusinessException {

        log.info("校验参数：{}", param);
        Validator validator = new Validator();

        List<ConstraintViolation> violationList = validator.validate(param);

        if (CollectionUtils.isEmpty(violationList)) {
            log.info("校验通过");
            return;
        }

        if (violationList.size() > 0) {

            List<String> errorList = new ArrayList<String>();

            for (ConstraintViolation constraintViolation : violationList) {

                errorList.add(constraintViolation.getMessage());

            }
            
            log.info("参数不合法：{}", errorList);

            throw new BusinessException(exceptionCode, exceptionMsg + errorList);
        }

    }

}
