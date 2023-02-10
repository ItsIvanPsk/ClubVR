package com.itsydev.clubvr.utils

import android.util.Log


class BearEncrypt {

    fun encrypt(input: String): String{
        var en = ""
        for (i in input.indices){
            en += input.get(i) + 5
        }
        en = en.reversed()
        Log.d("encrypted", en)
        return en
    }

    fun decrypt(input: String): String{
        var des = ""
        for (i in input.indices){
            des += input.get(i) - 5
        }
        des = des.reversed()
        Log.d("decrypt", des)
        return des
    }

}