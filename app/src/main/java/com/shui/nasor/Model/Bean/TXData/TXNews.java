package com.shui.nasor.Model.Bean.TXData;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： JuiceShui on 2017/2/21.
 * We improve ourselves by victories over ourselves.
 * There must be contests, and we must win.
 */

/**
 * 国内新闻
 */
public class TXNews implements Serializable{


    private static final long serialVersionUID = -3328979993058795077L;
    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-02-21 16:13","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481309964_ss.jpeg","title":"一图看懂2016年我国商务运行基本情况","url":"http://news.sohu.com/20170221/n481313161.shtml"},{"ctime":"2017-02-21 16:31","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481313163_ss.jpeg","title":"台湾推旅游新规：若旅途长 中途必须更换司机","url":"http://news.sohu.com/20170221/n481315042.shtml"},{"ctime":"2017-02-21 15:15","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481297965_ss.jpeg","title":"聚焦习近平总书记\u201c2-19\u201d重要讲话一周年综述","url":"http://news.sohu.com/20170221/n481304725.shtml"},{"ctime":"2017-02-21 15:20","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481305232_ss.jpg","title":"台中市长语出惊人：人类文明的发源地就在台湾","url":"http://news.sohu.com/20170221/n481305231.shtml"},{"ctime":"2017-02-21 15:42","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481309222_ss.jpg","title":"神吐槽：房价高 为何背锅的总是单身狗","url":"http://news.sohu.com/20170221/n481309221.shtml"},{"ctime":"2017-02-21 15:47","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481309964_ss.jpeg","title":"死亡人数增加H7N9进入高发期 如何预防才有效？","url":"http://news.sohu.com/20170221/n481309962.shtml"},{"ctime":"2017-02-21 14:17","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481295306_ss.jpeg","title":"支付宝可异地续签港澳通行证 109个城市率先接入","url":"http://news.sohu.com/20170221/n481297964.shtml"},{"ctime":"2017-02-21 14:29","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481297965_ss.jpeg","title":"刑讯逼供、冤假错案怎么破？最高法发文立规","url":"http://news.sohu.com/20170221/n481299325.shtml"},{"ctime":"2017-02-21 13:24","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481291166_ss.jpeg","title":"日本要台湾解禁核灾区食品 谢长廷：应该的","url":"http://news.sohu.com/20170221/n481293734.shtml"},{"ctime":"2017-02-21 13:26","description":"搜狐国内","picUrl":"http://photocdn.sohu.com/20170221/Img481293735_ss.jpeg","title":"人社部副部长孔昌生\u201c空降\u201d任河南省委组织部部长","url":"http://news.sohu.com/20170221/n481293864.shtml"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public static TXNews objectFromData(String str) {

        return new Gson().fromJson(str, TXNews.class);
    }

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

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-02-21 16:13
         * description : 搜狐国内
         * picUrl : http://photocdn.sohu.com/20170221/Img481309964_ss.jpeg
         * title : 一图看懂2016年我国商务运行基本情况
         * url : http://news.sohu.com/20170221/n481313161.shtml
         */

        private String ctime;
        private String description;
        private String picUrl;
        private String title;
        private String url;

        public static NewslistBean objectFromData(String str) {

            return new Gson().fromJson(str, NewslistBean.class);
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
