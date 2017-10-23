package hello;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * The class was created to help us display data from the database.
 * First we need to do some research on how to do it.
 *
 * Working with the documentation from thymeleaf:
 * http://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html
 */

/*public class GTVGApplication {

    private static TemplateEngine templateEngine;

    static {
        initializeTemplateEngine();
    }


    private static void initializeTemplateEngine() {

        ServletContextTemplateResolver templateResolver =
                new ServletContextTemplateResolver();
        // XHTML is the default mode, but we set it anyway for better understanding of code
        templateResolver.setTemplateMode("XHTML");
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // Template cache TTL=1h. If not set, entries would be cached until expelled by LRU
        templateResolver.setCacheTTLMs(3600000L);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }
}*/
