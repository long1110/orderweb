package pers.clw.orderweb.command.core.util;

import org.apache.commons.lang.StringUtils;
import pers.clw.orderweb.command.core.model.GenFieldEntity;

import java.util.List;

/**
 * 表处理通用类
 *
 * @author JueYue on 2017/10/25.
 */
public class TableHanlderUtil {

    private TableHanlderUtil() {

    }

    /**
     * 处理一遍字段,根据字段类型做合适的处理
     */
    public static void hanlderFields(List<GenFieldEntity> fields) {
        GenFieldEntity entity;
        for (int i = 0, le = fields.size(); i < le; i++) {
            entity = fields.get(i);
            entity.setChinaName(getFieldName(entity.getFieldName(), entity.getComment()));
            if (entity.getChinaName().equals(entity.getComment())) {
                entity.setComment(null);
            }
            entity.setName(NameUtil.getFieldHumpName(entity.getFieldName()));
            entity.setType(convertType(entity.getFieldType(), entity.getFieldPointLength()));
        }
    }

    /**
     * 处理字段名称
     */
    public static String getFieldName(String fieldName, String comment) {
        if (StringUtils.isNotEmpty(comment)) {
            String[] nameAndComment = comment.split(",");
            return nameAndComment[0];
        }
        return NameUtil.getEntityHumpName(fieldName);
    }

    /**
     * 转换类型
     */
    public static String convertType(String dataType, Integer pointLength) {
        dataType = dataType.toLowerCase();
        if (dataType.contains("char") || dataType.contains("text")) {
            dataType = "String";
        } else if (dataType.contains("int")) {
            dataType = "Integer";
        } else if (dataType.contains("float")) {
            dataType = "Float";
        } else if (dataType.contains("double")) {
            dataType = "Double";
        } else if (dataType.contains("number")) {
            if (pointLength != null && pointLength > 0) {
                dataType = "Double";
            }/* else if (StringUtils.isNotEmpty(precision) && Integer.parseInt(precision) > 10) {
                dataType = "Long";
            } else {
                dataType = "Integer";
            }*/
        } else if (dataType.contains("decimal")) {
            dataType = "BigDecimal";
        } else if (dataType.contains("date")) {
            dataType = "Date";
        } else if (dataType.contains("time")) {
            dataType = "Date";
        } else if (dataType.contains("blob")) {
            dataType = "byte[]";
        } else if (dataType.contains("clob")) {
            dataType = "java.sql.Clob";
        } else if (dataType.contains("numeric")) {
            dataType = "BigDecimal";
        } else {
            dataType = "Object";
        }
        return dataType;
    }
}
