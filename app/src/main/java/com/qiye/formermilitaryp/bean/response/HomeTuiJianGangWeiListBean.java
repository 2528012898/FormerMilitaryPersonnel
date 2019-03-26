package com.qiye.formermilitaryp.bean.response;

import java.util.List;

public class HomeTuiJianGangWeiListBean {

    /**
     * message : 查询成功！！！
     * retCode : 0
     * data : {"total":2,"list":[{"thumbnail":"https://mail.163.com/js6/s?func=mbox:getComposeData&sid=uCVAyBDsSVpKnnbiBfssKhpWAgeRXgre&composeId=c:1553154218863&attachId=2&rnd=0.34969568310736787","newTags":["单休","社保"],"createTime":"2019-03-21 15:36:52","subtitle":"经验不限/大专/全职","id":"C3A625B499FB48EAB9047897859E033A","title":"房地产销售","tags":"单休,社保","wage":"4000-5000"},{"thumbnail":"https://mail.163.com/js6/s?func=mbox:getComposeData&sid=uCVAyBDsSVpKnnbiBfssKhpWAgeRXgre&composeId=c:1553154218863&attachId=1&rnd=0.5046077500928694","newTags":["双休","五险一金"],"createTime":"2019-03-21 15:26:16","subtitle":"经验不限/大专/全职","id":"CD6F4C223CA64C0087284EF64DD153A3","title":"全职销售","tags":"双休,五险一金","wage":"4000-5000"}],"pageNum":1,"pageSize":10,"size":2,"startRow":1,"endRow":2,"pages":1,"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"lastPage":1,"firstPage":1}
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
         * total : 2
         * list : [{"thumbnail":"https://mail.163.com/js6/s?func=mbox:getComposeData&sid=uCVAyBDsSVpKnnbiBfssKhpWAgeRXgre&composeId=c:1553154218863&attachId=2&rnd=0.34969568310736787","newTags":["单休","社保"],"createTime":"2019-03-21 15:36:52","subtitle":"经验不限/大专/全职","id":"C3A625B499FB48EAB9047897859E033A","title":"房地产销售","tags":"单休,社保","wage":"4000-5000"},{"thumbnail":"https://mail.163.com/js6/s?func=mbox:getComposeData&sid=uCVAyBDsSVpKnnbiBfssKhpWAgeRXgre&composeId=c:1553154218863&attachId=1&rnd=0.5046077500928694","newTags":["双休","五险一金"],"createTime":"2019-03-21 15:26:16","subtitle":"经验不限/大专/全职","id":"CD6F4C223CA64C0087284EF64DD153A3","title":"全职销售","tags":"双休,五险一金","wage":"4000-5000"}]
         * pageNum : 1
         * pageSize : 10
         * size : 2
         * startRow : 1
         * endRow : 2
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
             * thumbnail : https://mail.163.com/js6/s?func=mbox:getComposeData&sid=uCVAyBDsSVpKnnbiBfssKhpWAgeRXgre&composeId=c:1553154218863&attachId=2&rnd=0.34969568310736787
             * newTags : ["单休","社保"]
             * createTime : 2019-03-21 15:36:52
             * subtitle : 经验不限/大专/全职
             * id : C3A625B499FB48EAB9047897859E033A
             * title : 房地产销售
             * tags : 单休,社保
             * wage : 4000-5000
             */

            private String thumbnail;
            private String createTime;
            private String subtitle;
            private String id;
            private String title;
            private String tags;
            private String wage;
            private List<String> newTags;

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getWage() {
                return wage;
            }

            public void setWage(String wage) {
                this.wage = wage;
            }

            public List<String> getNewTags() {
                return newTags;
            }

            public void setNewTags(List<String> newTags) {
                this.newTags = newTags;
            }
        }
    }
}
