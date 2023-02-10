package lectureBasic.core.annotation

import org.springframework.beans.factory.annotation.Qualifier
import java.lang.annotation.Documented
import java.lang.annotation.Inherited

@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
annotation class MainDiscountPolicy()
