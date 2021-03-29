package com.design.todaynews.model;


import java.util.List;

public class NewsCategory {


    /**
     * code : 10000
     * charge : false
     * msg : 查询成功
     * result : {"showapi_res_error":"","showapi_res_id":"605d46b68d57baa47dede01b","showapi_res_code":0,"showapi_res_body":{"ret_code":0,"list":[{"name":"电影","childList":[{"id":"338","name":"爱情"},{"id":"340","name":"喜剧"},{"id":"339","name":"惊悚"},{"id":"437","name":"科幻"},{"id":"337","name":"剧情"},{"id":"26","name":"全部"}]},{"name":"电视剧","childList":[{"id":"349","name":"偶像"},{"id":"350","name":"言情"},{"id":"351","name":"古装"},{"id":"448","name":"家庭伦理"},{"id":"452","name":"美剧"},{"id":"453","name":"韩剧"},{"id":"466","name":"日剧"},{"id":"464","name":"港剧"},{"id":"465","name":"台剧"},{"id":"467","name":"泰剧"},{"id":"4","name":"全部"}]},{"name":"综艺","childList":[{"id":"439","name":"访谈综艺"},{"id":"440","name":"情感综艺"},{"id":"441","name":"选秀综艺"},{"id":"368","name":"内地综艺"},{"id":"369","name":"港台综艺"},{"id":"19","name":"全部"}]},{"name":"动漫","childList":[{"id":"442","name":"搞笑"},{"id":"443","name":"益智"},{"id":"444","name":"冒险"},{"id":"623","name":"情感"},{"id":"445","name":"国产"},{"id":"446","name":"日本"},{"id":"447","name":"欧美"},{"id":"23","name":"全部"}]},{"name":"小说","childList":[{"id":"353","name":"奇幻玄幻"},{"id":"355","name":"都市言情"},{"id":"354","name":"武侠仙侠"},{"id":"1508","name":"青春校园"},{"id":"1509","name":"穿越架空"},{"id":"356","name":"惊悚悬疑"},{"id":"459","name":"历史军事"},{"id":"1512","name":"游戏竞技"},{"id":"1510","name":"耽美同人"},{"id":"1513","name":"文学经典"},{"id":"7","name":"全部"}]},{"name":"游戏","childList":[{"id":"173","name":"网页游戏"},{"id":"62","name":"网络游戏"},{"id":"524","name":"手机游戏"},{"id":"451","name":"单机游戏"}]},{"name":"人物","childList":[{"id":"258","name":"热点人物"},{"id":"260","name":"名家人物"},{"id":"612","name":"公益人物"},{"id":"261","name":"财经人物"},{"id":"255","name":"体坛人物"},{"id":"454","name":"主持人"},{"id":"259","name":"历史人物"},{"id":"257","name":"互联网人物"},{"id":"1570","name":"女明星"},{"id":"1569","name":"男明星"},{"id":"491","name":"欧美明星"}]},{"name":"汽车","childList":[{"id":"1540","name":"热搜汽车"},{"id":"1543","name":"微型车"},{"id":"1544","name":"小型车"},{"id":"1545","name":"中级车"},{"id":"1548","name":"豪华车"},{"id":"1549","name":"MPV"},{"id":"1564","name":"汽车月度榜"},{"id":"1676","name":"电动汽车"}]},{"name":"生活","childList":[{"id":"302","name":"旅游城市"},{"id":"14","name":"风景名胜"},{"id":"1579","name":"博物馆"},{"id":"24","name":"宠物"},{"id":"1434","name":"小吃"},{"id":"450","name":"畅销书"},{"id":"12","name":"高校"},{"id":"1565","name":"化妆品"},{"id":"270","name":"奢侈品"},{"id":"367","name":"慈善公益组.."}]},{"name":"科技","childList":[{"id":"1566","name":"手机"},{"id":"1627","name":"软件"}]},{"name":"热点","childList":[{"id":"1","name":"实时热点"},{"id":"341","name":"今日热点"},{"id":"42","name":"七日热点"}]}]}}
     * requestId : 320b4eaae82d4f03b4df9e7bc72277f4
     */

    private String code;
    private Boolean charge;
    private String msg;
    private ResultBean result;
    private String requestId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getCharge() {
        return charge;
    }

