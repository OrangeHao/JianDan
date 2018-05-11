package com.orange.jiandan.model;

import java.util.List;

/**
 * created by czh on 2018/5/11
 */
public class PicsBean {


    /**
     * status : ok
     * current_page : 1
     * total_comments : 1742
     * page_count : 70
     * count : 25
     * comments : [{"comment_ID":"3810048","comment_post_ID":"21183"
     * ,"comment_author":"铁啸求缘","comment_date":"2018-05-11 16:40:04","co
     * mment_date_gmt":"2018-05-11 08:40:04","comment_content":"<img src=\"http://ww
     * 3.sinaimg.cn/mw600/0073tLPGgy1fr7hoacrp7j30u011hwl7.jpg\" />\n<img src=\"http://w
     * w3.sinaimg.cn/mw600/006XNEY7gy1fr7ho4dqxuj30ms0sg10k.jpg\" />","user_id":"0","vote_p
     * ositive":"0","vote_negative":"0","sub_comment_count":"0","text_content":"\n",
     */

    private String status;
    private int current_page;
    private int total_comments;
    private int page_count;
    private int count;
    private List<CommentsBean> comments;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_comments() {
        return total_comments;
    }

    public void setTotal_comments(int total_comments) {
        this.total_comments = total_comments;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * comment_ID : 3810048
         * comment_post_ID : 21183
         * comment_author : 铁啸求缘
         * comment_date : 2018-05-11 16:40:04
         * comment_date_gmt : 2018-05-11 08:40:04
         * comment_content : <img src="http://ww3.sinaimg.cn/mw600/0073tLPGgy1fr7hoacrp7j30u011hwl7.jpg" />
         <img src="http://ww3.sinaimg.cn/mw600/006XNEY7gy1fr7ho4dqxuj30ms0sg10k.jpg" />
         * user_id : 0
         * vote_positive : 0
         * vote_negative : 0
         * sub_comment_count : 0
         * text_content :

         * pics : ["http://ww3.sinaimg.cn/mw600/0073tLPGgy1fr7hoacrp7j30u011hwl7.jpg","http://ww3.sinaimg.cn/mw600/006XNEY7gy1fr7ho4dqxuj30ms0sg10k.jpg"]
         */

        private String comment_ID;
        private String comment_post_ID;
        private String comment_author;
        private String comment_date;
        private String comment_date_gmt;
        private String comment_content;
        private String user_id;
        private String vote_positive;
        private String vote_negative;
        private String sub_comment_count;
        private String text_content;
        private List<String> pics;

        public String getComment_ID() {
            return comment_ID;
        }

        public void setComment_ID(String comment_ID) {
            this.comment_ID = comment_ID;
        }

        public String getComment_post_ID() {
            return comment_post_ID;
        }

        public void setComment_post_ID(String comment_post_ID) {
            this.comment_post_ID = comment_post_ID;
        }

        public String getComment_author() {
            return comment_author;
        }

        public void setComment_author(String comment_author) {
            this.comment_author = comment_author;
        }

        public String getComment_date() {
            return comment_date;
        }

        public void setComment_date(String comment_date) {
            this.comment_date = comment_date;
        }

        public String getComment_date_gmt() {
            return comment_date_gmt;
        }

        public void setComment_date_gmt(String comment_date_gmt) {
            this.comment_date_gmt = comment_date_gmt;
        }

        public String getComment_content() {
            return comment_content;
        }

        public void setComment_content(String comment_content) {
            this.comment_content = comment_content;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getVote_positive() {
            return vote_positive;
        }

        public void setVote_positive(String vote_positive) {
            this.vote_positive = vote_positive;
        }

        public String getVote_negative() {
            return vote_negative;
        }

        public void setVote_negative(String vote_negative) {
            this.vote_negative = vote_negative;
        }

        public String getSub_comment_count() {
            return sub_comment_count;
        }

        public void setSub_comment_count(String sub_comment_count) {
            this.sub_comment_count = sub_comment_count;
        }

        public String getText_content() {
            return text_content;
        }

        public void setText_content(String text_content) {
            this.text_content = text_content;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }
    }
}
