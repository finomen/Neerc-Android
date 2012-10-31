package ru.kt15.tsar.hallchat;

import java.util.ArrayList;

import ru.kt15.finomen.neerc.hall.UserInfo;
import ru.kt15.finomen.neerc.hall.UserStatus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserListAdapter extends ArrayAdapter<UserInfo> {
	private ArrayList<UserInfo> users;

	public UserListAdapter(Context context, int textViewResourceId, ArrayList<UserInfo> users) {
		super(context, textViewResourceId, users);
		this.users = users;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.user_layout, null);
		}
		UserInfo u = users.get(position);
		if (u != null) {
			ImageView statusIcon = (ImageView)v.findViewById(R.id.imgStatusIcon);
			
			//TODO: make status icons for other statuses
			if (u.status == UserStatus.OFFLINE)
				statusIcon.setImageResource(R.drawable.status_icon_offline);
			else
				statusIcon.setImageResource(R.drawable.status_icon_online);
			
			TextView textUserName = (TextView)v.findViewById(R.id.textUserName);
			TextView textLastMessage = (TextView)v.findViewById(R.id.textLastMessage);
			textUserName.setText(u.getName());
			textLastMessage.setText("<empty>");
			
			LinearLayout ll = (LinearLayout)v.findViewById(R.id.llUserNameAndMsg);
			
			statusIcon.setMinimumHeight(ll.getHeight());
		}
		return v;
	}
	
	public synchronized void update(UserInfo info) {
		//TODO: implement more effective update using map, если не влом
		for (int i = 0; i < users.size(); ++i) {
			if (users.get(i).getId().equals(info.getId())) {
				users.set(i, info);
				break;
			}
		}
	}
	
	public synchronized void remove(String userId) {
		//TODO: implement more effective remove
		for (int i = 0; i < users.size(); ++i) {
			if (users.get(i).getId().equals(userId)) {
				users.remove(i);
				break;
			}
		}
	}
}
