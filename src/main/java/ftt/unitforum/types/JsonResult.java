package ftt.unitforum.types;

public class JsonResult {
	private int code;
    private String message;
    private String debug;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    public static JsonResult success() {
        JsonResult result = new JsonResult(); 
        
        result.code = 0;
        result.message = "success";
        
        return result;
    }
    
    public static JsonResult success(Object data) {
        JsonResult result = new JsonResult(); 
        
        result.code    = 0;
        result.message = "success";
        result.data    = data;
        
        return result;
    }
    
    public static JsonResult fail() {
        JsonResult result = new JsonResult(); 
        
        result.code    = -1;
        result.message = "fail";
        
        return result;
    }
    
    public static JsonResult fail(int code) {
        JsonResult result = new JsonResult(); 
        
        result.code    = code;
        result.message = "fail";
        
        return result;
    }

    public static JsonResult fail(int code, String message) {
        JsonResult result = new JsonResult(); 
        
        result.code    = code;
        result.message = message;
        
        return result;
    }
    
    public static JsonResult fail(int code, String message, String debug) {
        JsonResult result = new JsonResult(); 
        
        result.code    = code;
        result.message = message;
        result.debug   = debug;
        
        return result;
    }
}
