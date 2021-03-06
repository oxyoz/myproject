package me.wangolf.bean.practice;

/**
 * ============================================================
 * 
 * 版权 ：美高传媒 版权所有 (c) 2015年1月10日
 * 
 * 作者:copy
 * 
 * 版本 ：1.0
 * 
 * 创建日期 ： 2015年1月10日
 * 
 * 描述 ：练习场详细信息
 * 
 * 
 * 修订历史 ：
 * 
 * ============================================================
 **/
import java.io.Serializable;
import java.util.List;

public class PracDetailEntity_new implements Serializable, Cloneable
{

	private static final long serialVersionUID = 1L;

	private static PracDetailEntity_new mPracDetailEntity_new = new PracDetailEntity_new();
	
	public static PracDetailEntity_new newPracDetailEntity_new() throws CloneNotSupportedException
	{
		
		return (PracDetailEntity_new) mPracDetailEntity_new.clone();
		
	}
	
	  /**
     * status : 1
     * data : [{"range_name":"深圳铭利高尔夫练习场","tee_floor_num":"2","summy":"铭利高尔夫坐落罗湖体育馆内，位于罗湖区罗沙路径二路48号，是罗湖区一间室外高尔夫练习场，紧邻东湖公园，环境优美，空气清新，交通便利，是商务活动和健身娱乐的理想场所。练习场内环境优雅、打位布局合理、各种备置齐全；草场占地面积1.5万平方米，拥有58个标准打位和练习推杆、沙坑杆及短杆的果岭；配套设施一应俱全，配有幽雅舒适的VIP套房、专卖店、餐饮店、休闲馆、淋浴房、存包房及免费停车场等。 配套设施一应俱全，宏厚的师资力量，让你迅速掌握高球技巧，享受高尔夫带来的慢生活！","tee_num":"58","distributors":[{"id":"1364","price":"50","book":"【该价格节假日通用】支付成功后您将收到系统发送的订单短信，您可凭下单手机号和短信验证码前往所订球场消费，无需预约，消费时需提前告知工作人员来自【打球APP】，订单3个月内有效。","price_tag":"节假日通用","price_description":"￥50元/100粒球"}],"city":"77","id":"3","service_time":"9:00-23:00","img_list":"http://www.wangolf.me/public/court/pic_20140814120525.jpg,http://www.wangolf.me/public/court/pic_20140814120533.jpg","supporting":"VIP套房、专卖店、餐饮店、休闲馆、淋浴房、存包房及免费停车场等。","address":"罗湖区 罗沙公路经二路48号罗湖体育馆","web_app_uri":"http://www.wangolf.me//webApp/share/range.html?range_id=3","longitude":"114.15759","latitude":"22.561158","mobile":"075525544776"}]
     * info : 成功
     */
    private int status;
    private List<DataEntity> data;
    private String info;

    public void setStatus(int status) 
    {
        this.status = status;
    }

    public void setData(List<DataEntity> data) 
    {
        this.data = data;
    }

    public void setInfo(String info) 
    {
        this.info = info;
    }

    public int getStatus() 
    {
        return status;
    }

    public List<DataEntity> getData() 
    {
        return data;
    }

    public String getInfo() 
    {
        return info;
    }

