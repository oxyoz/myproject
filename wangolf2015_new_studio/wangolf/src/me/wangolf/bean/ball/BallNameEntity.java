package me.wangolf.bean.ball;

import java.io.Serializable;
import java.util.List;

public class BallNameEntity implements Serializable 
{
	private static final long serialVersionUID = 1L;

    /**
     * status : 1
     * data : [{"court_name":"佛山云东海高尔夫","city_name":"佛山市","court_id":"98"},{"court_name":"江苏太仓新东海高尔夫","city_name":"南京市","court_id":"300"},{"court_name":"山东南山东海海韵高尔夫","city_name":"烟台市","court_id":"622"},{"court_name":"山东南山东海海景高尔夫","city_name":"烟台市","court_id":"621"},{"court_name":"山东南山东海松涛高尔夫","city_name":"烟台市","court_id":"492"},{"court_name":"山东海阳旭宝高尔夫","city_name":"青岛市","court_id":"345"}]
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
         * court_name : 佛山云东海高尔夫
         * city_name : 佛山市
         * court_id : 98
         */
        private String court_name;
        private String city_name;
        private String court_id;

        public void setCourt_name(String court_name) {
            this.court_name = court_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public void setCourt_id(String court_id) {
            this.court_id = court_id;
        }

        public String getCourt_name() {
            return court_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public String getCourt_id() {
            return court_id;
        }
    }
}
