package dz.lab.widget;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import dz.lab.widget.actionbar.R;

/**
 * 
 * @author dzlab
 * Original code written by Johan Nilsson <http://markupartist.com>
 */
public class ActionBarOptions {
	
	protected Context context;
	protected LayoutInflater inflater;
	protected View layout;
	protected ListView options;
	protected ActionAdapter adapter;
	
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	public ActionBarOptions(Context context) {
		this.context = context;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	    layout = inflater.inflate(R.layout.actionbar_options, null);
	    options = (ListView) layout.findViewById(R.id.options);
	    adapter = new ActionAdapter();
	    options.setAdapter(adapter);
	}
	
	public void show(View anchor) {		
	    final PopupWindow popup = new PopupWindow(layout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 	    
	    popup.setOutsideTouchable(true);
	    popup.setTouchInterceptor(new OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				System.out.print("PopupWindow- Touch event: "+event.getAction());
				popup.dismiss();
				return false;
			}
		});	    	
	    //popupWindow.showAsDropDown(anchor, 50, -30);
	    //popup.showAtLocation(anchor, Gravity.NO_GRAVITY, x, y);
	    popup.showAsDropDown(anchor);
	}
	
    public void addQuickAction(Action action) {
        if (action != null) {
            actions.add(action);  
            adapter.notifyDataSetChanged();
        }
    }
    public void clearAllQuickActions() {
        if (!actions.isEmpty()) {
            actions.clear();
            adapter.notifyDataSetChanged();
        }
    }
    
    private class ActionAdapter extends BaseAdapter {
        private class ViewHolder {
        	ImageView img;
        	TextView txt;
        }
		@Override
		public int getCount() {			
			return actions.size();
		}
		@Override
		public Object getItem(int position) {
			return actions.get(position);
		}
		@Override
		public long getItemId(int position) {			
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Action action = (Action) getItem(position);
			ViewHolder holder;
			if(convertView == null) {
				holder = new ViewHolder();				 
				convertView = inflater.inflate(R.layout.actionbar_options_item, null);
				holder.img = (ImageView) convertView.findViewById(R.id.actionbar_image);
				holder.txt = (TextView) convertView.findViewById(R.id.actionbar_text);
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.img.setImageResource(action.getDrawable());
			holder.txt.setText(action.getTitle());
			return convertView;
		}    	
    }
}
