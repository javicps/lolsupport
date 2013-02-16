package org.ninjalamp.lolsupport;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public abstract class GalleryAdapter extends BaseAdapter {

	private Context context;
	private int mGalleryItemBackground;

	public GalleryAdapter(Context c) {
		context = c;
		TypedArray attr = c.obtainStyledAttributes(R.styleable.SupportGallery);
		mGalleryItemBackground = attr.getResourceId(R.styleable.SupportGallery_android_galleryItemBackground, 0);
	}

	protected Integer[] SUPPORT_ELEMENTS = {R.drawable.question, R.drawable.leona, R.drawable.janna, R.drawable.lulu , R.drawable.lux, R.drawable.nunu, R.drawable.sona, R.drawable.soraka, R.drawable.taric, R.drawable.zyra, R.drawable.alistar, R.drawable.blitzcrank, R.drawable.fiddlesticks};
	protected Integer[] ADC_ELEMENTS = {R.drawable.question, R.drawable.ezreal, R.drawable.corki, R.drawable.graves, R.drawable.vayne, R.drawable.kogmaw, R.drawable.caitlyn, R.drawable.missfortune, R.drawable.draven, R.drawable.sivir, R.drawable.varus, R.drawable.tristana, R.drawable.ashe, R.drawable.urgot };

	protected abstract Integer[] getElements();
	
	@Override
	public int getCount() {
		return getElements().length;
	}

	@Override
	public Object getItem(int position) {
		return getElements()[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(context);
		imageView.setImageResource(getElements()[position]);
		int width = (int) context.getResources().getDimension(R.dimen.champion_width);
		int height = (int) context.getResources().getDimension(R.dimen.champion_height);
		imageView.setLayoutParams(new Gallery.LayoutParams(width, height));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setBackgroundResource(mGalleryItemBackground);
		return imageView;
	}

}
