package pers.clw.orderweb.command.core.db.convert.sql;


import pers.clw.orderweb.command.core.db.convert.sql.impl.SqlConvertOfMysql;
import pers.clw.orderweb.command.core.db.exception.GenerationRunTimeException;

import static pers.clw.orderweb.command.core.GenCoreConstant.*;

/**
 * @author JueYue on 2017/10/25.
 */
public class SqlConvertFactory {

    public static ISqlConvert getReadTable(String dbType) {
        if (MYSQL.equalsIgnoreCase(dbType)) {
            return new SqlConvertOfMysql();
        }
        throw new GenerationRunTimeException("数据库不支持");
    }
}
