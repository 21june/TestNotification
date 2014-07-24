package arabiannight.tistory.com.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TestNotificationActivity extends Activity implements OnClickListener{
	
	private NotificationManager nm = null;
	Notification notification;
	Notification notification2;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setLayout();
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_commit:
			

			
			nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			
			// PendingIntent를 등록 하고, noti를 클릭시에 어떤 클래스를 호출 할 것인지 등록. 
			PendingIntent intent = PendingIntent.getActivity(
					TestNotificationActivity.this, 0, 
					new Intent(TestNotificationActivity.this, NotificationConfirm.class), 0);
			
			String ticker = et_Tiker.getText().toString();
			String title = et_Title.getText().toString();
			String text = et_Message.getText().toString();
										
			// status bar 에 등록될 메시지(Tiker, 아이콘, 그리고 noti가 실행될 시간)
			notification =
				new Notification(android.R.drawable.btn_star,
						ticker, System.currentTimeMillis());
			notification2 =
					new Notification(android.R.drawable.btn_star,
							ticker, System.currentTimeMillis());			
			// List에 표시될 항목
			notification.setLatestEventInfo(TestNotificationActivity.this, 
					title, text, intent);
			
			notification2.setLatestEventInfo(TestNotificationActivity.this, 
					title, text, intent);
			
			
			// noti를 클릭 했을 경우 자동으로 noti Icon 제거
//			notification.flags = notification.flags | notification.FLAG_AUTO_CANCEL;
			
			// 1234 notification 의 고유아이디

			Toast.makeText(TestNotificationActivity.this, "Notification Registered.", 
					Toast.LENGTH_SHORT).show();
			// SLEEP 5 SECONDS HERE ...
			Handler handler = new Handler(); 
			handler.postDelayed(new Runnable() { 
			     public void run() { 
			          // createNotification(SmsMessage.createFromPdu((byte[])smsExtra[0]), context);
						nm.notify(1234, notification);
						nm.notify(12345, notification2);
			     } 
			}, 5000); 
			break;

		default:
			break;
		}
	}
	
	/*
	 * Layout
	 */
	private EditText et_Tiker = null;
	private EditText et_Title = null;
	private EditText et_Message = null;
	private Button btn_Commit = null;
	
	private void setLayout() {
		btn_Commit = (Button) findViewById(R.id.btn_commit);
		btn_Commit.setOnClickListener(this);
		et_Tiker = (EditText) findViewById(R.id.et_tiker);
		et_Title = (EditText) findViewById(R.id.et_title);
		et_Message = (EditText) findViewById(R.id.et_message);
		
	}
}




