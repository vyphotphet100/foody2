package hcmute.edu.vn.caodinhsyvy_19110143.foody2.utils;

import hcmute.edu.vn.caodinhsyvy_19110143.foody2.R;

public class PictureUtils {
    private int getRestaurantPic(String src) {
        if (src.equals("1"))
            return R.drawable.pic_restaurant1;
        else if (src.equals("2"))
            return R.drawable.pic_restaurant2;
        else if (src.equals("3"))
            return R.drawable.pic_restaurant3;
        else if (src.equals("4"))
            return R.drawable.pic_restaurant4;
        else if (src.equals("5"))
            return R.drawable.pic_restaurant5;
        else if (src.equals("6"))
            return R.drawable.pic_restaurant6;
        else if (src.equals("7"))
            return R.drawable.pic_restaurant7;
        else if (src.equals("8"))
            return R.drawable.pic_restaurant8;
        else if (src.equals("9"))
            return R.drawable.pic_restaurant9;
        else if (src.equals("10"))
            return R.drawable.pic_restaurant10;
        else if (src.equals("11"))
            return R.drawable.pic_restaurant11;
        else if (src.equals("12"))
            return R.drawable.pic_restaurant12;

        return R.drawable.img_nearest_res_1;
    }
}
