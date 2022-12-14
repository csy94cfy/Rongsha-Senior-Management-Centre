package com.framework.util.task;

/**
 * 系统不可变参数设定类
 * @author 王修成 2016/06/22
 */
public class Constants {
	
	/**
	 * 
	 */
	public static final String LOGINED = "user_login_id";
	
	/************************** 管控登录用常量 **************************/
	public static final String LOGIN_USER = "LOGIN_USER";// 登录用户信息
	public static final String LOGIN_MENU = "LOGIN_MENU";// 登录菜单结果集
	
	
	/************************** 接口返回状态码 **************************/
	public static final String FAIL = "0"; // 接口返回状态码:失败
	public static final String SUCCESS = "100"; // 接口返回状态码:成功
	public static final String LOGIN_TIMEOUT = "1"; // 接口返回状态码:登录Session超时
	
	/************************** 分页参数 **************************/
	public static final Integer PAGING_MAX_SIZE = 20; // 每页允许查询的最大值

	/**
	 * 删除状态码:未删除
	 */
	public static final String DELETE_FLAG_NOT = "0"; //未删除
	
	/**
	 * 删除状态码:已删除	
	 */
	public static final String DELETE_FLAG_OK = "1"; //已删除
	
	/************************** UUID标识 **************************/
	public static final String UUID_FLAG_SELLER_ID = "10"; // UUID标识（商家ID）
	public static final String UUID_FLAG_BUYER_ID = "11"; // UUID标识（用户ID）
	public static final String UUID_FLAG_SELF_ORDER_ID = "12"; // UUID标识（自营订单ID）
	public static final String UUID_FLAG_DIST_ORDER_ID = "13"; // UUID标识（分销订单ID）
	public static final String UUID_FLAG_TRANS_WECHAT_ID = "14"; // UUID标识（微信交易流水号ID）
	public static final String UUID_FLAG_TRANS_ALI_ID = "15"; // UUID标识（支付宝交易流水号ID）
	public static final String UUID_FLAG_TRANS_ACCOUNT_ID = "16"; // UUID标识（余额交易流水号ID）
	public static final String UUID_FLAG_TRANS_OTHER_ID = "17"; // UUID标识（其他交易流水号ID）
	public static final String UUID_FLAG_PAY_ORDER_ID = "18"; // UUID标识（支付订单ID）
	public static final String UUID_FLAG_OTHER_ID = "99"; // UUID标识（其他ID）
	
	/************************** 运行环境标识 **************************/
	public static final String ENVIRONMENT_TEST1 = "1"; // 运行环境标识（测试1）
	public static final String ENVIRONMENT_TEST2 = "2"; // 运行环境标识（测试2）
	public static final String ENVIRONMENT_FORMAL = "9"; // 运行环境标识（正式）
	
	/************************** 各种FLAG **************************/
	public static final String FLAG_YES = "1"; // 各种FLAG（YES）
	public static final String FLAG_NO = "0"; // 各种FLAG（NO）
	
	/************************** 商品CHECK_FLAG **************************/
	public static final String FLAG_DEPOT = "1"; // 仓库存放
	public static final String FLAG_SELL = "2"; // 出售中
	
	/************************** 支付方式 **************************/
	public static final String PAYWAY_ALI = "1100"; // 支付方式（支付宝）
	public static final String PAYWAY_WECHAT = "1101"; // 支付方式（微信）
	public static final String PAYWAY_WECHAT_SP = "1102"; // 支付方式（微信小程序）
	public static final String PAYWAY_ONENET = "1103"; // 支付方式（一网通）
	public static final String PAYWAY_POCKET = "1104"; // 支付方式（余额）
	public static final String PAYWAY_CASH = "1105"; // 支付方式（现金）
	
