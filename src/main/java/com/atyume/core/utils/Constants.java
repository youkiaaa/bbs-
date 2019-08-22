package com.atyume.core.utils;

/**
 * 常量定义
 */
public class Constants {

    /**
     * 当前用户
     */
    public static final String CURRENT_USER = "user";

    /**
     * 权限集合
     */
    public static final String PERMISSIONS = "permissions";

    /**
     * 菜单根id
     */
    public static final Long MENU_ROOT_ID = 1L;

    /**
     * 菜单树
     */
    public static final String MENU_TREE = "menuTree";

    public static final String SHARP = "#";
    /**
     * 组织机构根id
     */
    public static final Long ORG_ROOT_ID = 0L;

    /** 文件上传/图片   根目录 */
    public static final String UPLOAD_PATH = "D:/work/test/img/";

    /** 图片目录 */
    public static final String IMG_FILE_NAME = "img";

    /** 图片相对路径 */
    public static final String VIRTUAL_IMG_PATH = "img/virtual";

    /**
     * 构造函数私有化，避免被实例化
     */
    private Constants() {
    }

}
