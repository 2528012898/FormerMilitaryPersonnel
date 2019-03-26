package com.qiye.formermilitaryp.bean.response;

import java.io.Serializable;

public class KunNanBangFuDetailsBean implements Serializable {

    /**
     * message : 查询成功！！！
     * retCode : 0
     * data : {"id":"328D26F3CA264DAD82A61436688798D6","realName":"小明","event":"aaaa","details":"因为某些原因导致xxxx","createTime":"2019-03-21T09:15:48.000+0000"}
     */

    private String message;
    private int retCode;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 328D26F3CA264DAD82A61436688798D6
         * realName : 小明
         * event : aaaa
         * details : 因为某些原因导致xxxx
         * createTime : 2019-03-21T09:15:48.000+0000
         */

        private String id;
        private String realName;
        private String event;
        private String details;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
