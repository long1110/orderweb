package pers.clw.orderweb.command.core.exception;

public class ApplicationException extends RuntimeException{
    /**  */
    private static final long serialVersionUID = 2810043502787154207L;

    private int code;

    private String message;

    private Object data;

    public ApplicationException() {
        code=1;
        message="操作失败!";
    }

    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public ApplicationException(int code, String message, Object data) {
        super(message);
        this.code = code;
        this.message = message;
        this.data=data;
    }

    public ApplicationException(String message) {
        super(message);
        this.code=1;
        this.message = message;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
