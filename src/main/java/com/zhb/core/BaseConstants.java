package com.zhb.core;

/**
 * 基本常量类
 * @author HuangBinghong
 */
public class BaseConstants {

	/** 跳转至各个页面时，要添加到前台Global对象中的属性map在request中的标识符，PageSupport类会将该map里的名值对复制到Global对象中 */
	public static final String PAGE_GLOBAL_VALUES_IDENTIFIER = "GLOBAL_VALUES";
	
	/** 匿名用户id */
	public static final String ANONYMOUS_USER_ID = "anonymous";

    /** 全局配置的用户名称 */
    public static final String GLOBAL_CONFIG_USERNAME = "GLOBAL";

    /** 标红用的颜色 */
    public static final String HIGHTLIGHT_COLOR = "red";

    /** 配置类型：检索门户的专题配置 */
    public static final int CONFIG_TYPE_SUBJECT = 10;
    /** 配置类型：检索门户的排行榜配置 */
    public static final int CONFIG_TYPE_TOP = 11;
    /** 配置类型：首页的热门配置 */
    public static final int CONFIG_TYPE_HOT = 12;
    /** 配置类型：首页的订阅配置 */
    public static final int CONFIG_TYPE_SUBSCRIPTION = 13;
    /** 配置类型：检索门户的海报配置 */
    public static final int CONFIG_TYPE_POSTER = 14;
    /** 配置类型：检索门户的个人中心配置 **/
    public static final int CONFIG_TYPE_MY_SPACE = 15;
    /** 配置类型：检索门户的最新汇聚配置 **/
    public static final int CONFIG_TYPE_CONVERGE = 16;
    /** 配置类型：公告配置 **/
    public static final int CONFIG_TYPE_NOTICE = 17;

    /** 配置类型：推荐资源 */
    public static final int CONFIG_TYPE_RECOMMENDRESOURCE = 20;
    /** 配置类型：联想词配置 */
    public static final int CONFIG_TYPE_GUESSWORDS = 21;
    /** 配置类型：检索结果属性配置*/
    public static final int CONFIG_TYPE_SEARCH_RESULT_METADATA = 22;
    /** 配置类型：QuickView区域属性配置*/
    public static final int CONFIG_TYPE_QUICKVIEW_METADATA = 23;

    /** 配置类型：检索条件树配置*/
    public static final int CONFIG_TYPE_SEARCH_CONDITION_TREE = 30;
    /** 配置类型：检索高级条件配置*/
    public static final int CONFIG_TYPE_SEARCH_ADV_CONDITION = 31;
    /** 配置类型：高级检索页面条件配置*/
    public static final int CONFIG_TYPE_ADV_SEARCH_PAGE_CONDITION = 32;
    /** 配置类型：资源管理页面条件配置*/
    public static final int CONFIG_TYPE_PROJECT_PAGE_CONDITION = 33;
    /** 配置类型：自动主题条件配置*/
    public static final int CONFIG_TYPE_AUTO_SUBJECT_CONDITION = 34;
    /** 配置类型：待删除资源条件模板配置*/
    public static final int CONFIG_TYPE_TODEL_RESOURCE_CONDITION_TEMPLATE = 36;
    /** 配置类型：默认高级检索条件配置*/
    public static final int CONFIG_TYPE_DEFAULT_SEARCH_ADV_CONDITION = 37;

    /** 配置类型：详情页面编目属性配置*/
    public static final int CONFIG_TYPE_DETAIL_METADATA = 40;
    /** 配置类型：详情页面基本信息属性配置*/
    public static final int CONFIG_TYPE_DETAIL_BASIC_METADATA = 41;
    /**配置类型：详情页面要显示的页签配置*/
    public static final int CONFIG_TYPE_DETAIL_TAB_DISPLAY = 42;
    /**配置类型：详情页面新闻串联单条目表格配置*/
    public static final int CONFIG_TYPE_DETAIL_NEWSLIST_ITEM_GRID = 43;

    /**配置类型：页头的模块显示配置*/
    public static final int CONFIG_TYPE_MODULE_DISPLAY = 50;

    /**内审重播重审记录审核结果对应编目属性组表名及字段配置 **/
    public static final int CONFIG_TYPE_CONTENTAUDIT_NOTE = 61;

    /**技审重播重审记录审核结果对应编目属性组表名及字段配置 **/
    public static final int CONFIG_TYPE_TECHAUDIT_NOTE = 62;

    /** 配置类型：下载管理的下载资源列配置 **/
    public static final int CONFIG_TYPE_DOWNLOAD = 70;