	/************************** 系统类型 **************************/
	public static final String SYSTEM_TYPE_ANDROID_USER = "1200"; // 系统类型（Android用户端APP）
	public static final String SYSTEM_TYPE_IOS_USER = "1201"; // 系统类型（IOS用户端APP）
	public static final String SYSTEM_TYPE_ANDROID_SELLER = "1202"; // 系统类型（i邻淘-Android商家端APP）
	public static final String SYSTEM_TYPE_IOS_SELLER = "1203"; // 系统类型（i邻淘-IOS商家端APP）
	public static final String SYSTEM_TYPE_MANAGER = "1204"; // 系统类型（管控）
	public static final String SYSTEM_TYPE_WECHAT_SP = "1205"; // 系统类型（微信小程序）
	public static final String SYSTEM_TYPE_365ANDROID_USER = "1206"; // 系统类型（365Android用户端APP）
	public static final String SYSTEM_TYPE_365IOS_USER = "1207"; // 系统类型（365IOS用户端APP）
	public static final String SYSTEM_TYPE_CLOUD_ANDROID_SELLER = "1208"; // 系统类型（365云商-Android商家端APP）
	public static final String SYSTEM_TYPE_CLOUD_IOS_SELLER = "1209"; // 系统类型（365云商-IOS商家端APP）
	
	/************************** 订单配送方式 **************************/
	public static final String DISPATCH_TYPE_PAOTUI = "1300"; // 订单配送方式（跑腿网）
	public static final String DISPATCH_TYPE_SELLER = "1301"; // 订单配送方式（卖家配送）
	public static final String DISPATCH_TYPE_BUYER = "1302"; // 订单配送方式（买家自提）
	public static final String DISPATCH_TYPE_EXPRESS = "1303"; // 订单配送方式（传统快递）
	
	/************************** 审核状态 **************************/
	public static final String CHECK_STATUS_NONE = "1400"; // 审核状态（未审核）
	public static final String CHECK_STATUS_OK = "1401"; // 审核状态（审核完成，审核通过）
	public static final String CHECK_STATUS_FAILED = "1402"; // 审核状态（审核失败）
	public static final String CHECK_STATUS_REVOKE = "1403"; // 审核状态（撤销审核）
	
	/************************** 商品状态 **************************/
	public static final String GOODS_STATUS_ONSALE = "1500"; // 商品状态（出售中）
	public static final String GOODS_STATUS_UNABLESELL = "1501"; // 商品状态（暂时无法出售）
	public static final String GOODS_STATUS_SELDOUT = "1502"; // 商品状态（已售完）
	public static final String GOODS_STATUS_OFFSALE = "1503"; // 商品状态（已下架）
	public static final String GOODS_STATUS_INVALID = "1504"; // 商品状态（已失效）
	public static final String GOODS_STATUS_COERCION = "1505"; // 商品状态（强制下架）
	
	/************************** 商铺认证状态 **************************/
	public static final String SELLER_CHECK_STATUS_NONE = "1600"; // 商铺认证状态（待认证）
	public static final String SELLER_CHECK_STATUS_WAIT_PAY = "1601"; // 商铺认证状态（待付款）
	public static final String SELLER_CHECK_STATUS_VERIFYING = "1602"; // 商铺认证状态（认证中）
	public static final String SELLER_CHECK_STATUS_OK = "1603"; // 商铺认证状态（认证通过）
	public static final String SELLER_CHECK_STATUS_REJECT = "1604"; // 商铺认证状态（认证驳回）
	public static final String SELLER_CHECK_STATUS_NO_INFO = "1605"; // 商铺认证状态（信息未录入）
	
	/************************** 商铺营业状态 **************************/
	public static final String SELLER_OPEN_STATUS_OPENING = "1700"; // 商铺营业状态（营业中）
	public static final String SELLER_OPEN_STATUS_BREAK = "1701"; // 商铺营业状态（临时休息）
	public static final String SELLER_OPEN_STATUS_CLOSE = "1702"; // 商铺营业状态（暂停营业）
	public static final String SELLER_OPEN_STATUS_COERCION = "1703"; // 商铺营业状态（强制停业）
	
	/************************** 商铺创建流程 **************************/
	public static final String CREATE_SHOP_PATH_CREATE = "1800"; // 商铺创建流程（创建店铺）
	public static final String CREATE_SHOP_PATH_SET_RECEIVE_RULE = "1801"; // 商铺创建流程（设置接单和配送方式）
	public static final String CREATE_SHOP_PATH_SET_DISPATCH_RULE = "1802"; // 商铺创建流程（设置配送规则）
	public static final String CREATE_SHOP_PATH_ADD_GOODS = "1803"; // 商铺创建流程（添加商品）
	public static final String CREATE_SHOP_PATH_DECORATION_SHOP = "1804"; // 商铺创建流程（装修店铺、完善店铺信息）
	
