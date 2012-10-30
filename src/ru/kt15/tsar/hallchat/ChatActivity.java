package ru.kt15.tsar.hallchat;

import java.util.ArrayList;
import java.util.Date;

import ru.kt15.finomen.neerc.core.Log;
import ru.kt15.finomen.neerc.hall.ChatListener;
import ru.kt15.finomen.neerc.hall.Message;
import ru.kt15.finomen.neerc.hall.UserInfo;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

public class ChatActivity extends Activity implements ChatListener{
	private ListView mainChatList;
	private MessageListAdapter mainChatAdapter;
	private EditText newMsgText;
	private ArrayList<Message> testMsgs = new ArrayList<Message>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectionManager.Start();
        ConnectionManager.getChatManager().addListener(this);
        setContentView(R.layout.activity_chat);
        
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("chatTab");
        tabSpec.setIndicator(getString(R.string.chatTabTitle));
        tabSpec.setContent(R.id.chatTab);
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("dialogsTab");
        tabSpec.setIndicator(getString(R.string.dialogsTabTitle));
        tabSpec.setContent(R.id.dialogsTab);
        tabHost.addTab(tabSpec);
        
        tabHost.setCurrentTabByTag("chatTab");
        
        // TODO: get rid of 'test' shit; init MessageListAdapter with some count of last messages in chat
                
        mainChatAdapter = new MessageListAdapter(this, android.R.layout.simple_list_item_1, testMsgs);
        
        mainChatList = (ListView)findViewById(R.id.listChatMessages);
        mainChatList.setAdapter(mainChatAdapter);
        
        newMsgText = (EditText)findViewById(R.id.newMsgText);
        
        mainChatList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Message msg = (Message)mainChatList.getItemAtPosition(position);
				newMsgText.setText(newMsgText.getText() + msg.fromName + ", ");
			}
		});
        
        Button sendMsgButton = (Button)findViewById(R.id.sendMsgButton);
        sendMsgButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Message newMsg = new Message();
				//FIXME: fill Message
				newMsg.text = newMsgText.getText().toString();
				newMsgText.setText("");
				ConnectionManager.getChatManager().sendMessage(newMsg);
			}
		});
    }

	public void addUser(UserInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(UserInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void removeUser(String id) {
		// TODO Auto-generated method stub
		
	}

	public void newMessage(Message message) {
		mainChatAdapter.add(message);
		Log.writeInfo(message.text);
	}

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_chat, menu);
        return true;
    }
    */
}
