package com.fruit.xgh.home.bean;

import java.util.List;

public class ChannelBean {
    /**
     * code : 200
     * msg : 请求成功
     * result : {"act_info":[{"icon_url":"/operation/img/1478169868/1478761370286.png","name":"福利专区之111.1专区","url":"/oper/1478169868app.html"},{"icon_url":"/operation/img/1478763176/1478762941492.png","name":"黄金狗粮限量11.1元抢","url":"/oper/1478763176app.html"},{"icon_url":"/operation/img/1478763176/1478762941492.png","name":"福利专区","url":"/oper/1478763176app.html"}],"channel_info":[{"channel_name":"推荐","image":"/app/img/tuijian.png","option":2,"type":1,"value":{"channel_id":"8"}},{"channel_name":"新鲜水果","image":"/app/img/shuiguo.png","option":2,"type":1,"value":{"channel_id":"4"}},{"channel_name":"签到有礼","image":"/app/img/qiandao.png","option":2,"type":1,"value":{"channel_id":"3"}},{"channel_name":"优惠卷","image":"/app/img/youhuijuan.png","option":2,"type":1,"value":{"channel_id":"5"}}]}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<ActInfoBean> act_info;
        private List<ChannelInfoBean> channel_info;

        public List<ActInfoBean> getAct_info() {
            return act_info;
        }

        public void setAct_info(List<ActInfoBean> act_info) {
            this.act_info = act_info;
        }

        public List<ChannelInfoBean> getChannel_info() {
            return channel_info;
        }

        public void setChannel_info(List<ChannelInfoBean> channel_info) {
            this.channel_info = channel_info;
        }

        public static class ActInfoBean {
            /**
             * icon_url : /operation/img/1478169868/1478761370286.png
             * name : 福利专区之111.1专区
             * url : /oper/1478169868app.html
             */

            private String icon_url;
            private String name;
            private String url;

            public String getIcon_url() {
                return icon_url;
            }

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ChannelInfoBean {
            /**
             * channel_name : 推荐
             * image : /app/img/tuijian.png
             * option : 2
             * type : 1
             * value : {"channel_id":"8"}
             */

            private String channel_name;
            private String image;
            private int option;
            private int type;
            private ValueBean value;

            public String getChannel_name() {
                return channel_name;
            }

            public void setChannel_name(String channel_name) {
                this.channel_name = channel_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getOption() {
                return option;
            }

            public void setOption(int option) {
                this.option = option;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public ValueBean getValue() {
                return value;
            }

            public void setValue(ValueBean value) {
                this.value = value;
            }

            public static class ValueBean {
                /**
                 * channel_id : 8
                 */

                private String channel_id;

                public String getChannel_id() {
                    return channel_id;
                }

                public void setChannel_id(String channel_id) {
                    this.channel_id = channel_id;
                }
            }
        }
    }
}