	/************************** 交易流水类型  **************************/
	public static final String TRADE_FACE_TO_FACE_PAYMEN = "1900"; // 当面付订单(营收类型:收入)
	public static final String TRADE_SELF_SUPPORT_DISPATCH = "1901"; // 自营商品配送单(营收类型:收入)
	public static final String TRADE_SUPPLIER_DISPATCH = "1902"; // 供货商品配送单(营收类型:收入)
	public static final String TRADE_DISTRIBUTION_COMMISSION = "1903"; // 分销商品佣金(营收类型:收入)
	public static final String TRADE_PICK_BY_CUSTOMER = "1904"; // 自提订单(营收类型:收入)
	public static final String TRADE_RECHARGE_FOR_APP = "1905"; // APP充值(营收类型:收入)
	public static final String TRADE_365_CANCEL_DISTRIBUTION = "1906"; // 365配送费(退款) (营收类型:收入)
	
	public static final String TRADE_365_DISTRIBUTION = "1920"; // 365配送费(营收类型:支出)
	public static final String TRADE_GROUP_CASHBACK = "1921"; // 拼团返现(订单) (营收类型:支出)
	public static final String TRADE_ADVERTISEMENT_EXPEND = "1922"; // 广告支出(营收类型:支出)
	public static final String TRADE_WITHDRAW_DEPOSIT = "1923"; // 提现(营收类型:支出)
	public static final String TRADE_GROUP_POINT  = "1924"; // 平台拼团扣点(营收类型:支出)
	public static final String TRADE_SELF_SUPPORT_DISTRIBUTION = "1925"; // 自营商品配送单(退款) (营收类型:支出)
	public static final String TRADE_CANCEL_DISTRIBUTION_COMMISSION = "1926"; // 分销商品佣金(退款) (营收类型:支出)
	public static final String TRADE_SUPPLIER_DISTRIBUTION = "1927"; // 供货商品配送单(退款) (营收类型:支出)
	public static final String TRADE_TAKE_THEIR_ORDERS = "1928"; // 自提订单(退款) (营收类型:支出)

	public static final String TRADE_USER_ORDER_PAY = "1940"; // 营收类型(用户订单支付)
	public static final String TRADE_USER_ORDER_PAYBACK = "1941"; // 营收类型(用户订单返现)
	public static final String TRADE_USER_ORDER_REFUND = "1942"; // 营收类型(用户订单退款)
	
	/************************** 订单状态 **************************/
	public static final String ORDER_STATUS_WAIT_PAY = "2000";// 订单状态（待付款）
	public static final String ORDER_STATUS_WAIT_GROUP = "2001"; // 订单状态（待成团）
	public static final String ORDER_STATUS_WAIT_RECEIVE = "2002"; // 订单状态（待接单）
	public static final String ORDER_STATUS_WAIT_SEND = "2003"; // 订单状态（待发货）
	public static final String ORDER_STATUS_ALREADY_SEND = "2004"; // 订单状态（已发货）
	public static final String ORDER_STATUS_COMPLETE = "2005"; // 订单状态（已完成）
	public static final String ORDER_STATUS_CLOSED = "2006"; // 订单状态（已关闭）

	/************************** 退款状态 **************************/
	public static final String REFUND_STATUS_NO_REFUND = "2100"; // 退款状态（取消订单，无需退款）
	public static final String REFUND_STATUS_ALREADY_REFUND = "2101"; // 退款状态（取消订单，已退款）
	public static final String REFUND_STATUS_GROUPFAIL_ALREADY_REFUND = "2102"; // 退款状态（拼团失败，已退款）
	
