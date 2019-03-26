package com.qiye.formermilitaryp.bean.response;

import java.util.List;

public class HomeMenuBean {

    /**
     * message : 查询成功！！！
     * retCode : 0
     * data : {"total":8,"list":[{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/a50f649717504086b9108f6f37545456.png?Expires=1867301401&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=t4bZjeUh9uGi2RjAenLJein8apI%3D","menuUrl":"./courseList/courseList","createTime":null,"menuName":"动态"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/a770d43e474b4a80977092d54e6bac36.png?Expires=1867301444&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=wdI8cjA9E5mPD%2FI1og322o2RgMU%3D","menuUrl":"./activeList/activeList","createTime":null,"menuName":"预约"},{"img":"https://wantongbao.oss-cn-beijing.aliyuncs.com/images/2019-03-15/d29def55c23345078f0c75f84ee871b2.png?Expires=1867992273&OSSAccessKeyId=LTAIafl9gvZoAKf6&Signature=Eohr4e34eUjoH25sHOj8MydDS2c%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"困难帮扶"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"培训"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"安置"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"就业"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"咨询"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"采集"}],"pageNum":1,"pageSize":10,"size":8,"startRow":1,"endRow":8,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
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
         * total : 8
         * list : [{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/a50f649717504086b9108f6f37545456.png?Expires=1867301401&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=t4bZjeUh9uGi2RjAenLJein8apI%3D","menuUrl":"./courseList/courseList","createTime":null,"menuName":"动态"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/a770d43e474b4a80977092d54e6bac36.png?Expires=1867301444&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=wdI8cjA9E5mPD%2FI1og322o2RgMU%3D","menuUrl":"./activeList/activeList","createTime":null,"menuName":"预约"},{"img":"https://wantongbao.oss-cn-beijing.aliyuncs.com/images/2019-03-15/d29def55c23345078f0c75f84ee871b2.png?Expires=1867992273&OSSAccessKeyId=LTAIafl9gvZoAKf6&Signature=Eohr4e34eUjoH25sHOj8MydDS2c%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"困难帮扶"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"培训"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"安置"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"就业"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"咨询"},{"img":"http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/612f691d147c4c5f8bf9af1396233058.png?Expires=1867301497&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=ZZJzJFTP39XRQIE0j%2BUpqLFU5Oo%3D","menuUrl":"../commingSoon/commingSoon","createTime":null,"menuName":"采集"}]
         * pageNum : 1
         * pageSize : 10
         * size : 8
         * startRow : 1
         * endRow : 8
         * pages : 1
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * lastPage : 1
         * firstPage : 1
         */

        private int total;
        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int lastPage;
        private int firstPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * img : http://qiyecrm-oss.oss-cn-beijing.aliyuncs.com/images/2019-03-07/a50f649717504086b9108f6f37545456.png?Expires=1867301401&OSSAccessKeyId=LTAIThD3KM2viOhv&Signature=t4bZjeUh9uGi2RjAenLJein8apI%3D
             * menuUrl : ./courseList/courseList
             * createTime : null
             * menuName : 动态
             */

            private String img;
            private String menuUrl;
            private Object createTime;
            private String menuName;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getMenuUrl() {
                return menuUrl;
            }

            public void setMenuUrl(String menuUrl) {
                this.menuUrl = menuUrl;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public String getMenuName() {
                return menuName;
            }

            public void setMenuName(String menuName) {
                this.menuName = menuName;
            }
        }
    }
}
