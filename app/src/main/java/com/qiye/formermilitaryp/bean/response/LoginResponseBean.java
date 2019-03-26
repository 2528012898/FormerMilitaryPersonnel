package com.qiye.formermilitaryp.bean.response;

import java.util.List;

public class LoginResponseBean {

    /**
     * message : 登录成功！
     * retCode : 0
     * data : {"position":["普通用户"],"user":{"id":"34C413C8BBD9406987F2989F4F9DF082","userName":"波本","sex":1,"age":10,"certType":null,"certNumber":"410711199700000000","phone":"15836035235","registeredPlace":"河南新乡","homeAddress":"河南新乡"},"sid":"df7dc13d-e992-4d9b-9989-1183443fc73d"}
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

    public static class DataBean {
        /**
         * position : ["普通用户"]
         * user : {"id":"34C413C8BBD9406987F2989F4F9DF082","userName":"波本","sex":1,"age":10,"certType":null,"certNumber":"410711199700000000","phone":"15836035235","registeredPlace":"河南新乡","homeAddress":"河南新乡"}
         * sid : df7dc13d-e992-4d9b-9989-1183443fc73d
         */

        private UserBean user;
        private String sid;
        private List<String> position;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public List<String> getPosition() {
            return position;
        }

        public void setPosition(List<String> position) {
            this.position = position;
        }

        public static class UserBean {
            /**
             * id : 34C413C8BBD9406987F2989F4F9DF082
             * userName : 波本
             * sex : 1
             * age : 10
             * certType : null
             * certNumber : 410711199700000000
             * phone : 15836035235
             * registeredPlace : 河南新乡
             * homeAddress : 河南新乡
             */

            private String id;
            private String userName;
            private int sex;
            private int age;
            private Object certType;
            private String certNumber;
            private String phone;
            private String registeredPlace;
            private String homeAddress;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public Object getCertType() {
                return certType;
            }

            public void setCertType(Object certType) {
                this.certType = certType;
            }

            public String getCertNumber() {
                return certNumber;
            }

            public void setCertNumber(String certNumber) {
                this.certNumber = certNumber;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRegisteredPlace() {
                return registeredPlace;
            }

            public void setRegisteredPlace(String registeredPlace) {
                this.registeredPlace = registeredPlace;
            }

            public String getHomeAddress() {
                return homeAddress;
            }

            public void setHomeAddress(String homeAddress) {
                this.homeAddress = homeAddress;
            }
        }
    }
}
