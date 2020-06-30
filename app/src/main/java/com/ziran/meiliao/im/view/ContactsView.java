package com.ziran.meiliao.im.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.adapter.StickyListAdapter;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.im.controller.ContactsController;
import com.ziran.meiliao.im.utils.SharePreferenceManager;
import com.ziran.meiliao.im.utils.sidebar.SideBar;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


/**
 * Created by ${chenyn} on 2017/3/13.
 */

public class ContactsView extends LinearLayout {
    private StickyListHeadersListView mListView;
    private SideBar mSideBar;
    private TextView mDialogTextView;

    private ImageButton mIb_goToAddFriend;
    private TextView mLetterHintTv;
    private LinearLayout mVerify_ll;
    private LinearLayout mGroup_ll;
    private TextView mGroup_verification_num;
    private TextView mNewFriendNum;
    private LayoutInflater mInflater;
    private LinearLayout mSearch_title;
    private Context mContext;
    private ImageView mLoadingIv;
    private LinearLayout mLoadingTv;
    private View mView_line;
    private ImageButton return_btn;

    public ContactsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mInflater = LayoutInflater.from(context);

    }


    public void initModule(float ratio, float density) {
        mIb_goToAddFriend = (ImageButton) findViewById(R.id.ib_goToAddFriend);

        mListView = (StickyListHeadersListView) findViewById(R.id.listview);
        mSideBar = (SideBar) findViewById(R.id.sidebar);
        mSideBar.setTextView(mDialogTextView);

        return_btn  = (ImageButton) findViewById(R.id.return_btn);
        mLetterHintTv = (TextView) findViewById(R.id.group_dialog);
        mSideBar.setTextView(mLetterHintTv);
        mSideBar.bringToFront();


        View header = mInflater.inflate(R.layout.contact_list_header, null);
        mVerify_ll = (LinearLayout) header.findViewById(R.id.verify_ll);
        mGroup_ll = (LinearLayout) header.findViewById(R.id.group_ll);
        mGroup_verification_num = (TextView) header.findViewById(R.id.group_verification_num);
        mNewFriendNum = (TextView) header.findViewById(R.id.friend_verification_num);
        mSearch_title = (LinearLayout) header.findViewById(R.id.search_title);
        mView_line = header.findViewById(R.id.view_line);
        mGroup_verification_num.setVisibility(INVISIBLE);

        RelativeLayout loadingHeader = (RelativeLayout) mInflater.inflate(R.layout.jmui_drop_down_list_header, null);
        mLoadingIv = (ImageView) loadingHeader.findViewById(R.id.jmui_loading_img);
        mLoadingTv = (LinearLayout) loadingHeader.findViewById(R.id.loading_view);
        mListView.addHeaderView(loadingHeader);
        mListView.addHeaderView(header, null, false);
        mListView.setDrawingListUnderStickyHeader(true);
        mListView.setAreHeadersSticky(true);
        mListView.setStickyHeaderTopOffset(0);
        if (SharePreferenceManager.getCachedNewFriendNum() > 0) {
            showNewFriends(SharePreferenceManager.getCachedNewFriendNum());
        } else {
            mNewFriendNum.setVisibility(INVISIBLE);
        }
    }


    public void setListener(ContactsController contactsController) {
        mIb_goToAddFriend.setOnClickListener(contactsController);
        mVerify_ll.setOnClickListener(contactsController);
        mGroup_ll.setOnClickListener(contactsController);
        mSearch_title.setOnClickListener(contactsController);
        return_btn.setOnClickListener(contactsController);
    }

    public void setSelection(int position) {
        mListView.setSelection(position);
    }

    public void setSideBarTouchListener(SideBar.OnTouchingLetterChangedListener listener) {
        mSideBar.setOnTouchingLetterChangedListener(listener);
    }

    public void setAdapter(StickyListAdapter adapter) {
        mListView.setAdapter(adapter);
    }

    public void showNewFriends(int num) {
        mNewFriendNum.setVisibility(VISIBLE);
        if (num > 99) {
            mNewFriendNum.setText("99+");
        } else {
            mNewFriendNum.setText(String.valueOf(num));
        }
    }

    public void dismissNewFriends() {
        SharePreferenceManager.setCachedNewFriendNum(0);
        JGApplication.forAddIntoGroup.clear();
        JGApplication.forAddFriend.clear();
        mNewFriendNum.setVisibility(INVISIBLE);
    }

    public void showLoadingHeader() {
        mLoadingIv.setVisibility(View.VISIBLE);
        mLoadingTv.setVisibility(View.VISIBLE);
        AnimationDrawable drawable = (AnimationDrawable) mLoadingIv.getDrawable();
        drawable.start();
    }

    public void dismissLoadingHeader() {
        mLoadingIv.setVisibility(View.GONE);
        mLoadingTv.setVisibility(View.GONE);

    }

    /**
     * desc:当没有好友时候在群组下面画一条线.以区分和好友列表
     * 当有好友时候,字母索充当分割线
     *
     * 如果不这样设置的话,当有好友时候还会显示view线,这样ui界面不太好看
     */

    public void showLine() {
        mView_line.setVisibility(View.VISIBLE);
    }



    public void showContact() {
        mSideBar.setVisibility(VISIBLE);
        mListView.setVisibility(VISIBLE);
    }
}
