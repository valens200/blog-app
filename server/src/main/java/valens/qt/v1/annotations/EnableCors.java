package valens.qt.v1.annotations;

import org.springframework.context.annotation.Import;
import valens.qt.v1.config.CorsConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CorsConfig.class)
public @interface EnableCors {
}
