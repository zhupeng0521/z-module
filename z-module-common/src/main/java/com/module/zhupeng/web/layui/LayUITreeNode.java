/**
 * FileName: LayUITreeNode
 * Author:   DONGSK
 * Datetime: 2020/4/1 14:33
 * Description: LayUI TreeNode
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.web.layui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * LayUITreeNode
 * LayUI TreeNode
 *
 * @author DONGSK
 * @date 2020/4/1
 * @since 1.0.0
 */
public abstract class LayUITreeNode implements Serializable {

    private static final long serialVersionUID = 5046267569060880530L;

    private String title;
    private String id;
    private String field;
    private boolean spread = false;
    private boolean checked = false;
    private boolean disabled = false;
    private List<LayUITreeNode> children = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public List<LayUITreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<LayUITreeNode> children) {
        this.children = children;
    }

    public void addChildren(LayUITreeNode node) {
        this.children.add(node);
    }

    public abstract LayUITreeNode nodeSpread(String... keys);

}
