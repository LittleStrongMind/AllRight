package jojo.allright.CustomFont;

/**
 * Created by Jojo on 08/05/2015.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


public class CustomFontButton extends Button {

    public CustomFontButton(Context context) {
        super( context );
        setFont();
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super( context, attrs );
        setFont();
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyle) {
        super( context, attrs, defStyle );
        setFont();
    }

    private void setFont() {
        Typeface normal = Typeface.createFromAsset(getContext().getAssets(),"fonts/BalsamiqSansRegular.ttf");
        setTypeface( normal, Typeface.NORMAL );

        Typeface bold = Typeface.createFromAsset( getContext().getAssets(), "fonts/BalsamiqSansBold.ttf" );
        setTypeface( normal, Typeface.BOLD );
    }




}