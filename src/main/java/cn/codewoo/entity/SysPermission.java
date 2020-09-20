package cn.codewoo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission implements Serializable {
    private String id;

    private String code;

    private String name;

    private String perms;

    private String url;

    private String method;

    private String pid;

    //父菜单权限名称
    private String pidName;

    private Integer orderNum;

    private Integer type;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private static final long serialVersionUID = 1L;


}