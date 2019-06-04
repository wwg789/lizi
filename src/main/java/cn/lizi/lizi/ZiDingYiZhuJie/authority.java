package cn.lizi.lizi.ZiDingYiZhuJie;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//可以通过反射获取到
@Target(ElementType.METHOD)//在方法上使用
@Documented
@Inherited
public @interface authority {

}
