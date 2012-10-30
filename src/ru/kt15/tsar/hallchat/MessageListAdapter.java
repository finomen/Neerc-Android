package ru.kt15.tsar.hallchat;

import java.util.ArrayList;

import ru.kt15.finomen.neerc.hall.Message;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MessageListAdapter extends ArrayAdapter<Message> {
	private ArrayList<Message> msgs;

	public MessageListAdapter(Context context, int textViewResourceId, ArrayList<Message> msgs) {
		super(context, textViewResourceId, msgs);
		this.msgs = msgs;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.message_layout, null);
		}
		Message msg = msgs.get(position);
		if (msg != null) {
			TextView msgInfo = (TextView)v.findViewById(R.id.textMsgInfo);
			TextView msgText = (TextView)v.findViewById(R.id.textMsgText);
			if (msgInfo != null) {
				msgInfo.setText(msg.fromName + ", " + DateFormat.format("dd.MM.yy kk:mm:ss", msg.time));
			}
			if (msgText != null) {
				msgText.setText(msg.text);
			}
		}
		return v;
	}
}
