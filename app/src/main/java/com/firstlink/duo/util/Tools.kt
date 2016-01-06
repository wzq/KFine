package com.firstlink.duo.util

import android.content.Context
import android.util.Base64
import android.util.TypedValue
import java.security.KeyFactory
import java.security.interfaces.RSAPublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

/**
 * Created by wzq on 15/12/26.
 */
object Tools {

    val LOGIN_OK= "login_ok"

    var DENSITY = 0

    val RSA_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMd0JqoWXhrtOchq6uenZ6mpA6\n" +
            "rPjlQP+9a2DE0znRvNIFvFHwagCaiVgXIvXwkSY3hYG0h+AO7xFWTWV1jq8+zzl1\n" +
            "X0QKREAaFmJDS+OqMsG2uPtlXYmXnds1lZIyR3x23BijIvJJewHrrImDCjPN3bOr\n" +
            "zbKY+5VeKdNEU51VjQIDAQAB\n"

    fun dp2px(context: Context, dp: Int) : Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    fun formatPrice(f : Float) : String{
        return "¥${(f/100.0).toString().format("%.2f")}"
    }

    fun cdn6s(context: Context, url : String, w : Int, h : Int) : String{
        if(DENSITY == 0){
            val d = (context.resources.displayMetrics.density/2+0.5).toInt()
            DENSITY = if (d>0) d else 1
        }
        return url.plus("@${w}w_${h}h_1e_1c_${DENSITY}x_1o")
    }

    fun cdn1(url : String, w : Int, h : Int) : String{
        return url.plus("@${w}w_${h}h_1e_1c_1x_1o")
    }

    fun getEncrypy(value: String): String? {
        var testRSAEnWith64: String? = null
        try {
            testRSAEnWith64 = encryptWithBase64(value)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return testRSAEnWith64
    }

    @Throws(Exception::class)
    private fun encryptWithBase64(string: String): String {
        val publicKey = loadPublicKey(Tools.RSA_KEY)
        val binaryData = encrypt(publicKey, string.toByteArray())
        // return new BASE64Encoder().encodeBuffer(binaryData);
        return String(Base64.encode(binaryData, Base64.DEFAULT))
    }

    private fun encrypt(publicKey: RSAPublicKey?, plainTextData: ByteArray): ByteArray {
        if (publicKey == null) {
            return byteArrayOf()
        }
        var cipher: Cipher? = null
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher!!.init(Cipher.ENCRYPT_MODE, publicKey)
            val output = cipher.doFinal(plainTextData)
            return output
        } catch (e: Exception) {
            return byteArrayOf()
        }

    }

    private fun loadPublicKey(publicKeyStr: String): RSAPublicKey? {
        try {
            // BASE64Decoder base64Decoder = new BASE64Decoder();
            // byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
            val buffer = Base64.decode(publicKeyStr, Base64.DEFAULT)
            val keyFactory = KeyFactory.getInstance("RSA")
            val keySpec = X509EncodedKeySpec(buffer)
            val publicKey = keyFactory.generatePublic(keySpec) as RSAPublicKey
            return publicKey
        } catch (e: Exception) {
        }
        return null
    }


}