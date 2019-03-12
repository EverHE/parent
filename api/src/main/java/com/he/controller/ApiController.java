package com.he.controller;

import com.alibaba.fastjson.JSONObject;
import com.he.entity.sys.SysApiServerRoute;
import com.he.util.JsonConstant;
import com.he.util.ApiServerRouteUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * API请求处理总入口类
 */
@RestController
@RequestMapping("/client")
public class ApiController {

	@RequestMapping("/requestdeal")
	public String dealClientRequest(HttpServletRequest request){
		try {
			InputStream in = request.getInputStream();
			int i;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((i = in.read()) != -1) {
				baos.write(i);
			}
			String reqContent = baos.toString("UTF-8");

			if (reqContent != null) {
				JSONObject jsonObject = JSONObject.parseObject(reqContent);
				if (jsonObject != null) {
					String sFuncNo = jsonObject.getString("funcno");
					if (sFuncNo != null && !sFuncNo.equals("")) {
						SysApiServerRoute route = ApiServerRouteUtil.getSysApiServerRoute(sFuncNo);
						if (route != null) {
							if(route.getFuncType().equals("1")){
								//登陆验证
								String token = jsonObject.getString("token");
								Integer userid = jsonObject.getInteger("userid");
								//token 合法性判断
								if(true){//如果token不合法
									return JsonConstant.TOKEN_ERR_JSON;
								}
							}
							WebApplicationContext c = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
							Object cls = c.getBean(route.getDealClass());
							Method deal_func= cls.getClass().getMethod(route.getDealFunc(),JSONObject.class);
							Object result = deal_func.invoke(cls, new Object[]{jsonObject});
							return result.toString();
						} else {//报文错误，funco不存在
							return JsonConstant.NOT_LEGAL_JSON;
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return JsonConstant.NOT_LEGAL_JSON;
	}
}
