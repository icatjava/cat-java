package cloud.ffeng.cat.spring.strategy.annotation;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * Self-define Strategy Annotation.
 * resume: cat.dev@foxmail.com
 *
 * @author cat-feng
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@Component
public @interface Strategy {


    /**
     * biz
     *
     * @return biz code
     */
    String biz() default "";

    /**
     * strategy type
     *
     * @return type code
     */
    String type();
}
