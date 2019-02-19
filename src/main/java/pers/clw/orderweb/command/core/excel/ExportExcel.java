package pers.clw.orderweb.command.core.excel;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface ExportExcel {
	
	String name();
	
}
