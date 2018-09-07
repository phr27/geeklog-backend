package com.geeklog.common.errorhandler;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geeklog.common.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author 潘浩然
 * 创建时间 2018/09/07
 * 功能：全局异常处理器
 */
@Controller
public class GlobalErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @author 潘浩然
     * 创建时间 2018/09/07
     * 功能：对 404, 405 之类的全局异常进行处理，将 spring boot 默认的异常 json 转换为 ResponseEntity，保证响应体格式一致
     */
    @RequestMapping(PATH)
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(new ServletRequestAttributes(request), false);
        int code = (int) attributes.get("status");
        String message = (String) attributes.get("error");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code);

        Writer writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(ResponseEntity.build(code, message)));
        writer.close();
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
