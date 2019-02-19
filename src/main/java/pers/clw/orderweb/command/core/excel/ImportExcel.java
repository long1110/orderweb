package pers.clw.orderweb.command.core.excel;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)//自定义的annotation保存于class档中由vm读入
@Target({ ElementType.FIELD })//设定注解使用范围,field用于域之上
@Documented
public @interface ImportExcel {
	
	String name();
	
}
