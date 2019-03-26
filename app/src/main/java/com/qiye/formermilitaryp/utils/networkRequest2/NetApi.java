package com.qiye.formermilitaryp.utils.networkRequest2;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.qiye.formermilitaryp.bean.request.InsertSupportBean;
import com.qiye.formermilitaryp.bean.request.RegRequestBean;
import com.qiye.formermilitaryp.bean.request.UpdateSupportBean;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetApi {
    /**
     * 首页标题
     *
     * @param map
     * @param subscriber
     */
    public static void getTitle(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().gettitleForMap(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取商品列表
     *
     * @param map
     * @param subscriber
     */
    public static void getGoodList(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().getGoodList(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 一键登录
     * 获取个人信息
     *
     * @param map
     * @param subscriber
     */
    public static void Login(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().Login(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


    /**
     * 商品详情
     *
     * @param map
     * @param subscriber
     */
    public static void goodsDetails(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().goodsDetails(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 收藏商品
     *
     * @param id
     * @param subscriber
     */
    public static void favorite(String id, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().favorite(id);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取收藏
     *
     * @param map
     * @param subscriber
     */
    public static void getFavorite(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().getFavorite(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 删除收藏
     *
     * @param map
     * @param subscriber
     */
    public static void delFavorite(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().delFavorite(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 区域列表
     *
     * @param map
     * @param subscriber
     */
    public static void AreaList(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().AreaList(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 收获地址新增
     *
     * @param map
     * @param subscriber
     */
    public static void HarvestAddressAdd(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().HarvestAddressAdd(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取所有的收货地址
     * <p>
     * 获取所有的收货地址 或者选择默认收货地址
     *
     * @param map
     * @param subscriber
     */
    public static void HarvestAddressAll(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().HarvestAddressAll(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 删除一个收货地址
     *
     * @param map
     * @param subscriber
     */
    public static void HarvestAddressDelete(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().HarvestAddressDelete(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 添加 修改购物车
     *
     * @param map
     * @param subscriber
     */
    public static void addCar(/*String gid, int count*/Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().addCar(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取购物车
     *
     * @param map
     * @param subscriber
     */
    public static void getCar(/*String gid, int count*/Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().getCar(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 删除购物车
     *
     * @param map
     * @param subscriber
     */
    public static void delCar(/*String gid, int count*/Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().delCar(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 确认订单
     *
     * @param map
     * @param subscriber
     */
    public static void ConfirmationOfOrders(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().ConfirmationOfOrders(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取运费
     *
     * @param map
     * @param subscriber
     */
    public static void Freight(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().Freight(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 生成订单
     *
     * @param map
     * @param subscriber
     */
    public static void CreateOrder(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().CreateOrder(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 唤醒支付
     *
     * @param map
     * @param subscriber
     */
    public static void AwakeningPayment(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().AwakeningPayment(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 足迹
     *
     * @param map
     * @param subscriber
     */
    public static void footPrint(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().footPrint(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取订单列表
     *
     * @param map
     * @param subscriber
     */
    public static void OrderList(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().OrderList(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 热搜词
     *
     * @param map
     * @param subscriber
     */
    public static void hotKeywords(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().hotKeywords(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


    /**
     * 修改个人信息
     */
    public static void UpdateUserInfo(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().UpdateUserInfo(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取自购返佣订单列表
     *
     * @param map
     * @param subscriber
     */
    public static void ZiGouCommissionOrderList(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().ZiGouCommissionOrderList(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取推广返佣订单列表
     *
     * @param map
     * @param subscriber
     */
    public static void TuiGuangCommissionOrderList(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().TuiGuangCommissionOrderList(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 获取推荐返佣订单列表
     *
     * @param map
     * @param subscriber
     */
    public static void TuiJianCommissionOrderList(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().TuiJianCommissionOrderList(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 上传图片
     */
    public static void uploadPictures(Context context) {
        String url = "http://demoshop.appudid.cn/index.php?controller=theapi&action=user_head";
        try {
            post(url, context, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void post(String url, Context context, String local_file_path) throws IOException {
        File file = new File(local_file_path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("attach", "sd" + ".png", fileBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(150, TimeUnit.SECONDS)
                .cookieJar(new CookieJarImpl(new SPCookieStore(context)))
                        .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e("debug", result);
            }

            @Override
            public void onFailure(Call arg0, IOException e) {
                // TODO Auto-generated method stub
            }
        });
    }


    /**
     * 退伍军人
     */

    /**
     * 注册
     * @param map
     * @param subscriber
     */
    public static void reg(RegRequestBean map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().reg(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 登录
     * @param map
     * @param subscriber
     */
    public static void login(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().login(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 查询我所参加的培训
     * @param map
     * @param subscriber
     */
    public static void selectMeTrainingList(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().selectMeTrainingList(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }


    /**
     * 首页轮播图
     * @param map
     * @param subscriber
     */
    public static void homeBanner(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().homeBanner(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     *  首页菜单按钮
     * @param map
     * @param subscriber
     */
    public static void selectHomeMenu(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().selectHomeMenu(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     *  首页工作动态
     * @param map
     * @param subscriber
     */
    public static void selectHomeGongZuoDongTai(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().selectHomeGongZuoDongTai(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 首页推荐岗位
     * @param map
     * @param subscriber
     */
    public static void selectHomeTuiJianGangWei(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().selectHomeTuiJianGangWei(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 首页政策公示
     * @param map
     * @param subscriber
     */
    public static void selectHomeZhengCeGongShi(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().selectHomeZhengCeGongShi(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 困难帮扶列表
     * @param map
     * @param subscriber
     */
    public static void selectSupportList(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().selectSupportList(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 根据id查看我申请的困难
     * @param map
     * @param subscriber
     */
    public static void selectSupportById(Map map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().selectSupportById(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 添加或者修改我的帮扶
     * @param map
     * @param subscriber
     */
    public static void insertOrUpdateSupport(InsertSupportBean map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().insertOrUpdateSupport(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 添加或者修改我的帮扶
     * @param map
     * @param subscriber
     */
    public static void insertOrUpdateSupport2(UpdateSupportBean map, DisposableObserver<ResponseBody> subscriber) {
        Observable<ResponseBody> observable = HttpMethods.getInstance().getHttpService().insertOrUpdateSupport2(map);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
}