	/************************** 活动类型 **************************/
	public static final String ACTIVITY_STATUS_NONE = "2200"; // 活动类型（无活动）
	public static final String ACTIVITY_STATUS_PLATFORM_GROUP = "2201"; // 活动类型（平台拼团）
	public static final String ACTIVITY_STATUS_SELLER_GROUP = "2202"; // 活动类型（拼团购）
	public static final String ACTIVITY_STATUS_GROUP_CASHBACK = "2203"; // 活动类型（拼返现）
	public static final String ACTIVITY_STATUS_SELLER = "2204"; // 活动类型（商家活动）
	public static final String ACTIVITY_STATUS_SELLER_GROUP_CASHBACK = "2205"; // 活动类型（拼团购+拼返现）
	public static final String ACTIVITY_STATUS_HELP_TO_BUY = "2206"; // 活动类型（帮帮购）
	
	/**************************  拼团状态 **************************/
	public static final String GROUP_STATUS_ONGOING = "2300"; // 拼团状态（进行中）
	public static final String GROUP_STATUS_FAILED = "2301"; // 拼团状态（拼团失败）
	public static final String GROUP_STATUS_SUCCESS = "2302"; // 拼团状态（拼团成功）
	public static final String GROUP_STATUS_VIRTUAL_SUCCESS = "2303"; // 拼团状态（虚拟拼团成功）
	
	/**************************  订单类型 **************************/
	public static final String ORDER_TYPE_SELF = "2400"; // 订单类型（自营）
	public static final String ORDER_TYPE_SUPPLY = "2401"; // 订单类型（供货）
	
	/**************************  平台拼团活动参加状态（PG.platform group） **************************/
	public static final String PG_JOIN_STATUS_NONE = "2500"; // 平台拼团活动参加状态（未审核）
	public static final String PG_JOIN_STATUS_OK = "2501"; // 平台拼团活动参加状态（审核通过）
	public static final String PG_JOIN_STATUS_FAILE = "2502"; // 平台拼团活动参加状态（审核失败）
	public static final String PG_JOIN_STATUS_INVALID = "2503"; // 平台拼团活动参加状态（已失效）
	
	/**************************  满减活动状态 **************************/
	public static final String FULL_SUB_STATUS_NOT_START = "2600"; // 满减活动状态（未开始）
	public static final String FULL_SUB_STATUS_ONGOING = "2601"; // 满减活动状态（进行中）
	public static final String FULL_SUB_STATUS_END = "2602"; // 满减活动状态（已结束）
	
	/************************** 客户优惠券使用状态 **************************/
	public static final String COUPON_USE_STATUS_NOSTART = "2700"; // 优惠券使用状态（未开始）
	public static final String COUPON_USE_STATUS_NOUSE = "2701"; // 优惠券使用状态（未使用）
	public static final String COUPON_USE_STATUS_USED = "2702"; // 优惠券使用状态（已使用）
	public static final String COUPON_USE_STATUS_INVALID = "2703"; // 优惠券使用状态（已失效）
	
	/**************************  商品类型 **************************/
	public static final String GOODS_SELL_TYPE_SELF = "2800"; // 自营商品
	public static final String GOODS_SELL_TYPE_SUPPLY = "2801"; // 供货商品
	public static final String GOODS_SELL_TYPE_DISTRIBUTION = "2802"; // 分销商品
	public static final String GOODS_SELL_TYPE_SELFANDSUPPLY = "2803";//即自营又供货
	
	/************************** 商家活动状态 **************************/
	public static final String SHOP_ACTIVITY_STATUS_NOT_START = "2900"; // 商家活动状态（未开始）
	public static final String SHOP_ACTIVITY_STATUS_ONGOING = "2901"; // 商家活动状态（进行中）
	public static final String SHOP_ACTIVITY_STATUS_END = "2902"; // 商家活动状态（已结束）
	
	/************************** 商家优惠券使用状态 **************************/
	public static final String SHOP_COUPON_STATUS_NOSTART = "3000"; // 优惠券使用状态（未开始）
	public static final String SHOP_COUPON_STATUS_ONGOING = "3001"; // 优惠券使用状态（进行中）
	public static final String SHOP_COUPON_STATUS_END = "3002"; // 优惠券使用状态（已结束）
	
	/************************** 商家优惠券适用范围 **************************/
	public static final String COUPON_USE_RANG_ALL = "3100"; // 商家优惠券适用范围(全店商品可用)
	public static final String COUPON_USE_RANG_APPOINT_AVAILABLE = "3101"; // 商家优惠券适用范围(指定商品可用)
	public static final String COUPON_USE_RANG_APPOINT_UNAVAILABLE = "3102"; // 商家优惠券适用范围(指定商品不可用)
	
