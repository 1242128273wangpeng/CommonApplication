package com.yimeng.yunma.lib.shop.model;



import java.util.List;

/**
*  Desc :
*  Create by WangPeng on 2017/6/5 0005
*/
public class HistoryUserNetData extends BasisData {
    private List<ResultMap> resultMap;

    public List<ResultMap> getResultMap() {
        return resultMap;
    }

    public void setResultMap(List<ResultMap> resultMap) {
        this.resultMap = resultMap;
    }

    public class ResultMap {
        private int id;

        private String phone;

        private int flag;

        private String marks;

        private int userId;

        private String name;

        private String subscribeTime;

        private String qq;

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getFlag() {
            return this.flag;
        }

        public void setMarks(String marks) {
            this.marks = marks;
        }

        public String getMarks() {
            return this.marks;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return this.userId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setSubscribeTime(String subscribeTime) {
            this.subscribeTime = subscribeTime;
        }

        public String getSubscribeTime() {
            return this.subscribeTime;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getQq() {
            return this.qq;
        }

    }
}
