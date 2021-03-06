package vn.edu.fpt.fts.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import vn.edu.fpt.fts.activity.GoodsDetailActivity;
import vn.edu.fpt.fts.adapter.GoodsModelAdapter;
import vn.edu.fpt.fts.classes.GoodsModel;
import vn.edu.fpt.fts.common.Common;

public class GoodsFragment extends Fragment {
	private class WebServiceTask extends AsyncTask<String, Integer, String> {

		// connection timeout, in milliseconds (waiting to connect)
		private static final int CONN_TIMEOUT = 3000;
		public static final int GET_TASK = 2;

		public static final int POST_TASK = 1;

		// socket timeout, in milliseconds (waiting for data)
		private static final int SOCKET_TIMEOUT = 10000;

		private static final String TAG = "WebServiceTask";

		private Context mContext = null;
		private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		private ProgressDialog pDlg = null;

		private String processMessage = "Processing...";

		private int taskType = GET_TASK;

		public WebServiceTask(int taskType, Context mContext,
				String processMessage) {

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
			if (response.equals("null")) {
				tvGone = (TextView) getActivity().findViewById(
						R.id.tvGone);
				tvGone.setVisibility(View.VISIBLE);
			} else {
				try {
					JSONObject jsonObject = new JSONObject(response);
					List<String> lv = new ArrayList<String>();
					ArrayList<GoodsModel> goodsModels = new ArrayList<GoodsModel>();
					Object obj = jsonObject.get("goods");
					if (obj instanceof JSONArray) {
						JSONArray array = jsonObject.getJSONArray("goods");
						// String[] result = new String[array.length()];

						// int counter = 0;
						for (int i = 0; i < array.length(); i++) {							
							JSONObject jsonObject2 = array.getJSONObject(i);
							int active = Integer.parseInt(jsonObject2
									.getString("active"));
							String deliveryDate = jsonObject2
									.getString("deliveryTime");
							String pickupDate = jsonObject2
									.getString("pickupTime");
							String temp[] = deliveryDate.split(" ");
							String temp1[] = pickupDate.split(" ");

							if (active == 1 && !Common.expireDate(temp[0])) {
								String a = jsonObject2.getString("goodsID");

								goodsID.add(jsonObject2.getString("goodsID"));
								goodsCategoryID.add(jsonObject2
										.getString("goodsCategoryID"));

								String createTime = jsonObject2
										.getString("createTime");
								String tmp[] = createTime.split(" ");
								createTime = tmp[0].replace("-", "")
										+ jsonObject2.getString("goodsID");

								JSONObject jsonObject3 = jsonObject2
										.getJSONObject("goodsCategory");
								String object = jsonObject3.getString("name")
										+ " " + "#" + createTime;
								lv.add(object);

								String name = jsonObject3.getString("name");
								String price = jsonObject2.getString("price")
										.replace(".0", "") + " nghìn";
								String weight = jsonObject2.getString("weight")
										+ " kg";
								String date = Common
										.formatDateFromString(temp1[0])
										+ " - "
										+ Common.formatDateFromString(temp[0]);
								GoodsModel goodsModel = new GoodsModel(name,
										weight, date, price);
								goodsModels.add(goodsModel);

							}

						}
					} else if (obj instanceof JSONObject) {
						JSONObject jsonObject2 = jsonObject
								.getJSONObject("goods");
						int active = Integer.parseInt(jsonObject2
								.getString("active"));
						String deliveryDate = jsonObject2
								.getString("deliveryTime");
						String pickupDate = jsonObject2.getString("pickupTime");
						String temp[] = deliveryDate.split(" ");
						String temp1[] = pickupDate.split(" ");

						if (active == 1 && !Common.expireDate(temp[0])) {
							String a = jsonObject2.getString("goodsID");

							goodsID.add(jsonObject2.getString("goodsID"));
							goodsCategoryID.add(jsonObject2
									.getString("goodsCategoryID"));

							String createTime = jsonObject2
									.getString("createTime");
							String tmp[] = createTime.split(" ");
							createTime = tmp[0].replace("-", "")
									+ jsonObject2.getString("goodsID");

							JSONObject jsonObject3 = jsonObject2
									.getJSONObject("goodsCategory");
							String object = jsonObject3.getString("name") + " "
									+ "#" + createTime;
							lv.add(object);
							String name = jsonObject3.getString("name");
							String price = jsonObject2.getString("price")
									.replace(".0", "") + " nghìn";
							String weight = jsonObject2.getString("weight")
									+ " kg";
							String date = Common
									.formatDateFromString(temp1[0])
									+ " - "
									+ Common.formatDateFromString(temp[0]);
							GoodsModel goodsModel = new GoodsModel(name,
									weight, date, price);
							goodsModels.add(goodsModel);
						}
					}
					if (goodsModels.size() == 0) {
						tvGone = (TextView) getActivity().findViewById(
								R.id.tvGone);
						tvGone.setVisibility(View.VISIBLE);
					}

					adapter = new GoodsModelAdapter(getActivity(), goodsModels);
					listView.setAdapter(adapter);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Log.e(TAG, e.getLocalizedMessage());
				}
			}

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
	// private ArrayAdapter<String> adapter;
	private GoodsModelAdapter adapter;
	private List<String> goodsID, goodsCategoryID;
	private ListView listView;
	private String ownerid;

	private TextView tvGone;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_goods, container,
				false);

		WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK,
				getActivity(), "Đang xử lý...");
		String url = Common.IP_URL + Common.Service_Goods_Get;
		SharedPreferences preferences = getActivity().getSharedPreferences(
				"MyPrefs", Context.MODE_PRIVATE);
		ownerid = preferences.getString("ownerID", "");
		wst.addNameValuePair("ownerID", ownerid);
		// wst.execute(new String[] { url });
		wst.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
				new String[] { url });

		goodsID = new ArrayList<String>();
		goodsCategoryID = new ArrayList<String>();

		listView = (ListView) rootView.findViewById(R.id.listview_goods);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int pos = listView.getPositionForView(view);

				Intent intent = new Intent(view.getContext(),
						GoodsDetailActivity.class);
				String tmp = goodsCategoryID.get(pos);
				String tmp2 = goodsID.get(pos);

				intent.putExtra("goodsID", goodsID.get(pos));
				intent.putExtra("goodsCategoryID", goodsCategoryID.get(pos));

				startActivity(intent);
			}
		});

		return rootView;
	}
}