	/************************** 评价类型 **************************/
	public static final String ASSESS_TYPE_ALL = "3200"; // 评价类型(全部)
	public static final String ASSESS_TYPE_GOOD = "3201"; // 评价类型(好评)
	public static final String ASSESS_TYPE_MIDDLE = "3202"; // 评价类型(中评)
	public static final String ASSESS_TYPE_BAD = "3203"; // 评价类型(差评)
	public static final String ASSESS_TYPE_IMAGE = "3204"; // 评价类型(有图)
	public static final String ASSESS_TYPE_APPEND = "3205"; // 评价类型(追评)
	
	/************************** 订单取消原因标识 **************************/
	public static final String ORDER_CANCEL_FLAG_S_NO_STOCK = "3300"; // 订单取消原因(商家:缺货)
	public static final String ORDER_CANCEL_FLAG_S_NO_TIME = "3301"; // 订单取消原因(商家:无法按时发货)
	public static final String ORDER_CANCEL_FLAG_S_USER = "3302"; // 订单取消原因(商家:用户原因取消)
	public static final String ORDER_CANCEL_FLAG_S_OTHER = "3303"; // 订单取消原因(商家:其它)
	public static final String ORDER_CANCEL_FLAG_S_ORDER_TIMEOUT = "3304"; // 订单取消原因(商家手动接单超时)
	
	public static final String ORDER_CANCEL_FLAG_B_SPEC = "3320"; // 订单取消原因(用户:尺码规格拍错)
	public static final String ORDER_CANCEL_FLAG_B_NO_TIME = "3321"; // 订单取消原因(用户:订单不能按照预计时间送达)
	public static final String ORDER_CANCEL_FLAG_B_MISOPERATION = "3322"; // 订单取消原因(用户:操作失误)
	public static final String ORDER_CANCEL_FLAG_B_REPEAT = "3323"; // 订单取消原因(用户:重复下单)
	public static final String ORDER_CANCEL_FLAG_B_PRICE = "3324"; // 订单取消原因(用户:其他渠道价格更低)
	public static final String ORDER_CANCEL_FLAG_B_NO_BUY = "3325"; // 订单取消原因(用户:不想买了)
	public static final String ORDER_CANCEL_FLAG_B_OTHER = "3326"; // 订单取消原因(用户:其他原因)
	
	public static final String ORDER_CANCEL_FLAG_PAY_TIMEOUT = "3330"; // 订单取消原因(付款超时自动取消)
	public static final String ORDER_CANCEL_FLAG_GROUP_TIMEOUT = "3331"; // 订单取消原因(拼团超时自动取消)

	
	/************************** 用户类型 **************************/
	public static final String USER_TYPE_BUYER = "3400"; // 用户类型（买家）
	public static final String USER_TYPE_SELLER = "3401"; // 用户类型（卖家）
	
	/************************** 交易确认标识 **************************/
	public static final String TRANSACTION_CONFIRM_FLAG_NONE = "3500";//未确认
	public static final String TRANSACTION_CONFIRM_FLAG_OK = "3501";//交易成功 
	public static final String TRANSACTION_CONFIRM_FLAG_NG = "3502";//交易失败
	/************************** 商品配送方式 **************************/
	public static final String GOODS_DISP_TYPE_DISPATCH = "3600"; // 商品配送方式（仅配送）
	public static final String GOODS_DISP_TYPE_TOMORROW = "3601"; // 商品配送方式（配送+次日达）
	public static final String GOODS_DISP_TYPE_BUYER = "3602"; // 商品配送方式（仅自提）
	public static final String GOODS_DISP_TYPE_DISPATCHANDBUYER = "3603"; // 商品配送方式（配送+自提）
	public static final String GOODS_DISP_TYPE_DISPATCHANDTOMORROWANDBUYER = "3604"; // 商品配送方式（配送+次日达+自提）
	
