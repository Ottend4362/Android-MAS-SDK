/*
 * Copyright (c) 2016 CA. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 *
 */
package com.ca.mas.foundation;

import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.util.Base64;

import com.ca.mas.MASCallbackFuture;
import com.ca.mas.MASMockGatewayTestBase;

import junit.framework.Assert;

import org.junit.Test;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class MASEnrollmentStartTest extends MASMockGatewayTestBase {

    @Test
    public void testEnrollment() throws Exception {

        SSLSocketFactory factory = createSSLSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket(getHost(), getPort());
        socket.startHandshake();
        Certificate[] certs = socket.getSession().getPeerCertificates();
        Certificate cert = certs[0];
        PublicKey key = cert.getPublicKey();

        MASCallbackFuture<Void> callback = new MASCallbackFuture<>();

        Uri uri = new Uri.Builder()
                .scheme("https")
                .encodedAuthority(getHost() + ":" + getPort())
                .appendPath("connect")
                .appendPath("device")
                .appendPath("config")
                .appendQueryParameter("subjectKeyHash", toHash(key))
                .build();

        MAS.setConnectionListener(null);

        MAS.start(InstrumentationRegistry.getTargetContext(), new URL(uri.toString()), callback);
        assertNull(callback.get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnrollmentWithoutKeyHash() throws Throwable {

        MASCallbackFuture<Void> callback = new MASCallbackFuture<>();

        Uri uri = new Uri.Builder()
                .scheme("https")
                .encodedAuthority(getHost() + ":" + getPort())
                .appendPath("connect")
                .appendPath("device")
                .appendPath("config")
                .build();

        MAS.start(InstrumentationRegistry.getTargetContext(), new URL(uri.toString()), callback);

        try {
            callback.get();
            fail();
        } catch (Exception e) {
            throw ((MASException) e.getCause()).getRootCause();
        }
    }

    @Test
    public void startWithNullUrl() throws Exception {
        MASCallbackFuture<Void> callback = new MASCallbackFuture<>();
        MAS.start(InstrumentationRegistry.getTargetContext(), null, callback);
        callback.get();
        Assert.assertEquals(MASConfiguration.getCurrentConfiguration().getGatewayHostName(), "localhost");
    }

    @Test
    public void testEnrollmentWithInvalidHashKey() throws Exception {
        MASCallbackFuture<Void> callback = new MASCallbackFuture<>();

        Uri uri = new Uri.Builder()
                .scheme("https")
                .encodedAuthority(getHost() + ":" + getPort())
                .appendPath("connect")
                .appendPath("device")
                .appendPath("config")
                .appendQueryParameter("subjectKeyHash", "PX7agsaKVqzj3ee1C2sxE15z5KSJOEaEZCxdRKWlMD0=")
                .build();

        MAS.setConnectionListener(null);

        MAS.start(InstrumentationRegistry.getTargetContext(), new URL(uri.toString()), callback);
        try {
            callback.get();
            fail();
        } catch (Exception e) {
            assertTrue(e.getCause().getCause() instanceof SSLHandshakeException);
            assertTrue(((MASException) e.getCause()).getRootCause() instanceof SSLHandshakeException);
        }
    }

    @Test
    public void testExpiredEnrollmentUrl() throws Exception {
        MASCallbackFuture<Void> callback = new MASCallbackFuture<>();
        SSLSocketFactory factory = createSSLSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket(getHost(), getPort());
        socket.startHandshake();
        Certificate[] certs = socket.getSession().getPeerCertificates();
        Certificate cert = certs[0];
        PublicKey key = cert.getPublicKey();

        Uri uri = new Uri.Builder()
                .scheme("https")
                .encodedAuthority(getHost() + ":" + getPort())
                .appendPath("connect")
                .appendPath("device")
                .appendPath("expiredConfig")
                .appendQueryParameter("subjectKeyHash", toHash(key))
                .build();

        MAS.start(InstrumentationRegistry.getTargetContext(), new URL(uri.toString()), callback);
        try {
            callback.get();
            fail();
        } catch (Exception e) {
            assertTrue(e.getCause().getCause() instanceof MASServerException);
            assertTrue(((MASException) e.getCause()).getRootCause() instanceof MASServerException);
        }
    }

    private static String toHash(PublicKey publicKey) throws NoSuchAlgorithmException {
        byte[] encoded = publicKey.getEncoded();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(encoded);
        byte[] digest = md.digest();
        return Base64.encodeToString(digest, Base64.NO_WRAP | Base64.URL_SAFE).trim();
    }

    private SSLSocketFactory createSSLSocketFactory() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManager[] trustManagers = {new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        sslContext.init(new KeyManager[0], trustManagers, new SecureRandom());
        return sslContext.getSocketFactory();

    }

}
