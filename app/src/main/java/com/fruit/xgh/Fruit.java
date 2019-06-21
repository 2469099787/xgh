package com.fruit.xgh;

import java.util.List;

public class Fruit {

    /**
     * MESSAGE : success
     * REQUEST : {"classify":{"id":1,"name":"今日推荐","status":1},"discountPrice":6.5,"evaluates":[],"fruitDetail":{"id":2,"picture1":"p/2-1.jpg","picture2":null,"picture3":null,"picture4":null,"picture5":null,"pictureA":"p/2-a.jpg","pictureB":"p/2-b.jpg","pictureC":null},"grade":"B级","id":3,"intro":"","name":"油桃","picture":"p/2.jpg","price":7.1,"producingArea":"四川","remind":"冷藏后口感更加","repertoty":2,"salesVolume":56,"specificaion":"250-300g","srorageCondition":"常温","status":1}
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
         * classify : {"id":1,"name":"今日推荐","status":1}
         * discountPrice : 6.5
         * evaluates : []
         * fruitDetail : {"id":2,"picture1":"p/2-1.jpg","picture2":null,"picture3":null,"picture4":null,"picture5":null,"pictureA":"p/2-a.jpg","pictureB":"p/2-b.jpg","pictureC":null}
         * grade : B级
         * id : 3
         * intro :
         * name : 油桃
         * picture : p/2.jpg
         * price : 7.1
         * producingArea : 四川
         * remind : 冷藏后口感更加
         * repertoty : 2
         * salesVolume : 56
         * specificaion : 250-300g
         * srorageCondition : 常温
         * status : 1
         */

        private ClassifyBean classify;
        private double discountPrice;
        private FruitDetailBean fruitDetail;
        private String grade;
        private int id;
        private String intro;
        private String name;
        private String picture;
        private double price;
        private String producingArea;
        private String remind;
        private int repertoty;
        private int salesVolume;
        private String specificaion;
        private String srorageCondition;
        private int status;
        private List<?> evaluates;

        public ClassifyBean getClassify() {
            return classify;
        }

        public void setClassify(ClassifyBean classify) {
            this.classify = classify;
        }

        public double getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(double discountPrice) {
            this.discountPrice = discountPrice;
        }

        public FruitDetailBean getFruitDetail() {
            return fruitDetail;
        }

        public void setFruitDetail(FruitDetailBean fruitDetail) {
            this.fruitDetail = fruitDetail;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getPrice() {
            return (int) price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getProducingArea() {
            return producingArea;
        }

        public void setProducingArea(String producingArea) {
            this.producingArea = producingArea;
        }

        public String getRemind() {
            return remind;
        }

        public void setRemind(String remind) {
            this.remind = remind;
        }

        public int getRepertoty() {
            return repertoty;
        }

        public void setRepertoty(int repertoty) {
            this.repertoty = repertoty;
        }

        public int getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(int salesVolume) {
            this.salesVolume = salesVolume;
        }

        public String getSpecificaion() {
            return specificaion;
        }

        public void setSpecificaion(String specificaion) {
            this.specificaion = specificaion;
        }

        public String getSrorageCondition() {
            return srorageCondition;
        }

        public void setSrorageCondition(String srorageCondition) {
            this.srorageCondition = srorageCondition;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<?> getEvaluates() {
            return evaluates;
        }

        public void setEvaluates(List<?> evaluates) {
            this.evaluates = evaluates;
        }

        public static class ClassifyBean {
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

        public static class FruitDetailBean {
            /**
             * id : 2
             * picture1 : p/2-1.jpg
             * picture2 : null
             * picture3 : null
             * picture4 : null
             * picture5 : null
             * pictureA : p/2-a.jpg
             * pictureB : p/2-b.jpg
             * pictureC : null
             */

            private int id;
            private String picture1;
            private Object picture2;
            private Object picture3;
            private Object picture4;
            private Object picture5;
            private String pictureA;
            private String pictureB;
            private Object pictureC;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPicture1() {
                return picture1;
            }

            public void setPicture1(String picture1) {
                this.picture1 = picture1;
            }

            public Object getPicture2() {
                return picture2;
            }

            public void setPicture2(Object picture2) {
                this.picture2 = picture2;
            }

            public Object getPicture3() {
                return picture3;
            }

            public void setPicture3(Object picture3) {
                this.picture3 = picture3;
            }

            public Object getPicture4() {
                return picture4;
            }

            public void setPicture4(Object picture4) {
                this.picture4 = picture4;
            }

            public Object getPicture5() {
                return picture5;
            }

            public void setPicture5(Object picture5) {
                this.picture5 = picture5;
            }

            public String getPictureA() {
                return pictureA;
            }

            public void setPictureA(String pictureA) {
                this.pictureA = pictureA;
            }

            public String getPictureB() {
                return pictureB;
            }

            public void setPictureB(String pictureB) {
                this.pictureB = pictureB;
            }

            public Object getPictureC() {
                return pictureC;
            }

            public void setPictureC(Object pictureC) {
                this.pictureC = pictureC;
            }
        }
    }
}
