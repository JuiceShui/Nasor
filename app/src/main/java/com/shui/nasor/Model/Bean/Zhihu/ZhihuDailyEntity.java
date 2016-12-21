package com.shui.nasor.Model.Bean.Zhihu;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： max_Shui on 2016/12/10.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */
//日版实体

public class ZhihuDailyEntity implements Serializable{
    private static final long serialVersionUID = -4194119277751283021L;
    /**
     * date : 20161210
     * stories : [{"images":["http://pic2.zhimg.com/53f46bcd3e13150be3f69952ba3cb2c1.jpg"],"type":0,"id":9044079,"ga_prefix":"121018","title":"小区停车位的归属：我的地盘我做主？"},{"images":["http://pic1.zhimg.com/d3cad417599b3bf759e51bdb54f8eea8.jpg"],"type":0,"id":9053927,"ga_prefix":"121017","title":"知乎好问题 · 如何拍摄一部微电影？"},{"images":["http://pic3.zhimg.com/3c17c67fd6cfc079932fe4cb16550a96.jpg"],"type":0,"id":9044500,"ga_prefix":"121016","title":"24 帧电影最适合人眼？一个持续百年的错觉"},{"images":["http://pic1.zhimg.com/ae4f534b7f4bd0bda1cd69afe2d82b8c.jpg"],"type":0,"id":9038685,"ga_prefix":"121015","title":"你要是有这些习惯，车子一定坏得很快"},{"images":["http://pic1.zhimg.com/36ef575b099e9edb8faf9240b9612f24.jpg"],"type":0,"id":9044244,"ga_prefix":"121014","title":"钻法律漏洞骗钱的人，最后都折自己手里了"},{"images":["http://pic1.zhimg.com/480b58795f779ba927ad524741425d38.jpg"],"type":0,"id":9047658,"ga_prefix":"121013","title":"为什么大部分人点数喜欢「2，4，6，8\u2026\u2026」?"},{"images":["http://pic2.zhimg.com/314159c25bc2b825c1c874f9b890afe9.jpg"],"type":0,"id":9051160,"ga_prefix":"121012","title":"大误 · 关于利用瞬间移动赚钱的可行性研究"},{"images":["http://pic4.zhimg.com/f1d8b0f4f3e6f6920bd04a11765baaf3.jpg"],"type":0,"id":9039749,"ga_prefix":"121011","title":"冬日里，窝在沙发上等高压锅焖一锅热汤"},{"images":["http://pic4.zhimg.com/8703d19f192c5ff60c05c6a4be860b77.jpg"],"type":0,"id":9051503,"ga_prefix":"121010","title":"新发布的完整版 Windows 10，是个新方向的开始"},{"images":["http://pic1.zhimg.com/c93d4ee6fd1e432b93394878357700a0.jpg"],"type":0,"id":9047668,"ga_prefix":"121009","title":"为什么说「网游公布抽取概率」非常好，我来给你算算"},{"images":["http://pic2.zhimg.com/195076f4911a39b87a9611c611ee60b5.jpg"],"type":0,"id":9050762,"ga_prefix":"121008","title":"都是美食，为什么有的越吃越喜欢，有的很快就吃腻了？"},{"images":["http://pic2.zhimg.com/42125350a8466ad14068ed9768d388bd.jpg"],"type":0,"id":9041652,"ga_prefix":"121007","title":"监制很厉害也可能是烂片？看看他们的分工就知道了"},{"images":["http://pic2.zhimg.com/548ef6cc7c31941711df3a2365bc2fb9.jpg"],"type":0,"id":9045026,"ga_prefix":"121007","title":"老实说，电力行业本来就应该是部分垄断的"},{"images":["http://pic1.zhimg.com/ed9e087f68e53d4ab8d0ce80d4e44f84.jpg"],"type":0,"id":9049434,"ga_prefix":"121007","title":"中国商业医疗保险目前存在哪些问题？"},{"images":["http://pic3.zhimg.com/d91d4af06c4c7eb09e6ec5645911c89e.jpg"],"type":0,"id":9052551,"ga_prefix":"121007","title":"读读日报 24 小时热门 TOP 5 · 我妈卷入了广场舞天团的夺权纷争"},{"images":["http://pic3.zhimg.com/aca76eae6d8ece8b7b7e008a4edc9032.jpg"],"type":0,"id":9050475,"ga_prefix":"121006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic1.zhimg.com/3855411ce2400a6fbd183310c0af13bc.jpg","type":0,"id":9053927,"ga_prefix":"121017","title":"知乎好问题 · 如何拍摄一部微电影？"},{"image":"http://pic4.zhimg.com/e7d1c60ad3c2d3a3cb1fddaa31e87283.jpg","type":0,"id":9038685,"ga_prefix":"121015","title":"你要是有这些习惯，车子一定坏得很快"},{"image":"http://pic2.zhimg.com/d50123a31ffc66defb31370324e77639.jpg","type":0,"id":9044244,"ga_prefix":"121014","title":"钻法律漏洞骗钱的人，最后都折自己手里了"},{"image":"http://pic4.zhimg.com/078659fe175594dc20d4afe5da8e2aa3.jpg","type":0,"id":9047668,"ga_prefix":"121009","title":"为什么说「网游公布抽取概率」非常好，我来给你算算"},{"image":"http://pic1.zhimg.com/22dd9e61f8cc1bff41dd32f136b1c2a4.jpg","type":0,"id":9051503,"ga_prefix":"121010","title":"新发布的完整版 Windows 10，是个新方向的开始"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public static ZhihuDailyEntity objectFromData(String str) {

        return new Gson().fromJson(str, ZhihuDailyEntity.class);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic2.zhimg.com/53f46bcd3e13150be3f69952ba3cb2c1.jpg"]
         * type : 0
         * id : 9044079
         * ga_prefix : 121018
         * title : 小区停车位的归属：我的地盘我做主？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public boolean getReadState() {
            return ReadState;
        }

        public void setReadState(boolean readState) {
            ReadState = readState;
        }

        private boolean ReadState;
        public static StoriesBean objectFromData(String str) {

            return new Gson().fromJson(str, StoriesBean.class);
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic1.zhimg.com/3855411ce2400a6fbd183310c0af13bc.jpg
         * type : 0
         * id : 9053927
         * ga_prefix : 121017
         * title : 知乎好问题 · 如何拍摄一部微电影？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public static TopStoriesBean objectFromData(String str) {

            return new Gson().fromJson(str, TopStoriesBean.class);
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
