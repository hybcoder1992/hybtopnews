package com.example.hybtopnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view=View.inflate(getApplicationContext(), R.layout.activity_splash, null);
		super.setContentView(view);
		AlphaAnimation animation=new AlphaAnimation(0.3f,1.0f);
		animation.setDuration(2000);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SplashActivity.this,MainActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
			}
		});
	}
}
