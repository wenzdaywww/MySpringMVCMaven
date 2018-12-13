/**
 * 
 */
package com.www.utils;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * 缓存工具
 * @author www
 */
public class CacheUtils {
	/**缓存名*/
	private final static String CACHE_NAME="testCache";
	/**缓存对象*/
	private static Cache cache=CacheManager.getInstance().getCache(CACHE_NAME);
	/**
	 * 设置缓存内容
	 * @param key
	 * @param value
	 */
	public synchronized static void putCaches(String key,Object value){
		Element element=new Element(key, value);
		cache.put(element);
	}
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public synchronized static Object getCaches(String key){
        Element element = cache.get(key);
        if (element == null) {
            return null;
        } else {
            return  element.getValue();
        }
    }
	/**
	 * 判断是否存在缓存
	 * @param key
	 * @return
	 */
	public synchronized static boolean isExist(String key){
		Element element =cache.get(key);
		if (element == null) {
			return false;
		}
		return true;
	}
	/**
	 * 删除缓存
	 * @param key
	 */
	public synchronized static void removeCache(String key){
		cache.remove(key);
    }
	/**
	 * 删除全部缓存
	 */
	public synchronized static void removeAllCache(){
		cache.removeAll();
		cache.getStatistics();
		cache.flush();
   }
}
