package com.qiye.formermilitaryp.utils.networkRequest2;


import com.qiye.formermilitaryp.bean.request.InsertSupportBean;
import com.qiye.formermilitaryp.bean.request.RegRequestBean;
import com.qiye.formermilitaryp.bean.request.UpdateSupportBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface HttpService {
    /**
     * 标题分类
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=category")
    abstract Observable<ResponseBody> gettitleForMap(@QueryMap Map<String, Integer> map);

    /**
     * 获取商品列表
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=goods_list")
    abstract Observable<ResponseBody> getGoodList(@QueryMap(encoded = true) Map<String, Integer> map);

    /**
     * 一键登录
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=member")
    abstract Observable<ResponseBody> Login(@QueryMap Map<String, Integer> map);

    /**
     * 商品详情
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=goods")
    abstract Observable<ResponseBody> goodsDetails(@QueryMap Map<String, Integer> map);

    /**
     * 收藏
     *
     * @param goods_id
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?controller=theapi&action=favorite")
    abstract Observable<ResponseBody> favorite(@Field("gid") String goods_id);

    /**
     * 获取收藏
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=favorite")
    abstract Observable<ResponseBody> getFavorite(@QueryMap Map<String, Integer> map);

    /**
     * 删除收藏
     *
     * @param map
     * @return
     */
    @DELETE("index.php?controller=theapi&action=favorite")
    abstract Observable<ResponseBody> delFavorite(@QueryMap(encoded = true) Map<String, Integer> map);

    /**
     * 地区列表
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=area")
    abstract Observable<ResponseBody> AreaList(@QueryMap Map<String, Integer> map);

    /**
     * 收获地址增加
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?controller=theapi&action=address")
    abstract Observable<ResponseBody> HarvestAddressAdd(@FieldMap Map<String, Integer> map);

    /**
     * 获取所有收货地址
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=address")
    abstract Observable<ResponseBody> HarvestAddressAll(@QueryMap Map<String, Integer> map);

    /**
     * 删除一个收货地址
     *
     * @param map
     * @return
     */
    @DELETE("index.php?controller=theapi&action=address")
    abstract Observable<ResponseBody> HarvestAddressDelete(@QueryMap Map<String, Integer> map);

    /**
     * 添加 修改购物车
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("index.php?controller=theapi&action=shoppingCart")
    abstract Observable<ResponseBody> addCar(@FieldMap Map<String, Integer> map);

    /**
     * 获取购物车
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=shoppingCart")
    abstract Observable<ResponseBody> getCar(@QueryMap Map<String, Integer> map);

    /**
     * 删除购物车
     *
     * @param map
     * @return
     */
    @DELETE("index.php?controller=theapi&action=shoppingCart")
    abstract Observable<ResponseBody> delCar(@QueryMap(encoded = true) Map<String, Integer> map);


    /**
     * 确认订单
     *
     * @return
     */
    @GET("index.php?controller=theapi&action=cart2")
    abstract Observable<ResponseBody> ConfirmationOfOrders(@QueryMap Map<String, Integer> map);

    /**
     * 获取运费
     *
     * @return
     */
    @GET("index.php?controller=theapi&action=getFreight")
    abstract Observable<ResponseBody> Freight(@QueryMap Map<String, Integer> map);

    /**
     * 生成订单
     *
     * @return
     */
    @GET("index.php?controller=theapi&action=cart3")
    abstract Observable<ResponseBody> CreateOrder(@QueryMap Map<String, Integer> map);


    /**
     * 唤醒支付
     *
     * @return
     */
    @GET("index.php?controller=theapi&action=doPay")
    abstract Observable<ResponseBody> AwakeningPayment(@QueryMap Map<String, Integer> map);

    /**
     * 足迹接口
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=goodsVisitLog")
    abstract Observable<ResponseBody> footPrint(@QueryMap Map<String, Integer> map);


    /**
     * 订单列表
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=order")
    abstract Observable<ResponseBody> OrderList(@QueryMap Map<String, Integer> map);

    /**
     * 热搜词
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=hotKeyword")
    abstract Observable<ResponseBody> hotKeywords(@QueryMap Map<String, Integer> map);


    /**
     * 修改个人信息
     */
    @FormUrlEncoded
    @POST("index.php?controller=theapi&action=member")
    abstract Observable<ResponseBody> UpdateUserInfo(@FieldMap Map<String, Integer> map);


    /**
     * 自购返佣订单列表
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=order")
    abstract Observable<ResponseBody> ZiGouCommissionOrderList(@QueryMap Map<String, Integer> map);

    /**
     * 推广返佣订单列表
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=order")
    abstract Observable<ResponseBody> TuiGuangCommissionOrderList(@QueryMap Map<String, Integer> map);

    /**
     * 推荐返佣订单列表
     *
     * @param map
     * @return
     */
    @GET("index.php?controller=theapi&action=order")
    abstract Observable<ResponseBody> TuiJianCommissionOrderList(@QueryMap Map<String, Integer> map);

    /**
     * 退伍军人
     */
    /**
     * 注册
     *
     */
    @POST("veterans/register")
    abstract Observable<ResponseBody> reg(@Body RegRequestBean bean);

    /**
     * 注册
     *
     */
    @FormUrlEncoded
    @POST("veterans/login")
    abstract Observable<ResponseBody> login(@FieldMap Map<String, String> map);

    /**
     * 查询我参加的培训
     *
     */
    @GET("veterans/app/training/selectMeTrainingList")
    abstract Observable<ResponseBody> selectMeTrainingList(@QueryMap Map<String, String> map);

    /**
     * 首页轮播图
     *
     */
    @GET("veterans/app/rotationChart/selectRotationChart")
    abstract Observable<ResponseBody> homeBanner(@QueryMap Map<String, String> map);

    /**
     *
     *  首页菜单按钮
     */
    @GET("veterans/app/appMenu/selectAppMenu")
    abstract Observable<ResponseBody> selectHomeMenu(@QueryMap Map<String, String> map);

    /**
     *
     *  首页工作动态列表
     */
    @GET("veterans/app/dynamic/selectDynamic")
    abstract Observable<ResponseBody> selectHomeGongZuoDongTai(@QueryMap Map<String, String> map);

    /**
     *
     * 首页推荐岗位列表
     */
    @GET("veterans/app/jobs/selectJobsList")
    abstract Observable<ResponseBody> selectHomeTuiJianGangWei(@QueryMap Map<String, String> map);

    /**
     *
     * 首页政策公示查询列表
     */
    @GET("veterans/app/policyPublic/selectPolicyPublic")
    abstract Observable<ResponseBody> selectHomeZhengCeGongShi(@QueryMap Map<String, String> map);

    /**
     *
     * 困难帮扶列表
     */
    @GET("veterans/app/support/selectSupportList")
    abstract Observable<ResponseBody> selectSupportList(@QueryMap Map<String, String> map);

    /**
     *
     * 根据id查看我申请的困难
     */
    @GET("veterans/app/support/selectSupportById")
    abstract Observable<ResponseBody>selectSupportById (@QueryMap Map<String, String> map);

    /**
     *
     * 添加或者修改我的帮扶
     */
    @POST("veterans/app/support/insertOrUpdateSupport")
    abstract Observable<ResponseBody> insertOrUpdateSupport(@Body InsertSupportBean map);

    /**
     *
     * 添加或者修改我的帮扶
     */
    @POST("veterans/app/support/insertOrUpdateSupport")
    abstract Observable<ResponseBody> insertOrUpdateSupport2(@Body UpdateSupportBean map);
}
