package com.he.util;

import com.he.entity.sys.SysApiServerRoute;

import java.util.HashMap;
import java.util.Map;

public class ApiServerRouteUtil {
	private static Map<String,SysApiServerRoute> SysClientServerRouteMap= new HashMap<String, SysApiServerRoute>();

	public void init(){
		//TODO 从数据库中取出接口映射，以map形式保持到缓存
		System.out.println("SysClientServerRoute init...");
//		for(SysApiServerRoute func:SysClientServerRouteList){
//			SysClientServerRouteMap.put(func.getFuncId(),func);
//		}
	}

	/**
	 * 根据功能编号获取配置信息
	 * @param funcCode
	 * @return
	 */
	public static SysApiServerRoute getSysApiServerRoute(String funcCode){
		return SysClientServerRouteMap.get(funcCode);
	}
}
