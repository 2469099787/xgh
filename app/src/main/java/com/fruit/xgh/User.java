package com.fruit.xgh;

public class User {


    /**
     * MESSAGE : success
     * REQUEST : {"id":4,"intgral":null,"nickname":"12345678902","password":"123","phone":"12345678902","picture":null,"sex":null}
     */

    private String MESSAGE;
    private REQUESTBean REQUEST;


    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public REQUESTBean getREQUEST() {
        return REQUEST;
    }

    public void setREQUEST(REQUESTBean REQUEST) {
        this.REQUEST = REQUEST;
    }

    public static class REQUESTBean {
        /**
         * id : 4
         * intgral : null
         * nickname : 12345678902
         * password : 123
         * phone : 12345678902
         * picture : null
         * sex : null
         */

        private int id;
        private Object intgral;
        private String nickname;
        private String password;
        private String phone;
        private Object picture;
        private Object sex;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getIntgral() {
            return intgral;
        }

        public void setIntgral(Object intgral) {
            this.intgral = intgral;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getPicture() {
            return picture;
        }

        public void setPicture(Object picture) {
            this.picture = picture;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "REQUESTBean{" +
                    "id=" + id +
                    ", intgral=" + intgral +
                    ", nickname='" + nickname + '\'' +
                    ", password='" + password + '\'' +
                    ", phone='" + phone + '\'' +
                    ", picture=" + picture +
                    ", sex=" + sex +
                    '}';
        }
    }
}

