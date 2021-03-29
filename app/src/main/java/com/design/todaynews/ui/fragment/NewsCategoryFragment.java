package com.design.todaynews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.design.todaynews.R;
import com.design.todaynews.common.Constant;
import com.design.todaynews.model.NewsCategory;
import com.design.todaynews.ui.activity.NewsCategoryRankActivity;
import com.design.todaynews.ui.adapter.NewsCategoryListAdapter;
import com.design.todaynews.util.network.SingletonRetrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NewsCategoryFragment extends Fragment {

    private static final String TAG = "CategoryFragment";
    private Context mContext;


    private RecyclerView recyclerView;
    private NewsCategoryListAdapter adapter;
    private List<NewsCategory.ResultBean.ShowapiResBodyBean.ListBean> mDataList = new ArrayList<>();
    private final List<String> newsCategoryBackgroundImage = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "生命周期点位：  NewsCategoryFragment onCreate");
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "生命周期点位：  NewsCategoryFragment onCreateView");
        View root = inflater.inflate(R.layout.fragment_news_category, container, false);
        mContext = root.getContext();

        initData();


        recyclerView = root.findViewById(R.id.news_category_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));


        requestData();

        //requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.colorNavigationA)); //设置系统状态栏颜色
        return root;
    }

    private void requestData() {
        Observer<NewsCategory> observer = new Observer<NewsCategory>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull NewsCategory data) {
                if (data.getResult().getShowapi_res_code() == 0 && data.getResult().getShowapi_res_body().getRet_code() == 0) {
                    mDataList = data.getResult().getShowapi_res_body().getList();

                    adapter = new NewsCategoryListAdapter(mDataList, newsCategoryBackgroundImage);
                    recyclerView.setAdapter(adapter);


                    adapter.setOnItemClickListener((view, position) -> {
                        Intent intent = new Intent(mContext, NewsCategoryRankActivity.class);
                        intent.putExtra(Constant.NewsCategoryTitle, mDataList.get(position).getName());

                        ArrayList<String> dataList1 = new ArrayList<>();
                        ArrayList<String> dataList2 = new ArrayList<>();
                        List<NewsCategory.ResultBean.ShowapiResBodyBean.ListBean.ChildListBean> resultList;
                        resultList = mDataList.get(position).getChildList();
                        for (int i = 0; i < resultList.size(); i++) {
                            dataList1.add(resultList.get(i).getId());
                            dataList2.add(resultList.get(i).getName());
                        }
                        intent.putStringArrayListExtra(Constant.NewsCategoryIDList, dataList1);
                        intent.putStringArrayListExtra(Constant.NewsCategoryNameList, dataList2);
                        startActivity(intent);
                    });

                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: " + e);
                Toast.makeText(mContext, "onError: " + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().fetchNewsCategory(observer, Constant.JDAPI_KEY);


    }

    private void initData() {
        newsCategoryBackgroundImage.add("https://img1.doubanio.com/view/photo/l/public/p2564906178.webp");
        newsCategoryBackgroundImage.add("https://img2.doubanio.com/view/photo/l/public/p2269343423.webp");
        newsCategoryBackgroundImage.add("https://weiliicimg6.pstatp.com/weili/l/243130711343759365.webp");
        newsCategoryBackgroundImage.add("https://img1.doubanio.com/view/photo/l/public/p604983657.webp");
        newsCategoryBackgroundImage.add("https://img2.doubanio.com/view/photo/l/public/p2356947303.webp");
        newsCategoryBackgroundImage.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=179292083,506023000&fm=26&gp=0.jpg");
        newsCategoryBackgroundImage.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=207926224,2168957337&fm=26&gp=0.jpg");
        newsCategoryBackgroundImage.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=653463355,1390891814&fm=26&gp=0.jpg");
        newsCategoryBackgroundImage.add("https://icweiliimg9.pstatp.com/weili/l/903121499439628398.webp");
        newsCategoryBackgroundImage.add("https://weiliicimg6.pstatp.com/weili/l/919016254283317365.webp");
        newsCategoryBackgroundImage.add("https://icweiliimg9.pstatp.com/weili/l/400605386644914228.webp");

    }


}