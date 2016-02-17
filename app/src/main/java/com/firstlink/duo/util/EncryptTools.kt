package com.firstlink.duo.util

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

/**
 * Created by wzq on 16/1/5.
 */
object EncryptTools {

    val X = "x0485293"

    fun aesEncrypt(msg: String, key: String):String {
        val kg = KeyGenerator.getInstance("AES")
        val sr = SecureRandom.getInstance("SHA1PRNG", "Crypto")
        sr.setSeed(key.toByteArray())
        kg.init(128, sr)

        val cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding")
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(kg.generateKey().encoded, "AES"))

        return Base64.encodeToString(cipher.doFinal(msg.toByteArray()), Base64.DEFAULT)
    }

    fun aesDecrypt(msg: String, key: String): String{
        val kg = KeyGenerator.getInstance("AES")
        val sr = SecureRandom.getInstance("SHA1PRNG", "Crypto")
        sr.setSeed(key.toByteArray())
        kg.init(128, sr)

        val cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding")
        cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(kg.generateKey().encoded, "AES"))
        val temp = Base64.decode(msg.toByteArray(), Base64.DEFAULT)
        return String(cipher.doFinal(temp))
    }
}