    public static class DataEntity implements Serializable
    {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
         * range_name : 深圳铭利高尔夫练习场
         * tee_floor_num : 2
         * summy : 铭利高尔夫坐落罗湖体育馆内，位于罗湖区罗沙路径二路48号，是罗湖区一间室外高尔夫练习场，紧邻东湖公园，环境优美，空气清新，交通便利，是商务活动和健身娱乐的理想场所。练习场内环境优雅、打位布局合理、各种备置齐全；草场占地面积1.5万平方米，拥有58个标准打位和练习推杆、沙坑杆及短杆的果岭；配套设施一应俱全，配有幽雅舒适的VIP套房、专卖店、餐饮店、休闲馆、淋浴房、存包房及免费停车场等。 配套设施一应俱全，宏厚的师资力量，让你迅速掌握高球技巧，享受高尔夫带来的慢生活！
         * tee_num : 58
         * distributors : [{"id":"1364","price":"50","book":"【该价格节假日通用】支付成功后您将收到系统发送的订单短信，您可凭下单手机号和短信验证码前往所订球场消费，无需预约，消费时需提前告知工作人员来自【打球APP】，订单3个月内有效。","price_tag":"节假日通用","price_description":"￥50元/100粒球"}]
         * city : 77
         * id : 3
         * service_time : 9:00-23:00
         * img_list : http://www.wangolf.me/public/court/pic_20140814120525.jpg,http://www.wangolf.me/public/court/pic_20140814120533.jpg
         * supporting : VIP套房、专卖店、餐饮店、休闲馆、淋浴房、存包房及免费停车场等。
         * address : 罗湖区 罗沙公路经二路48号罗湖体育馆
         * web_app_uri : http://www.wangolf.me//webApp/share/range.html?range_id=3
         * longitude : 114.15759
         * latitude : 22.561158
         * mobile : 075525544776
         */
        private String range_name;
        private String tee_floor_num;
        private String summy;
        private String tee_num;
        private List<DistributorsEntity> distributors;
        private String city;
        private String id;
        private String service_time;
        private String img_list;
        private String supporting;
        private String address;
        private String web_app_uri;
        private String longitude;
        private String latitude;
        private String mobile;

        public void setRange_name(String range_name) {
            this.range_name = range_name;
        }

        public void setTee_floor_num(String tee_floor_num) {
            this.tee_floor_num = tee_floor_num;
        }

        public void setSummy(String summy) {
            this.summy = summy;
        }

        public void setTee_num(String tee_num) {
            this.tee_num = tee_num;
        }

        public void setDistributors(List<DistributorsEntity> distributors) 
        {
            this.distributors = distributors;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setService_time(String service_time) {
            this.service_time = service_time;
        }

        public void setImg_list(String img_list) {
            this.img_list = img_list;
        }

        public void setSupporting(String supporting) {
            this.supporting = supporting;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setWeb_app_uri(String web_app_uri) {
            this.web_app_uri = web_app_uri;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRange_name() {
            return range_name;
        }

        public String getTee_floor_num() {
            return tee_floor_num;
        }

        public String getSummy() {
            return summy;
        }

        public String getTee_num() {
            return tee_num;
        }

        public List<DistributorsEntity> getDistributors() {
            return distributors;
        }

        public String getCity() {
            return city;
        }

        public String getId() {
            return id;
        }

        public String getService_time() {
            return service_time;
        }

        public String getImg_list() {
            return img_list;
        }

        public String getSupporting() {
            return supporting;
        }

        public String getAddress() {
            return address;
        }

        public String getWeb_app_uri() {
            return web_app_uri;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getMobile() {
            return mobile;
        }

        public static class DistributorsEntity implements Serializable{
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/**
             * id : 1364
             * price : 50
             * book : 【该价格节假日通用】支付成功后您将收到系统发送的订单短信，您可凭下单手机号和短信验证码前往所订球场消费，无需预约，消费时需提前告知工作人员来自【打球APP】，订单3个月内有效。
             * price_tag : 节假日通用
             * price_description : ￥50元/100粒球
             */
            private String id;
            private String price;
            private String book;
            private String price_tag;
            private String price_description;           

            public void setId(String id) {
                this.id = id;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setBook(String book) {
                this.book = book;
            }

            public void setPrice_tag(String price_tag) {
                this.price_tag = price_tag;
            }

            public void setPrice_description(String price_description) {
                this.price_description = price_description;
            }

            public String getId() {
                return id;
            }

            public String getPrice() {
                return price;
            }

            public String getBook() {
                return book;
            }

            public String getPrice_tag() {
                return price_tag;
            }

            public String getPrice_description() {
                return price_description;
            }
        }
    }
}
