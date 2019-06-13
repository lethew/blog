package studio.greeks.blog.restful;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author <a href="http://greeks.studio">吴昭</a>
 * @version blog@2019/6/11 23:21
 */

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {
    private static final String SUCCESS_CODE = "000000";
    private static final String SUCCESS_MSG = "请求成功";
    private String code;
    private String msg;
    private T data;
    private Response(){

    }

    public static <T> Response<T> success(T t){
        return new Response<>(SUCCESS_CODE, SUCCESS_MSG, t);
    }
}
