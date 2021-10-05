package com.group.smarthome.utils;

public class JsonResult {
    private Integer code;
    private Object data;
    private String msg;

    /**
     * success
     */
    public static JsonResult isOk(Object data) {
        return new JsonResult(200,data,"SUCCESS");
    }

    /**
     * error
     */
    public static JsonResult isError(Integer code,Object data,String msg) {
        return new JsonResult(code,data,msg);
    }

    public JsonResult() {
        super();
    }

    public JsonResult(Integer code, Object data, String msg) {
        super();
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


