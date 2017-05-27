package com.zhangwy.sample.ui;

import android.os.Bundle;
import android.view.View;

import com.zhangwy.download.DownloadApp;
import com.zhangwy.download.Downloader;
import com.zhangwy.sample.R;
import com.zhangwy.sample.widget.SinkView;

/**
 * Author: zhangwy(张维亚)
 * Email:  zhangweiya@yixia.com
 * 创建时间:2017/5/27 上午11:35
 * 修改时间:2017/5/27 上午11:35
 * Description:
 */

public class DownloadActivity extends BaseActivity implements View.OnClickListener, Downloader.DownloadListener {
    private DownloadApp downloadApp;
    String url = "https://wsqncdn.miaopai.com/ad-yixia/apks/aa2e4499a583333eb6729827b3a9761d.apk";
    private SinkView sinkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        this.findViewById(R.id.download_start).setOnClickListener(this);
        this.findViewById(R.id.download_stop).setOnClickListener(this);
        this.findViewById(R.id.download_restart).setOnClickListener(this);
        this.sinkView = (SinkView) this.findViewById(R.id.download_sink);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_start:
                String title = "美团外卖";
                String iconUrl = "";
                String md5 = "26f09bdfb6ccffc50d4580a28528d25d";
                long size = 12875668;
                if (downloadApp == null)
                    downloadApp = DownloadApp.newInstance(this).setListener(this);
                downloadApp.download(url, title, iconUrl, md5, size);
                break;
            case R.id.download_stop:
                if (downloadApp != null) {
                    showMsg("走了，拜拜");
                    downloadApp.stop(url);
                }
                break;
            case R.id.download_restart:
                if (downloadApp != null) {
                    showMsg("我又来了");
                    downloadApp.reStart(url);
                }
                break;
        }
    }

    @Override
    public void onAddTask(String taskId, String taskUrl, boolean status) {
        showMsg(status ? "添加任务成功" : "添加任务失败");
    }

    @Override
    public void onStart(String taskId, String taskUrl) {
        sinkView.refreshDrawableState();
    }

    @Override
    public void onProgressChanged(String taskId, String taskUrl, float progress) {
        sinkView.setPercent(progress);
    }

    @Override
    public void onStop(String taskId, String taskUrl) {
        showMsg("爱我别走……");
    }

    @Override
    public void onSuccess(String taskId, String taskUrl, String localPath) {

    }

    @Override
    public void onFailed(String taskId, String taskUrl, int errCode, String errMsg) {

    }
}
