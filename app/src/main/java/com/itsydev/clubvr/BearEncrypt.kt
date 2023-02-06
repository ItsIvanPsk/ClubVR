package com.itsydev.clubvr

import android.util.Log
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


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