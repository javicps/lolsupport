package org.ninjalamp.lolsupport;

import android.content.Context;

public class SupportGalleryAdapter extends GalleryAdapter {

	public SupportGalleryAdapter(Context c) {
		super(c);
	}

	@Override
	protected Integer[] getElements() {
		return SUPPORT_ELEMENTS;
	}

}
