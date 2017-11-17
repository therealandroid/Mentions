## Mentions for Android

This class provides an easily way to create `Clickable` span area based on a Regex Pattern for basic `TextView`. The class use `SpannableString` to build it up. 

### How it works?

Go to the class and replace the two lines that match the charactere you want by any charactere of your choice, 
in this case `@`.

`static final String MENTION_CHARACTER = "@";`

`static final String A_MENTION_REGEX = "\\B@\\w+$";`

#### Simple Usage

```java
   MentionUtil.createMentions(this, "message with people mentioned starting with the character @", textView, new MentionUtil.OnMentionClickListener() {
      @Override
      public void onMentionClick(String textClicked) {
          //You have been clicked in a mentioned name
      }
    });
```

#### Example text input

Hello @diogojayme how are you? Im contacting you because I have to presentate you @guilhermedourado my new friend, he is from Brazil and want's to go out today, are you ready?

#### Exemple text Output

Hello **diogojayme** how are you? Im contacting you because I have to presentate you **guilhermedourado** my new friend, he is from Brazil and want's to go out today, are you ready?

#### Next steps

Customize for accept custom Span styles
