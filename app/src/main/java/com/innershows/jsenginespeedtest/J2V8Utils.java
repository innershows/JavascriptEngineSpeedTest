package com.innershows.jsenginespeedtest;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

/**
 * Created by innershows on 2017/3/30.
 *
 * @author innershows
 * @date 2017/3/30
 * @e_mail innershow@gmail.com
 */

public class J2V8Utils {


    public static Object callJS(String js) {
        V8 context = V8.createV8Runtime();
        Object ret = context.executeScript(js);
        context.release();
        return ret;
    }


    public  Object callJSWithJava(String js) {
        V8 context = V8.createV8Runtime();
        context.registerJavaMethod(new JavaCallback() {
            @Override
            public Object invoke(V8Object v8Object, V8Array v8Array) {
//                Log.e("=====","J2V8");
                return null;
            }
        }, "func");
        Object ret = context.executeScript(js);
        context.release();
        return ret;
    }

}
