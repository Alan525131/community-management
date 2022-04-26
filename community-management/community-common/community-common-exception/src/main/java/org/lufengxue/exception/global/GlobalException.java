//package org.lufengxue.exception.global;
//
//
//import com.thoughtworks.xstream.core.BaseException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.lufengxue.enums.StatusCode;
//import org.lufengxue.response.Result;
//import org.omg.CORBA.UserException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//import java.util.Objects;
///**
// * 目前所有异常的处理方式都一样, 为了以后的扩展性, 这里还是分开处理.
// */
//@Slf4j
//@RestControllerAdvice
//public class GlobalException {
//
//    public GlobalException() {
//        log.info("[初始化] {} 初始化完成.", "GlobalException");
//    }
//
//    /**
//     * 来源于用户的异常
//     */
//    @ExceptionHandler({UserException.class})
//    //@ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<String> userException(BaseException e) {
//        printError(e);
//        return new Result(false, StatusCode.ERROR,e.getMessage());
//    }
//
//
//    /**
//     * 其它异常
//     */
//    @ExceptionHandler({RuntimeException.class, Exception.class, Throwable.class})
//    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result<String> runtimeException(RuntimeException e) {
//        printError(e);
//        return new Result(false,StatusCode.ERROR,e.getMessage());
//    }
//
//
//    /**
//     * 打印请求属性
//     */
//    public void printError(Throwable ex) {
//        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        StringBuilder sb = new StringBuilder();
//        sb.append("\n全局异常: ");
//        if (ex instanceof BaseException) {
//            sb.append("code: ").append(((BaseException) ex).getCode()).append(" ");
//        }
//        if (ex != null && StringUtils.isNotBlank(ex.getMessage())) {
//            sb.append(ex.getMessage());
//        }
//        // 拼接请求IP
//        sb.append("\n\t请求 IP: ").append(IPUtil.getIpAddr(request));
//        // 拼接请求路径
//        sb.append("\n\t请求路径: ").append(request.getRequestURL().toString());
//        // 拼接请求头
//        sb.append("\n\t请求头: ");
//        String[] headers = properties.getHeaders();
//        for (String headerName : headers) {
//            String header = request.getHeader(headerName);
//            if (StringUtils.isNotEmpty(header)) {
//                sb.append("\n\t\t").append(headerName).append(": ").append(header);
//            }
//        }
//
//        // 拼接参数
//        Map<String, String[]> params = request.getParameterMap();
//        if (!params.isEmpty()) {
//            sb.append("\n\t请求参数: ");
//            for (String key : params.keySet()) {
//                sb.append("\n\t\t").append(key).append(" : ");
//                String[] val = params.get(key);
//                if (val == null) {
//                    sb.append("null");
//                } else {
//                    sb.append(String.join(",", val));
//                }
//            }
//        }
//        // 打印错误级别日志
//        log.error(sb.toString(), ex);
//
//
//}
