/**
 * FileName: LayUIModel
 * Author:   DONGSK
 * Datetime: 2020/4/3 14:03
 * Description: LayUI数据查询模型
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.web.layui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * LayUIModel
 * LayUI数据查询模型
 *
 * @author DONGSK
 * @date 2020/4/3
 * @since 1.0.0
 */
public class LayUIModel implements Serializable {
    private static final long serialVersionUID = -7650435021609748823L;

    /**
     * 是否导出，0=默认查询，1，导出模板，2，导出当前页，3导出所有符合条件的数据
     */
    private int option = 0;

    private int page = 1;
    private int limit = 10;
    Map<String, Object> data = new HashMap<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}
