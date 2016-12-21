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
//热门实体

public class ZhihuHotEntity implements Serializable {
    private static final long serialVersionUID = 8193932724435185430L;

    private List<RecentBean> recent;

    public static ZhihuHotEntity objectFromData(String str) {

        return new Gson().fromJson(str, ZhihuHotEntity.class);
    }

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public static class RecentBean {
        /**
         * news_id : 9035836
         * url : http://news-at.zhihu.com/api/2/news/9035836
         * thumbnail : http://pic4.zhimg.com/713020e1b0b4180cff0048770b09ce87.jpg
         * title : 当法官示意记录员暂停记录时，好戏就开始了
         */

        private int news_id;
        private String url;
        private String thumbnail;
        private String title;
        private boolean readState;

        public boolean getReadState() {
            return readState;
        }

        public void setReadState(boolean readState) {
            this.readState = readState;
        }

        public static RecentBean objectFromData(String str) {

            return new Gson().fromJson(str, RecentBean.class);
        }

        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
