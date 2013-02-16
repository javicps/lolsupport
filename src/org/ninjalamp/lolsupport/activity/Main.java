package org.ninjalamp.lolsupport.activity;

import org.ninjalamp.lolsupport.AdcarryGalleryAdapter;
import org.ninjalamp.lolsupport.R;
import org.ninjalamp.lolsupport.SupportGalleryAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;

public class Main extends Activity {

	private Gallery enemyAdcGallery;
	private Gallery enemySupportGallery;
	private Gallery allyAdcGallery;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.screen_main);
		iniElements();
	}

	private void iniElements() {
		enemyAdcGallery = (Gallery) findViewById(R.id.gallery1);
		enemySupportGallery = (Gallery) findViewById(R.id.gallery2);
		allyAdcGallery = (Gallery) findViewById(R.id.gallery3);
		enemyAdcGallery.setAdapter(new AdcarryGalleryAdapter(this));
		enemySupportGallery.setAdapter(new SupportGalleryAdapter(this));
		allyAdcGallery.setAdapter(new AdcarryGalleryAdapter(this));
		Button goButton = (Button) findViewById(R.id.go_button);
		goButton.setOnClickListener(goClickListener);
	}

	OnClickListener goClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (allOptionsAreNotRandom()) {
				Intent intent = new Intent(Main.this, ResultActivity.class);
				intent.putExtra("ENEMY_ADC", (int) enemyAdcGallery.getSelectedItemId());
				intent.putExtra("ENEMY_SUPPORT", (int) enemySupportGallery.getSelectedItemId());
				intent.putExtra("ALLY_ADC", (int) allyAdcGallery.getSelectedItemId());
				startActivity(intent);
			}
		}

		private boolean allOptionsAreNotRandom() {
			return enemyAdcGallery.getSelectedItemId() > 0 || enemySupportGallery.getSelectedItemId() > 0 || allyAdcGallery.getSelectedItemId() > 0;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.screen_main, menu);
		return true;
	}

}