    public void setCharge(Boolean charge) {
        this.charge = charge;
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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public static class ResultBean {
        /**
         * showapi_res_error :
         * showapi_res_id : 605d46b68d57baa47dede01b
         * showapi_res_code : 0
         * showapi_res_body : {"ret_code":0,"list":[{"name":"电影","childList":[{"id":"338","name":"爱情"},{"id":"340","name":"喜剧"},{"id":"339","name":"惊悚"},{"id":"437","name":"科幻"},{"id":"337","name":"剧情"},{"id":"26","name":"全部"}]},{"name":"电视剧","childList":[{"id":"349","name":"偶像"},{"id":"350","name":"言情"},{"id":"351","name":"古装"},{"id":"448","name":"家庭伦理"},{"id":"452","name":"美剧"},{"id":"453","name":"韩剧"},{"id":"466","name":"日剧"},{"id":"464","name":"港剧"},{"id":"465","name":"台剧"},{"id":"467","name":"泰剧"},{"id":"4","name":"全部"}]},{"name":"综艺","childList":[{"id":"439","name":"访谈综艺"},{"id":"440","name":"情感综艺"},{"id":"441","name":"选秀综艺"},{"id":"368","name":"内地综艺"},{"id":"369","name":"港台综艺"},{"id":"19","name":"全部"}]},{"name":"动漫","childList":[{"id":"442","name":"搞笑"},{"id":"443","name":"益智"},{"id":"444","name":"冒险"},{"id":"623","name":"情感"},{"id":"445","name":"国产"},{"id":"446","name":"日本"},{"id":"447","name":"欧美"},{"id":"23","name":"全部"}]},{"name":"小说","childList":[{"id":"353","name":"奇幻玄幻"},{"id":"355","name":"都市言情"},{"id":"354","name":"武侠仙侠"},{"id":"1508","name":"青春校园"},{"id":"1509","name":"穿越架空"},{"id":"356","name":"惊悚悬疑"},{"id":"459","name":"历史军事"},{"id":"1512","name":"游戏竞技"},{"id":"1510","name":"耽美同人"},{"id":"1513","name":"文学经典"},{"id":"7","name":"全部"}]},{"name":"游戏","childList":[{"id":"173","name":"网页游戏"},{"id":"62","name":"网络游戏"},{"id":"524","name":"手机游戏"},{"id":"451","name":"单机游戏"}]},{"name":"人物","childList":[{"id":"258","name":"热点人物"},{"id":"260","name":"名家人物"},{"id":"612","name":"公益人物"},{"id":"261","name":"财经人物"},{"id":"255","name":"体坛人物"},{"id":"454","name":"主持人"},{"id":"259","name":"历史人物"},{"id":"257","name":"互联网人物"},{"id":"1570","name":"女明星"},{"id":"1569","name":"男明星"},{"id":"491","name":"欧美明星"}]},{"name":"汽车","childList":[{"id":"1540","name":"热搜汽车"},{"id":"1543","name":"微型车"},{"id":"1544","name":"小型车"},{"id":"1545","name":"中级车"},{"id":"1548","name":"豪华车"},{"id":"1549","name":"MPV"},{"id":"1564","name":"汽车月度榜"},{"id":"1676","name":"电动汽车"}]},{"name":"生活","childList":[{"id":"302","name":"旅游城市"},{"id":"14","name":"风景名胜"},{"id":"1579","name":"博物馆"},{"id":"24","name":"宠物"},{"id":"1434","name":"小吃"},{"id":"450","name":"畅销书"},{"id":"12","name":"高校"},{"id":"1565","name":"化妆品"},{"id":"270","name":"奢侈品"},{"id":"367","name":"慈善公益组.."}]},{"name":"科技","childList":[{"id":"1566","name":"手机"},{"id":"1627","name":"软件"}]},{"name":"热点","childList":[{"id":"1","name":"实时热点"},{"id":"341","name":"今日热点"},{"id":"42","name":"七日热点"}]}]}
         */

        private String showapi_res_error;
        private String showapi_res_id;
        private Integer showapi_res_code;
        private ShowapiResBodyBean showapi_res_body;

        public String getShowapi_res_error() {
            return showapi_res_error;
        }

        public void setShowapi_res_error(String showapi_res_error) {
            this.showapi_res_error = showapi_res_error;
        }

        public String getShowapi_res_id() {
            return showapi_res_id;
        }

        public void setShowapi_res_id(String showapi_res_id) {
            this.showapi_res_id = showapi_res_id;
        }

        public Integer getShowapi_res_code() {
            return showapi_res_code;
        }

        public void setShowapi_res_code(Integer showapi_res_code) {
            this.showapi_res_code = showapi_res_code;
        }

        public ShowapiResBodyBean getShowapi_res_body() {
            return showapi_res_body;
        }

        public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
            this.showapi_res_body = showapi_res_body;
        }

        public static class ShowapiResBodyBean {
            /**
             * ret_code : 0
             * list : [{"name":"电影","childList":[{"id":"338","name":"爱情"},{"id":"340","name":"喜剧"},{"id":"339","name":"惊悚"},{"id":"437","name":"科幻"},{"id":"337","name":"剧情"},{"id":"26","name":"全部"}]},{"name":"电视剧","childList":[{"id":"349","name":"偶像"},{"id":"350","name":"言情"},{"id":"351","name":"古装"},{"id":"448","name":"家庭伦理"},{"id":"452","name":"美剧"},{"id":"453","name":"韩剧"},{"id":"466","name":"日剧"},{"id":"464","name":"港剧"},{"id":"465","name":"台剧"},{"id":"467","name":"泰剧"},{"id":"4","name":"全部"}]},{"name":"综艺","childList":[{"id":"439","name":"访谈综艺"},{"id":"440","name":"情感综艺"},{"id":"441","name":"选秀综艺"},{"id":"368","name":"内地综艺"},{"id":"369","name":"港台综艺"},{"id":"19","name":"全部"}]},{"name":"动漫","childList":[{"id":"442","name":"搞笑"},{"id":"443","name":"益智"},{"id":"444","name":"冒险"},{"id":"623","name":"情感"},{"id":"445","name":"国产"},{"id":"446","name":"日本"},{"id":"447","name":"欧美"},{"id":"23","name":"全部"}]},{"name":"小说","childList":[{"id":"353","name":"奇幻玄幻"},{"id":"355","name":"都市言情"},{"id":"354","name":"武侠仙侠"},{"id":"1508","name":"青春校园"},{"id":"1509","name":"穿越架空"},{"id":"356","name":"惊悚悬疑"},{"id":"459","name":"历史军事"},{"id":"1512","name":"游戏竞技"},{"id":"1510","name":"耽美同人"},{"id":"1513","name":"文学经典"},{"id":"7","name":"全部"}]},{"name":"游戏","childList":[{"id":"173","name":"网页游戏"},{"id":"62","name":"网络游戏"},{"id":"524","name":"手机游戏"},{"id":"451","name":"单机游戏"}]},{"name":"人物","childList":[{"id":"258","name":"热点人物"},{"id":"260","name":"名家人物"},{"id":"612","name":"公益人物"},{"id":"261","name":"财经人物"},{"id":"255","name":"体坛人物"},{"id":"454","name":"主持人"},{"id":"259","name":"历史人物"},{"id":"257","name":"互联网人物"},{"id":"1570","name":"女明星"},{"id":"1569","name":"男明星"},{"id":"491","name":"欧美明星"}]},{"name":"汽车","childList":[{"id":"1540","name":"热搜汽车"},{"id":"1543","name":"微型车"},{"id":"1544","name":"小型车"},{"id":"1545","name":"中级车"},{"id":"1548","name":"豪华车"},{"id":"1549","name":"MPV"},{"id":"1564","name":"汽车月度榜"},{"id":"1676","name":"电动汽车"}]},{"name":"生活","childList":[{"id":"302","name":"旅游城市"},{"id":"14","name":"风景名胜"},{"id":"1579","name":"博物馆"},{"id":"24","name":"宠物"},{"id":"1434","name":"小吃"},{"id":"450","name":"畅销书"},{"id":"12","name":"高校"},{"id":"1565","name":"化妆品"},{"id":"270","name":"奢侈品"},{"id":"367","name":"慈善公益组.."}]},{"name":"科技","childList":[{"id":"1566","name":"手机"},{"id":"1627","name":"软件"}]},{"name":"热点","childList":[{"id":"1","name":"实时热点"},{"id":"341","name":"今日热点"},{"id":"42","name":"七日热点"}]}]
             */

            private Integer ret_code;
            private List<ListBean> list;

            public Integer getRet_code() {
                return ret_code;
            }

            public void setRet_code(Integer ret_code) {
                this.ret_code = ret_code;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * name : 电影
                 * childList : [{"id":"338","name":"爱情"},{"id":"340","name":"喜剧"},{"id":"339","name":"惊悚"},{"id":"437","name":"科幻"},{"id":"337","name":"剧情"},{"id":"26","name":"全部"}]
                 */

                private String name;
                private List<ChildListBean> childList;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<ChildListBean> getChildList() {
                    return childList;
                }

                public void setChildList(List<ChildListBean> childList) {
                    this.childList = childList;
                }

                public static class ChildListBean {
                    /**
                     * id : 338
                     * name : 爱情
                     */

                    private String id;
                    private String name;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }
            }
        }
    }
}
