package com.itsydev.clubvr

import java.nio.charset.Charset
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class BearEncrypt {

    fun encryptAES(plainText: String, secretKey: String): String {
        val cipher = Cipher.getInstance("AES")
        val secretKeySpec = SecretKeySpec(secretKey.toByteArray(Charset.defaultCharset()), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
        val encryptedBytes = cipher.doFinal(plainText.toByteArray(Charset.defaultCharset()))
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

}