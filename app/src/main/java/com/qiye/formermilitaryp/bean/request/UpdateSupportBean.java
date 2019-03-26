package com.qiye.formermilitaryp.bean.request;

/**
 * 修改困难请求bean
 */
public class UpdateSupportBean {

    /**
     * details :
     * event :
     * realName :
     * id :
     */

    private String details;
    private String event;
    private String realName;
    private String id;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
