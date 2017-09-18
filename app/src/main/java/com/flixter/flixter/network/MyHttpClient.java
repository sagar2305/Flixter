package com.flixter.flixter.network;

import okhttp3.OkHttpClient;

/**
 * Created by SagarMutha on 9/17/17.
 */

public class MyHttpClient {

    private static OkHttpClient client = null;

    public static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient();
        }

        return client;
    }
}
