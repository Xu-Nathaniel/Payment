package com.wechat.payment.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wechat.payment.utils.DateTimeUtil;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 用于业务Entity继承
 */
@Data
public abstract class AbstractEntity implements Serializable {

  private static final long serialVersionUID = -8087221823680082795L;

  @TableId(value = "id", type = IdType.AUTO)
  private String id;

  @JsonFormat(pattern = DateTimeUtil.DATE_TIME_FORMATTER, timezone = DateTimeUtil.ZONE_ID)
  @TableField(value = "created_at", fill = FieldFill.INSERT)
  private LocalDateTime createdAt;

  @JsonFormat(pattern = DateTimeUtil.DATE_TIME_FORMATTER, timezone = DateTimeUtil.ZONE_ID)
  @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updatedAt;

}
