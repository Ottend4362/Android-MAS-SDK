/*
 * Copyright (c) 2016 CA. All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 *
 */

package com.ca.mas.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ca.mas.foundation.MASAuthorizationResponse;
import com.ca.mas.foundation.MASCallback;
import com.ca.mas.foundation.MASUser;

public class MASAppAuthRedirectHandlerActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        Uri redirectUri = getIntent().getData();
        if (redirectUri != null) {
            MASAuthorizationResponse response = MASAuthorizationResponse.fromUri(redirectUri);
            MASUser.login(response, getLoginCallback());
        } else {
            Toast.makeText(this, "No redirect URI detected.", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    private MASCallback<MASUser> getLoginCallback() {
        return new MASCallback<MASUser>() {
            @Override
            public void onSuccess(MASUser result) {
                finish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
        };
    }

}
