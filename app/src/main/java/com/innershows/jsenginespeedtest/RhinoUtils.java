package com.innershows.jsenginespeedtest;


import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

/**
 * Created by innershows on 2017/3/30.
 *
 * @author innershows
 * @date 2017/3/30
 * @e_mail innershow@gmail.com
 */

public class RhinoUtils {


    public static Object callJS(String js) {
        Context ctx = Context.enter();
        ctx.setOptimizationLevel(-1);
        ScriptableObject scope = ctx.initSafeStandardObjects();
        Object ret = ctx.evaluateString(scope, js, "RhinoUtils", 1, null);
        Context.exit();
        return ret;
    }

    public class Func {
       public void func() {
            //相关处理
//            Log.e("=====", "Rhino");
        }
    }

    public Object callJSWithJava(String js) {

        //创建JS上下文
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scope = context.initStandardObjects();

        try {
            //执行JS
            context.evaluateString(scope, js, "RhinoUtils", 1, null);

            //将Java方法封装到类中，并作为参数传递给JS方法
            Function function = (Function) scope.get("rhinofunc", scope);
            return function.call(context, scope, scope, new Object[]{new Func()});
        } finally {
            Context.exit();
        }
    }
}
