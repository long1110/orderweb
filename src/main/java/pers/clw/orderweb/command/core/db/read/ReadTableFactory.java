package pers.clw.orderweb.command.core.db.read;


import pers.clw.orderweb.command.core.db.exception.GenerationRunTimeException;
import pers.clw.orderweb.command.core.db.read.impl.ReadTableForMysqlImpl;

import static pers.clw.orderweb.command.core.GenCoreConstant.MYSQL;

/**
 * 读取库的工厂
 *
 * @author JueYue
 * @date 2014年12月25日
 */
public class ReadTableFactory {

    public static IReadTable getReadTable(String dbType) {
        if (MYSQL.equalsIgnoreCase(dbType)) {
            return new ReadTableForMysqlImpl();
        }
        throw new GenerationRunTimeException("数据库不支持");
    }

    public static String getDeiver(String dbType) {
        if (MYSQL.equalsIgnoreCase(dbType)) {
            return "com.mysql.jdbc.Driver";
        }
        throw new GenerationRunTimeException("数据库不支持");
    }
}
