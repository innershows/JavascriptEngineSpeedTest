package com.innershows.jsenginespeedtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void callJs(View view) {
//        String js = generateScript(1000*10);
//        String js = generateScript(1000*1000);
        String js = generateScript(1000 * 1000 * 100);
        long start = System.currentTimeMillis();
        switch (view.getId()) {
            case R.id.jscore_call_js:
                JSCoreUtils.callJS(js);
                break;
            case R.id.v8_call_js:
                J2V8Utils.callJS(js);
                break;
            case R.id.rhino_call_js:
                RhinoUtils.callJS(js);
                break;
            default:
                break;

        }
        long end = System.currentTimeMillis();

        ((Button) view).setText((end - start) + "");
    }


    public void callJava(View view) {
//        long time = 1000*10L;
        long time = 1000*1000L;
        String js = generateScriptWithJava(time);
//        String js = generateScript(1000*1000);
//        String js = generateScript(1000 * 1000 * 100);
        long start = System.currentTimeMillis();
        switch (view.getId()) {
            case R.id.jscore_call_java:
                new JSCoreUtils().callJSWithJava(js);
                break;
            case R.id.v8_call_java:
                new J2V8Utils().callJSWithJava(js);
                break;
            case R.id.rhino_call_java:
                js = generateScriptWithJavaRhino(time);
                new RhinoUtils().callJSWithJava(js);
                break;
            default:
                break;

        }
        long end = System.currentTimeMillis();

        ((Button) view).setText((end - start) + "");
    }

    public String generateScript(long times) {
        return "for(var i = 0 ;i<" + times + ";i++){}";
    }

    public String generateScriptWithJava(long times) {
        return "for(var i = 0 ;i<" + times + ";i++){func();}";
    }

    public String generateScriptWithJavaRhino(long times) {
        return "function rhinofunc(obj){for(var i = 0 ;i<" + times + ";i++){obj.func();}}";
    }


}
