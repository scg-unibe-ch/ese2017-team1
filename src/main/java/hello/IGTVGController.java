package hello;

import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Working with the documentation from thymeleaf:
 * http://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html
 */


public interface IGTVGController {

    public void process(
            HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, TemplateEngine templateEngine);

}