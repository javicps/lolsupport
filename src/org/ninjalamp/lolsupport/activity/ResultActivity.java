package org.ninjalamp.lolsupport.activity;

import org.ninjalamp.lolsupport.R;
import org.ninjalamp.lolsupport.SupportHelperManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.screen_result);
		iniElements();
	}

	private void iniElements() {
		TextView result1 = (TextView) findViewById(R.id.result1);
		TextView result2 = (TextView) findViewById(R.id.result2);
		TextView result3 = (TextView) findViewById(R.id.result3);
		ImageView image1 = (ImageView) findViewById(R.id.imageView1);
		ImageView image2 = (ImageView) findViewById(R.id.imageView2);
		ImageView image3 = (ImageView) findViewById(R.id.imageView3);
		Button backButton = (Button) findViewById(R.id.back_button);
		backButton.setOnClickListener(backClickLister);
		Bundle extras = getIntent().getExtras();

		int[] bestSupports = SupportHelperManager.getBestSupport(extras.getInt("ALLY_ADC"), extras.getInt("ENEMY_ADC"), extras.getInt("ENEMY_SUPPORT"));

		result1.setText(SupportHelperManager.getSupportName(bestSupports[0]));
		result2.setText(SupportHelperManager.getSupportName(bestSupports[1]));
		result3.setText(SupportHelperManager.getSupportName(bestSupports[2]));

		String support1 = SupportHelperManager.getSupportName(bestSupports[0]).toLowerCase();
		String support2 = SupportHelperManager.getSupportName(bestSupports[1]).toLowerCase();
		String support3 = SupportHelperManager.getSupportName(bestSupports[2]).toLowerCase();

		int drawable1 = getResources().getIdentifier(support1, "drawable", "org.ninjalamp.lolsupport");
		int drawable2 = getResources().getIdentifier(support2, "drawable", "org.ninjalamp.lolsupport");
		int drawable3 = getResources().getIdentifier(support3, "drawable", "org.ninjalamp.lolsupport");

		image1.setBackgroundDrawable(getResources().getDrawable(drawable1));
		image2.setBackgroundDrawable(getResources().getDrawable(drawable2));
		image3.setBackgroundDrawable(getResources().getDrawable(drawable3));
	}

	OnClickListener backClickLister = new OnClickListener() {

		@Override
		public void onClick(View v) {
			finish();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.screen_main, menu);
		return true;
	}

}
