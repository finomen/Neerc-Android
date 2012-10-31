package ru.kt15.tsar.hallchat;

import java.util.ArrayList;

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
import android.widget.TabHost.OnTabChangeListener;

public class ChatActivity extends Activity implements ChatListener{
	private TabHost tabHost;
	private TabHost.TabSpec chatTab;
	private TabHost.TabSpec dialogsTab;
	private ListView mainChatList;
	private MessageListAdapter mainChatAdapter;
	private ListView userList;
	private UserListAdapter userListAdapter;
	private EditText newMsgText;
	private int unreadMsg_chatTab;
	//private int unreadMsg_dialogsTab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectionManager.Start();
        ConnectionManager.getChatManager().addListener(this);
        setContentView(R.layout.activity_chat);
        
        unreadMsg_chatTab = 0;
        
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        
        chatTab = tabHost.newTabSpec("chatTab");
        chatTab.setIndicator(getString(R.string.chatTabTitle));
        chatTab.setContent(R.id.chatTab);
        tabHost.addTab(chatTab);
        
        dialogsTab = tabHost.newTabSpec("dialogsTab");
        dialogsTab.setIndicator(getString(R.string.dialogsTabTitle));
        dialogsTab.setContent(R.id.dialogsTab);
        tabHost.addTab(dialogsTab);
        
        tabHost.setCurrentTabByTag("chatTab");
        
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				if (tabHost.getCurrentTabTag().equals("chatTab")) {
					unreadMsg_chatTab = 0;
					chatTab.setIndicator(getString(R.string.chatTabTitle));
				}
			}
		});
        
        mainChatAdapter = new MessageListAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<Message>());
        
        mainChatList = (ListView)findViewById(R.id.listChatMessages);
        mainChatList.setAdapter(mainChatAdapter);
        
        newMsgText = (EditText)findViewById(R.id.newMsgText);
        
        mainChatList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Message msg = (Message)mainChatList.getItemAtPosition(position);
				newMsgText.setText(newMsgText.getText() + msg.fromName + ", ");
				newMsgText.setSelection(newMsgText.getText().length() - 1);  //FIXME: remove "- 1", very hard :)
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
        
        userListAdapter = new UserListAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<UserInfo>());
        
        userList = (ListView)findViewById(R.id.listDialogs);
        userList.setAdapter(userListAdapter);
    }

	public void addUser(UserInfo info) {
		userListAdapter.add(info);
		//TODO: any logging?
	}

	public void updateUser(UserInfo info) {
		userListAdapter.update(info);
		//TODO: any logging?
	}

	public void removeUser(String id) {
		userListAdapter.remove(id);
		//TODO: any logging?
	}

	public void newMessage(Message message) {
		//TODO: analyze if this is a private message or message for main chat
		
		mainChatAdapter.add(message);
		if (!tabHost.getCurrentTabTag().equals("chatTab")) {
			++unreadMsg_chatTab;
			chatTab.setIndicator(getString(R.string.chatTabTitle) + " (" + Integer.toString(unreadMsg_chatTab) + ")");
		}
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
