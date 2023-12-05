package com.soa.secondservice.config

import java.io.FileInputStream
import java.security.KeyStore
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

object SslSettings {
    private fun getKeyStore(): KeyStore {
        val keyStoreFile = FileInputStream("/home/osmi/itmo/soa/ssl/server.keystore")
        val keyStorePassword = "testtest".toCharArray()
        val keyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(keyStoreFile, keyStorePassword)
        return keyStore
    }

    private fun getTrustManagerFactory(): TrustManagerFactory? {
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(getKeyStore())
        return trustManagerFactory
    }

    fun getSslContext(): SSLContext? {
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, getTrustManagerFactory()?.trustManagers, null)
        return sslContext
    }
}