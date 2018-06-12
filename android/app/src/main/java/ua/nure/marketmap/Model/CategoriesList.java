package ua.nure.marketmap.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ua.nure.marketmap.R;

public class CategoriesList {
    private static List<Category> mCategories;
    public static void init(Context context) {
        mCategories = new ArrayList<>();
        mCategories.add(new Category(0, Color.getColorFromRgbHex(0x9C27B0), context.getResources().getString(R.string.cat_clothes), R.drawable.ic_cat_clothes));
        mCategories.add(new Category(1, Color.getColorFromRgbHex(0xFFF176), context.getResources().getString(R.string.cat_child_goods), R.drawable.ic_cat_child_goods));
        mCategories.add(new Category(2, Color.getColorFromRgbHex(0xA1887F), context.getResources().getString(R.string.cat_stationery), R.drawable.ic_cat_stationery));
        mCategories.add(new Category(3, Color.getColorFromRgbHex(0xb71c1c), context.getResources().getString(R.string.cat_household), R.drawable.ic_cat_household));
        mCategories.add(new Category(4, Color.getColorFromRgbHex(0xA5D6A7), context.getResources().getString(R.string.cat_petshops), R.drawable.ic_cat_petshops));
        mCategories.add(new Category(5, Color.getColorFromRgbHex(0x388E3C), context.getResources().getString(R.string.cat_plants), R.drawable.ic_cat_plants));
        mCategories.add(new Category(6, Color.getColorFromRgbHex(0x039BE5), context.getResources().getString(R.string.cat_sanitary), R.drawable.ic_cat_sanitary));
        mCategories.add(new Category(7, Color.getColorFromRgbHex(0x3F51B5), context.getResources().getString(R.string.cat_caffee), R.drawable.ic_cat_caffee));
        mCategories.add(new Category(8, Color.getColorFromRgbHex(0xd50000), context.getResources().getString(R.string.cat_medicine), R.drawable.ic_cat_medicine));
        mCategories.add(new Category(9, Color.getColorFromRgbHex(0x795548), context.getResources().getString(R.string.cat_tobacco), R.drawable.ic_cat_tobacco));
        mCategories.add(new Category(10, Color.getColorFromRgbHex(0xE0E0E0), context.getResources().getString(R.string.cat_dairy), R.drawable.ic_cat_dairy));
        mCategories.add(new Category(11, Color.getColorFromRgbHex(0xef5350), context.getResources().getString(R.string.cat_meat_fish), R.drawable.ic_cat_meat_fish));
        mCategories.add(new Category(12, Color.getColorFromRgbHex(0xCDDC39), context.getResources().getString(R.string.cat_vegetables_and_fruits), R.drawable.ic_cat_vegetables_fruits));
        mCategories.add(new Category(13, Color.getColorFromRgbHex(0xFF6E40), context.getResources().getString(R.string.cat_bakery), R.drawable.ic_cat_bakery));
        mCategories.add(new Category(14, Color.defaultColor(), context.getResources().getString(R.string.cat_other), R.drawable.ic_cat_other));
    }
    public static int getCategoryCount() { return 15; }
    public static Category getCategory(int id) {
        for(Category category : mCategories)
            if(id == category.getId())
                return category;
        return mCategories.get(14);
    }
}
