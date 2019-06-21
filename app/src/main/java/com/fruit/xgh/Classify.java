package com.fruit.xgh;

import java.util.List;

public class Classify {

    /**
     * MESSAGE : success
     * REQUEST : [{"id":1,"name":"今日推荐","status":1},{"id":10,"name":"芒橙柑柚","status":1}]
     */

    private String MESSAGE;
    private List<REQUESTBean> REQUEST;

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public List<REQUESTBean> getREQUEST() {
        return REQUEST;
    }

    public void setREQUEST(List<REQUESTBean> REQUEST) {
        this.REQUEST = REQUEST;
    }

    public static class REQUESTBean {
        /**
         * id : 1
         * name : 今日推荐
         * status : 1
         */

        private int id;
        private String name;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
