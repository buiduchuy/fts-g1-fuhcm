package vn.edu.fpt.fts.layout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import vn.edu.fpt.fts.classes.Constant;
import vn.edu.fpt.fts.drawer.ListItem;
import vn.edu.fpt.fts.drawer.ListItemAdapter3;

public class History extends Fragment {

	private class WebService extends AsyncTask<String, Integer, String> {

		// connection timeout, in milliseconds (waiting to connect)
		private static final int CONN_TIMEOUT = 30000;
		public static final int GET_TASK = 2;

		public static final int POST_TASK = 1;

		// socket timeout, in milliseconds (waiting for data)
		private static final int SOCKET_TIMEOUT = 30000;

		private static final String TAG = "WebServiceTask";

		private Context mContext = null;
		private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		private ProgressDialog pDlg = null;

		private String processMessage = "Processing...";

		private int taskType = GET_TASK;

		public WebService(int taskType, Context mContext, String processMessage) {

			this.taskType = taskType;
			this.mContext = mContext;
			this.processMessage = processMessage;
		}

		public void addNameValuePair(String name, String value) {

			params.add(new BasicNameValuePair(name, value));
		}

		@Override
		protected String doInBackground(String... urls) {
			String url = urls[0];
			String result = "";

			HttpResponse response = doResponse(url);

			if (response == null) {
				return result;
			} else {
				try {
					result = inputStreamToString(response.getEntity()
							.getContent());

				} catch (IllegalStateException e) {
					Log.e(TAG, e.getLocalizedMessage(), e);

				} catch (IOException e) {
					Log.e(TAG, e.getLocalizedMessage(), e);
				}

			}

			return result;
		}

