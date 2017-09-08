package be.heroesofmightandmagic.www.monsterandcie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by adphi on 08/09/17.
 */

public class Utils {
    /**
     * Utility Method to return a String from Resource System from the resource String name
     * @param ressourceName
     * @param context
     * @return
     */
    public static String getResourceStringByString(String ressourceName, Context context) {
        Resources resources = context.getResources();

        // Get the resource ID
        int resourceId = resources.getIdentifier(ressourceName, "string", context.getPackageName());
        // get and return the String
        return resources.getString(resourceId);
    }

    /**
     * Utility Method to return a Drawable from Resource System from the resource String name
     * @param resourceName
     * @param context
     * @return
     */
    public static Drawable getResourceDrawableByString(String resourceName, Context context) {
        Resources resources = context.getResources();
        // Get the resource ID
        int resourceId = resources.getIdentifier(resourceName, "drawable", context.getPackageName());
        // Get and return the Drawable
        return resources.getDrawable(resourceId, context.getTheme());
    }
}
