 package com.javaer.plusxposed.util.view;

import android.widget.LinearLayout;

 public class LayoutUtil {

     /**
      * 线性 wrap wrap
      * @return 布局参数
      */
     public static LinearLayout.LayoutParams lineWrap(){
         return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
     }

     /**
      * 线性 match match
      * @return 布局参数
      */
     public static LinearLayout.LayoutParams lineMatch(){
         return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
     }


 }
