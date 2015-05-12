package com.gzh.logisticstimeline;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.gzh.library.LogisticsTimeline;


public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    LogisticsTimeline timeline = (LogisticsTimeline) findViewById(R.id.logistic_timeline);

    CharSequence[] logisticsDesc = {"arrived one city","arrived new city","mall shipped"};
    CharSequence[] date ={"2015-4-12","2015-4-11","2015-3-1"};
    timeline.setDescArray(logisticsDesc);
    timeline.setDateArray(date);
  }

}
