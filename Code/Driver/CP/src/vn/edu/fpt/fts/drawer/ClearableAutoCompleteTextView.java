package vn.edu.fpt.fts.drawer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import vn.edu.fpt.fts.layout.R;

public class ClearableAutoCompleteTextView extends AutoCompleteTextView {
	public interface OnClearListener {
		void onClear();
	}
 
	private OnClearListener defaultClearListener = new OnClearListener() {
 
		@Override
		public void onClear() {
			ClearableAutoCompleteTextView et = ClearableAutoCompleteTextView.this;
			et.setText("");
		}
	};
 
	public Drawable imgClearButton = getResources().getDrawable(
			R.drawable.ic_action_cancel);
 
	boolean justCleared = false;
 
	private OnClearListener onClearListener = defaultClearListener;
 
	public ClearableAutoCompleteTextView(Context context) {
		super(context);
		init();
	}
 
	/* Required methods, not used in this implementation */
	public ClearableAutoCompleteTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
 
	/* Required methods, not used in this implementation */
	public ClearableAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
 
	public void hideClearButton() {
		this.setCompoundDrawables(null, null, null, null);
	}
 
	void init() {
		// Set the bounds of the button
		this.setCompoundDrawablesWithIntrinsicBounds(null, null,
				imgClearButton, null);
 
		// if the clear button is pressed, fire up the handler. Otherwise do nothing
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
 
				ClearableAutoCompleteTextView et = ClearableAutoCompleteTextView.this;
 
				if (et.getCompoundDrawables()[2] == null)
					return false;
 
				if (event.getAction() != MotionEvent.ACTION_UP)
					return false;
 
				if (event.getX() > et.getWidth() - et.getPaddingRight()	- imgClearButton.getIntrinsicWidth()) {
					onClearListener.onClear();
					justCleared = true;
				}
				return false;
			}
		});
	}
 
	public void setImgClearButton(Drawable imgClearButton) {
		this.imgClearButton = imgClearButton;
	}
 
	public void setOnClearListener(final OnClearListener clearListener) {
		this.onClearListener = clearListener;
	}
 
	public void showClearButton() {
		this.setCompoundDrawablesWithIntrinsicBounds(null, null, imgClearButton, null);
	}
 
}
