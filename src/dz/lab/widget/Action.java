package dz.lab.widget;

import dz.lab.widget.actionbar.R;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

/**
 * Definition of an action that could be performed, along with a icon to show.
 * @author dzlab
 */
public abstract class Action {
	
	final private int mDrawable;
    
    public Action(int drawable) {
        mDrawable = drawable;
    }
    
    public int getDrawable() {
        return mDrawable;
    }
    
	public abstract String getTitle();
	
    public abstract void performAction(View view);
    
    public static class IntentAction extends Action {
        private Context mContext;
        private Intent mIntent;
        private String mTitle;
        
        public IntentAction(Context context, Intent intent, int drawable) {
            super(drawable);
            mContext = context;
            mIntent = intent;
        }

        @Override
        public void performAction(View view) {
            try {
               mContext.startActivity(mIntent); 
            } catch (ActivityNotFoundException e) {
                Toast.makeText(mContext,
                        mContext.getText(R.string.actionbar_activity_not_found),
                        Toast.LENGTH_SHORT).show();
            }
        }        
        public void setTitle(String title) {
            this.mTitle = title;
        }
        @Override
        public String getTitle() {
            return mTitle;
        }
    }

    /*
    public static abstract class SearchAction extends AbstractAction {
        public SearchAction() {
            super(R.drawable.actionbar_search);
        }
    }
    */
}