		private HttpResponse doResponse(String url) {

			// Use our connection and data timeouts as parameters for our
			// DefaultHttpClient
			HttpClient httpclient = new DefaultHttpClient(getHttpParams());

			HttpResponse response = null;

			try {
				switch (taskType) {

				case POST_TASK:
					HttpPost httppost = new HttpPost(url);
					// Add parameters
					httppost.setEntity(new UrlEncodedFormEntity(params,
							HTTP.UTF_8));

					response = httpclient.execute(httppost);
					break;
				case GET_TASK:
					HttpGet httpget = new HttpGet(url);
					response = httpclient.execute(httpget);
					break;
				}
			} catch (ConnectTimeoutException e) {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getActivity(),
								"Không thể kết nối tới máy chủ",
								Toast.LENGTH_SHORT).show();
					}
				});
			} catch (Exception e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
			}

			return response;
		}

		// Establish connection and socket (data retrieval) timeouts
		private HttpParams getHttpParams() {

			HttpParams htpp = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(htpp, CONN_TIMEOUT);
			HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);

			return htpp;
		}

		private String inputStreamToString(InputStream is) {

			String line = "";
			StringBuilder total = new StringBuilder();

			// Wrap a BufferedReader around the InputStream
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			try {
				// Read response until the end
				while ((line = rd.readLine()) != null) {
					total.append(line);
				}
			} catch (IOException e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
			}

			// Return full string
			return total.toString();
		}

		@Override
		protected void onPostExecute(String response) {
			// Xu li du lieu tra ve sau khi insert thanh cong
			// handleResponse(response);
			JSONObject obj;
			filter = new ArrayList<String>();
			filter.add("Tất cả");
			if (!response.equals("null")) {
				try {
					obj = new JSONObject(response);
					Object invervent = obj.get("order");
					if (invervent instanceof JSONArray) {
						JSONArray array = obj.getJSONArray("order");
						int count = 1;
						for (int i = 0; i < array.length(); i++) {
							JSONObject item = array.getJSONObject(i);
							JSONObject rt = item.getJSONObject("deal")
									.getJSONObject("route");

							String title = "";
							String[] start = rt.getString("startingAddress")
									.replaceAll("(?i), Vietnam", "")
									.replaceAll("(?i), Viet Nam", "")
									.replaceAll("(?i), Việt Nam", "")
									.split(",");
							title = start[start.length - 1].trim();

							if (obj.has("routeMarkers")) {
								Object intervent = obj.get("routeMarkers");
								if (intervent instanceof JSONArray) {
									JSONArray catArray = obj
											.getJSONArray("routeMarkers");
									for (int j = 0; j < catArray.length(); j++) {
										JSONObject cat = catArray
												.getJSONObject(j);
										if (!cat.getString(
												"routeMarkerLocation").equals(
												"")) {
											title += " - "
													+ cat.getString("routeMarkerLocation");
										}
									}
								} else if (intervent instanceof JSONObject) {
									JSONObject cat = obj
											.getJSONObject("routeMarkers");
									title += " - "
											+ cat.getString("routeMarkerLocation");
								}
							}

							String[] end = rt.getString("destinationAddress")
									.replaceAll("(?i), Vietnam", "")
									.replaceAll("(?i), Viet Nam", "")
									.replaceAll("(?i), Việt Nam", "")
									.split(",");
							title += " - " + end[end.length - 1].trim();
							String status = "Trạng thái: ";
							String driverStatus = item
									.getString("orderStatusID");
							String price = item.getString("price");
							if (driverStatus.equals("1")) {
								status = "Chưa nhận tiền";
							} else if (driverStatus.equals("2")) {
								status = "Đã chuyển tiền cho hệ thống";
							} else if (driverStatus.equals("3")) {
								status += "Đang vận chuyển";
							} else if (driverStatus.equals("4")) {
								status += "Đã chuyển hàng";
							} else if (driverStatus.equals("5")) {
								status += "Đã bị hủy";
							} else if (driverStatus.equals("6")) {
								status += "Mất/hỏng hàng";
							} else if (driverStatus.equals("7")) {
								status += "Hoàn thành";
							}
							SimpleDateFormat format = new SimpleDateFormat(
									"yyyy-MM-dd hh:mm:ss");
							Date createDate = format.parse(item
									.getString("createTime"));
							Calendar cal = Calendar.getInstance();
							cal.setTime(createDate);
							cal.add(Calendar.MONTH, 3);
							Date timeout = cal.getTime();
							format.applyPattern("dd/MM/yyyy");
							String createD = format.format(createDate);
							if (!filter.contains(createD)) {
								filter.add(createD);
							}
							list.add(new ListItem("Hóa đơn cho lộ trình: ",
									title, status, createD));
							count++;
							map.add(item.getString("orderID"));
							mapFilter.add(item.getString("orderID"));
						}
					} else if (invervent instanceof JSONObject) {
						JSONObject item = obj.getJSONObject("order");
						JSONObject rt = item.getJSONObject("deal")
								.getJSONObject("route");

						String title = "";
						String[] start = rt.getString("startingAddress")
								.replaceAll("(?i), Vietnam", "")
								.replaceAll("(?i), Viet Nam", "")
								.replaceAll("(?i), Việt Nam", "").split(",");
						title = start[start.length - 1].trim();

						if (obj.has("routeMarkers")) {
							Object intervent = obj.get("routeMarkers");
							if (intervent instanceof JSONArray) {
								JSONArray catArray = obj
										.getJSONArray("routeMarkers");
								for (int j = 0; j < catArray.length(); j++) {
									JSONObject cat = catArray.getJSONObject(j);
									if (!cat.getString("routeMarkerLocation")
											.equals("")) {
										title += " - "
												+ cat.getString("routeMarkerLocation");
									}
								}
							} else if (intervent instanceof JSONObject) {
								JSONObject cat = obj
										.getJSONObject("routeMarkers");
								title += " - "
										+ cat.getString("routeMarkerLocation");
							}
						}

						String[] end = rt.getString("destinationAddress")
								.replaceAll("(?i), Vietnam", "")
								.replaceAll("(?i), Viet Nam", "")
								.replaceAll("(?i), Việt Nam", "").split(",");
						title += " - " + end[end.length - 1].trim();
						String status = "Trạng thái: ";
						String driverStatus = item.getString("orderStatusID");
						String price = item.getString("price");
						if (driverStatus.equals("1")) {
							status = "Chưa nhận tiền";
						} else if (driverStatus.equals("2")) {
							status = "Đã chuyển tiền cho hệ thống";
						} else if (driverStatus.equals("3")) {
							status += "Đang vận chuyển";
						} else if (driverStatus.equals("4")) {
							status += "Đã chuyển hàng";
						} else if (driverStatus.equals("5")) {
							status += "Đã bị hủy";
						} else if (driverStatus.equals("6")) {
							status += "Mất/hỏng hàng";
						} else if (driverStatus.equals("7")) {
							status += "Hoàn thành";
						}
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						Date createDate = format.parse(item
								.getString("createTime"));
						Calendar cal = Calendar.getInstance();
						cal.setTime(createDate);
						cal.add(Calendar.MONTH, 3);
						Date timeout = cal.getTime();
						format.applyPattern("dd/MM/yyyy");
						String createD = format.format(createDate);
						if (!filter.contains(createD)) {
							filter.add(createD);
						}
						list.add(new ListItem("Hóa đơn cho lộ trình: ", title,
								status, createD));
						map.add(item.getString("orderID"));
						mapFilter.add(item.getString("orderID"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			adapter = new ListItemAdapter3(getActivity(), list);
			list1.setEmptyView(myFragmentView.findViewById(R.id.emptyElement));
			list1.setAdapter(adapter);
			@SuppressWarnings("unchecked")
			SpinnerAdapter mSpinnerAdapter = new ArrayAdapter(getActivity(),
					android.R.layout.simple_spinner_dropdown_item, filter);

			OnNavigationListener mOnNavigationListener = new OnNavigationListener() {
				@Override
				public boolean onNavigationItemSelected(int position, long itemId) {
					String selected = filter.get(position);
					if (selected.equals("Tất cả")) {
						adapter = new ListItemAdapter3(getActivity(), list);
						list1.setEmptyView(myFragmentView
								.findViewById(R.id.emptyElement));
						list1.setAdapter(adapter);
						mapFilter = map;
					} else {
						listFilter = new ArrayList<ListItem>();
						mapFilter = new ArrayList<String>();
						for (ListItem item : list) {
							if (item.getDate().equals(selected)) {
								listFilter.add(item);
								mapFilter.add(map.get(list.indexOf(item)));
							}
						}
						adapter = new ListItemAdapter3(getActivity(), listFilter);
						list1.setEmptyView(myFragmentView
								.findViewById(R.id.emptyElement));
						list1.setAdapter(adapter);
					}
					return true;
				}
			};
			ActionBar actionBar = getActivity().getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
			actionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);

			pDlg.dismiss();
		}

		@Override
		protected void onPreExecute() {
			showProgressDialog();

		}

		private void showProgressDialog() {

			pDlg = new ProgressDialog(mContext);
			pDlg.setMessage(processMessage);
			pDlg.setProgressDrawable(mContext.getWallpaper());
			pDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pDlg.setCancelable(false);
			pDlg.show();

		}
	}
	private static final String SERVICE_URL = Constant.SERVICE_URL
			+ "Order/getOrderByDriverID";
	ListItemAdapter3 adapter;
	ArrayList<String> filter;
	ArrayList<ListItem> list;
	ListView list1;
	ArrayList<ListItem> listFilter;
	ArrayList<String> map;
	ArrayList<String> mapFilter;
	View myFragmentView;

	Spinner spinner;

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		menu.findItem(R.id.action_create).setVisible(false);
	}
	
	@Override
	@SuppressLint("UseSparseArrays")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ActionBar actionBar = getActivity().getActionBar();
		getActivity().getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE
				| ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP);
		actionBar.setTitle("Hóa đơn");
		actionBar.setIcon(R.drawable.ic_action_copy_white);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		list = new ArrayList<ListItem>();
		map = new ArrayList<String>();
		mapFilter = new ArrayList<String>();
		getActivity().setTitle("Lịch sử giao dịch");
		WebService ws = new WebService(WebService.POST_TASK, getActivity(),
				"Đang lấy dữ liệu ...");
		ws.addNameValuePair("driverID", getActivity().getIntent()
				.getStringExtra("driverID"));
		ws.execute(new String[] { SERVICE_URL });
		myFragmentView = inflater.inflate(R.layout.activity_history, container,
				false);
		list1 = (ListView) myFragmentView.findViewById(R.id.listView3);
		list1.setOnItemClickListener((new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int id = Integer.parseInt(mapFilter.get((int) arg3));
				NotificationManager mNotificationManager = (NotificationManager) getActivity()
						.getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.cancel(id);
				FragmentManager mng = getActivity().getSupportFragmentManager();
				FragmentTransaction trs = mng.beginTransaction();
				HistoryDetail frag = new HistoryDetail();
				Bundle bundle = new Bundle();
				bundle.putString("orderID", String.valueOf(id));
				frag.setArguments(bundle);
				trs.replace(R.id.content_frame, frag);
				trs.addToBackStack(null);
				trs.commit();
			}
		}));
		setHasOptionsMenu(true);
		return myFragmentView;
	}
}
