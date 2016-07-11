# Android Mentions
This class provides an easily way to create mentions in a `TextView` with boldered and clickable events.

##How it works?

###Input

Hello @diogojayme how are you? Im contacting you because I have to presentate you @guilhermedourado my new friend, he is from Brazil and want's to go out today, are you ready?

###Output

Hello **diogojayme** how are you? Im contacting you because I have to presentate you **guilhermedourado** my new friend, he is from Brazil and want's to go out today, are you ready?

##Easy usage

    MentionUtil.createMentions(this, "message with people mentioned starting with the character @", textView, new MentionUtil.OnMentionClickListener() {
      @Override
      public void onMentionClick(String textClicked) {
          //You have been clicked in a mentioned name
      }
    });

##Next steps

* Add the hability to create custom spans
