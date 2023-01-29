# Builder Mario Bot

I little bot I made with Java and [JDA](https://github.com/DV8FromTheWorld/JDA).

## Use

You can do some cool things in this bot like:

- Auto react
- Send custom messages

## Custom Events

<details>
<summary>Message Event</summary>

Create a ``message_event.json`` file in folder of the bot.

### Auto react

Make sure to replace ``channelidhere`` with your channel id.

```json
{
  "channelidhere": {
    "type": "reaction",
    "reactions": [
      ":arrow_up:",
      ":arrow_down"
    ]
  }
}
```

</details>

## Custom Messages

Create a Json with any name.

### Embeds

```json
{
  "type": "embed",
  "title": "Your title. Not needed tho",
  "description": "Your description. ot needed tho"
}
```

### Messages

```json
{
  "type": "msg",
  "msg": "Your message"
}
```

Then send it with ``m!send yourfilename.json``

## Compiling

You can also grab a jar from the releases.

You need to compile to a shadowJar.

Run:
```
gradlew shadowJar
```

## Running the bot

You can make .bat file or just use the commandline

Run:
```
java -jar BuilderMarioBot.jar
```

If you don't have token.txt file it will automatically create one. Just put your bot token in there.