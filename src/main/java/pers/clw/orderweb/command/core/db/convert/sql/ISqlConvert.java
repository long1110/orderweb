package pers.clw.orderweb.command.core.db.convert.sql;


import pers.clw.orderweb.command.core.model.GenBeanEntity;

/**
 * @author JueYue on 2017/10/25.
 */
public interface ISqlConvert {

    /**
     * 解析SQL
     */
    public GenBeanEntity parseSql(String sql);
}
