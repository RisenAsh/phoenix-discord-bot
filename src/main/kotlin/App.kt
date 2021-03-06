import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.Snowflake

val TEST_SERVER_ID = Snowflake( // Store this as a Discord snowflake, aka an ID
    env("TEST_SERVER") // An exception will be thrown if it can't be found
)

private val TOKEN = env("TOKEN")

suspend fun main() {
    val bot = ExtensibleBot(TOKEN) {}
    bot.start()
}
