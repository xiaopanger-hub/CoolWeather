package com.hy.coolweather.util;

import android.text.TextUtils;

import com.hy.coolweather.db.City;
import com.hy.coolweather.db.Country;
import com.hy.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
   /**
 	     * 解析和处理服务器返回的省级数据
	     */
        	    public static boolean handleProvinceResponse(String response) {
        	        if (!TextUtils.isEmpty(response)) {  // 数据非空
           	            try {
                            JSONArray allProvinces = new JSONArray(response);   // 创建一个用于解析的 JSON 数组
                            	                for (int i = 0; i < allProvinces.length(); i++) {
                                                    JSONObject provinceObject = allProvinces.getJSONObject(i);  // 获取每一个 JSON 对象
                                                    Province province = new Province();
                                                    province.setProvinceName(provinceObject.getString("name")); // 将 JSON 对象中的 name 字段赋给 ProvinceName
                                                    province.setProvinceCode(provinceObject.getInt("id"));  // 将 JSON 对象中的 id 字段赋给 ProvinceCode
                                                   province.save(); // 将数据存到数据库中
                                	                }
                                           return true;
                            	            } catch (JSONException e) {
                                            e.printStackTrace();
                                      }
                                }

                          return false;
                       }

            /**
	     * 解析和处理服务器返回的市级数据
 	     */
           public static boolean handleCityResponse(String response, int provinceId) {
                    if (!TextUtils.isEmpty(response)) {
               	            try {
                                    JSONArray allCity = new JSONArray(response);
                                    for (int i = 0; i < allCity.length(); i++) {
                                           JSONObject cityObject = allCity.getJSONObject(i);
                                            City city = new City();
                       	                    city.setCityName(cityObject.getString("name"));
                                            city.setCityCode(cityObject.getInt("id"));
                                          city.setProvinceId(provinceId);
                                            city.save();
                       	                }
                                    return true;
                               } catch (JSONException e) {
                    	                e.printStackTrace();
                    	            }
                	        }
            	        return false;
                }
        /**
       	     * 解析和处理服务器返回的县级数据
        	     */
	    public static boolean handleCountryResponse(String response, int cityId) {
                  if (!TextUtils.isEmpty(response)) {
                          try {
                   	                JSONArray allCounties = new JSONArray(response);
                   	                for (int i = 0; i < allCounties.length(); i++) {
                                          JSONObject countryObject = allCounties.getJSONObject(i);
                       	                    Country country = new Country();
                                            country.setCountryName(countryObject.getString("name"));
                                            country.setWeatherId(countryObject.getString("weather_id"));
                        	                    country.setCityId(cityId);
                                           country.save();
                        	                }
                                   return true;
                    	            } catch (JSONException e) {
                   	                e.printStackTrace();
                                }

                	        }
            	        return false;
               }

        	}

