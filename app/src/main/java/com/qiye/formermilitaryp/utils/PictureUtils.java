package com.qiye.formermilitaryp.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 图片管理类 create 2018.11.28 , update 2018.11.29 ,jjm
 */
public class PictureUtils {
    private static final String savePictureToLocalDirectoryName = "appViewConversionBitmap";

    private static PictureUtils pictureUtils = null;

    private PictureUtils() {
    }


    //静态工厂返回单例对象
    public synchronized static PictureUtils getInstance() {
        if (pictureUtils == null) {
            pictureUtils = new PictureUtils();
        }
        return pictureUtils;
    }

    /**
     * @param bitmap               原bitmap图
     * @param targetBitmapWidthPx  目标图片宽
     * @param targetBitmapHeightPx 目标图片高
     * @return bitmap
     * 重新定义bitmap图片的大小并返回一个新的bitmap图片
     */
    public static Bitmap createBitmapThumbnail(Bitmap bitmap, int targetBitmapWidthPx, int targetBitmapHeightPx) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 设置想要的大小
        int newWidth = targetBitmapWidthPx;
        int newHeight = targetBitmapHeightPx;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        //if (true) bitmap.recycle();//销毁异常:java.lang.RuntimeException: Canvas: trying to use a recycled bitmap android.graphics.Bitmap@b8d72fd
        return newBitMap;
    }

    /**
     * @param context 环境
     * @param dpValue dp值
     * @return px值
     * dp值转换为px值
     */
    public static int dpConversionPx(Context context, int dpValue) {
        float scale = context.getResources().getDisplayMetrics().density; //px = dp * scale
        return (int) (scale * (float) dpValue);
    }

    //根据手机的分辨率从 px(像素) 的单位 转成为 dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param drawable
     * @return bitmap
     * drawable 转化为 bitmap
     */
    public static Bitmap drawableConversionBitmap(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        return bitmap;
    }

    /**
     * 颜色选择器
     */
    public static class ColorPickerDialog extends Dialog {
        /**
         * 调用:
         * PictureUtils.ColorPickerDialog dialog = new PictureUtils.ColorPickerDialog(MainActivity.this, ((TextView) findViewById(R.id.tv)).getTextColors().getDefaultColor(),
         * getResources().getString(R.string.app_name),
         * new PictureUtils.ColorPickerDialog.OnColorChangedListener() {
         * public void colorChanged(int color) {
         * ((TextView) findViewById(R.id.tv)).setTextColor(color);
         * }
         * });
         * dialog.show();
         */
        private final boolean debug = true;
        private final String TAG = "ColorPicker";

        Context context;
        private String title;       //标题
        private int mInitialColor;  //初始颜色
        private OnColorChangedListener mListener;

        /**
         * 初始颜色黑色
         *
         * @param context
         * @param title    对话框标题
         * @param listener 回调
         */
        public ColorPickerDialog(Context context, String title,
                                 OnColorChangedListener listener) {
            this(context, Color.BLACK, title, listener);
        }

        /**
         * @param context
         * @param initialColor 初始颜色
         * @param title        标题
         * @param listener     回调
         */
        public ColorPickerDialog(Context context, int initialColor,
                                 String title, OnColorChangedListener listener) {
            super(context);
            this.context = context;
            mListener = listener;
            mInitialColor = initialColor;
            this.title = title;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            WindowManager manager = getWindow().getWindowManager();
            int height = (int) (manager.getDefaultDisplay().getHeight() * 0.38f);       //0.5
            int width = (int) (manager.getDefaultDisplay().getWidth() * 0.5f);          //0.7
            ColorPickerView myView = new ColorPickerView(context, height, width);
            setContentView(myView);
            setTitle(title);
        }

        private class ColorPickerView extends View {
            private Paint mPaint;           //渐变色环画笔
            private Paint mCenterPaint;     //中间圆画笔
            private Paint mLinePaint;       //分隔线画笔
            private Paint mRectPaint;       //渐变方块画笔

            private Shader rectShader;      //渐变方块渐变图像
            private float rectLeft;         //渐变方块左x坐标
            private float rectTop;          //渐变方块右x坐标
            private float rectRight;        //渐变方块上y坐标
            private float rectBottom;       //渐变方块下y坐标

            private final int[] mCircleColors;      //渐变色环颜色
            private final int[] mRectColors;        //渐变方块颜色

            private int mHeight;                    //View高
            private int mWidth;                     //View宽
            private float r;                        //色环半径(paint中部)
            private float centerRadius;             //中心圆半径

            private boolean downInCircle = true;    //按在渐变环上
            private boolean downInRect;             //按在渐变方块上
            private boolean highlightCenter;        //高亮
            private boolean highlightCenterLittle;  //微亮

            public ColorPickerView(Context context, int height, int width) {
                super(context);
                this.mHeight = height - 36;
                this.mWidth = width;
                setMinimumHeight(height - 36);
                setMinimumWidth(width);

                //渐变色环参数
                mCircleColors = new int[]{0xFFFF0000, 0xFFFF00FF, 0xFF0000FF,
                        0xFF00FFFF, 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000};
                Shader s = new SweepGradient(0, 0, mCircleColors, null);
                mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mPaint.setShader(s);
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(50);
                r = width / 2 * 0.7f - mPaint.getStrokeWidth() * 0.5f;

                //中心圆参数
                mCenterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mCenterPaint.setColor(mInitialColor);
                mCenterPaint.setStrokeWidth(5);
                centerRadius = (r - mPaint.getStrokeWidth() / 2) * 0.7f;

                //边框参数
                mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mLinePaint.setColor(Color.parseColor("#72A1D1"));
                mLinePaint.setStrokeWidth(4);

                //黑白渐变参数
                mRectColors = new int[]{0xFF000000, mCenterPaint.getColor(), 0xFFFFFFFF};
                mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                mRectPaint.setStrokeWidth(5);
                rectLeft = -r - mPaint.getStrokeWidth() * 0.5f;
                rectTop = r + mPaint.getStrokeWidth() * 0.5f +
                        mLinePaint.getStrokeMiter() * 0.5f + 15;
                rectRight = r + mPaint.getStrokeWidth() * 0.5f;
                rectBottom = rectTop + 50;
            }

            @Override
            protected void onDraw(Canvas canvas) {
                //移动中心
                canvas.translate(mWidth / 2, mHeight / 2 - 50);
                //画中心圆
                canvas.drawCircle(0, 0, centerRadius, mCenterPaint);

                //是否显示中心圆外的小圆环
                if (highlightCenter || highlightCenterLittle) {
                    int c = mCenterPaint.getColor();
                    mCenterPaint.setStyle(Paint.Style.STROKE);
                    if (highlightCenter) {
                        mCenterPaint.setAlpha(0xFF);
                    } else if (highlightCenterLittle) {
                        mCenterPaint.setAlpha(0x90);
                    }
                    canvas.drawCircle(0, 0,
                            centerRadius + mCenterPaint.getStrokeWidth(), mCenterPaint);

                    mCenterPaint.setStyle(Paint.Style.FILL);
                    mCenterPaint.setColor(c);
                }
                //画色环
                canvas.drawOval(new RectF(-r, -r, r, r), mPaint);
                //画黑白渐变块
                if (downInCircle) {
                    mRectColors[1] = mCenterPaint.getColor();
                }
                rectShader = new LinearGradient(rectLeft, 0, rectRight, 0, mRectColors, null, Shader.TileMode.MIRROR);
                mRectPaint.setShader(rectShader);
                canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, mRectPaint);
                float offset = mLinePaint.getStrokeWidth() / 2;
                canvas.drawLine(rectLeft - offset, rectTop - offset * 2,
                        rectLeft - offset, rectBottom + offset * 2, mLinePaint);//左
                canvas.drawLine(rectLeft - offset * 2, rectTop - offset,
                        rectRight + offset * 2, rectTop - offset, mLinePaint);//上
                canvas.drawLine(rectRight + offset, rectTop - offset * 2,
                        rectRight + offset, rectBottom + offset * 2, mLinePaint);//右
                canvas.drawLine(rectLeft - offset * 2, rectBottom + offset,
                        rectRight + offset * 2, rectBottom + offset, mLinePaint);//下
                super.onDraw(canvas);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                float x = event.getX() - mWidth / 2;
                float y = event.getY() - mHeight / 2 + 50;
                boolean inCircle = inColorCircle(x, y,
                        r + mPaint.getStrokeWidth() / 2, r - mPaint.getStrokeWidth() / 2);
                boolean inCenter = inCenter(x, y, centerRadius);
                boolean inRect = inRect(x, y);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downInCircle = inCircle;
                        downInRect = inRect;
                        highlightCenter = inCenter;
                    case MotionEvent.ACTION_MOVE:
                        if (downInCircle && inCircle) {//down按在渐变色环内, 且move也在渐变色环内
                            float angle = (float) Math.atan2(y, x);
                            float unit = (float) (angle / (2 * Math.PI));
                            if (unit < 0) {
                                unit += 1;
                            }
                            mCenterPaint.setColor(interpCircleColor(mCircleColors, unit));
                            if (debug) Log.v(TAG, "色环内, 坐标: " + x + "," + y);
                        } else if (downInRect && inRect) {//down在渐变方块内, 且move也在渐变方块内
                            mCenterPaint.setColor(interpRectColor(mRectColors, x));
                        }
                        if (debug)
                            Log.v(TAG, "[MOVE] 高亮: " + highlightCenter + "微亮: " + highlightCenterLittle + " 中心: " + inCenter);
                        if ((highlightCenter && inCenter) || (highlightCenterLittle && inCenter)) {//点击中心圆, 当前移动在中心圆
                            highlightCenter = true;
                            highlightCenterLittle = false;
                        } else if (highlightCenter || highlightCenterLittle) {//点击在中心圆, 当前移出中心圆
                            highlightCenter = false;
                            highlightCenterLittle = true;
                        } else {
                            highlightCenter = false;
                            highlightCenterLittle = false;
                        }
                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (highlightCenter && inCenter) {//点击在中心圆, 且当前启动在中心圆
                            if (mListener != null) {
                                mListener.colorChanged(mCenterPaint.getColor());
                                ColorPickerDialog.this.dismiss();
                            }
                        }
                        if (downInCircle) {
                            downInCircle = false;
                        }
                        if (downInRect) {
                            downInRect = false;
                        }
                        if (highlightCenter) {
                            highlightCenter = false;
                        }
                        if (highlightCenterLittle) {
                            highlightCenterLittle = false;
                        }
                        invalidate();
                        break;
                }
                return true;
            }

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(mWidth, mHeight);
            }

            /**
             * 坐标是否在色环上
             *
             * @param x         坐标
             * @param y         坐标
             * @param outRadius 色环外半径
             * @param inRadius  色环内半径
             * @return
             */
            private boolean inColorCircle(float x, float y, float outRadius, float inRadius) {
                double outCircle = Math.PI * outRadius * outRadius;
                double inCircle = Math.PI * inRadius * inRadius;
                double fingerCircle = Math.PI * (x * x + y * y);
                if (fingerCircle < outCircle && fingerCircle > inCircle) {
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * 坐标是否在中心圆上
             *
             * @param x            坐标
             * @param y            坐标
             * @param centerRadius 圆半径
             * @return
             */
            private boolean inCenter(float x, float y, float centerRadius) {
                double centerCircle = Math.PI * centerRadius * centerRadius;
                double fingerCircle = Math.PI * (x * x + y * y);
                if (fingerCircle < centerCircle) {
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * 坐标是否在渐变色中
             *
             * @param x
             * @param y
             * @return
             */
            private boolean inRect(float x, float y) {
                if (x <= rectRight && x >= rectLeft && y <= rectBottom && y >= rectTop) {
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * 获取圆环上颜色
             *
             * @param colors
             * @param unit
             * @return
             */
            private int interpCircleColor(int colors[], float unit) {
                if (unit <= 0) {
                    return colors[0];
                }
                if (unit >= 1) {
                    return colors[colors.length - 1];
                }

                float p = unit * (colors.length - 1);
                int i = (int) p;
                p -= i;

                // now p is just the fractional part [0...1) and i is the index
                int c0 = colors[i];
                int c1 = colors[i + 1];
                int a = ave(Color.alpha(c0), Color.alpha(c1), p);
                int r = ave(Color.red(c0), Color.red(c1), p);
                int g = ave(Color.green(c0), Color.green(c1), p);
                int b = ave(Color.blue(c0), Color.blue(c1), p);

                return Color.argb(a, r, g, b);
            }

            /**
             * 获取渐变块上颜色
             *
             * @param colors
             * @param x
             * @return
             */
            private int interpRectColor(int colors[], float x) {
                int a, r, g, b, c0, c1;
                float p;
                if (x < 0) {
                    c0 = colors[0];
                    c1 = colors[1];
                    p = (x + rectRight) / rectRight;
                } else {
                    c0 = colors[1];
                    c1 = colors[2];
                    p = x / rectRight;
                }
                a = ave(Color.alpha(c0), Color.alpha(c1), p);
                r = ave(Color.red(c0), Color.red(c1), p);
                g = ave(Color.green(c0), Color.green(c1), p);
                b = ave(Color.blue(c0), Color.blue(c1), p);
                return Color.argb(a, r, g, b);
            }

            private int ave(int s, int d, float p) {
                return s + Math.round(p * (d - s));
            }
        }

        /**
         * 回调接口
         *
         * @author <a href="clarkamx@gmail.com">LynK</a>
         * <p>
         * Create on 2012-1-6 上午8:21:05
         */
        public interface OnColorChangedListener {
            /**
             * 回调函数
             *
             * @param color 选中的颜色
             */
            void colorChanged(int color);
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getmInitialColor() {
            return mInitialColor;
        }

        public void setmInitialColor(int mInitialColor) {
            this.mInitialColor = mInitialColor;
        }

        public OnColorChangedListener getmListener() {
            return mListener;
        }

        public void setmListener(OnColorChangedListener mListener) {
            this.mListener = mListener;
        }
    }

    /**
     * @param bitmap                 原bitmap
     * @param zoomWidthProportion_f  缩放宽比例,f后缀float类型
     * @param zoomHeightProportion_f 缩放高比例,f后缀float类型
     * @return bitmap
     * bitmap缩放,bitmap在原来的bitmap基础上缩放与控件无关
     */
    public static Bitmap bitmapZoom(Bitmap bitmap, float zoomWidthProportion_f, float zoomHeightProportion_f) {
        Matrix matrix = new Matrix();
        matrix.postScale(zoomWidthProportion_f, zoomHeightProportion_f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

    /**
     * @param bitmap  bitmap图片
     * @param widthPx widthPx为剪切后图片的宽和高,单位为px
     * @return bitmap
     * 剪切bitmap图片, 按正方形中间剪裁图片 指定正方形边长
     */
    public static Bitmap imageCrop(Bitmap bitmap, int widthPx) {
        // 得到图片的宽，高
        int w = bitmap.getWidth();//px
        int h = bitmap.getHeight();
        //width最大不能超过长方形的短边
        if (w < widthPx || h < widthPx) {
            widthPx = w > h ? h : w;
        }
        int retX = (w - widthPx) / 2;
        int retY = (h - widthPx) / 2;
        return Bitmap.createBitmap(bitmap, retX, retY, widthPx, widthPx, null, false);//retX为剪切的初始横向位置、retY为剪切的初始纵向位置、width为剪切的距离
    }

    /**
     * @param rl_share 控件
     * @return String 1:"" 内存无法读写 ; 2:"error" 存储异常 ;3:"xxx" 图片本地存储路径
     * view 转化成 bitmap ,分享时可能会用到,先生成图片,再保存本地,将地址传出,供分享时使用;将文章做成bitmap图片;该方法必须延时调用,单击调用等,不能在activityCreate时直接调用
     */
    public static String viewConversionBitmap(View rl_share) {
        // Bitmap b = Bitmap.createBitmap( rl_share.getLayoutParams().width, rl_share.getLayoutParams().height, Bitmap.Config.ARGB_8888);---error不能用
        Bitmap b = Bitmap.createBitmap(rl_share.getRight() - rl_share.getLeft(), rl_share.getBottom() - rl_share.getTop(), Bitmap.Config.ARGB_8888);//根据布局的实际大小构建bitmap
        Canvas c = new Canvas(b);
        rl_share.layout(rl_share.getLeft(), rl_share.getTop(), rl_share.getRight(), rl_share.getBottom());
        rl_share.draw(c);//之后的bitmap对象就是想要的图片
        //图片保存至本地
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + savePictureToLocalDirectoryName;//外部存储目录
        String state = Environment.getExternalStorageState();//获取内部存储状态
        if (!state.equals(Environment.MEDIA_MOUNTED)) {//如果状态不是mounted，无法读写
            return "";
        }
        String fileName1 = UUID.randomUUID().toString();//随机命名：通过UUID生成字符串文件名
        Random random = new Random();//通过Random()类生成数组命名
        String fileName2 = String.valueOf(random.nextInt(Integer.MAX_VALUE));
        try {
            File file = new File(dir + fileName1 + fileName2 + ".jpg");
            FileOutputStream out = new FileOutputStream(file);
            Bitmap mBitmap = b;//文章图片
            //mBitmap=createBitmapThumbnail(mBitmap,true);//bitmap变小后存入  ----这个图片大小符合原生微信的要求不需要变小
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            //Log.e("debug",""+"图片保存成功");
            mBitmap.recycle();
            String imgUrlPath = file.getAbsolutePath();//存储图片的本地路径
            out.flush();
            out.close();
            return imgUrlPath;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * @param bitmap      原图
     * @param roundPixels 原型化像素
     * @return bitmap
     * bitmap图片圆形化,最好将原bitmap进行最大的正方形化再进行圆形化,传参130dp可以构建成圆形,传入的px需要自行转换
     */
    public static Bitmap getRoundCornerImage(Bitmap bitmap, int roundPixels) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //创建一个和原始图片一样大小位图
        Bitmap roundConcerImage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //创建带有位图roundConcerImage的画布
        Canvas canvas = new Canvas(roundConcerImage);
        //创建画笔
        Paint paint = new Paint();
        //创建一个和原始图片一样大小的矩形
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        // 去锯齿
        paint.setAntiAlias(true);

        //画一个和原始图片一样大小的圆角矩形
        canvas.drawRoundRect(rectF, roundPixels, roundPixels, paint);
        //设置相交模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //把图片画到矩形去
        canvas.drawBitmap(bitmap, null, rect, paint);
        return roundConcerImage;
    }

    /**
     * @param networkImgUrl                         网络图片监听器
     * @param networkImgUrlConversionBitmapListener 网络图片转换bitmap的监听
     * @return void
     * 网络图片路径转换为bitmap
     */
    public static void networkImgUrlConversionBitmap(final String networkImgUrl, final NetworkImgUrlConversionBitmapListener networkImgUrlConversionBitmapListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL myFileUrl = null;
                try {
                    myFileUrl = new URL(networkImgUrl);
                    HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    Message message = new Message();
                    message.what = 0;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bitmap", bitmap);
                    message.obj = networkImgUrlConversionBitmapListener;
                    message.setData(bundle);
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    networkImgUrlConversionBitmapListener.networkImgUrlConversionBitmapListener(null);
                    e.printStackTrace();
                } catch (IOException e) {
                    networkImgUrlConversionBitmapListener.networkImgUrlConversionBitmapListener(null);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //网络图片转换bitmap的监听
    public interface NetworkImgUrlConversionBitmapListener {
        void networkImgUrlConversionBitmapListener(Bitmap bitmap);
    }

    /**
     * @param context   环境
     * @param imgIntUrl R类型资源文件
     * @return void 返回含结果的Toast
     * 保存图片到本地
     */
    public static void savePictureToLocal(Context context, int imgIntUrl) {
        //权限: <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + savePictureToLocalDirectoryName;//外部存储目录
        //获取内部存储状态
        String state = Environment.getExternalStorageState();
        //如果状态不是mounted，无法读写
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "你的手机暂不支持存储本图片", Toast.LENGTH_SHORT).show();
            return;
        }
        //随机命名：通过UUID生成字符串文件名
        String fileName1 = UUID.randomUUID().toString();
        //通过Random()类生成数组命名
        Random random = new Random();
        String fileName2 = String.valueOf(random.nextInt(Integer.MAX_VALUE));
        //时间命名：
        //Calendar now = new GregorianCalendar();
        //SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        //String fileName = simpleDate.format(now.getTime());
        //保存到文件中
        try {
            File file = new File(dir + fileName1 + fileName2 + ".jpg");
            FileOutputStream out = new FileOutputStream(file);
            Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), imgIntUrl);//imgIntUrl如：R.mipmap.xxx
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //保存图片后发送广播通知更新数据库--作用：即时将图片在相册中显示出来  alert
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            Toast.makeText(context, "保存图片成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "" + e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * @param context   环境
     * @param activity  环境
     * @param imgIntUrl R类型资源文件
     * @return void 存在写入内存权限进行图片本地存储,不存在申请权限并回调在activity 中
     * 保存图片到本地,存在权限的校验
     */
    public static void savePictureToLocal_before(Context context, Activity activity, int imgIntUrl) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        savePictureToLocal(context, imgIntUrl);
    }

    /**
     * @param activity 环境
     * @return void
     * 获取本地图片,获取图片第一阶段:获取,结果回调在activity 的onActivityResult回调中,再调用获取图片第二阶段
     */
    public static void getLoaclImg_before(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);//内存内容拾取
        intent.setType("image/*");//拾取类型
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * @param data                第一阶段回调的Intent对象
     * @param context             环境
     * @param requestRequestCode  请求的请求码
     * @param responseRequestCode 响应的请求码
     * @param resultCode          响应码
     * @return map path:图片的本地路径,bitmap:bitmap对象;null 不符合规则
     * 获取本地图片,获取图片第二阶段:结果,方法代替onActivityResult进行校验不符合规则的直接返回
     */
    public static Map<String, Object> getLoaclImg_after(Intent data, Context context, int requestRequestCode, int responseRequestCode, int resultCode) {
        if (!(requestRequestCode == responseRequestCode && resultCode == Activity.RESULT_OK && data != null))//不符合
            return null;
//    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" /> 本地图片获取需要读取内存权限,动态请求权限放在第一阶段前
        Uri selectedImage = data.getData();//返回的是uri
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String path = cursor.getString(columnIndex);//获取本地图片的路径
        //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        //Log.e("debug", path + "");
        Bitmap bitmap = BitmapFactory.decodeFile(path);//e.g. : /storage/emulated/0/jifenduihuanb2e43b84-6cd8-4379-ad2d-bea4d97175dd664617475.jpg
        //注意：本地获取图片返回的是原图片的路径，若做剪切，剪切后只会保存在原图上，给原图造成损害，所以做剪切应该先把拾取的图片做再存储，然后在新存储的图片上做剪切操作
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("path", path);
        hashMap.put("bitmap", bitmap);
        return hashMap;
    }


    /**
     * Android给图片加文字和图片水印
     * 图片工具类,在bitmap图片的基础上进行操作,dp入参转化为px由方法内部处理
     */
    public static class WatermarkImageUtil {
        private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark, int paddingLeft, int paddingTop) {
            if (src == null) {
                return null;
            }
            int width = src.getWidth();
            int height = src.getHeight();
            //创建一个bitmap
            Bitmap newb = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
            //将该图片作为画布
            Canvas canvas = new Canvas(newb);
            //在画布 0，0坐标上开始绘制原始图片
            canvas.drawBitmap(src, 0, 0, null);
            //在画布上绘制水印图片
            canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
            // 保存
            canvas.save();
            //canvas.save(Canvas.ALL_SAVE_FLAG);
            // 存储
            canvas.restore();
            return newb;
        }

        //设置水印图片在左上角
        public static Bitmap createWaterMaskLeftTop(Context context, Bitmap src, Bitmap watermark, int paddingLeftDp, int paddingTopDp) {
            return createWaterMaskBitmap(src, watermark,
                    dp2px(context, paddingLeftDp), dp2px(context, paddingTopDp));
        }

        // 设置水印图片在右下角
        public static Bitmap createWaterMaskRightBottom(Context context, Bitmap src, Bitmap watermark, int paddingRightDp, int paddingBottomDp) {
            return createWaterMaskBitmap(src, watermark,
                    src.getWidth() - watermark.getWidth() - dp2px(context, paddingRightDp),
                    src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottomDp));
        }

        // 设置水印图片到右上角
        public static Bitmap createWaterMaskRightTop(Context context, Bitmap src, Bitmap watermark, int paddingRightDp, int paddingTopDp) {
            return createWaterMaskBitmap(src, watermark,
                    src.getWidth() - watermark.getWidth() - dp2px(context, paddingRightDp),
                    dp2px(context, paddingTopDp));
        }

        //设置水印图片到左下角
        public static Bitmap createWaterMaskLeftBottom(Context context, Bitmap src, Bitmap watermark, int paddingLeftDp, int paddingBottomDp) {
            return createWaterMaskBitmap(src, watermark, dp2px(context, paddingLeftDp),
                    src.getHeight() - watermark.getHeight() - dp2px(context, paddingBottomDp));
        }

        //设置水印图片到中间
        public static Bitmap createWaterMaskCenter(Bitmap src, Bitmap watermark) {
            return createWaterMaskBitmap(src, watermark,
                    (src.getWidth() - watermark.getWidth()) / 2,
                    (src.getHeight() - watermark.getHeight()) / 2);//px
        }

        //给图片添加文字到左上角
        public static Bitmap drawTextToLeftTop(Context context, Bitmap bitmap, String text, int size, int color, int paddingLeftDp, int paddingTopDp) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(color);
            paint.setTextSize(dp2px(context, size));
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            return drawTextToBitmap(context, bitmap, text, paint, bounds,
                    dp2px(context, paddingLeftDp),
                    dp2px(context, paddingTopDp) + bounds.height());
        }

        // 绘制文字到右下角
        public static Bitmap drawTextToRightBottom(Context context, Bitmap bitmap, String text, int size, int color, int paddingRightDp, int paddingBottomDp) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(color);
            paint.setTextSize(dp2px(context, size));
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            return drawTextToBitmap(context, bitmap, text, paint, bounds,
                    bitmap.getWidth() - bounds.width() - dp2px(context, paddingRightDp),
                    bitmap.getHeight() - dp2px(context, paddingBottomDp));
        }

        // 绘制文字到右上方
        public static Bitmap drawTextToRightTop(Context context, Bitmap bitmap, String text, int size, int color, int paddingRightDp, int paddingTopDp) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(color);
            paint.setTextSize(dp2px(context, size));
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            return drawTextToBitmap(context, bitmap, text, paint, bounds,
                    bitmap.getWidth() - bounds.width() - dp2px(context, paddingRightDp),
                    dp2px(context, paddingTopDp) + bounds.height());
        }

        // 绘制文字到左下方
        public static Bitmap drawTextToLeftBottom(Context context, Bitmap bitmap, String text, int size, int color, int paddingLeftDp, int paddingBottomDp) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(color);
            paint.setTextSize(dp2px(context, size));
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            return drawTextToBitmap(context, bitmap, text, paint, bounds,
                    dp2px(context, paddingLeftDp),
                    bitmap.getHeight() - dp2px(context, paddingBottomDp));
        }

        // 绘制文字到中间
        public static Bitmap drawTextToCenter(Context context, Bitmap bitmap, String text, int size, int color) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(color);
            paint.setTextSize(dp2px(context, size));
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);
            return drawTextToBitmap(context, bitmap, text, paint, bounds,
                    (bitmap.getWidth() - bounds.width()) / 2,
                    (bitmap.getHeight() + bounds.height()) / 2);
        }

        //图片上绘制文字
        private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text, Paint paint, Rect bounds, int paddingLeft, int paddingTop) {
            Bitmap.Config bitmapConfig = bitmap.getConfig();

            paint.setDither(true); // 获取跟清晰的图像采样
            paint.setFilterBitmap(true);// 过滤一些
            if (bitmapConfig == null) {
                bitmapConfig = Bitmap.Config.ARGB_8888;
            }
            bitmap = bitmap.copy(bitmapConfig, true);
            Canvas canvas = new Canvas(bitmap);

            canvas.drawText(text, paddingLeft, paddingTop, paint);
            return bitmap;
        }

        /**
         * 缩放图片
         */
        public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
            if (w == 0 || h == 0 || src == null) {
                return src;
            } else {
                // 记录src的宽高
                int width = src.getWidth();
                int height = src.getHeight();
                // 创建一个matrix容器
                Matrix matrix = new Matrix();
                // 计算缩放比例
                float scaleWidth = (float) (w / width);
                float scaleHeight = (float) (h / height);
                // 开始缩放
                matrix.postScale(scaleWidth, scaleHeight);
                // 创建缩放后的图片
                return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
            }
        }

        //dip转pix
        public static int dp2px(Context context, float dp) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dp * scale + 0.5f);
        }
    }


    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://网络图片路径转换为bitmap方法,处理bitmap到ui线程
                    ((NetworkImgUrlConversionBitmapListener) msg.obj).networkImgUrlConversionBitmapListener((Bitmap) msg.getData().getParcelable("bitmap"));
                    break;
            }
        }
    };

}
