package de.armgdon.discord;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.io.IOException;

public class App extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException {

        //Properties properties = new Properties();

        //properties.load(new FileInputStream("config.properties"));
        //String token = properties.getProperty("discord.token");

        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(System.getenv("discord_token"));
        builder.addEventListeners(new App());
        builder.build();


    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        } else {
            if (event.getMessage().getContentRaw().equalsIgnoreCase("!RickBot")) {
                QuotesGenerator quotesGenerator = new QuotesGenerator();
                String generatedQuote = QuotesGenerator.generateQuote();
                event.getChannel().sendMessage(generatedQuote).queue();
            }
        }
    }
}
