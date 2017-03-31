package com.innershows.jsenginespeedtest;

import org.liquidplayer.webkit.javascriptcore.JSContext;
import org.liquidplayer.webkit.javascriptcore.JSFunction;

/**
 * Created by innershows on 2017/3/30.
 *
 * @author innershows
 * @date 2017/3/30
 * @e_mail innershow@gmail.com
 */

public class JSCoreUtils {

    public static Object callJS(String js) {
        JSContext ctx = new JSContext();
        return ctx.evaluateScript(js);
    }

    public  Object callJSWithJava(String js) {
        JSContext ctx = new JSContext();
        JSFunction func = new JSFunction(ctx, "func") {
            public void func() {
//                Log.e("=====","JSCore");
            }
        };
        ctx.property("func", func);

        return ctx.evaluateScript(js);
    }





}
