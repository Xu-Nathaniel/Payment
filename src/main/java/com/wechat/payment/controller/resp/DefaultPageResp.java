package com.wechat.payment.controller.resp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import lombok.Data;


@Data
@ApiModel("默认的分页结果")
public class DefaultPageResp<T> implements Serializable {

  private static final long serialVersionUID = -6204051013664247170L;

  @ApiModelProperty("查询结果集")
  private List<T> content;

  @ApiModelProperty("查询结果集数量")
  private int currentElements;

  @ApiModelProperty("当前页码. 如果页码未设置，则为-1")
  private int currentPage;

  @ApiModelProperty("总页数")
  private long totalPages;

  @ApiModelProperty("总数量")
  private long totalElements;


  public DefaultPageResp() {
  }


  public DefaultPageResp(IPage<T> page) {
    this(-1, page);
  }


  public DefaultPageResp(int currentPage, IPage<T> page) {
    List<T> records = page.getRecords();
    this.content = records;
    this.currentElements = records.size();
    this.totalPages = page.getPages();
    this.totalElements = page.getTotal();
    this.currentPage = currentPage;
  }


  public static <T> DefaultPageResp<T> empty() {
    DefaultPageResp<T> resp = new DefaultPageResp<>();
    resp.setContent(Collections.emptyList());
    resp.setCurrentElements(0);
    resp.setCurrentPage(1);
    resp.setTotalPages(0);
    resp.setTotalElements(0);
    return resp;
  }
}
