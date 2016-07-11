package <your_package>;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

/**
 * Created by diogojayme on 5/21/16.
 */
public class MentionUtil {

    static final String WHITE_SPACE = " ";
    static final String MENTION_CHARACTER = "@";
    static final int FLAG_EMPTY = 0;
    static final int INITIAL_POSITION = 0;
    static final String A_MENTION_REGEX = "\\B@\\w+$";
    /***
     * Create a TextView with clickable and highlight mentions texts
     *
     * @param content the text with mentions
     * @param target the textView you wan't to add the clicks
     * @param listener onClick for the mentions
     *
     *                 output
     *                 "an @username mentioned @otheruser"
     */
    public static void createMentions(final Activity activity, final String content,final TextView target, final OnMentionClickListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {

                long initialTime = System.currentTimeMillis();

                String[] words = content.split(WHITE_SPACE);
                final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

                for (String word : words) {
                    SpannableString spannableString = new SpannableString(word.concat(WHITE_SPACE).replace(MENTION_CHARACTER, WHITE_SPACE));

                    if(word.matches(A_MENTION_REGEX)){
                        //Each view needs to create a clickable listener every time, it can causes waste of memory
                        spannableString.setSpan(new CustomClickableSpan(listener, word), INITIAL_POSITION, word.length(), Spanned.SPAN_COMPOSING);
                        spannableString.setSpan(new StyleSpan(Typeface.BOLD), INITIAL_POSITION, word.length(), FLAG_EMPTY);
                    }

                    spannableStringBuilder.append(spannableString);
                }

                (activity).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        target.setMovementMethod(LinkMovementMethod.getInstance());
                        target.setText(spannableStringBuilder);
                    }
                });

                long finalTime = System.currentTimeMillis() - initialTime;
                System.out.println(finalTime);
            }
        }).start();
    }

    public static class CustomClickableSpan extends ClickableSpan {

        private String mention;
        private OnMentionClickListener listener;

        public CustomClickableSpan(OnMentionClickListener listener, String mention){
            this.listener = listener;
            this.mention = mention.replace(MENTION_CHARACTER, WHITE_SPACE);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
            ds.setColor(Color.BLACK);
        }

        @Override
        public void onClick(View view) {
            listener.onMentionClick(mention);
        }

    }

    public interface OnMentionClickListener{
        void onMentionClick(String textClicked);
    }
}