    /**用户的账户配置(目前只包含提醒配置) **/
    public static final int CONFIG_TYPE_USER_ACCOUNT = 100;

    /**
     * 排行榜类型 *
     */
    public static final int TOP_HOT_TYPE_NEWEST = 0;//最新入库
    public static final int TOP_HOT_TYPE_DOWNLOADS_MOST = 1;//最多下载
    public static final int TOP_HOT_TYPE_VIEWS_MOST = 2;//最多浏览

    /**
     * 个人中心显示类型 *
     */
    public static final int MY_SPACE_TYPE_SUBSCIBE = 0;//我的订阅
    public static final int MY_SPACE_TYPE_COLLECT = 1;//我的收藏
    public static final int MY_SPACE_TYPE_RECOMMEND = 2;//为我推荐
    public static final int MY_SPACE_TYPE_UPLOAD = 3;//我的上传
    public static final int MY_SPACE_TYPE_VIEWS_RECORD = 4;//观看记录

    /**
     * 资源来源类型
     */
    public static final String CONVERGE_SOURCE_ALL = "all";             //全部
    public static final String CONVERGE_SOURCE_SELF = "self";           //自采,几种类型的总称：上载、mamwebservice、agent
    public static final String CONVERGE_SOURCE_WISE = "wise";           //外电
    public static final String CONVERGE_SOURCE_WEBPAGE = "webpage";     //网页抓取
    public static final String CONVERGE_SOURCE_TWEET = "weibo";         //微博
    public static final String CONVERGE_SOURCE_WECHAT = "weixin";       //微信
    public static final String CONVERGE_SOURCE_NEWSITEM = "newsitem";   //文稿
    public static final String CONVERGE_SOURCE_RECORD = "record";       //收录
    public static final String CONVERGE_SOURCE_DIRWATCH = "dirwatch";   //dirWatch扫描纯视音频文件入库
    public static final String CONVERGE_SOURCE_WEBUPLOAD = "webupload"; //web上传
    public static final String CONVERGE_SOURCE_HOGE = "hoge";           //厚建入库

    /**
     * 资源推荐类型
     */
    public static final int RECOMMEND_TYPE_ALL = 0;     //全部推荐资源
    public static final int RECOMMEND_TYPE_TO_ME = 1;    //他人推荐给我的资源
    public static final int RECOMMEND_TYPE_FROM_ME = 2;   //我推荐给他人的资源

    /**
     * 我的上传资源类型
     */
    public static final int MYUPLOAD_TYPE_ALL = -1;     //全部资源
    public static final int MYUPLOAD_TYPE_PRIVATE = 0;     //私有资源
    public static final int MYUPLOAD_TYPE_PUBLIC = 1;    //共享资源
    
    /** 未知 */
    public static final int DATABASE_TYPE_UNKNOWN = -1; 
    /** sql server */
    public static final int DATABASE_TYPE_SQLSERVER = 0;
    /** oracle */
    public static final int DATABASE_TYPE_ORACLE = 1;
    /** db2 */
    public static final int DATABASE_TYPE_DB2 = 2;
    
    /** 属性类型：未知 */
    public final static int ATTRI_TYPE_UNKNOW = -1;				// 未知
    /** 属性类型：字符串 */
    public final static int ATTRI_TYPE_STRING = 0;				// 字符串
    /** 属性类型：整数 */
    public final static int ATTRI_TYPE_INT = 1;					// 整数
    /** 属性类型：浮点数 */
    public final static int ATTRI_TYPE_FLOAT = 2;					// 浮点数
    /** 属性类型：时间 */
    public final static int ATTRI_TYPE_DATE = 3;					// 时间
    /** 属性类型：时码 */
    public final static int ATTRI_TYPE_TIMECODE = 4;				// 时码
    /** 属性类型：受控词 */
    public final static int ATTRI_TYPE_LIMITED = 5;				// 受控词
    /** 属性类型：大文本 */
    public final static int ATTRI_TYPE_TEXT = 6;					// 大文本
    /** 属性类型：分类 */
    public final static int ATTRI_TYPE_CLASS = 7;					// 分类
    /** 属性类型：叙词表 */
    public final static int ATTRI_TYPE_THESAURUS = 8;				// 叙词表

    /** 富文本资源上传的请求参数 */
    public static final String UE_UPLOAD_ACTION_CONFIG = "config";
    public static final String UE_UPLOAD_ACTION_UPLOADIMAGE = "uploadimage";
    public static final String UE_UPLOAD_ACTION_UPLOADVIDEO = "uploadvideo";

    /** 定制样式 */
    public static final String CUSTOM_THEME_RED = "red";
}
