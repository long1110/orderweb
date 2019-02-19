package pers.clw.orderweb.command.core.util;

/**
 * 常量
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

	/**
	 * 菜单类型
	 * 
	 * @author chenshun
	 * @email sunlightcs@gmail.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * app用户sesion
     */
    public static final String USER_SESSION = "LYZD_SZT_USER";
    
    public static final String KEY_MSG = "LYZD_KEY_MSG";
    
    public static final String KEY_BANNER = "LYZD_KEY_BANNER";

	/**
	 * 公众号用户session
	 */
	public  static  final  String WX_USER_SESSION ="LYZD_WX_USER";
	public static final String KEY_WEIXIN_PAY = "KEY_WEIXIN_PAY";


	/**
	 * redis Utils key
	 */
	public static final String KEY_UPDATE_SESSION = "KEY_UPDATE_SESSION";

	/**
	 * 订单状态
	 */
	/**待付款(下单但未完成付款)*/
	public static final Integer ORDER_STATUS_0 = 0;
	/**已预定(付款成功)*/
	public static final Integer ORDER_STATUS_1 = 1;
	/**待评价(已完成订单)*/
	public static final Integer ORDER_STATUS_2 = 2;
	/**已完成*/
	public static final Integer ORDER_STATUS_3 = 3;
	/**待退款(已提交退款申请)*/
	public static final Integer ORDER_STATUS_4 = 4;
	/**已拒绝(拒绝退款)*/
	public static final Integer ORDER_STATUS_5 = 5;
	/**已退款(已通过并退款)*/
	public static final Integer ORDER_STATUS_6 = 6;
	/*public static final Integer ORDER_STATUS_7 = 7;
	public static final Integer ORDER_STATUS_8 = 8;*/

	/**
	 * 支付类型
	 */
	/**微信支付*/
	public static final Integer PAY_TYPE_0 = 0;
	/**支付宝支付*/
	public static final Integer PAY_TYPE_1 = 1;

	/**订单来源*/
	/**APP*/
	public static final Integer ORDER_FROM_0 = 0;
	/**微信公众号*/
	public static final Integer ORDER_FROM_1 = 1;

}
