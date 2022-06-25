package com.wechat.payment.config;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wechat.payment.utils.DateTimeUtil;
import java.time.LocalDateTime;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 填充表字段
 */
@Component
public class FieldFillHandler implements MetaObjectHandler {

  /**
   * table field name
   */
  private static final String CREATED_AT = "createdAt";
  private static final String UPDATED_AT = "updatedAt";

  @Override
  public void insertFill(MetaObject metaObject) {
    LocalDateTime now = DateTimeUtil.now().toLocalDateTime();
    if (metaObject.hasGetter("CREATED_AT")) {
      if (LocalDateTime.class == metaObject.getGetterType(CREATED_AT)
          && ObjectUtil.isNull(metaObject.getValue(CREATED_AT))) {
        this.setFieldValByName(CREATED_AT, now, metaObject);
      }
    }
    this.setFieldValByName(CREATED_AT, now, metaObject);
    this.setFieldValByName(UPDATED_AT, now, metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    LocalDateTime now = DateTimeUtil.now().toLocalDateTime();
    this.setFieldValByName(UPDATED_AT, now, metaObject);
  }
}
