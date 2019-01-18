package com.example.think.radiancepro.Student;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.think.radiancepro.ConnectionDetector;
import com.example.think.radiancepro.MainActivity;
import com.example.think.radiancepro.R;
import com.example.think.radiancepro.Utils;

public class ReportCard extends Fragment {

    String AcademiYear[] = {" Select-One", "2018-2019", "2019-2020"};
    String Term[] = {"All", "Term-I", "Term-II"};
    String item;
    String item1;
    ProgressDialog pd;
    ConnectionDetector reportcd;
    WebView web;
    Spinner selectacademi, selectterm;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.reportcard, container, false);
        web = (WebView) view.findViewById(R.id.webview);

        Toast toast = Toast.makeText(getActivity(), "Please Select Your Session !!!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        Button show = (Button) view.findViewById(R.id.btnShow);
        selectacademi = (Spinner) view.findViewById(R.id.academiyear);
        selectterm = (Spinner) view.findViewById(R.id.term);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear);
        pd = new ProgressDialog(getActivity());
        final ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, AcademiYear);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (aa.getCount() > 1) {
            selectacademi.setAdapter(aa);
        }

        selectacademi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                item = parent.getItemAtPosition(position).toString();
                if (item.equals("--Select--")) {
                    item = "0";
                } else if (item.equals("2018-2019")) {
                    item = "2018-2019";
                } else if (item.equals("2019-2020")) {
                    item = "2019-2020";
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aaa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, Term);
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (aaa.getCount() > 1)

        {
            selectterm.setAdapter(aaa);

        }

        selectterm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item1 = parent.getItemAtPosition(position).toString();
                if (item1.equals("All")) {
                    item1 = "All";
                } else if (item1.equals("Term-I")) {
                    item1 = "Term-I";
                } else if (item1.equals("Term-II")) {
                    item1 = "Term-II";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        pd.dismiss();


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                webView1();


            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void webView1() {

        String reportUrl1 = "http://navayuga.icampus360.in/NurseryTOVResult.aspx?";
        String reportUrl2 = "http://navayuga.icampus360.in/ReportCardVITOVIII.aspx?";
        String reportUrl3 = "http://navayuga.icampus360.in//ReportCardIXTOX.aspx?";
        String reportUrl4 = "http://navayuga.icampus360.in/ReportCardXI.aspx?";


        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.setWebViewClient(new WebViewClient());
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.setBackgroundColor(Color.WHITE);

        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        pd.show();
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getContext(), "Error:" + description, Toast.LENGTH_SHORT).show();
            }
        });

        if (Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("Nursery") ||
                Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("I") ||
                Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("II") ||
                Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("III") ||
                Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("IV") ||
                Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("V")) {

            String url1 = reportUrl1 + "StdntId=" + Utils.getSavedPreferences(getContext(), MainActivity.stuid, "") + "&AcdYr=" +
                    item + "&CollegeID=" + Utils.getSavedPreferences(getContext(), MainActivity.clgid, "") +
                    "&CenterID=" + Utils.getSavedPreferences(getContext(), MainActivity.cenid, "") + "&sptype=" + item1;

            web.loadUrl(url1);
            Log.d("k1", "k1" + url1);

        } else if (Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("VI") ||
                Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("VII") ||
                Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("VIII")) {

            String url2 = reportUrl2 + "StdntId=" + Utils.getSavedPreferences(getContext(), MainActivity.stuid, "") + "&AcdYr=" +
                    item + "&CollegeID=" + Utils.getSavedPreferences(getContext(), MainActivity.clgid, "") +
                    "&CenterID=" + Utils.getSavedPreferences(getContext(), MainActivity.cenid, "") + "&sptype=" + item1;

            web.loadUrl(url2);
            Log.d("k2", "k2" + url2);

        } else if (Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("IX") ||
                Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("X")) {

            String url3 = reportUrl3 + "StdntId=" + Utils.getSavedPreferences(getContext(), MainActivity.stuid, "") + "&AcdYr=" +
                    item + "&CollegeID=" + Utils.getSavedPreferences(getContext(), MainActivity.clgid, "") +
                    "&CenterID=" + Utils.getSavedPreferences(getContext(), MainActivity.cenid, "") + "&sptype=" + item1 + "&SC="
                    + Utils.getSavedPreferences(getContext(), MainActivity.classid, "");

            Log.d("k3", "k3" + url3);
            web.loadUrl(url3);
        } else if (Utils.getSavedPreferences(getContext(), MainActivity.classid, "").equals("XI"))
        {


            String url4 = reportUrl4 + "StdntId=" + Utils.getSavedPreferences(getContext(), MainActivity.stuid, "") + "&AcdYr=" +
                    item + "&CollegeID=" + Utils.getSavedPreferences(getContext(), MainActivity.clgid, "") +
                    "&CenterID=" + Utils.getSavedPreferences(getContext(), MainActivity.cenid, "") + "&sptype=" + item1;

            Log.d("k4", "k4" + url4);
            web.loadUrl(url4);

        }
    }

}

