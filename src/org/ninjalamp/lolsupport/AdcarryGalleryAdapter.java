package org.ninjalamp.lolsupport;

import android.content.Context;

public class AdcarryGalleryAdapter extends GalleryAdapter {

	public AdcarryGalleryAdapter(Context c) {
		super(c);
	}

	@Override
	protected Integer[] getElements() {
		return ADC_ELEMENTS;
	}

}