	/************************** 反馈类型 **************************/
	public static final String FEEDBACK_TYPE_3700 = "3700"; // 提现到帐时间
	public static final String FEEDBACK_TYPE_3701 = "3701"; // 配送问题
	public static final String FEEDBACK_TYPE_3702 = "3702"; // 功能改善
	public static final String FEEDBACK_TYPE_3703 = "3703"; // 其它问题
	
	/************************** 配送费计费规则 **************************/
	public static final String DISPATCH_COST_TYPE_365 = "3800"; // 使用365跑腿网计费规则
	public static final String DISPATCH_COST_TYPE_LADDER = "3801"; // 自定义计费规则-阶梯计价
	public static final String DISPATCH_COST_TYPE_FIXED = "3802"; // 自定义计费规则-固定价格
	/************************** 交易确认标识 **************************/
	public static final String CONFIRM_FLAG_NONE = "3900"; // 交易确认标识(未确认)
	public static final String CONFIRM_FLAG_OK = "3901"; // 交易确认标识(交易成功)
	public static final String CONFIRM_FLAG_NG = "3902"; // 交易确认标识(交易失败)
	
	/************************** 规则分类 **************************/
	public static final String RULE_TYPE_4000 = "4000"; // 买家规则
	public static final String RULE_TYPE_4001 = "4001"; // 卖家规则
	public static final String RULE_TYPE_4002 = "4002"; // 交易规则
	
	/************************** 买家广告分类 **************************/
	public static final String BUYER_ADVERT_TYPE_MAIN_LOOP = "4100"; // 买家广告分类(主页轮播广告)
	public static final String BUYER_ADVERT_TYPE_MAIN_STATIC = "4101"; // 买家广告分类(主页静态广告)
	public static final String BUYER_ADVERT_TYPE_PLATFORM_GROUP = "4102"; // 买家广告分类(平台拼团轮播广告)
	public static final String BUYER_ADVERT_TYPE_CLASSIFICATION = "4103"; // 买家广告分类(分类页广告)
	
	/************************** 商家广告分类 **************************/
	public static final String SELLER_ADVERT_TYPE_MAIN = "4200"; // 商家广告分类(主页广告)
	
	/************************** 商家订单类型 **************************/
	public static final String SELLER_ORDER_TYPE_CHECK_PLATFORM = "4300"; // 商家订单类型(平台入驻认证)
	public static final String SELLER_ORDER_TYPE_CHECK_APPLET = "4301"; // 商家订单类型(独立小程序认证)
	public static final String SELLER_ORDER_TYPE_CHECK_PLATFORMANDAPPLET = "4302"; // 商家订单类型(独立小程序和平台入驻认证)
	
	/************************** 订单评价类型 **************************/
	public static final String ORDER_EVALUATE_TYPE_BUYER = "4400"; // 订单评价类型(商家对买家评价)
	public static final String ORDER_EVALUATE_TYPE_SELLER = "4401"; // 订单评价类型(买家对商家评价)
	
	/************************** 帮帮购状态**************************/
	public static final String HELP_TO_BUY_STATUS_ONGOING = "4500"; // 帮帮购(帮购进行中)
	public static final String HELP_TO_BUY_STATUS_FAILED = "4501"; //  帮帮购(帮购失败)
	public static final String HELP_TO_BUY_STATUS_SUCCESS = "4502"; // 帮帮购(帮购成功)
	
	/************************** 365跑腿网配送订单状态**************************/
	public static final String PAOTUI_ORDER_STATUS_WAIT_RECEIVE = "4600"; // 365跑腿网配送订单状态(365骑士待接单)
	public static final String PAOTUI_ORDER_STATUS_WAIT_TAKE_PART = "4601"; //  365跑腿网配送订单状态(365骑士待取件)
	public static final String PAOTUI_ORDER_STATUS_DISPATCHING = "4602"; // 365跑腿网配送订单状态(365骑士配送中)
	public static final String PAOTUI_ORDER_STATUS_CALL_STOP = "4603"; // 365跑腿网配送订单状态(暂停呼叫365骑士)
	public static final String PAOTUI_ORDER_STATUS_CALL_OVERTIME = "4604"; // 365跑腿网配送订单状态(呼叫365骑士超时)
	public static final String PAOTUI_ORDER_STATUS_COMPLETE = "4605"; // 365跑腿网配送订单状态(365骑士配送完成)
	
