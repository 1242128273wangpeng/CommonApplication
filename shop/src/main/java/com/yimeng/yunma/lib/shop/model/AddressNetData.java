package com.yimeng.yunma.lib.shop.model;



import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/4 0004.
 */
public class AddressNetData extends BasisData implements Serializable {
    private ResultMap resultMap;

    public ResultMap getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    public class ResultMap implements Serializable {
        private int count;

        private List<Result> result;

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }

        public void setResult(List<Result> result) {
            this.result = result;
        }

        public List<Result> getResult() {
            return this.result;
        }
        public class Result implements Serializable {
            private String address;

            private int id;

            private String name;

            private String phone;

            private String status;

            private String userId;

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAddress() {
                return this.address;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getId() {
                return this.id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return this.name;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPhone() {
                return this.phone;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatus() {
                return this.status;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserId() {
                return this.userId;
            }

        }
    }



}
