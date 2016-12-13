package com.zhb.core;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.OutputStream;
import java.io.Serializable;
import java.lang.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zoujuan on 14-3-17.
 */
public abstract class ObjectBase implements Serializable{
    @Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface SetMyAnnotation {
        boolean needView() default true;
    }

    protected String id;

    @SetMyAnnotation(needView = true)
    protected String name;
//    protected int objectType;

//    protected String objectClassID;

    public static String EMPTY_OBJECT = "-";
    public static String EMPTY_JSON_ARRAY = "[]";
    public static String EMPTY_JSON_OBJECT = "{}";
    public static String EVERYONE = "everyone";
    public ObjectBase(){

    }

//    public ObjectBase(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public ObjectBase(String id, String name, int objectType){
        this.id = id;
        this.name = name;
//        this.objectType = objectType;
    }

//    @Column(name="id", nullable=true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    @Column(name="name", nullable=true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getObjectType() {
//        return objectType;
//    }
//
//    public void setObjectType(int objectType) {
//        this.objectType = objectType;
//    }
//
//
//    public String getObjectClassID() {
//        return objectClassID;
//    }
//
//    public void setObjectClassID(String objectClassID) {
//        this.objectClassID = objectClassID;
//    }

    public static String generateID() {
        java.util.UUID uuid = java.util.UUID.randomUUID();
        String id = uuid.toString().toUpperCase();
        id = id.replace("-", "");
        return id;
    }
    
    public int overrideHashCode(String keyOne,String keyTwo) {
		final int prime = 31;
        int result = 1;
        result = prime * result + ((keyOne == null) ? 0 : keyOne.hashCode());
        result = prime * result + ((keyTwo == null) ? 0 : keyTwo.hashCode());
        return result;
	}
    /**
     * 清除操作，具体操作由子类实现
     */
    protected void clear() {
    	
    }
    
    public static boolean isEmpty(String s){
    	if (s == null)
    		return true;
        return s.equals(EMPTY_OBJECT);
    }
	/**
	 * 根据objectType构建serialVersionUID，所有要放入缓存的对象都应调用本方法生成serialVersionUID，否则容易出现序列化对象不一致的报错
	 * @param objectType
	 * @return
	 */
	protected static long buildSerialVersionUID(int objectType) {
		return 7840819529857750150L + objectType;
	}

    protected void print(StringBuffer sb, OutputStream os) {
        print(sb, os);
    }

    public static String getCurrentTime() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String nowString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
        return nowString;
    }

    abstract public void copyForUpdate(ObjectBase object);

    public Map<String, String> convertJSONObjectToMap(JSONObject jsonObject) {
        Map<String, String> map = new HashMap<>();
        for (Iterator iterator = jsonObject.keys(); iterator.hasNext();) {
            String key = (String)iterator.next();
            String value = jsonObject.getString(key);
            map.put(key, value);
        }
        return map;
    }

    public static List convertJSONArrayToList(JSONArray jsonArray, Class clazz, Map classMap) {
        List list = new ArrayList();
        if (jsonArray == null)
            return list;
        for (int i = 0; i < jsonArray.size(); i ++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Object object = JSONObject.toBean(jsonObject, clazz, classMap);
            list.add(object);
        }
        return list;
    }

}