	/************************** 推送的系统分类**************************/
	public static final String PUSH_SYSTEM_TYPE_USER = "4700"; // 用户端
	public static final String PUSH_SYSTEM_TYPE_SELLER = "4701"; // 商家端
	
	/************************** 推送标识**************************/
	public static final String PUSH_FLAG_NORMAL = "4800"; // 普通推送
	public static final String PUSH_FLAG_SHOP_COUPON = "4801"; // 商家优惠券到期提醒
	public static final String PUSH_FLAG_GOODS_STATUS = "4802";//商品状态变更提醒
	public static final String PUSH_FLAG_BUYER_CURRENT_COUNT = "4803";// 用户端下单后减库存后 库存小于等于零 推送库存不足
	public static final String PUSH_FLAG_ARRIVAL_REMINDING = "4804";// 商家补货提醒
	public static final String PUSH_FLAG_ORDER_WAIT_RECEIVE = "4805";// 订单提醒 待处理
	public static final String PUSH_FLAG_ORDER_GROUP_SUCCESS= "4806"; // 订单提醒 已成团
	public static final String PUSH_FLAG_SHOP_OPEN_TIME = "4807";//营业休息时间提醒
	public static final String PUSH_FLAG_CREATE_DISTRIBUTION_GOODS = "4808";//添加分销商品 在分销库中看到此消息
	public static final String PUSH_FLAG_PAOTUI_ACCEPT_ORDER = "4809";//365骑士接单提醒
	public static final String PUSH_FLAG_PAOTUI_TIME_OUT = "4810";//365配送订单已超时提醒
	
	/************************** 消息标题**************************/
	public static final String PUSH_TITLE_GOODS_STATUS = "4900"; // 商品状态变更提醒
	public static final String PUSH_TITLE_ORDER_REMINDING = "4901"; // 订单提醒
	
	/************************** 商品审核属性**************************/
	public static final String CHECK_GOODS_IMG = "5000"; // 商品图片
	public static final String CHECK_GOODS_NAME = "5001"; // 商品名称
	public static final String CHECK_GOODS_TYPE = "5002"; // 系统分类
	public static final String CHECK_SHOP_GOODS_TYPE = "5003"; // 店内分类
	public static final String CHECK_COMMEND_MESSAGE = "5004"; //推荐语
	public static final String CHECK_GOODS_DETAIL = "5005"; //商品描述
	public static final String CHECK_GOODS_SPEC = "5006";//规格（包含审核表的拼团方式、拼团人数，规格表中的价格）
	
	/************************** 图片类型 **************************/
	public static final String IMAGE_TYPE_BUYER_LOGO = "6000"; // 用户头像(用户端)
	public static final String IMAGE_TYPE_BUYER_ASSESS_PIC = "6001"; // 评价图片(用户端)
	
	/************************** 任务类型 **************************/
	public static final String TASK_TYPE_SHOP_OPEN_TIME_OPENING = "6100"; // 营业休息时间提前十分钟提醒是否继续营业
	public static final String TASK_TYPE_SHOP_OPEN_TIME_BREAK = "6101"; // 营业休息时间切换临时休息
	
	/************************** 商家审核属性**************************/
	public static final String CHECK_SELLER_SHOPNAME = "6200"; // 店铺名称
	public static final String CHECK_SELLER_SHOPADDRESS = "6201"; // 店铺地址
	
	/**************************  扫码买单活动状态 **************************/
	public static final String PAY_BY_CODE_STATUS_NOT_START = "6300"; // 扫码买单活动状态（未开始）
	public static final String PAY_BY_CODE_STATUS_ONGOING = "6301"; // 扫码买单活动状态（进行中）
	public static final String PAY_BY_CODE_STATUS_END = "6302"; // 扫码买单活动状态（已结束）
	
	/**************************  扫码买单适用人群 **************************/
	public static final String FOR_PEOPLE_NOT_ALL = "6400"; // 扫码买单适用人群（全部用户）
	public static final String FOR_PEOPLE_NEW = "6401"; // 扫码买单适用人群（新用户）
	public static final String FOR_PEOPLE_OLD = "6402"; // 扫码买单适用人群（老用户）
}
