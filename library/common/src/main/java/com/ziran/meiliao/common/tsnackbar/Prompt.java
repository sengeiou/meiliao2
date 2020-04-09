package com.ziran.meiliao.common.tsnackbar;


import com.ziran.meiliao.common.R;

/**
 * Created by baiiu on 15/12/7.
 * 提示警告类
 */
public enum Prompt {
    /**
     * 红色,错误s
     */
    ERROR(R.drawable.common_bounced_icon_error, R.color.prompt_error),

    /**
     * 红色,警告
     */
    WARNING(R.drawable.common_bounced_icon_warning, R.color.textColor_teshe2),

    /**
     * 绿色,成功
     */
    SUCCESS(R.drawable.common_bounced_icon_successful, R.color.prompt_success);

    private int resIcon;
    private int backgroundColor;

    Prompt(int resIcon, int backgroundColor) {
        this.resIcon = resIcon;
        this.backgroundColor = backgroundColor;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
