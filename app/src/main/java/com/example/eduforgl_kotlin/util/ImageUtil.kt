package com.example.eduforgl_kotlin.util

import android.graphics.BitmapFactory
import android.graphics.Bitmap



class ImageUtil {
    companion object {
        //下面的这个方法是将byte数组转化为Bitmap对象的一个方法
        fun getPicFromBytes(bytes: ByteArray?, opts: BitmapFactory.Options?): Bitmap? {
            if (bytes != null)
                if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.size, opts)
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            return null
        }
    }
}