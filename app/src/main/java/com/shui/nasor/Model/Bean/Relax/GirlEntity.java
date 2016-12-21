package com.shui.nasor.Model.Bean.Relax;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： max_Shui on 2016/12/16.
 * 邮箱：shuicz0505@qq.com
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 * ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ  ( ゜- ゜)つロ
 */


public class GirlEntity implements Serializable {
    private static final long serialVersionUID = 7607784404155937251L;

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"newslist":[{"title":"sugarå°\u008fç\u201dœå¿ƒCC","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/13/201612161359328891-2285289.jpg","description":"????","ctime":"2016-12-16 15:00","url":"http://bbs.voc.com.cn/mm/meinv-7588935-0-1.html"},{"title":"è¶Šå\u008d\u2014æ­£å¦¹ é˜®æ°\u008fçŽ\u2030åª\u203a çŽ¯ç\u0090ƒå°\u008få§\u0090å\u2020 å\u2020\u203aæ¯\u201dåŸºå°¼å\u2020™çœŸ","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/11/m.xxxiao.com_9dd3e72aae77f0036d2eb1a3f43099cf-682x1024.jpg","description":"ç¾Žå¥³å\u2020™çœŸ","ctime":"2016-12-16 15:00","url":"http://m.xxxiao.com/90629"},{"title":"å\u2020¯é\u203aªå¨\u2021 çº¤ç»\u2020å\u008fŒè\u2026¿æ\u009d¨æŸ³ç»\u2020è\u2026°é\u009d¢è\u2039¥æ¡ƒèŠ±å¤§å°ºåº¦SMæƒ\u2026è¶£æ\u008d\u2020ç¼š","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/12/m.xxxiao.com_71750f4710b2b2d6ac3d362a4ce2ea62-614x1024.jpg","description":"ç¾Žå¥³å\u2020™çœŸ","ctime":"2016-12-16 15:00","url":"http://m.xxxiao.com/102000"},{"title":"æ°\u201dè´¨ç¾Žå¥³ æž\u2014è\u2022¾KikiCå©§ ä¼˜é\u203a\u2026è¿žè¡£è£™ å¤§å°ºåº¦å\u2020\u2026è¡£æ\u008d\u201aä¸\u008dä½\u008fçš\u201eå\u008fŒå³°è¯±æƒ\u2018","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/12/m.xxxiao.com_d77c6713f6cc1e4d247969be9fe3c2ee-614x1024.jpg","description":"ç¾Žå¥³å\u2020™çœŸ","ctime":"2016-12-16 15:00","url":"http://m.xxxiao.com/102001"},{"title":"美臀女神 胡晓斐沐熙 极品性感猫女郎","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/09/m.xxxiao.com_c9f5825f9afa25607a5e926a1be7729c-683x1024.jpg","description":"美女写真","ctime":"2016-12-16 14:00","url":"http://m.xxxiao.com/76207"},{"title":"爱的尤物 温鈊怡 少女的美妙大尺度诱人照","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/09/m.xxxiao.com_21561ad65f874f2105456e9b3c1c6575-614x1024.jpg","description":"美女写真","ctime":"2016-12-16 13:00","url":"http://m.xxxiao.com/72435"},{"title":"肉感妹子 严佳丽 尺度情趣内衣写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/07/m.xxxiao.com_06f1f4d7ab63f98f7198d8e87b120ec3-681x1024.jpg","description":"美女写真","ctime":"2016-12-16 11:00","url":"http://m.xxxiao.com/57906"},{"title":"[贴图]姑娘心恋白云湖","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/09/201612160918596681-4217076.jpg","description":"华声美女","ctime":"2016-12-16 10:00","url":"http://bbs.voc.com.cn/mm/meinv-7588388-0-1.html"},{"title":"[贴图]龙潭公园拍美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/09/201612160914587591-4217076.jpg","description":"华声美女","ctime":"2016-12-16 10:00","url":"http://bbs.voc.com.cn/mm/meinv-7588380-0-1.html"},{"title":"[贴图]漂亮的睡衣姑娘","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/09/201612160909523981-4217076.jpg","description":"华声美女","ctime":"2016-12-16 10:00","url":"http://bbs.voc.com.cn/mm/meinv-7588373-0-1.html"}],"code":200,"msg":"success"}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public static GirlEntity objectFromData(String str) {

        return new Gson().fromJson(str, GirlEntity.class);
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * newslist : [{"title":"sugarå°\u008fç\u201dœå¿ƒCC","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/13/201612161359328891-2285289.jpg","description":"????","ctime":"2016-12-16 15:00","url":"http://bbs.voc.com.cn/mm/meinv-7588935-0-1.html"},{"title":"è¶Šå\u008d\u2014æ­£å¦¹ é˜®æ°\u008fçŽ\u2030åª\u203a çŽ¯ç\u0090ƒå°\u008få§\u0090å\u2020 å\u2020\u203aæ¯\u201dåŸºå°¼å\u2020™çœŸ","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/11/m.xxxiao.com_9dd3e72aae77f0036d2eb1a3f43099cf-682x1024.jpg","description":"ç¾Žå¥³å\u2020™çœŸ","ctime":"2016-12-16 15:00","url":"http://m.xxxiao.com/90629"},{"title":"å\u2020¯é\u203aªå¨\u2021 çº¤ç»\u2020å\u008fŒè\u2026¿æ\u009d¨æŸ³ç»\u2020è\u2026°é\u009d¢è\u2039¥æ¡ƒèŠ±å¤§å°ºåº¦SMæƒ\u2026è¶£æ\u008d\u2020ç¼š","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/12/m.xxxiao.com_71750f4710b2b2d6ac3d362a4ce2ea62-614x1024.jpg","description":"ç¾Žå¥³å\u2020™çœŸ","ctime":"2016-12-16 15:00","url":"http://m.xxxiao.com/102000"},{"title":"æ°\u201dè´¨ç¾Žå¥³ æž\u2014è\u2022¾KikiCå©§ ä¼˜é\u203a\u2026è¿žè¡£è£™ å¤§å°ºåº¦å\u2020\u2026è¡£æ\u008d\u201aä¸\u008dä½\u008fçš\u201eå\u008fŒå³°è¯±æƒ\u2018","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/12/m.xxxiao.com_d77c6713f6cc1e4d247969be9fe3c2ee-614x1024.jpg","description":"ç¾Žå¥³å\u2020™çœŸ","ctime":"2016-12-16 15:00","url":"http://m.xxxiao.com/102001"},{"title":"美臀女神 胡晓斐沐熙 极品性感猫女郎","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/09/m.xxxiao.com_c9f5825f9afa25607a5e926a1be7729c-683x1024.jpg","description":"美女写真","ctime":"2016-12-16 14:00","url":"http://m.xxxiao.com/76207"},{"title":"爱的尤物 温鈊怡 少女的美妙大尺度诱人照","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/09/m.xxxiao.com_21561ad65f874f2105456e9b3c1c6575-614x1024.jpg","description":"美女写真","ctime":"2016-12-16 13:00","url":"http://m.xxxiao.com/72435"},{"title":"肉感妹子 严佳丽 尺度情趣内衣写真","picUrl":"http://m.xxxiao.com/wp-content/uploads/sites/3/2016/07/m.xxxiao.com_06f1f4d7ab63f98f7198d8e87b120ec3-681x1024.jpg","description":"美女写真","ctime":"2016-12-16 11:00","url":"http://m.xxxiao.com/57906"},{"title":"[贴图]姑娘心恋白云湖","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/09/201612160918596681-4217076.jpg","description":"华声美女","ctime":"2016-12-16 10:00","url":"http://bbs.voc.com.cn/mm/meinv-7588388-0-1.html"},{"title":"[贴图]龙潭公园拍美女","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/09/201612160914587591-4217076.jpg","description":"华声美女","ctime":"2016-12-16 10:00","url":"http://bbs.voc.com.cn/mm/meinv-7588380-0-1.html"},{"title":"[贴图]漂亮的睡衣姑娘","picUrl":"http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/09/201612160909523981-4217076.jpg","description":"华声美女","ctime":"2016-12-16 10:00","url":"http://bbs.voc.com.cn/mm/meinv-7588373-0-1.html"}]
         * code : 200
         * msg : success
         */

        private int code;
        private String msg;
        private List<NewslistBean> newslist;

        public static ShowapiResBodyBean objectFromData(String str) {

            return new Gson().fromJson(str, ShowapiResBodyBean.class);
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
             * title : sugarå°ç”œå¿ƒCC
             * picUrl : http://s.image.hnol.net/x/246x0/auto/http://image.hnol.net/c/2016-12/16/13/201612161359328891-2285289.jpg
             * description : ????
             * ctime : 2016-12-16 15:00
             * url : http://bbs.voc.com.cn/mm/meinv-7588935-0-1.html
             */

            private String title;
            private String picUrl;
            private String description;
            private String ctime;
            private String url;

            public static NewslistBean objectFromData(String str) {

                return new Gson().fromJson(str, NewslistBean.class);
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
