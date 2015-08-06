package me.wangolf.bean.ball;

import java.io.Serializable;
import java.util.List;

/**
 * 球场详细实体
 * @author Administrator
 *
 */
public class BallInfoEntity implements Serializable
{

	private static final long serialVersionUID = 1L;
	
    /**
     * status : 1
     * data : [{"id":"321","court_name":"湖州温泉高尔夫","img_list":"http://192.168.1.222/public/court/pic_20140815181525.jpg,http://192.168.1.222/public/court/pic_20140815181529.jpg,http://192.168.1.222/public/court/pic_20140815181543.jpg","address":"浙江省湖州市吴兴区杨白线路1888号","web_app_uri":"http://192.168.1.222//webApp/share/court.html?court_id=321","longitude":"120.055461","latitude":"30.918116","distributors":[{"id":"5797956","order_description":"当天取消减少扣全款","supplier_id":"56","price":"580","supplier_name":"乐高高尔夫","service":"18洞场果岭/球僮/球车/衣柜","cancel_description":"当天取消减少扣全款"},{"id":"5809459","order_description":"当天取消减少扣全款","supplier_id":"555","price":"580","supplier_name":"精英高尔夫","service":"18洞场果岭/球僮/球车/衣柜","cancel_description":"当天取消减少扣全款"},{"id":"5818648","order_description":"当天取消减少扣全款","supplier_id":"777","price":"590","supplier_name":"100Golf","service":"18洞场果岭/球僮/球车/衣柜","cancel_description":"当天取消减少扣全款"},{"id":"5824720","order_description":"当天取消减少扣全款","supplier_id":"666","price":"590","supplier_name":"富豪高尔夫","service":"18洞场果岭/球僮/球车/衣柜","cancel_description":"当天取消减少扣全款"}],"city":"384"}]
     * info : 成功
     */
    private int status;
    private List<DataEntity> data;
    private String info;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public static class DataEntity implements Serializable{
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
         * id : 321
         * court_name : 湖州温泉高尔夫
         * img_list : http://192.168.1.222/public/court/pic_20140815181525.jpg,http://192.168.1.222/public/court/pic_20140815181529.jpg,http://192.168.1.222/public/court/pic_20140815181543.jpg
         * address : 浙江省湖州市吴兴区杨白线路1888号
         * web_app_uri : http://192.168.1.222//webApp/share/court.html?court_id=321
         * longitude : 120.055461
         * latitude : 30.918116
         * distributors : [{"id":"5797956","order_description":"当天取消减少扣全款","supplier_id":"56","price":"580","supplier_name":"乐高高尔夫","service":"18洞场果岭/球僮/球车/衣柜","cancel_description":"当天取消减少扣全款"},{"id":"5809459","order_description":"当天取消减少扣全款","supplier_id":"555","price":"580","supplier_name":"精英高尔夫","service":"18洞场果岭/球僮/球车/衣柜","cancel_description":"当天取消减少扣全款"},{"id":"5818648","order_description":"当天取消减少扣全款","supplier_id":"777","price":"590","supplier_name":"100Golf","service":"18洞场果岭/球僮/球车/衣柜","cancel_description":"当天取消减少扣全款"},{"id":"5824720","order_description":"当天取消减少扣全款","supplier_id":"666","price":"590","supplier_name":"富豪高尔夫","service":"18洞场果岭/球僮/球车/衣柜","cancel_description":"当天取消减少扣全款"}]
         * city : 384
         */
        private String id;
        private String court_name;
        private String img_list;
        private String address;
        private String web_app_uri;
        private String longitude;
        private String latitude;
        private List<DistributorsEntity> distributors;
        private String city;

        public void setId(String id) {
            this.id = id;
        }

        public void setCourt_name(String court_name) {
            this.court_name = court_name;
        }

        public void setImg_list(String img_list) {
            this.img_list = img_list;
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

        public void setDistributors(List<DistributorsEntity> distributors) {
            this.distributors = distributors;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getId() {
            return id;
        }

        public String getCourt_name() {
            return court_name;
        }

        public String getImg_list() {
            return img_list;
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

        public List<DistributorsEntity> getDistributors() {
            return distributors;
        }

        public String getCity() {
            return city;
        }

        public static class DistributorsEntity implements Serializable{
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/**
             * id : 5797956
             * order_description : 当天取消减少扣全款
             * supplier_id : 56
             * price : 580
             * supplier_name : 乐高高尔夫
             * service : 18洞场果岭/球僮/球车/衣柜
             * cancel_description : 当天取消减少扣全款
             */
            private String id;
            private String order_description;
            private String supplier_id;
            private String price;
            private String supplier_name;
            private String service;
            private String cancel_description;

            public void setId(String id) {
                this.id = id;
            }

            public void setOrder_description(String order_description) {
                this.order_description = order_description;
            }

            public void setSupplier_id(String supplier_id) {
                this.supplier_id = supplier_id;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setSupplier_name(String supplier_name) {
                this.supplier_name = supplier_name;
            }

            public void setService(String service) {
                this.service = service;
            }

            public void setCancel_description(String cancel_description) {
                this.cancel_description = cancel_description;
            }

            public String getId() {
                return id;
            }

            public String getOrder_description() {
                return order_description;
            }

            public String getSupplier_id() {
                return supplier_id;
            }

            public String getPrice() {
                return price;
            }

            public String getSupplier_name() {
                return supplier_name;
            }

            public String getService() {
                return service;
            }

            public String getCancel_description() {
                return cancel_description;
            }
        }
    }

}
