package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class JsFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest) req).getRequestURI();
        
        
        HttpServletRequest request = (HttpServletRequest) req;
        JsRequest wrapRequest = new JsRequest(request,
                request.getParameterMap());
        chain.doFilter(wrapRequest, res);
        
        /*if (url.indexOf("admin") > 0
                || url.equals("/seller/store_nav_save.htm")
                || url.equals("/seller/goods_format_save.htm")
                || url.equals("/seller/decorate_module_save.htm")) {
            chain.doFilter(req, res);
        } else {
            
        }*/
//		if(url.equals("/circle/invitation_reply_save.htm") 
//				|| url.equals("/circle/invitation_publish_save.htm")
//				|| url.equals("/seller/add_goods_finish.htm")
//				|| url.equals("/seller/freegoods_save.htm")
//				|| url.equals("/seller/goods_format_save.htm")
//				|| url.equals("/seller/group_goods_save.htm")
//				|| url.equals("/seller/grouplife_goods_save.htm")
//				|| url.equals("/seller/information_save.htm")
//				|| url.equals("/seller/send_email_save.htm")
//				|| url.equals("/seller/store_nav_save.htm")){//鍟嗗鍙婂墠鍙伴渶瑕佹彁浜ゆ枃鏈紪杈戞鏃讹紝涓嶈繃婊�
//			chain.doFilter(req, res);
//	    }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
