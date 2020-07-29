package com.lijie.finalproject.Resolver;



import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**国际化解析器
 * @author LiJie
 * @date 2020/4/13-14:48
 */
@Component
public class LocaleResolver implements org.springframework.web.servlet.LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //从前端获得区域信息
        String l=null;
//        try {
             l =request.getParameter("Locale");
//        }catch (NullPointerException e){
//            l="zh_CN";
//        }
        //先获取系统默认区域语言信息
        Locale locale=Locale.getDefault();
        if(!(l==null)){
            String[] s = l.split("_");
            locale=new Locale(s[0],s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
