package pl.zdunex25.tutorial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Scroller;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	final Context context = this;
	String[] tutuly;
	String[] opisy;

	 
	// Array of integers points to images stored in /res/drawable-ldpi/
	int[] flags = new int[]{
	        R.drawable.ic_updater_settings,
	        R.drawable.com_miui_cloudservice,
	        R.drawable.com_miui_notes,
	        R.drawable.ic_date_time_settings,
	        R.drawable.ic_settings_home,
	        R.drawable.ic_account_autostar,
	        R.drawable.com_miui_fmradio,
	        R.drawable.com_miui_securitycenter,
	        R.drawable.ic_key_settings,
	        R.drawable.com_android_camera,
	        R.drawable.com_android_thememanager,
	        R.drawable.com_miui_securitycenter,
	        R.drawable.com_android_browser
	};
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showMenu();

		tutuly = getResources().getStringArray(R.array.list_items);
		opisy = getResources().getStringArray(R.array.list_items_summary);
		
		// Each row in the list stores country name, opisy and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
 
        for(int i=0;i<13;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", tutuly[i]);
            hm.put("cur", opisy[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }
 
        // Keys used in Hashmap
        String[] from = { "flag","txt","cur" };
 
        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt,R.id.cur};
 
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);
 
        // Getting a reference to listview of main.xml layout file
        ListView listView = ( ListView ) findViewById(R.id.listview);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
					int i, long id) {
                // TODO Auto-generated method stub
                if(tutuly[i].equals("Aktualizacje")){
                	showDialog(getResources().getString(R.string.updates), getResources().getString(R.string.updates_summary));
                } else if(tutuly[i].equals("Aktywacja Wiadomości w chmurze lub dodanie lokalizacji w aplikacji Pogoda")){
                	showDialog(getResources().getString(R.string.cloudmsg), getResources().getString(R.string.cloudmsg_summary));
                } else if(tutuly[i].equals("Brak miniatur w ostatnich aplikacjach")){
                	showDialog(getResources().getString(R.string.recents), getResources().getString(R.string.recents_summary));
                } else if(tutuly[i].equals("Działanie aplikacji w tle")){
                	showDialog(getResources().getString(R.string.startup), getResources().getString(R.string.startup_summary));
                } else if(tutuly[i].equals("Edycja pulpitu")){
                	showDialog(getResources().getString(R.string.layout), getResources().getString(R.string.layout_summary));
                } else if(tutuly[i].equals("Niedziałające dymki i przejścia do aplikacji Messenger")){
                	showDialog(getResources().getString(R.string.popups), getResources().getString(R.string.popups_summary));
                } else if(tutuly[i].equals("Oczekiwanie na Wi-Fi w Sklepie Play i innych")){
                	showDialog(getResources().getString(R.string.downloads), getResources().getString(R.string.downloads_summary));
                } else if(tutuly[i].equals("Zarządzanie uprawnieniami aplikacji")){
                	showDialog(getResources().getString(R.string.permissions), getResources().getString(R.string.permissions_summary));
                } else if(tutuly[i].equals("Zmieniająca się klawiatura")){
                	showDialog(getResources().getString(R.string.missingkeys), getResources().getString(R.string.missingkeys_summary));
                } else if(tutuly[i].equals("Korzystanie z trybu slow motion przy nagrywaniu wideo")){
                	showDialog(getResources().getString(R.string.camera), getResources().getString(R.string.camera_summary));
                } else if(tutuly[i].equals("Mieszanie elementów z różnych motywów")){
                	showDialog(getResources().getString(R.string.themes), getResources().getString(R.string.themes_summary));
                } else if(tutuly[i].equals("Korzystanie z uprawnień root")){
                	showDialog(getResources().getString(R.string.root), getResources().getString(R.string.root_summary));
                } else if(tutuly[i].equals("Brak wczytywania linków sponsorowanych i reklam")){
                	showDialog(getResources().getString(R.string.adblock), getResources().getString(R.string.adblock_summary));
                }
          }
        });
	}

	private void showDialog(String title, String content) {
		final Dialog dialog = new Dialog(context,R.style.Theme_Holo_Light_Dialog);
    	//String chlog = getResources().getString(R.string.updates_summary);

        // Setting dialogview
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setTitle(R.string.updates);
        dialog.setContentView(R.layout.dialog_updates);
		TextView header = (TextView) dialog.findViewById(R.id.titlee);
		header.setText(String.format(getResources().getString(R.string.lets_try2), title));
		TextView description = (TextView) dialog.findViewById(R.id.text3);
		description.setText(Html.fromHtml(String.format(getResources().getString(R.string.lets_try), content)));
		description.setMaxLines(10);
		description.setScroller(new Scroller(context));
		description.setVerticalScrollBarEnabled(true);
		description.setMovementMethod(new ScrollingMovementMethod());
		description.setMovementMethod(new LinkMovementMethod());

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		dialogButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

        dialog.show();
    }

	private void showMenu() {
		final Button btnOpenPopup = (Button)findViewById(R.id.menu_btn);
		btnOpenPopup.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
				View popupView = layoutInflater.inflate(R.layout.popup, null);
				final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				popupWindow.setAnimationStyle(R.style.Animation_GetFromTop);
				//hide on outside click
				popupWindow.setBackgroundDrawable(new BitmapDrawable());
				popupWindow.setOutsideTouchable(true);
				popupWindow.setFocusable(true);
				popupWindow.showAsDropDown(btnOpenPopup, 50, -220);

				//dim hehind
				View container = (View) popupWindow.getContentView().getParent();
				WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
				WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
				p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
				p.dimAmount = 0.3f;
				wm.updateViewLayout(container, p);

				Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
				btnDismiss.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						popupWindow.dismiss();
					}
				});
				popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

				TextView install = (TextView) popupView.findViewById(R.id.about_app);
				install.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent aboutapp= new Intent(MainActivity.this, AboutActivity.class);
						startActivity(aboutapp);
					}
				});
			}
		});
	}
}
