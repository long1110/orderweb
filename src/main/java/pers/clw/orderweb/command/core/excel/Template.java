package pers.clw.orderweb.command.core.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * excel格式转换
 * @author Administrator
 *
 */
public interface Template<T> {
	/**
	 * 对字段进行格式转换
	 * @param cell
	 * @param fieldName
	 * @param val
	 * @throws Exception
	 */
	public void format(HSSFCell cell, String fieldName, Object val)  throws Exception;
	
}
