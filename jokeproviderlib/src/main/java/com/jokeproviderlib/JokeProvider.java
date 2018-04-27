package com.jokeproviderlib;

import java.util.Random;

public class JokeProvider {

    private static final String jokes[][] = new String[][] {
            {"What goes up and down but does not move?", "The stairs."},
            {"Where should a 500 pound alien go?", "On a diet."},
            {"What did one toilet say to the other?", "You look a little flushed."},
            {"Why did the picture go to jail?", "Because it was framed."},
            {"What did one wall say to the other wall?", "I'll meet you at the corner."},
            {"What did the paper say to the pencil?", "Write on!"},
            {"What do you call a boy named Lee that no one talks to?", "Lonely."},
            {"What gets wetter the more it dries?", "A towel."},

            {"Why do bicycles fall over?", "Because they are two-tired!"},
            {"Why do dragons sleep during the day?", "So they can fight knights!"},
            {"What did Cinderella say when her photos did not show up?", "Someday my prints will come!"},
            {"Why was the broom late?", "It over swept!"},
            {"What part of the car is the laziest?", "The wheels, because they are always tired!"},
            {"What did the stamp say to the envelope?", "Stick with me and we will go places!"},

            {"Why couldn't the pirate play cards?", "Because he was sitting on the deck!"},
            {"What's the difference between a TV and a newspaper?", "Ever tried swatting a fly with a TV?"},
            {"What did one elevator say to the other elevator?", "I think I'm coming down with something!"},
            {"Why was everyone so tired on April 1st?", "They had just finished a March of 31 days."},

            {"Which hand is it better to write with?", "Neither, it's best to write with a pen!"},
            {"What makes the calendar seem so popular?", "Because it has a lot of dates!"},
            {"Why did Mickey Mouse take a trip into space?", "He wanted to find Pluto!"},
            {"What is it that even the most careful person overlooks?", "Her nose!"},

            {"Why do you go to bed every night?", "Because the bed won't come to you!"},
            {"Why did Billy go out with a prune?", "Because he couldn't find a date!"},
            {"Why do eskimos do their laundry in Tide?", "Because it's too cold out-tide!"},
            {"How do you cure a headache?", "Put your head through a window and the pane will just disappear!"},
            {"What has four wheels and flies?", "A garbage truck!"},
            {"What kind of car does Mickey Mouse's wife drive?", "A minnie van!"},
            {"Why don't traffic lights ever go swimming?", "Because they take too long to change!"},

            {"Why did the man run around his bed?  ", "To catch up on his sleep!"},
            {"Why did the robber take a bath before he stole from the bank?", "He wanted to make a clean get away!"}
        };

    private String question;
    private String answer;

    private String joke;

    public String getJoke() {

        int jokeIndex = new Random().nextInt(jokes.length);

        question = jokes[jokeIndex][0];
        answer = jokes[jokeIndex][1];

        joke = "Q: " + question + "\n" + "-----------------" + "\n" + "A: " + answer;

        return joke;
    }

}
