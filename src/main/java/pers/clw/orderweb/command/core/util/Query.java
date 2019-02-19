package pers.clw.orderweb.command.core.util;



import pers.clw.orderweb.command.core.xss.SQLFilter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-14 23:15
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//当前页码
    private int page;
    //每页条数
    private int limit;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        if(params.get("page")==null) {
        	 this.page =1;
        }else{
        	 this.page = Integer.parseInt(params.get("page").toString());
        }
        if(params.get("limit")==null) {
       	 this.page =500;
       }else{
    	   this.limit = Integer.parseInt(params.get("limit").toString());
       }
     
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        if(params.get("sidx")!=null){
        	 String sidx = params.get("sidx").toString();
        	 this.put("sidx", SQLFilter.sqlInject(sidx));
        }
       if( params.get("order")!=null){
           String order = params.get("order").toString();
           this.put("order", SQLFilter.sqlInject(order));
       }

    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
