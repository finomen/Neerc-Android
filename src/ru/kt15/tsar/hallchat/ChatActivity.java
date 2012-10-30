package ru.kt15.tsar.hallchat;

import java.util.ArrayList;
import java.util.Date;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        ArrayList<Message> testMsgs = new ArrayList<Message>();
        Message testMsg1 = new Message();
        testMsg1.fromName = "vasya pupkin";
        testMsg1.text = "Hello everybody!";
        testMsg1.time = new Date();
        testMsg1.time.setTime(testMsg1.time.getTime() - 3600000);
        testMsgs.add(testMsg1);
        Message testMsg2 = new Message();
        testMsg2.fromName = "петя иванов";
        testMsg2.text = "Ваще русское сообщение!";
        testMsg2.time = new Date();
        testMsg2.time.setTime(testMsg2.time.getTime() - 1800000);
        testMsgs.add(testMsg2);
        Message testMsg3 = new Message();
        testMsg3.fromName = "сегаль";
        testMsg3.text = "Курсач всем выдал я!";
        testMsg3.time = new Date();
        testMsg3.time.setTime(testMsg3.time.getTime());
        testMsgs.add(testMsg3);
        //testMsgs.addAll(testMsgs);
        //testMsgs.addAll(testMsgs);  // 12 test messages after this
        
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
	}

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_chat, menu);
        return true;
    }
    */
}
