# LogisticsTimeLine
This is an android logistics timeline  widget which can display the progress of logistics info.

![image](https://cloud.githubusercontent.com/assets/7445664/7594333/73f0d86c-f912-11e4-8a79-91de7db97feb.png)



# Including in your project

The library is pushed to Maven Central as a AAR, so you just need to add the following dependency to your build.gradle.

dependencies {

    compile 'com.github.manuelpeinado.glassactionbar:glassactionbar:0.3.0'
    
}


# Usage

Add an element in your XML menu:

  <com.github.franket268.LogisticsTimeline
    android:id="@+id/logisticsTimeline"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
 
Then, set the logistics decription and logistcis date  in the your activty :

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    LogisticsTimeline timeline = (LogisticsTimeline) findViewById(R.id.logisticsTimeline);
    CharSequence[] logisticsDesc = {"arrived one city","arrived new city","mall shipped","order submit"};
    CharSequence[] date ={"2015-4-12","2015-4-11","2015-3-1","2015-2-1"};
    timeline.setDescArray(logisticsDesc);
    timeline.setDateArray(date);
  }
  
  
# License

The MIT License (MIT)

Copyright (c) 2015 GONGZhanhong

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
