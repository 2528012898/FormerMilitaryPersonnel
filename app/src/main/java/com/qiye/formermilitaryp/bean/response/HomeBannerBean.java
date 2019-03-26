package com.qiye.formermilitaryp.bean.response;

import java.util.List;

public class HomeBannerBean {

    /**
     * message : 查询成功！！！
     * retCode : 0
     * data : [{"imgUrl":"https://wantongbao.oss-cn-beijing.aliyuncs.com/images/2019-03-15/a1baf1eb8e944446ad3e7211feb28f95.png?Expires=1867977207&OSSAccessKeyId=LTAIafl9gvZoAKf6&Signature=jYrJaTRZwsB14CKsrbivFJMoLl0%3D","id":"2AF99A06FF0A40088F2F3825A37EB6D5"},{"imgUrl":"https://wantongbao.oss-cn-beijing.aliyuncs.com/images/2019-03-15/759267dddb834fd3b3c0c7fd2a477e1e.png?Expires=1867977331&OSSAccessKeyId=LTAIafl9gvZoAKf6&Signature=l0mtQ8xhYi15rpF7od6FvYfTWaU%3D","id":"6165BC00C40A4161BAC4DCDB8D977D26"},{"imgUrl":"https://wantongbao.oss-cn-beijing.aliyuncs.com/images/2019-03-15/a84e4c42b68d4021a18da513610eb590.png?Expires=1867982558&OSSAccessKeyId=LTAIafl9gvZoAKf6&Signature=BGjn9cCb7EvoQt%2BPbDeZ8OxxzN8%3D","id":"E06F402EB65D444493990B3652FDBF25"},{"imgUrl":"https://wantongbao.oss-cn-beijing.aliyuncs.com/images/2019-03-15/759267dddb834fd3b3c0c7fd2a477e1e.png?Expires=1867977331&OSSAccessKeyId=LTAIafl9gvZoAKf6&Signature=l0mtQ8xhYi15rpF7od6FvYfTWaU%3D","id":"ED24E1ACA97348EB8B81DF4D1C00E9A6"},{"imgUrl":"https://wantongbao.oss-cn-beijing.aliyuncs.com/images/2019-03-15/336be497c61c4df6ad6a1c92c8e9290a.png?Expires=1867977316&OSSAccessKeyId=LTAIafl9gvZoAKf6&Signature=OH440HYm5qd5uI3mZB91n4KRvXk%3D","id":"FB301449C92849429797D029B2128CEF"}]
     */

    private String message;
    private int retCode;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgUrl : https://wantongbao.oss-cn-beijing.aliyuncs.com/images/2019-03-15/a1baf1eb8e944446ad3e7211feb28f95.png?Expires=1867977207&OSSAccessKeyId=LTAIafl9gvZoAKf6&Signature=jYrJaTRZwsB14CKsrbivFJMoLl0%3D
         * id : 2AF99A06FF0A40088F2F3825A37EB6D5
         */

        private String imgUrl;
        private String id;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
